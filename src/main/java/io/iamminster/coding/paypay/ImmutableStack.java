package io.iamminster.coding.paypay;

import java.util.EmptyStackException;

public class ImmutableStack<T> implements Stack<T> {

    private final T head;
    private final Stack<T> tail;

    private ImmutableStack(T head, Stack<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public Stack<T> push(T t) {
        return new ImmutableStack<T>(t, this);
    }

    @Override
    public Stack<T> pop() throws Exception {
        return tail;
    }

    @Override
    public T head() throws Exception {
        return head;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public static final Stack getEmptyStack() {
        return EmptyStack.getInstance();
    }

    private static final class EmptyStack<T> implements Stack<T> {

        @SuppressWarnings("rawtypes")
        private static final EmptyStack instance = new EmptyStack();

        @SuppressWarnings("rawtypes")
        public static final EmptyStack getInstance() {
            return instance;
        }

        @Override
        public Stack<T> push(T t) {
            return new ImmutableStack<>(t, this);
        }

        @Override
        public Stack<T> pop() throws EmptyStackException {
            throw new EmptyStackException();
        }

        @Override
        public T head() throws EmptyStackException {
            throw new EmptyStackException();
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }
}
