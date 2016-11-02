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

        hashMaps[State.s7.ordinal()].put(Signal.dlm,State.sE);
        hashMaps[State.s7.ordinal()].put(Signal.cfr,State.sE);

        hashMaps[State.s7.ordinal()].put(Signal.dlm,State.s7);
        hashMaps[State.s2.ordinal()].put(Signal.cfr,State.s2);


        hashMaps[State.s3.ordinal()].put(Signal.dlm,State.s7);
        hashMaps[State.s5.ordinal()].put(Signal.cfr,State.s2);
    }

    public void printGraph(State s,Signal ... signals){
        String res="";
        boolean intState=false;
        for (int i = 0; i <signals.length ; i++) {
            res+=i+1+")"+signals[i]+": "+s+"->";

            s=hashMaps[s.ordinal()].get(signals[i]);
            if(s==null){
                s=State.stateError;
                res+=s+"\nError signal\n";
                intState=true;
                break;
            }else {
                res+=s+"\n";
            }
            if(s==State.sE){
                res+="End";
                intState=true;
                break;
            }



        }
        if(intState==false) {
            res += "Intermediate State";
        }
        System.out.println(res);
    }
}
