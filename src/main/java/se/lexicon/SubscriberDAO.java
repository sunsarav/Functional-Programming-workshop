package se.lexicon;

import java.util.List;
import java.util.Optional;

public interface SubscriberDAO {
    void save(Subscriber subscriber);
    List<Subscriber> findAll();
    Optional<Subscriber> findById(int id);
}
