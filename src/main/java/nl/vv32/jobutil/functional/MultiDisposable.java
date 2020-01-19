package nl.vv32.jobutil.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiDisposable implements Disposable {

    final private List<Disposable> disposables = new ArrayList<>();

    public MultiDisposable(Disposable... disposables) {
        add(disposables);
    }

    public void add(Disposable... disposables) {
        this.disposables.addAll(Arrays.asList(disposables));
    }

    public void remove(Disposable disposable) {
        disposables.remove(disposable);
    }

    @Override
    public void dispose() {
        disposables.forEach(Disposable::dispose);
    }
}
