package nl.vv32.jobutil.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventHub<T> {

    final private List<Consumer<T>> eventListeners = new ArrayList<>();

    public boolean addListener(Consumer<T> listener) {
        return eventListeners.add(listener);
    }

    public boolean removeListener(Consumer<T> listener) {
        return eventListeners.remove(listener);
    }

    public void clear() {
        eventListeners.clear();
    }

    public void supply(T t) {
        eventListeners.forEach(listener -> listener.accept(t));
    }
}
