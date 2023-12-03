package edu.hw9;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Task1StatsCollector {

    private final int producerCount;

    private final int consumerCount;

    private final BlockingQueue<StatInfo> dataQueue;

    private final BlockingQueue<ResultInfo> processedDataQueue;

    public Task1StatsCollector(int producerCount, int consumerCount) {
        this.producerCount = producerCount;
        this.consumerCount =  consumerCount;
        this.dataQueue = new LinkedBlockingQueue<>(10);
        this.processedDataQueue = new LinkedBlockingQueue<>();
    }

    public List<ResultInfo> getProcessedData() {
        return processedDataQueue
                .stream()
                .toList();
    }

    private record RunnableTask(List<StatInfo> statInfoList, BlockingQueue<StatInfo> queue, int index)
            implements Runnable {

        @Override
        public void run() {
            var data = statInfoList.get(index);
            try {
                queue.put(data);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void start(List<StatInfo> statInfoList) {
        new Thread(() -> startProduce(statInfoList)).start();
        new Thread(this::startConsume).start();
    }

    private void startProduce(List<StatInfo> statInfoList) {
        try (var pool = Executors.newFixedThreadPool(producerCount)) {
            for (var i = 0; i < statInfoList.size(); i++) {
                pool.submit(new RunnableTask(statInfoList, dataQueue, i));
            }
        }
    }

    private Double makeStat(StatInfo statInfo) {
        return switch (statInfo.metricName()) {
            case MAX -> statInfo.data()
                    .stream()
                    .max(Comparator.naturalOrder())
                    .orElseThrow();
            case MIN -> statInfo.data()
                    .stream()
                    .min(Comparator.naturalOrder())
                    .orElseThrow();
            case SUM -> statInfo.data()
                    .stream()
                    .reduce(Double::sum)
                    .orElse(0D);
            case AVG -> statInfo.data()
                    .stream()
                    .mapToDouble(elem -> elem)
                    .average()
                    .orElseThrow();
        };
    }

    private void startConsume() {
        try (var pool = Executors.newFixedThreadPool(consumerCount)) {
            while (true) {
                pool.submit(() -> {
                    try {
                        var data = dataQueue.take();
                        var stat = makeStat(data);
                        processedDataQueue.put(new ResultInfo(data.metricName(), stat));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    public record ResultInfo(MetricName metricName, Double data) {}

    public record StatInfo(MetricName metricName, List<Double> data) {}

    public enum MetricName {
        MAX,
        MIN,
        AVG,
        SUM
    }
}
