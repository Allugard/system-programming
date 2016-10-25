package lab1;

import java.util.*;

/**
 * Created by 111 on 04.10.2016.
 */
public class Row implements Comparable {
    private Character keyChar;
    private String keyString;
    private Double value;

    public Row(Character keyChar, String keyString, double d) {
        this.keyChar = keyChar;
        this.keyString = keyString;
        value=d;
    }

    public Row() {
    }

    public Character getKeyChar() {
        return keyChar;
    }

    public void setKeyChar(Character keyChar) {
        this.keyChar = keyChar;
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        Row entry = (Row) o;

        int result = this.keyString.compareTo(entry.keyString);
        if(result != 0) {
            return result;
        }

       /* result = this.keyChar.compareTo(entry.keyChar);
        if(result != 0) {
            return result;
        }*/
        return 0;
    }

    public static int compare(Row r1, String r2){
        List<Character> characterList1=new ArrayList<>();
        List<Character> characterList2=new ArrayList<>();
        char[] c1=r1.getKeyString().toCharArray();
       // char[] c2=r2.getKeyString().toCharArray();
         char[] c2=r2.toCharArray();

        for (int i = 0; i <c1.length ; i++) {
            characterList1.add(c1[i]);
        }
        for (int i = 0; i <c2.length ; i++) {
            characterList2.add(c2[i]);
        }
        int compare=0;
        Iterator it1=characterList1.listIterator();

        while (it1.hasNext()){
            char ch=(Character) it1.next();
            Iterator it2=characterList2.listIterator();
            while (it2.hasNext()){
                if (Character.compare(ch,(Character)it2.next())==0){
                    compare++;
                    it1.remove();
                    it2.remove();
                    break;
                }
            }
        }


        return compare;
    }

    @Override
    public int hashCode() {
        return (int) keyChar;
    }

    @Override
    public String toString() {
        return "lab1.Row{" +
                "keyChar=" + keyChar +
                ", keyString='" + keyString + '\'' +
                ", value=" + value +
                '}';
    }


}
