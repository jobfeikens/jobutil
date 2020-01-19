package nl.vv32.jobutil.lang;

@FunctionalInterface
public interface Disposable {

    void dispose();

    default MultiDisposable add(Disposable other) {
        return new MultiDisposable(this, other);
    }
}
