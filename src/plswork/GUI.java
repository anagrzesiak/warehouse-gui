package plswork;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import warehouse.Data;
import warehouse.Item;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GUI implements Initializable {
    @FXML
    private ComboBox<String> combobox1;
    @FXML
    private TableView<Item> itemTable;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, Float> priceColumn;
    @FXML
    private TableColumn<Item, Integer> quantityColumn;
    @FXML
    private TableColumn<Item, String> centerColumn;
    @FXML
    private TextField search;
    @FXML
    private Button add;
    @FXML
    private Button remove;
    @FXML
    private TableView<Item> cart;
    @FXML
    private TableColumn<Item, String> cartColumn;
    @FXML
    private TextArea summary;


    List<Item> cartt = new ArrayList<>();
    Data d = new Data();
    ObservableList items = FXCollections.observableList(d.getData());
    FilteredList<Item> f = new FilteredList<>(items);

    @Override
    public void initialize(URL FXMLFileLocation, ResourceBundle resources) {
        combobox1.getItems().addAll("WSZYSTKIE", "STUDENT WINO", "SKLEP Z ECTSAMI", "EDGY TEEN RECORDS SHOP", "DOMEK");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, Float>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        centerColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("center"));
        itemTable.setItems(items);
        cartColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        cart.setItems(null);
        summary.setText("CHOOSE A CENTER\n" +
                "\nTO SEE MORE INFO");
       itemTable.setRowFactory(tv -> new TableRow<Item>() {
           @Override
           protected void updateItem(Item o, boolean b) {
               super.updateItem(o, b);
               if (o == null) {
                   setTooltip(null);
               } else {
                   Tooltip tooltip = new Tooltip();
                   tooltip.setText(o.getCenterr().summaryText());
                   setTooltip(tooltip);
               }
           }
       });
    }

    @FXML
    private void searchBar(){
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            f.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty()) {
                    itemTable.setItems(items);
                }
                assert newValue != null;
                String lowerCaseFilter = newValue.toLowerCase();
                return item.getName().toLowerCase().contains(lowerCaseFilter);

            });
        });

        SortedList<Item> sortedData = new SortedList<>(f);
        sortedData.comparatorProperty().bind(itemTable.comparatorProperty());
        itemTable.setItems(sortedData);
    }

    @FXML
    private void combobox1Action(ActionEvent event) {
        String i = combobox1.getValue();
        if (i.equals("WSZYSTKIE")) {
            itemTable.setItems(items);
            summary.setText("CHOOSE A CENTER\n" +
                    "\nTO SEE MORE INFO");
        } else {
            itemTable.setItems(d.getDobre(i));
            summary.setText(d.getAll().getCenterOK(i).summaryText());
        }
    }

    public void clickedRecordd(javafx.scene.input.MouseEvent mouseEvent){
        Item selected = null;
        if (cart.getSelectionModel().getSelectedItem() != null) {
            selected = cart.getSelectionModel().getSelectedItem();
            System.out.println("WYBRANY: " + selected.getName());
        }
        Item finalSelected1 = selected;
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("CLICKED");
                if(finalSelected1!=null){
                    System.out.println("ILOSC USUWANIE: " +finalSelected1.getQuantity());
                    finalSelected1.more();
                    System.out.println("ILOSC USUWANIE: " +finalSelected1.getQuantity());
                    items.remove(finalSelected1);
                    items.add(finalSelected1);
                    itemTable.setItems(items);
                    ObservableList<Item> yourcart = FXCollections.observableList(cartt);
                    yourcart.remove(finalSelected1);
                    cart.setItems(yourcart);//kiedy mam jeden produkt w koszyku i go usuwam wartosc w tablicy się nie updatuje nie wiem dlaczego? jesli w koszyku jest wiecej produktow wszystko jest ok?
                }
            }
        });
    }


    public void clickedRecord(javafx.scene.input.MouseEvent mouseEvent) {
        Item selected = null;
        if (itemTable.getSelectionModel().getSelectedItem() != null) {
            selected = itemTable.getSelectionModel().getSelectedItem();
            System.out.println("WYBRANY: " + selected.getName());
        }
        Item finalSelected1 = selected;
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("CLICKED");
                if(finalSelected1!=null){
                    String m = JOptionPane.showInputDialog("HOW MANY?"); //probowalam zrobic to korzystajac z Dialogs, ale to 3rd party library:/ wiem, że to okropne uzywac tu swinga przepraszam!!!
                    int number = Integer.parseInt(m);
                    if (number <= finalSelected1.getQuantity()) {
                        System.out.println(m);
                        for(int i=0; i<number; i++){
                            cartt.add(finalSelected1);
                        }
                        System.out.println("QUANTITY: " +finalSelected1.getQuantity());
                        finalSelected1.less(number);
                        System.out.println("QUANTITY: " +finalSelected1.getQuantity());
                        items.remove(finalSelected1);
                        items.add(finalSelected1);
                        itemTable.setItems(items);
                        ObservableList<Item> yourcart = FXCollections.observableList(cartt);
                        cart.setItems(yourcart);
                    } else {
                        JOptionPane.showMessageDialog(null, "BEZ PRZESADY eh");
                    }
                }
            }
        });
    }
}


