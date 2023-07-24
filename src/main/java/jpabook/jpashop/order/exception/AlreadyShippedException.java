package jpabook.jpashop.order.exception;

public class AlreadyShippedException extends RuntimeException {

    public AlreadyShippedException(String message) {
        super(message);
    }
}
