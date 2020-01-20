package nl.vv32.jobutil.lang;

public interface Subscription extends Disposable {

    default Subscription add(Subscription... subscriptions) {
        return () -> {
            this.dispose();
            compose(subscriptions).dispose();
        };
    }

    static Subscription compose(Subscription... subscriptions) {
        return () -> {
            for (Subscription subscription : subscriptions) {
                subscription.dispose();
            }
        };
    }
}
