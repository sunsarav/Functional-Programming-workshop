package se.lexicon;


import java.util.List;

public class Main {
    static void main() {

        SubscriberDAO dao = new SubscriberDAOImplementation();
        SubscriberProcessor processor = new SubscriberProcessor();

        // Adding the Subscribers to the list
        dao.save(new Subscriber(1,"shamu@lexicon.com",Plan.PRO,true,7));
        dao.save(new Subscriber(2,"ragavi@lexicon.com",Plan.FREE,false,5));
        dao.save(new Subscriber(3,"sikdar@lexicon.com",Plan.BASIC,true,1));
        dao.save(new Subscriber(4,"muthana@lexicon.com",Plan.PRO,false,2));

        List<Subscriber> all = dao.findAll();

        System.out.println("---Starting Workshop Processing---");
        System.out.println("---1. Active Subscriber---");

        // Defining filter for active users
        SubscriberFilter activeSubscriber = subscriber -> subscriber.isActive();

        // Using Processor & loop to get only active people
        List<Subscriber> activeUsers = processor.findSubscribers(all,subscriber ->
                subscriber.isActive());
        for (Subscriber subscriber : activeUsers) {
            System.out.println("User: " + subscriber.getEmail());
        }

        System.out.println();
        System.out.println("---2. Expiring Subscription---");




    }
}
