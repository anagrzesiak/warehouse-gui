package warehouse;

import java.io.Serializable;

public class Item implements Serializable {
    String name;
    ItemCondition condition;
    double weight;
    int quantity;
    float price;
    String centersName;
    FullfillmentCenter center;

    public void changeCond(){
        condition=ItemCondition.NEW;
    }

    public void less(int x){
        quantity=quantity-x;
    }

    public void more(){
        quantity++;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCenter() {
        return centersName;
    }

    public FullfillmentCenter getCenterr(){ return center; }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public Item(String name, ItemCondition condition, double weight, int quantity, float price, String centersName, FullfillmentCenter center) {
        this.name = name;
        this.condition = condition;
        this.weight = weight;
        this.quantity = quantity;
        this.price = price;
        this.centersName = centersName;
        this.center=center;
    }

    public String summaryItem() {
        String output = "Item's name:" + name + "\nCondition:" + condition + "\nWeight:" + weight + "\nPrice: " +price +"\nLeft in center: " +quantity +"\nCenter: " +centersName;
        return output;
    }


    public void print() {
        System.out.println("Item's name:" + name);
        System.out.println("Condition: " + condition);
        System.out.println("Weight: " + weight);
        System.out.println("Price: " + price);
        System.out.println("Center: " + centersName);
    }
}

