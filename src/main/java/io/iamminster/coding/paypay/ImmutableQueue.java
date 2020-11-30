package io.iamminster.coding.paypay;

public class ImmutableQueue<T> implements Queue<T> {

    private final Stack<T> forwards;
    private final Stack<T> backwards;

    private ImmutableQueue(Stack<T> forwards, Stack<T> backwards) {
        this.forwards = forwards;
        this.backwards = backwards;
    }

    private static final Stack reverseStack(Stack stack) throws Exception {
        Stack r = ImmutableStack.getEmptyStack();
        while (!stack.isEmpty()) {
            r = r.push(stack.head());
            stack = stack.pop();
        }
        return r;
    }

    @Override
    public Queue<T> enQueue(T t) throws Exception {
        return new ImmutableQueue<>(forwards, backwards.push(t));
    }

    @Override
    public Queue<T> deQueue() throws Exception {
        Stack<T> f = forwards.pop();
        if (!f.isEmpty())
            return new ImmutableQueue<>(f, backwards);
        else if (backwards.isEmpty())
            return getEmptyQueue();
        else
            return new ImmutableQueue<>(reverseStack(backwards), ImmutableStack.getEmptyStack());
    }

    @Override
    public T head() throws Exception {
        return forwards.head();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public static final Queue getEmptyQueue() {
        return EmptyQueue.getInstance();
    }

    private static class EmptyQueue<T> implements Queue<T> {

        private static final EmptyQueue emptyQueue = new EmptyQueue();

        public static final EmptyQueue getInstance() {
            return emptyQueue;
        }

        @Override
        public Queue<T> enQueue(T t) throws Exception {
            return new ImmutableQueue<>(ImmutableStack.getEmptyStack().push(t), ImmutableStack.getEmptyStack());
        }

        @Override
        public Queue<T> deQueue() throws Exception {
            throw new Exception("Queue is empty");
        }

        @Override
        public T head() throws Exception {
            throw new Exception("Queue is empty");
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }
}
