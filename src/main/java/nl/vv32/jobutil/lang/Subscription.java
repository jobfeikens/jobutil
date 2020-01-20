package nl.vv32.jobutil.lang;

public interface Subscription extends Disposable {

    default Subscription add(Subscription... subscriptions) {
        return () -> {
            this.dispose();
            from(subscriptions).dispose();
        };
    }

    static Subscription from(Subscription... subscriptions) {
        return () -> {
            for (Subscription subscription : subscriptions) {
                subscription.dispose();
            }
        };
    }
}
