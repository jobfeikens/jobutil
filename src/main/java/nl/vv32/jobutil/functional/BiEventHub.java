package nl.vv32.jobutil.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class BiEventHub<T, U> {

    final private List<BiConsumer<T, U>> eventListeners = new ArrayList<>();

    public boolean addListener(BiConsumer<T, U> listener) {
        return eventListeners.add(listener);
    }

    public boolean removeListener(BiConsumer<T, U> listener) {
        return eventListeners.remove(listener);
    }

    public void clear() {
        eventListeners.clear();
    }

    public void supply(T t, U u) {
        eventListeners.forEach(listener -> listener.accept(t, u));
    }
}
