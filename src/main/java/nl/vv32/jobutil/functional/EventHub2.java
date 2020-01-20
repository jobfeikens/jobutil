package nl.vv32.jobutil.functional;

import nl.vv32.jobutil.lang.Disposable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class EventHub2<T, U> {

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
