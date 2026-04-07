package se.lexicon;

import java.util.ArrayList;
import java.util.List;

public class SubscriberProcessor {
    public List<Subscriber> findSubscribers(List<Subscriber> list,SubscriberFilter filter){
        List<Subscriber> result = new ArrayList<>();
        for (Subscriber subscriber : list) {
            if (filter.matches(subscriber)) {
                result.add(subscriber);
            }
        }
        return result;
    }
    public List<Subscriber> applyToMatching(List<Subscriber> list,SubscriberFilter filter,SubscriberAction
                                            action){
        for (Subscriber subscriber : list) {
            if (filter.matches(subscriber)) {
                action.run(subscriber);
            }
        }
        return list;
    }
}
