package nl.vv32.jobutil.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class BiEventHub<T, U> {

    final private List<BiConsumer<T, U>> eventListeners = new ArrayList<>();

    public Disposable addListener(BiConsumer<T, U> eventListener) {
        eventListeners.add(eventListener);
        return () -> eventListeners.remove(eventListener);
    }

    public void clear() {
        eventListeners.clear();
    }

    public void supply(T t, U u) {
        eventListeners.forEach(listener -> listener.accept(t, u));
    }
}
