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
import javafx.stage.WindowEvent;
import warehouse.Data;
import warehouse.Item;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class GUI implements Initializable, Serializable {
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
    private Button exportButton;
    @FXML
    private Button importButton;
    @FXML
    private TableView<Item> cart;
    @FXML
    private TableColumn<Item, String> cartColumn;

    public void onClose() throws IOException {
        serialize();
    }

    List<Item> cartt = new ArrayList<>();
    Data d = new Data();
    ObservableList items = FXCollections.observableList(d.getData());
    List<Item> itemss=new ArrayList<>();
    FilteredList<Item> f = new FilteredList<>(items);

    private void closeWindowEvent(WindowEvent event) {
        System.out.println("Window close request ...");

    }

    @Override
    public void initialize(URL FXMLFileLocation, ResourceBundle resources) {
        combobox1.getItems().addAll("WSZYSTKIE", "STUDENT WINO", "SKLEP Z ECTSAMI", "EDGY TEEN RECORDS SHOP", "DOMEK");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, Float>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        centerColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("center"));
        try {
            deserialize("centerState.csv");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ERROR IMPORTING DATA");
        }
        cartColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        try {
            deserialize("boughtItems.csv");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ERROR IMPORTING DATA");
        }
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
        cart.setRowFactory(tv -> new TableRow<Item>() {
            @Override
            protected void updateItem(Item o, boolean b) {
                super.updateItem(o, b);
                if (o == null) {
                    setTooltip(null);
                } else {
                    Tooltip tooltip = new Tooltip();
                    tooltip.setText(o.summaryItem());
                    setTooltip(tooltip);
                }
            }
        });
    }

    @FXML
    private void exportData(ActionEvent event) throws IOException {
       serialize();
    }

    public void serialize() throws IOException {
        FileOutputStream fos = new FileOutputStream("boughtItems.csv");
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(cartt);
        out.close();

        FileOutputStream fos1 = new FileOutputStream("centerState.csv");
        ObjectOutputStream out1 = new ObjectOutputStream(fos1);
        out1.writeObject(itemss);
        out1.close();
    }


    private void deserialize(String s) throws IOException, ClassNotFoundException {
        try {
            if(s.equals("boughtItems.csv")) {
                cartt = new ArrayList<>();

                FileInputStream fis = new FileInputStream(s);
                ObjectInputStream in = new ObjectInputStream(fis);

                cartt = (ArrayList<Item>) in.readObject();
                in.close();
                for(Item i : cartt){
                    i.changeCond();
                }
                ObservableList<Item> yourcart = FXCollections.observableList(cartt);
                cart.setItems(yourcart);
            }
            else{
                itemss =  new ArrayList<>();

                FileInputStream fis = new FileInputStream(s);
                ObjectInputStream in = new ObjectInputStream(fis);

                itemss = (ArrayList<Item>) in.readObject();
                in.close();
                items = FXCollections.observableList(itemss);
                itemTable.setItems(items);
            }
        }
        catch(IOException | ClassNotFoundException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Warning");
            alert.setContentText("CANNOT IMPORT DATABASE - GETTING DATA FROM DATA GENERATOR");
            alert.showAndWait();
            if(s.equals("boughtItems.csv")) {
                cart.setItems(null);
            }
            else{
                itemss=d.getData1();
                items=FXCollections.observableList(itemss);
                itemTable.setItems(items);
            }
        }
    }

    @FXML
    private void importData(ActionEvent event) throws IOException, ClassNotFoundException {
        deserialize("boughtItems.csv");
        deserialize("centerState.csv");
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
        } else {
            itemTable.setItems(d.getDobre(i));
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
                    itemss.remove(finalSelected1);
                    itemss.add(finalSelected1);
                    items=FXCollections.observableList(itemss);
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
                    TextInputDialog dialog = new TextInputDialog(" ");
                    dialog.setTitle("Input");
                    dialog.setHeaderText("How many?");
                    dialog.setContentText("Please enter how many:(");
                    Optional<String> result = dialog.showAndWait();
                    int number = Integer.parseInt(result.get());
                    if (number <= finalSelected1.getQuantity()) {
                        System.out.println(result.get());
                        for(int i=0; i<number; i++){
                            cartt.add(finalSelected1);
                        }
                        System.out.println("QUANTITY: " +finalSelected1.getQuantity());
                        finalSelected1.less(number);
                        System.out.println("QUANTITY: " +finalSelected1.getQuantity());
                        itemss.remove(finalSelected1);
                        itemss.add(finalSelected1);
                        items=FXCollections.observableList(itemss);
                        itemTable.setItems(items);
                        ObservableList<Item> yourcart = FXCollections.observableList(cartt);
                        cart.setItems(yourcart);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText("Error");
                        alert.setContentText("Ooops, too many!");
                        alert.showAndWait();
                    }
                }
            }
        });
    }
}


