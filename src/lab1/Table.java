package lab1;

import java.util.*;

/**
 * Created by 111 on 04.10.2016.
 */
public class Table  {
    private List<Row>[]rLists;
    private List<Row> rows;
    public Table(){
        rLists=new List[255];
        for (int i = 0; i <255 ; i++) {
            rLists[i]=new ArrayList();
            rows=new ArrayList<>();
        }
    }

    public void add(Row row){
        int code=row.hashCode();
            rLists[code].add(row);
    }

    public List<Row> getRawByKeyChar(char c){
            return rLists[(int)c];
    }
    public void delete(char c){
        rLists[(int)c].clear();
    }

    public void update(char c,double value){
        for (Row row:rLists[(int)c]) {
            row.setValue(value);
        }
    }
        
        

    public List<Row> getByLinearSearch(String str){
        ArrayList<Row>rows=new ArrayList<>();
        for (int i = 0; i <rLists.length ; i++) {
            for (Row row : rLists[i]) {
                if(Row.compare(row,str)==str.length()){
                    rows.add(row);
                }
            }
        }
        return rows;
    }

    public ArrayList<Row> sort(List<Row> rows, String str){
        ArrayList<Row> rowArrayList=new ArrayList<>();
        rowArrayList.add(rows.get(0));
        rows.remove(rows.get(0));
        for (Row row:rows){
            boolean ins=false;
            for (Row row1:rowArrayList){
                if(Row.compare(row,str)>Row.compare(row1,str)||Row.compare(row,str)==Row.compare(row1,str)){
                    rowArrayList.add(rowArrayList.indexOf(row1),row);
                    ins=true;
                    break;
                }
            }
            if(ins==false){
                rowArrayList.add(row);
            }

        }
        return rowArrayList;
    }



    public List<Row> getByBinarySearch(String str){
        ArrayList<Row> rows =new ArrayList<>();
        for (int i = 0; i <rLists.length ; i++) {
            rows.addAll(rLists[i]);
        }
        if (rows.isEmpty()){
            return null;
        }
       // System.out.println(rows);
        rows=sort(rows,str);
      //  System.out.println(rows);
        //Collections.sort(rows);

        ArrayList<Row>listRow=new ArrayList<>();
        for (Row row:rows) {
            if(Row.compare(row,str)==str.length()){
                listRow.add(row);
            }
        }
        // System.out.println(rows);
        //System.out.println(rows);
       /* int low=0;
        int high=rows.size();
        int mid;
        while (low<high){
            mid = (low + high)/2;
            if(str.compareTo(rows.get(mid).getKeyString())==0){
                 listRow.add(rows.get(mid));
                rows.remove(rows.get(mid));
            }else {
                if (str.compareTo(rows.get(mid).getKeyString())>0){
                    low=mid+1;
                }else {
                    high=mid;
                }
            }
        }*/
        return  listRow;
    }


    @Override
    public String toString() {
        return "lab1.Table{" +
                "rLists=" + Arrays.toString(rLists) +
                '}';
    }
}
