package nl.vv32.jobutil.lang;

@FunctionalInterface
public interface ThrowingRunnable<T extends Throwable> {

    void run() throws T;
}
