package tobyspring.hellospring;

public class ObjectFactory {
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    private WebApiExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }
}
