package tobyspring.hellospring.order;

import java.math.BigDecimal;

public class Order {
    private Long id;

    private String no;

    private BigDecimal total;

    public Order() {
    }

    public Order(String no, BigDecimal total) {
        this.no = no;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", total=" + total +
                '}';
    }
}
