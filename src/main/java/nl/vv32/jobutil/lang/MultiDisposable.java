package nl.vv32.jobutil.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiDisposable implements Disposable {

    final private List<Disposable> disposables = new ArrayList<>();

    public MultiDisposable(Disposable... disposables) {
        addAll(disposables);
    }

    public void add(Disposable... disposables) {
        addAll(disposables);
    }

    public void remove(Disposable disposable) {
        disposables.remove(disposable);
    }

    @Override
    public void dispose() {
        disposables.forEach(Disposable::dispose);
    }

    private void addAll(Disposable... disposables) {
        this.disposables.addAll(Arrays.asList(disposables));
    }
}
