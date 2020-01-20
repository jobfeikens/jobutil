package nl.vv32.jobutil.lang;

@FunctionalInterface
public interface Disposable {

    void dispose();

    default Disposable add(Disposable... disposables) {
        return () -> {
            this.dispose();
            from(disposables).dispose();
        };
    }

    static Disposable from(Disposable... disposables) {
        return () -> {
            for (Disposable disposable : disposables) {
                disposable.dispose();
            }
        };
    }
}
