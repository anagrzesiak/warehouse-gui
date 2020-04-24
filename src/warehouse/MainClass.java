package warehouse;

public class MainClass {

    public static void main(String[] args) {
        FullfillmentCenter sklep = new FullfillmentCenter("STUDENT WINO", 1000, "Z*wiercie");
        FullfillmentCenter domek = new FullfillmentCenter("DOMEK", 300, "Prawie Krakow");
        FullfillmentCenter edgy = new FullfillmentCenter("EGDY TEEN RECORDS SHOP", 1000, "Gdansk");
        FullfillmentCenter f4 = new FullfillmentCenter("SKLEP Z ECTSAMI", 30, "Krakow");
        Item domek1 = new Item("Ana", ItemCondition.NEW, 3, 1, 352, "DOMEK", domek);
        Item domek2 = new Item("Stary", ItemCondition.NEW, 6, 1, 696, domek.centerName, domek);
        Item domek3 = new Item("Kot", ItemCondition.NEW, 1, 2, 100, domek.centerName, domek);
        Item wino1 = new Item("HARNAS", ItemCondition.USED, 2, 1, 20, sklep.centerName, sklep);
        Item wino2 = new Item("moldawska beczka", ItemCondition.REFURBISHED, 10, 2, 9, sklep.centerName, sklep);
        Item wino3 = new Item("kadarka 1 litr", ItemCondition.NEW, 4, 5, 10, sklep.centerName, sklep);
        Item wino4 = new Item("carlo rossi na specjalne okazje", ItemCondition.NEW, 3, 1, 352, sklep.centerName, sklep);
        Item r1 = new Item("ART ANGELS", ItemCondition.NEW, 3, 1, 100, edgy.centerName, edgy);
        Item r2 = new Item("THE QUEEN IS DEAD", ItemCondition.NEW, 1, 2, 101, edgy.centerName, edgy);
        Item r3 = new Item("SPEAKING IN TONGUES", ItemCondition.USED, 2, 1, 102, edgy.centerName, edgy);
        Item r4 = new Item("SOCIAL CUES", ItemCondition.REFURBISHED, 10, 2, 103, edgy.centerName, edgy);
        Item r5 = new Item("THIS IS ALL YOURS", ItemCondition.NEW, 4, 5, 104, edgy.centerName, edgy);
        Item r6 = new Item("COME OVER WHEN YOURE SOBER", ItemCondition.NEW, 3, 1, 105, edgy.centerName, edgy);
        Item e1 = new Item("wf xd", ItemCondition.REFURBISHED, 1, 2, 150, f4.centerName, f4);
        Item e2 = new Item("fiza", ItemCondition.NEW, 6, 5, 300, f4.centerName, f4);
        Item e3 = new Item("algebra", ItemCondition.NEW, 5, 1, 500, f4.centerName, f4);
        FullfillmentCenterContainer c1 = new FullfillmentCenterContainer();
        domek.addProduct(domek1);
        domek.addProduct(domek2);
        domek.addProduct(domek3);
        sklep.addProduct(wino1);
        sklep.addProduct(wino2);
        sklep.addProduct(wino3);
        sklep.addProduct(wino4);
        edgy.addProduct(r1);
        edgy.addProduct(r2);
        edgy.addProduct(r3);
        edgy.addProduct(r4);
        edgy.addProduct(r5);
        edgy.addProduct(r6);
        f4.addProduct(e1);
        f4.addProduct(e2);
        f4.addProduct(e3);
        c1.addCenter(f4);
        c1.addCenter(sklep);
        c1.addCenter(edgy);
        c1.addCenter(domek);
    }
}
