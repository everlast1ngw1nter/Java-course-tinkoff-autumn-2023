package edu.hw3;

import java.util.PriorityQueue;
import org.jetbrains.annotations.NotNull;


public class Task6 {

    private Task6() {
    }

    public record Stock(String name, int value) implements Comparable<Stock> {
        @Override
        public int compareTo(@NotNull Task6.Stock o) {
            if (value != o.value) {
                return Integer.compare(o.value, value);
            }
            return o.name.compareTo(name);
        }
    }

    interface StockMarket {
        void add(Stock stock);

        void remove(Stock stock);

        Stock mostValuableStock();
    }

    public static class PriorityStockMarket implements StockMarket {

        private final PriorityQueue<Stock> queue;

        public PriorityStockMarket() {
            queue = new PriorityQueue<>();
        }

        @Override
        public void add(Stock stock) {
            queue.add(stock);
        }

        @Override
        public void remove(Stock stock) {
            queue.remove(stock);
        }

        @Override
        public Stock mostValuableStock() {
            return queue.peek();
        }
    }
}
