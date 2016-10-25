package lab1;

/**
 * Created by 111 on 04.10.2016.
 */
public class Main {
    public static void main(String[] args) {
       Table table=new Table();
        Row key1=new Row();
        key1.setKeyChar('z');
        key1.setKeyString("faf");
        key1.setValue(5.0);

        Row key2=new Row();
        key2.setKeyChar('b');
        key2.setKeyString("s a");
        key2.setValue(10.0);

        Row key3=new Row();
        key3.setKeyChar('c');
        key3.setKeyString("bsd");
        key3.setValue(10.0);

        Row key4=new Row();
        key4.setKeyChar('c');
        key4.setKeyString("as ");
        key4.setValue(15.0);

        Row key5=new Row();
        key5.setKeyChar('c');
        key5.setKeyString("as");
        key5.setValue(10.0);

        table.add(key1);
        table.add(key2);
        table.add(key3);
        table.add(key4);
        table.add(key5);

        System.out.println("Прямой"+table.getRawByKeyChar('c'));
        System.out.println("Бинарный"+table.getByBinarySearch("as"));
        System.out.println("Линейный"+table.getByLinearSearch("as"));


    }
}
