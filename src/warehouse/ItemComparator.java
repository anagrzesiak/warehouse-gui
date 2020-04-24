package warehouse;
import java.util.Comparator;

public  class ItemComparator implements Comparator<Item> {
    public int compare(Item o1, Item o2) {
        return Integer.compare(o1.getQuantity(), o2.getQuantity());
        // return o1.getIlosc()-o2.getIlosc();
    }
}
