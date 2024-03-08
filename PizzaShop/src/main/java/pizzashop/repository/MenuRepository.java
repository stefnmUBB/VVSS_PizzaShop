package pizzashop.repository;
import java.util.logging.Logger;


import javafx.scene.control.Alert;
import pizzashop.model.MenuDataModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MenuRepository {
    private static String filename = "data/menu.txt";
    private List<MenuDataModel> listMenu;

    public MenuRepository(){
    }

    private void readMenu(){
        //ClassLoader classLoader = MenuRepository.class.getClassLoader();
        File file = new File(filename);
        this.listMenu= new ArrayList();
        try(var br = new BufferedReader(new FileReader(file))) {
            String line = null;
            while((line=br.readLine())!=null){
                MenuDataModel menuItem=getMenuItem(line);
                listMenu.add(menuItem);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Logger logger = Logger.getLogger(getClass().getName());
    private MenuDataModel getMenuItem(String line) throws IllegalArgumentException {
        try {
            MenuDataModel item = null;
            if (line == null || line.equals("")) return null;
            StringTokenizer st = new StringTokenizer(line, ",");

            String name = st.nextToken();
            double price = Double.parseDouble(st.nextToken());
            item = new MenuDataModel(name, 0, price);
            return item;
        }
        catch (Exception e){
            throw new IllegalArgumentException(line+" => "+e.getMessage());
        }
    }

    public List<MenuDataModel> getMenu(){
        readMenu();//create a new menu for each table, on request
        return listMenu;
    }
}
