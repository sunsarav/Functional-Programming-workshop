package se.lexicon;


import java.util.List;

public class Main {
    static void main() {

        SubscriberDAO dao = new SubscriberDAOImplementation();
        SubscriberProcessor processor = new SubscriberProcessor();

        // Adding the Subscribers to the list
        dao.save(new Subscriber(1,"shamu@lexicon.com",Plan.FREE,true,7));
        dao.save(new Subscriber(2,"ragavi@lexicon.com",Plan.PRO,false,5));
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

        System.out.println("\n---4. Subscriber By Plan---");

        SubscriberFilter proFilter = subscriber -> subscriber.getPlan() == Plan.PRO;

        List<Subscriber> proUsers = processor.findSubscribers(all,proFilter);

        for (Subscriber subscriber : proUsers) {
            System.out.println("PRO Member: " + subscriber.getEmail());
        }

        System.out.println("\n---5. Paying Subscriber---");

        // Filter: Whose plan is not "FREE"
        List<Subscriber> payingUsers = processor.findSubscribers(all,subscriber ->
                subscriber.getPlan() != Plan.FREE);

        for (Subscriber subscriber : payingUsers) {
            System.out.println("Paid Users: " + subscriber.getEmail() + " ( " +
                    subscriber.getPlan() + " ) ");
        }

        System.out.println("\n---6. Extending Subscription (Adding 12 months to PRO)---");

        // Applying the filter and action to a specific group
        processor.applyToMatching(all,
                // Filter - Only for PRO memebers
                subscriber -> subscriber.getPlan() == Plan.PRO,
                // Adding 12 months
                subscriber -> subscriber.setMonthsRemaining(subscriber.getMonthsRemaining()+ 12)
        );

        // Print Results to verify the extension
        for (Subscriber subscriber : all) {
            if (subscriber.getPlan() == Plan.PRO) {
        System.out.println("Extended: " + subscriber.getEmail() + "now has" +
                subscriber.getMonthsRemaining() + "months");
            }
        }

        System.out.println("\n---Deactivate Subscriber (Set to false if 0 months)---");

        // Define filter (0 months) and the Action (setActive False)
        processor.applyToMatching(all,
                // Filter: No months left
                subscriber -> subscriber.getMonthsRemaining() == 0,
                // Action: Deactivate
                subscriber -> subscriber.setActive(false)
        );
        // Print final status of all Users
        System.out.println("\n---Final System Status---");
        for (Subscriber subscriber : all) {
            String status = subscriber.isActive() ? "ACTIVE" : "INACTIVE";
            System.out.println("User: " + subscriber.getEmail() + " | Status: " + status +
                    " | Months: " + subscriber.getMonthsRemaining());
        }

    }
}
