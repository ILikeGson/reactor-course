package reactor.utils;

import lombok.AllArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@AllArgsConstructor(staticName = "subscriber")
public class DefaultSubscriber<T> implements Subscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T t) {
        Logger.log(t);
    }

    @Override
    public void onError(Throwable t) {
        Logger.log(t.getMessage());
    }

    @Override
    public void onComplete() {
        Logger.log("Completed");
    }
}
