package Testat3;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class Monitor {
    static boolean activeWrite = false;
    static int readctr = 0;
    static int waitingWriter = 0;
    private Condition reader = new Condition() {
        @Override
        public void await() throws InterruptedException {
            wait();

        }

        @Override
        public void awaitUninterruptibly() {

        }

        @Override
        public long awaitNanos(long nanosTimeout) throws InterruptedException {
            return 0;
        }

        @Override
        public boolean await(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public boolean awaitUntil(Date deadline) throws InterruptedException {
            return false;
        }

        @Override
        public void signal() {
            notify();

        }

        @Override
        public void signalAll() {

        }
    };
    private Condition writer = new Condition() {
        @Override
        public void await() throws InterruptedException {
            wait();
        }

        @Override
        public void awaitUninterruptibly() {

        }

        @Override
        public long awaitNanos(long nanosTimeout) throws InterruptedException {
            return 0;
        }

        @Override
        public boolean await(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public boolean awaitUntil(Date deadline) throws InterruptedException {
            return false;
        }

        @Override
        public void signal() {
            notify();

        }

        @Override
        public void signalAll() {

        }
    };
    public void startRead() throws InterruptedException {
        readctr++;
        if(activeWrite)
        {
            reader.wait();
        }
            reader.notify();

    }

    public void endRead()
    {
        readctr--;
        if(readctr == 0)
        {
            writer.notify();
        }
    }

    public void startWrite() throws InterruptedException {
        if(readctr > 0 || activeWrite)
        {
            waitingWriter++;
            writer.wait();
        }
        activeWrite = true;

    }

    public void endWrite()
    {
        activeWrite = false;
        waitingWriter--;
        if(waitingWriter > 0)
        {
            writer.notify();
        }else if(readctr > 0)
        {
            reader.notify();
        }
    }
}
