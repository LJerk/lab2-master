import sofia_kp.SSAP_sparql_response;
import sofia_kp.iKPIC_subscribeHandler2;
import wrapper.SmartSpaceException;
import wrapper.SmartSpaceKPI;
import wrapper.SmartSpaceTriple;

import java.util.Vector;

import static java.lang.Thread.sleep;

public class UserHandler implements iKPIC_subscribeHandler2 {

    SmartSpaceKPI kpi;
    int secretNumber;
    int min=0;
    int max=100;

    String name;

    UserHandler(String nm) throws SmartSpaceException {
        kpi=new SmartSpaceKPI("127.0.0.1", 10010, "y");
        name=nm;
        kpi.insert(new SmartSpaceTriple(name,"want","play"));
        kpi.subscribe(new SmartSpaceTriple(null,null,null),this);
        System.out.println(kpi.query(new SmartSpaceTriple(null,null, null)));
    }

    @Override
    public void kpic_RDFEventHandler(Vector<Vector<String>> vector, Vector<Vector<String>> vector1, String s, String s1) {
        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Vector<String> data : vector) {
            if(!data.get(0).equals(name)){
                return;
            }
            if(data.get(1).equals("start")){
                try {
                    secretNumber=50;
                    kpi.insert(new SmartSpaceTriple(name,"suppose", String.valueOf(secretNumber)));
                } catch (SmartSpaceException e) {
                    e.printStackTrace();
                }
            }
            if(data.get(1).equals("gets hint")){
                if(data.get(2).equals("win")){
                    System.out.println(name+"win, secretNumber was"+secretNumber);
                    try {
                        kpi.unsubscribe(new SmartSpaceTriple(name,null,null),
                                true);
                    } catch (SmartSpaceException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                if(data.get(2).equals("smaller")){
                    max=secretNumber;
                    secretNumber=(secretNumber-min)/2 + min;
                }
                if(data.get(2).equals("bigger")){
                    min=secretNumber;
                    secretNumber=(max-secretNumber)/2 + secretNumber;
                }
                System.out.println(secretNumber);
                try {
                    kpi.remove(new SmartSpaceTriple(name,"suppose", null));;
                    kpi.insert(new SmartSpaceTriple(name,"suppose", String.valueOf(secretNumber)));
                } catch (SmartSpaceException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void kpic_SPARQLEventHandler(SSAP_sparql_response ssap_sparql_response, SSAP_sparql_response ssap_sparql_response1, String s, String s1) {

    }

    @Override
    public void kpic_UnsubscribeEventHandler(String s) {

    }

    @Override
    public void kpic_ExceptionEventHandler(Throwable throwable) {

    }
}

