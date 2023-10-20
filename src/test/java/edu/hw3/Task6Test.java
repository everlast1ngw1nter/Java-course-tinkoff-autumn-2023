package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    @Test
    void stockMarketTest1() {
        var stockMarket = new Task6.PriorityStockMarket();
        stockMarket.add(new Task6.Stock("2", 2));
        stockMarket.add(new Task6.Stock("1", 1));
        stockMarket.add(new Task6.Stock("3", 3));

        Task6.Stock maxStock = stockMarket.mostValuableStock();
        assertThat(maxStock.value())
                .isEqualTo(3);
    }

    @Test
    void stockMarketTest2() {
        var stockMarket = new Task6.PriorityStockMarket();
        stockMarket.add(new Task6.Stock("2", 2));
        stockMarket.add(new Task6.Stock("3", 1));
        stockMarket.add(new Task6.Stock("1", 3));
        Task6.Stock maxStock = stockMarket.mostValuableStock();
        assertThat(maxStock.value())
                .isEqualTo(3);
    }

    @Test
    void stockMarketTest3() {
        var stockMarket = new Task6.PriorityStockMarket();
        stockMarket.add(new Task6.Stock("2", -4));
        Task6.Stock maxStock = stockMarket.mostValuableStock();
        assertThat(maxStock.value())
                .isEqualTo(-4);
    }

    @Test
    void stockMarketTest4() {
        var stockMarket = new Task6.PriorityStockMarket();
        stockMarket.add(new Task6.Stock("2", -4));
        stockMarket.add(new Task6.Stock("1", 3));
        stockMarket.remove(new Task6.Stock("1", 3));
        Task6.Stock maxStock = stockMarket.mostValuableStock();
        assertThat(maxStock.value())
                .isEqualTo(-4);
    }
}
