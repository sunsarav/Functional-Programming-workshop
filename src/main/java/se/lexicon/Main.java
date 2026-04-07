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

        System.out.println("---Subscription List Management System---");
        System.out.println("---1. Active Subscriber---");

        // Defining filter for active users
        SubscriberFilter activeSubscriber = subscriber -> subscriber.isActive();

        // Using Processor & loop to get only active people
        List<Subscriber> activeUsers = processor.findSubscribers(all,subscriber ->
                subscriber.isActive());
        for (Subscriber subscriber : activeUsers) {
            System.out.println("User: " + subscriber.getEmail());
        }

        System.out.println("\n---2. Expiring Subscription---");

        // Define filter
        SubscriberFilter expiringFilter = subscriber -> subscriber.getMonthsRemaining() <= 1;

        // Process
        List<Subscriber> expiringUsers = processor.findSubscribers(all,expiringFilter);

        // Print
        for (Subscriber subscriber : expiringUsers) {
            System.out.println("User: " + subscriber.getEmail() + " | Months Left: " +
                    subscriber.getMonthsRemaining());
        }

        System.out.println("\n---3. Active and Expiring Subscriber---");

        // Define Filter (Combining 2 conditions using '&&')
        SubscriberFilter activeAndExpiring = subscriber -> subscriber.isActive() &&
                subscriber.getMonthsRemaining() <= 1;

        // Process
        List<Subscriber> urgentUsers = processor.findSubscribers(all,activeAndExpiring);

        // Print
        for (Subscriber subscriber : urgentUsers) {
            System.out.println("Urgent Update neede for: " + subscriber.getEmail());
        }

        System.out.println("\n---Subscriber By Plan---");

        SubscriberFilter proFilter = subscriber -> subscriber.getPlan() == Plan.PRO;

        List<Subscriber> proUsers = processor.findSubscribers(all,proFilter);

        for (Subscriber subscriber : proUsers) {
            System.out.println("PRO Member: " + subscriber.getEmail());
        }


    }
}
