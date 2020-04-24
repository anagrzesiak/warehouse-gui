package warehouse;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FullfillmentCenter {  //tutaj sa rozne funkcje z lab2, ktorych nie wykorzystywalam ale no moga sie przydac
    public String centerLocation;
    public int totalWeight;
    public int totalQuantity;
    public String centerName;
    public List<Item> itemsList=new ArrayList();
    public double maxWeight;
    public FullfillmentCenter(String centerName, double maxWeight, String centerLocation){
        this.maxWeight=maxWeight;
        this.centerName=centerName;
        this.centerLocation=centerLocation;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void addProduct(Item i1) {
        if (totalWeight + i1.weight <= maxWeight) {
            int size=itemsList.size();
            boolean ans=itemsList.contains(i1.name);
            if(ans){
                for (Item i_ : itemsList) {
                    if (i_.name.equals(i1.name)) {
                        totalWeight += i1.weight;
                        totalQuantity++;
                        (i_.quantity)++;
                        break;
                    }
                }
            }
            else
                itemsList.add(new Item(i1.name, i1.condition, i1.weight, i1.quantity, i1.price, i1.centersName, i1.center));
            totalWeight+=i1.weight;
        } else
            System.err.println("Maximum weight exceeded");
    };

    public void getProduct(Item i1){
        if(i1.quantity==1){
            itemsList.remove(i1);
            totalWeight-=i1.weight;
            totalQuantity--;
        }
        else {
            (i1.quantity)--;
            totalWeight -= i1.weight;
            totalQuantity--;
        }
    };
    public void removeProduct(Item i1){
        itemsList.remove(i1);
        totalWeight-=i1.weight;
        totalQuantity--;
    };
    public Item search(String name){
        int size=itemsList.size();
        for (Item i_ : itemsList) {
            if (i_.name.equals(name)) {
                return i_;
            }
        }
        return null;
    };
    public List searchPartial(String name){
        List<Item> foundItems=new ArrayList();
        int size=itemsList.size();
        for (Item i_ : itemsList) {
            if (i_.name.contains(name)) {
                foundItems.add(new Item(i_.name, i_.condition, i_.weight, i_.quantity, i_.price, i_.centersName, i_.center));
            }
        }
        return foundItems;
    };
    public int countByCondition(ItemCondition condition){
        int counted=0;
        int size=itemsList.size();
        for (Item i_ : itemsList) {
            if (i_.condition == condition) {
                counted++;
            }
        }
        return counted;
    };
    public void summary(){
        int size=itemsList.size();
        for (Item i_ : itemsList) {
            i_.print();
        }
    };
    public void sortByName(){
        itemsList.sort(Comparator.comparing(a -> a.name));
        int size=itemsList.size();
        for (Item i_ : itemsList) {
            i_.print();
        }
    };

    public Item max() {
        return Collections.max(itemsList, Comparator.comparing(s -> s.quantity));
    }

    public String summaryText() {
        String output = "CENTER'S NAME:" + centerName + "\nCENTER'S LOCATION:" + centerLocation + "\nMAX WEIGHT:" + maxWeight + "\nFILLED: " +summaryCenter() +"%";
        return output;
    }

    public double summaryCenter() {
        double percentFilled = (totalWeight / maxWeight) * 100;
        //System.out.println(+(totalWeight/maxWeight)*100);
       // System.out.println("Center's name:" + centerName);
        //System.out.println("Center Location: " + centerLocation);
        //System.out.println("Max Weight: " + maxWeight);
       // System.out.print("FILLED: " +percentFilled);
        //System.out.println(" % ");

        return percentFilled;
    }

    List<Item> sortByAmount()
    {
        itemsList.sort(new ItemComparator().reversed());
        return itemsList;
    }
}
