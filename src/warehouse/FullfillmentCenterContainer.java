package warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullfillmentCenterContainer implements Serializable {
   // public List<FullfillmentCenter> fullfillmentCentersList = new ArrayList();
    public Map<String, FullfillmentCenter> Centers = new HashMap();

    public void addCenter(FullfillmentCenter f1) {
        Centers.put(f1.centerName, f1);
    }

    public void removeCenter(String name) {
        for(Map.Entry<String, FullfillmentCenter> entry : Centers.entrySet())
            if (entry.getKey().equals(name)) {
                Centers.remove(entry.getKey());
            }
    }

    public List getItemsNames(){
        List<String> names=new ArrayList();
        for(Map.Entry<String, FullfillmentCenter> entry : Centers.entrySet()){
            List<Item> items=entry.getValue().getItemsList();
            for(Item i : items){
                names.add(i.name);
            }
        }
        return names;
    }

    public List getItems(){
        List<Item> items=new ArrayList();
        for(Map.Entry<String, FullfillmentCenter> entry : Centers.entrySet()){
            List<Item> itemss=entry.getValue().getItemsList();
            items.addAll(itemss);
        }
        return items;
    }

    public List getItemsPrices(){
        List<Float> prices=new ArrayList();
        for(Map.Entry<String, FullfillmentCenter> entry : Centers.entrySet()){
            List<Item> items=entry.getValue().getItemsList();
            for(Item i : items){
                prices.add(i.price);
            }
        }
        return prices;
    }

    public List getItemsQuantity(){
        List<Integer> quantity=new ArrayList();
        for(Map.Entry<String, FullfillmentCenter> entry : Centers.entrySet()){
            List<Item> items=entry.getValue().getItemsList();
            for(Item i : items){
                quantity.add(i.quantity);
            }
        }
        return quantity;
    }

    public List getCenter(){
        List<String> centersx=new ArrayList();
        for(Map.Entry<String, FullfillmentCenter> entry : Centers.entrySet()) {
            List<Item> items=entry.getValue().getItemsList();
            for(int i=0; i<items.size(); i++){
                centersx.add(entry.getKey());
            }
        }
        return centersx;
    }

    public FullfillmentCenter getCenterOK(String i){
        for(Map.Entry<String, FullfillmentCenter> entry : Centers.entrySet()) {
            List<Item> items=entry.getValue().getItemsList();
            if(entry.getKey().equals(i)){
                return entry.getValue();
            }
        }
        return null;
    }



    public List findEmpty() {
        List<FullfillmentCenter> emptyCenters = new ArrayList();
        for(Map.Entry<String, FullfillmentCenter> entry : Centers.entrySet())
            if (entry.getValue().totalQuantity == 0) {
                emptyCenters.add(new FullfillmentCenter(entry.getKey(), entry.getValue().maxWeight, entry.getValue().centerLocation));
            }
        return emptyCenters;
    }

    public void summary(){
        for(Map.Entry<String, FullfillmentCenter> entry : Centers.entrySet()){
            //System.out.print(entry.getKey()+" - FILLED: ");
           // System.out.print((entry.getValue().totalWeight/entry.getValue().maxWeight)*100);
           // System.out.println("%");
        }
    }

    public void summaryCenters(){
        List<FullfillmentCenter> list = new ArrayList<FullfillmentCenter>(Centers.values());
        for (FullfillmentCenter fullfillmentCenter : list) {
            fullfillmentCenter.summaryCenter();
        }
    }
}