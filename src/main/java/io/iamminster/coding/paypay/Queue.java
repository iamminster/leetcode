package io.iamminster.coding.paypay;

import java.util.Objects;

public interface Queue<T> {
    Queue<T> enQueue(T t) throws Exception;
    Queue<T> deQueue() throws Exception;
    T head() throws Exception;
    boolean isEmpty();
}

