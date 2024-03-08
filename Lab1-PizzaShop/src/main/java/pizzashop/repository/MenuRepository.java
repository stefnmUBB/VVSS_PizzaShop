package pizzashop.repository;

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
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                MenuDataModel menuItem=getMenuItem(line);
                listMenu.add(menuItem);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // !!!
        } catch (IOException e) {
            e.printStackTrace(); // !!!
        }
    }

    private MenuDataModel getMenuItem(String line){
        MenuDataModel item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ","); // probleme la parsare daca input nu e "a,b"
        String name= st.nextToken(); // throws NoSuchElementException
        double price = Double.parseDouble(st.nextToken());
        item = new MenuDataModel(name, 0, price); // MenuItem != Order?
        return item;
    }

    public List<MenuDataModel> getMenu(){
        // guard: if(menu was not read) : readMenu(); else read from cache();
        readMenu();//create a new menu for each table, on request
        return listMenu;
    }
}
