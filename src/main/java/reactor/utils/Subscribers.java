package reactor.utils;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import static reactor.utils.SerializationUtils.GSON;

public class Subscribers {
    static class DefaultSubscriber<T> extends BaseSubscriber<T> {
       private volatile Subscription subscription;
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            this.subscription = subscription;
            subscription.request(Long.MAX_VALUE);
        }

        @Override
        protected void hookOnNext(T value) {
            Logger.log(GSON.toJson(value));
            super.hookOnNext(value);
        }
    }

    static class NamedSubscriber<T> extends BaseSubscriber<T> {
        private final String name;
        private volatile Subscription subscription;

        NamedSubscriber(String name) {
            this.name = name;
        }

        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            this.subscription = subscription;
            subscription.request(Long.MAX_VALUE);
        }


        @Override
        protected void hookOnComplete() {
            Logger.log("Completed for " + name);
            super.hookOnComplete();
        }

        @Override
        protected void hookOnNext(T value) {
            Logger.log(name + " received a " + GSON.toJson(value));
            super.hookOnNext(value);
        }
    }

    public static DefaultSubscriber<Object> defaultSubscriber() {
        return new DefaultSubscriber<>();
    }

    public static NamedSubscriber<Object> namedSubscriber(String name) {
        return new NamedSubscriber<>(name);
    }
}
