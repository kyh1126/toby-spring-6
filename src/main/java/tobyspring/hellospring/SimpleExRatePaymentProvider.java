package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExRatePaymentProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(String currency) {
        if (currency.equals("USD")) return BigDecimal.valueOf(1000);

        throw new IllegalArgumentException("지원되지 않는 통화입니다");
    }
}
