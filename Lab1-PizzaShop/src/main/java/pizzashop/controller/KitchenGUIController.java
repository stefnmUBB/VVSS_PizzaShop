package pizzashop.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.util.Calendar;

public class KitchenGUIController {
    @FXML
    private ListView kitchenOrdersList;
    //private ListView<Order> kitchenOrdersList;
    @FXML
    public Button cook;
    @FXML
    public Button ready;

    // de ce static?
    public static  ObservableList<String> order = FXCollections.observableArrayList();
    private Object selectedOrder; // Order?
    private Calendar now = Calendar.getInstance(); // why?
    private String extractedTableNumberString = new String(); // reverse engineering
    private int extractedTableNumberInteger; // NO WAY
    //thread for adding data to kitchenOrderList
    // reassignable!!
    // public synchronized addOrders() { }
    // public Thread getAddOders() { return thread; }

    /*private?*/ public  Thread addOrders = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        kitchenOrdersList.setItems(order);
                        }
                });
                try {
                    Thread.sleep(100); // busy waiting? rezolvam cu wait-notify
                  } catch (InterruptedException ex) {
                    break;
                }
            }
        }
    });

    public void initialize() {
        //starting thread for adding data to kitchenOrderList
        addOrders.setDaemon(true);
        addOrders.start();
        //Controller for Cook Button
        cook.setOnAction(event -> {
            selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem(); // Object
            // null check selectedOrder!
            kitchenOrdersList.getItems().remove(selectedOrder);
            kitchenOrdersList.getItems().add(selectedOrder.toString()
                     .concat(" Cooking started at: ").toUpperCase()
                     .concat(now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE)));
        });

        // pizza masa 8 Cooking started at: 8:15

        //Controller for Ready Button
        ready.setOnAction(event -> {
            selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
            // null check
            kitchenOrdersList.getItems().remove(selectedOrder);
            extractedTableNumberString = selectedOrder.toString().subSequence(5, 6).toString();
            extractedTableNumberInteger = Integer.valueOf(extractedTableNumberString);
            System.out.println("--------------------------");
            System.out.println("Table " + extractedTableNumberInteger +" ready at: " + now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE));
            System.out.println("--------------------------");
        });
    }
}