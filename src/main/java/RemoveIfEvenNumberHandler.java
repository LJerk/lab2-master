import sofia_kp.SSAP_sparql_response;
import sofia_kp.iKPIC_subscribeHandler2;
import wrapper.SmartSpaceException;
import wrapper.SmartSpaceKPI;
import wrapper.SmartSpaceTriple;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class RemoveIfEvenNumberHandler implements iKPIC_subscribeHandler2 {

    SmartSpaceKPI kpi;

    RemoveIfEvenNumberHandler(SmartSpaceKPI kp){
        kpi=kp;
    }

    @Override
    public void kpic_RDFEventHandler(Vector<Vector<String>> vector, Vector<Vector<String>> vector1, String s, String s1) {
        System.out.println("Agent_X reporting: {}"+ new Date());
        System.out.println("    added" + vector);
        System.out.println("    removed" + vector1);

        for (Vector<String> data : vector) {
            int var=0;
            try {
                var = Integer.parseInt(data.get(2));
                int ss=var%2;
                if (var%2==0) {
                    try {
                        kpi.remove(new SmartSpaceTriple(data.get(0),data.get(1),data.get(2)));
                    } catch (SmartSpaceException e) {
                        e.printStackTrace();
                    }
                }
            }
            catch (NumberFormatException e){
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
