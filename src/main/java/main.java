import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Scanner;

import wrapper.SmartSpaceException;
import wrapper.SmartSpaceKPI;
import wrapper.SmartSpaceTriple;


public class main {
    public static void main(String args[]) throws ParseException {
        try {
//            SmartSpaceKPI kp = new SmartSpaceKPI("127.0.0.1", 10010, "x");
//            SmartSpaceTriple tr = new SmartSpaceTriple("Agent_X","has_temperature","10");
//            SmartSpaceTriple tr2 = new SmartSpaceTriple("Agent_Y","watches","Agent_X");
//            SmartSpaceTriple tr3 = new SmartSpaceTriple("Agent_X","gets_info_from","Agent_Y");
//            kp.insert(tr);
//            kp.insert(tr2);
//            kp.insert(tr3);
//            System.out.println("All null:" + kp.query(new SmartSpaceTriple(null,null,null)));
//            System.out.println("Subject = Agent_X:" +kp.query(new SmartSpaceTriple("Agent_X",null,null)));
//            System.out.println("Subject = Agent_X, predicate=has_temp...:" + kp.query(new SmartSpaceTriple("Agent_X","has_temperature",null)));
//            System.out.println("Subject = Agent_Y, predicate=watches, object=Agent_X:" +kp.query(new SmartSpaceTriple("Agent_Y","watches","Agent_X")));
//            kp.update((new SmartSpaceTriple("Agent_Z","produce","text")),(new SmartSpaceTriple("Agent_X","gets_info_from","Agent_Y")));
//            System.out.println("All null:" + kp.query(new SmartSpaceTriple(null,null,null)));
//            kp.remove(new SmartSpaceTriple("Agent_X",null,null));
//            System.out.println("All null:" + kp.query(new SmartSpaceTriple(null,null,null)));
//            System.out.println("___________________________________________");
//            System.out.println("Simple prt 1");
//            SmartSpaceTriple subscrTr = new SmartSpaceTriple(null,null,null);
//            RemoveIfEvenNumberHandler handler = new RemoveIfEvenNumberHandler(kp);
//            kp.subscribe(subscrTr, handler);
//
//            int kk=1;
//            while(kk<10) {
//                kp.insert(new SmartSpaceTriple("Agent_X", "has_item", Integer.toString(kk++)));
//                sleep(1000);
//            }
//
//            kp.unsubscribe(subscrTr,true);
//            System.out.println("fin:"+kp.query(new SmartSpaceTriple(null, null,null)));
//            kp.leave();

            System.out.println("___________________________________________");
            System.out.println("Guess a number");
            SmartSpaceKPI kp1 = new SmartSpaceKPI("127.0.0.1", 10010, "y");
            kp1.remove(new SmartSpaceTriple(null,null,null));

            SmartSpaceTriple subscrAll = new SmartSpaceTriple(null,null,null);

            ServerHandler server = new ServerHandler(kp1);
            kp1.subscribe(subscrAll, server);

            UserHandler user1=new UserHandler( "user1");
            UserHandler user2=new UserHandler( "user2");
            UserHandler user3=new UserHandler( "user3");
            UserHandler user4=new UserHandler( "user4");

            boolean exit=true;
            while(exit) {
                if (new Scanner(new InputStreamReader(System.in)).nextLine().length() > 0) {
                    exit=false;
                }
            }
            System.out.println(kp1.query(new SmartSpaceTriple(user1.name,null,null)));
            System.out.println(kp1.query(new SmartSpaceTriple(user2.name,null,null)));
            System.out.println(kp1.query(new SmartSpaceTriple(user3.name,null,null)));
            System.out.println(kp1.query(new SmartSpaceTriple(user4.name,null,null)));

            kp1.unsubscribe(subscrAll, true);

            kp1.leave();

            System.out.println("end");
        } catch (SmartSpaceException e) {
            e.printStackTrace();
        }

    }
}
