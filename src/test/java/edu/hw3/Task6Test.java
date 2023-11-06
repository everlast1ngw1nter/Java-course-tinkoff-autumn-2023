package edu.hw3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(
                        List.of(
                        new Task6.Stock("2", 2),
                        new Task6.Stock("1", 1),
                        new Task6.Stock("3", 3)),
                        3),
                Arguments.of(
                        List.of(
                                new Task6.Stock("2", 2),
                                new Task6.Stock("3", 1),
                                new Task6.Stock("1", 3)),
                        3),
                Arguments.of(
                        List.of(
                                new Task6.Stock("2", -4)),
                        -4)
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void addStockMarketTest(List<Task6.Stock> input, int expected) {
        var stockMarket = new Task6.PriorityStockMarket();
        for (var stock : input) {
            stockMarket.add(stock);
        }
        Task6.Stock maxStock = stockMarket.mostValuableStock();
        assertThat(maxStock.value())
                .isEqualTo(expected);
    }

    @Test
    void removeStockMarketTest() {
        var stockMarket = new Task6.PriorityStockMarket();
        stockMarket.add(new Task6.Stock("2", -4));
        stockMarket.add(new Task6.Stock("1", 3));
        stockMarket.remove(new Task6.Stock("1", 3));
        Task6.Stock maxStock = stockMarket.mostValuableStock();
        assertThat(maxStock.value())
                .isEqualTo(-4);
    }
}
