package lab2;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by 111 on 31.10.2016.
 */
public class Graph {
    HashMap<Signal,State>[]hashMaps;
    Map<Signal, State> signalStateMap=new HashMap<>();
    public Graph(int state){
        hashMaps=new HashMap[state];
        for (int i = 0; i <hashMaps.length ; i++) {
            hashMaps[i]=new HashMap<>();
        }
        hashMaps[State.s1.ordinal()].put(Signal.sg2,State.s2);
        hashMaps[State.s2.ordinal()].put(Signal.sg3,State.s3);
        hashMaps[State.s3.ordinal()].put(Signal.sg4,State.s4);
        hashMaps[State.s4.ordinal()].put(Signal.sg5,State.s5);
        hashMaps[State.s5.ordinal()].put(Signal.sg6,State.s6);
        hashMaps[State.s6.ordinal()].put(Signal.sg7,State.s7);
        hashMaps[State.s7.ordinal()].put(Signal.sg1,State.s1);

        hashMaps[State.s3.ordinal()].put(Signal.dlm,State.s7);
        hashMaps[State.s5.ordinal()].put(Signal.cfr,State.s2);
    }

    public void printGraph(State s,Signal ... signals){
        String res="";
        for (int i = 0; i <signals.length ; i++) {
            res+=signals[i]+": ";
            res+=i+1+")"+s+"->";
            State buf=s;
            s=hashMaps[s.ordinal()].get(signals[i]);
            if(s==null){
                s=buf;
                res+=s+" - No coincidence\n";
            }else {
                res+=s+"\n";
            }
        }
        System.out.println(res);

    }
}
