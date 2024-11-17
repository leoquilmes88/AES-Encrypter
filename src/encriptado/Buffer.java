package encriptado;

public class Buffer {
    private final Object[] data;
    private final int size;
    private int begin = 0;
    private int end = 0;

    public Buffer(int size) {
        this.size = size;
        this.data = new Object [size + 1];
    }

    public synchronized void write(Object object) throws InterruptedException {
        while(isFull()) {
            wait();
        }
        data[begin] = object;
        begin = next(begin);
        notifyAll();
    }

    public synchronized Object read(){
        while(isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Object result = data[end];
        end = next(end);
        notifyAll();
        return result;
    }

    private boolean isEmpty() {
        return begin == end;
    }

    private boolean isFull() {
        return next(begin) == end;
    }

    private int next(int i) {
        return (i + 1) % (size + 1);
    }

}