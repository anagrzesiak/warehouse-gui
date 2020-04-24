package warehouse;
import java.util.Comparator;

public  class ItemNameComparator implements Comparator<Item> {
    public int compare(Item o1, Item o2) {
        return o1.getName().compareTo(o2.getName());
        // return o1.getIlosc()-o2.getIlosc();
    }
}