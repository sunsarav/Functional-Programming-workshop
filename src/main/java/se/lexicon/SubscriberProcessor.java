package se.lexicon;

import java.util.List;

public class SubscriberProcessor {
    public List<Subscriber> findSubscribers(List<Subscriber> list,SubscriberFilter filter){
        return list;
    }
    public List<Subscriber> applyToMatching(List<Subscriber> list,SubscriberFilter filter,SubscriberAction
                                            action){
        return list;
    }
}
