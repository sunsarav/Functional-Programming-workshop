package se.lexicon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriberDAOImplementation implements SubscriberDAO {
    // Lists that acts as a Database
    private List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void save(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public List<Subscriber> findAll() {
        return subscribers;
    }

    @Override
    public Optional<Subscriber> findById(int id) {
        return Optional.empty();
    }
}
