package nl.vv32.jobutil.functional;

import nl.vv32.jobutil.lang.Disposable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventHub<T> {

    final private List<Consumer<T>> eventListeners = new ArrayList<>();

    public Disposable addListener(Consumer<T> eventListener) {
        eventListeners.add(eventListener);
        return () -> eventListeners.remove(eventListener);
    }

    public void clear() {
        eventListeners.clear();
    }

    public void supply(T t) {
        eventListeners.forEach(listener -> listener.accept(t));
    }
}
