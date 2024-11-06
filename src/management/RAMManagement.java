package management;

import data.RAMItem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;
import ui.Menu;
import utilities.MyUtils;

public class RAMManagement extends HashMap<String, RAMItem> implements IRAMManagement {

    @Override
    public void add() {
        String code = MyUtils.getString("Input RAM code: ", "Invalid, please input again. ", "RAM\\d{1}\\_\\d{3}");
        if (this.containsKey(code)) {
            System.err.println("The code already exists");
            System.err.println("Added failed.");
            return;
        }
        String type = MyUtils.getString("Type of RAM: ", "Invalid, please input again. ", "DDR\\d{1}|LPDDR\\d{1}");
        int bus = MyUtils.getInteger("Input bus speed: ", "Invalid, please input again. ", 0);
        String brand = MyUtils.getString("Input brand: ", "Invalid, please input again. ", ".*");
        int quantity = MyUtils.getInteger("Input quantity: ", "Invalid, please input again. ", 0);
        LocalDate production_month_year = MyUtils.getDate("Input production date (yyyy-mm-dd): ", "Invalid, please input again. ");
        boolean active = true;
        this.put(code, new RAMItem(code, type, bus, brand, quantity, production_month_year, active));
        System.out.println("This is the added RAM item: ");
        System.out.printf("%2s%10s%20s%13s%12s%18s%10s\n", "RAM Code", "Type", "Bus speed (MHz)", "Brand", "Quantity", "Production date", "Active");
        System.out.println(this.get(code).toString());
        System.out.println("Added successfully.");
    }

    @Override
    public void search() {
        Menu searchMenu = new Menu("search");
        searchMenu.addNewOption("Search by type");
        searchMenu.addNewOption("Search by bus");
        searchMenu.addNewOption("Search by brand");
        searchMenu.printSubMenu();
        int choice = searchMenu.getChoice();
        ArrayList<RAMItem> RAMList = new ArrayList<>();
        this.forEach((k, v) -> RAMList.add(v));
        if (RAMList.isEmpty()) {
            System.out.println("There is no item.");
            return;
        }
        switch(choice) {
            case 1:
                String s1 = MyUtils.getString("What type do you want to search? ", "Invalid, please input again.", ".*");
                System.out.println("Search result: ");
                System.out.printf("%2s%10s%18s%12s\n", "RAM Code", "Type", "Production date", "Quantity");
                for (RAMItem x : RAMList) {
                    if (x.getType().toLowerCase().startsWith(s1.toLowerCase()))
                        System.out.println(x.searchByTypeResult());
                }
                break;
            case 2:
                String s2 = MyUtils.getString("Input bus speed (MHz): ", "Invalid, please input again.", ".*");
                System.out.println("Search result: ");
                System.out.printf("%2s%20s%18s%12s\n", "RAM Code", "Bus speed (MHz)", "Production date", "Quantity");
                for (RAMItem x : RAMList) {
                    if (x.getBus() == Integer.parseInt(s2))
                        System.out.println(x.searchByBusResult());
                }
                break;
            case 3:
                String s3 = MyUtils.getString("What brand do you want to search? ", "Invalid, please input again.", ".*");
                System.out.println("Search result: ");
                System.out.printf("%2s%13s%18s%12s\n", "RAM Code", "Brand", "Production date", "Quantity");
                for (RAMItem x : RAMList) {
                    if (x.getBrand().toLowerCase().contains(s3.toLowerCase()))
                        System.out.println(x.searchByBrandResult());
                }
                break;
        }
    }

    @Override
    public void update() {
        String code;
        do {
            code = MyUtils.getString("Input RAM code: ", "Invalid, please input again. ", ".*").toUpperCase();
            if(!this.containsKey(code) && !code.isEmpty())
                System.err.println("RAM doesn't exist.");
            if(code.isEmpty())
                System.err.println("Update failed.");
        } while(!this.containsKey(code));
        System.out.println("This is RAM item before update: ");
        System.out.printf("%2s%10s%20s%13s%12s%18s%10s\n", "RAM Code", "Type", "Bus speed (MHz)", "Brand", "Quantity", "Production date", "Active");
        System.out.println(this.get(code).toString());
        
        String type = MyUtils.getString1("Update RAM type (Press Enter if you don't want to update): ", "Invalid, please input again. ", "DDR\\d{1}|LPDDR\\d{1}").toUpperCase();
        if(!type.isEmpty())
            this.get(code).setType(type);
        
        String bus = MyUtils.getString1("Update bus speed (Press Enter if you don't want to update): ", "Invalid, please input again. ", ".*");
        if(!bus.isEmpty())
            this.get(code).setBus(Integer.parseInt(bus));
        
        String brand = MyUtils.getString1("Update brand (Press Enter if you don't want to update): ", "Invalid, please input again. ", ".*");
        if(!brand.isEmpty())
            this.get(code).setBrand(brand);
        
        String quantity = MyUtils.getString1("Update quantity (Press Enter if you don't want to update): ", "Invalid, please input again. ", ".*");
        if(!quantity.isEmpty())
            this.get(code).setQuantity(Integer.parseInt(quantity));
        
        String production_month_year = MyUtils.getString1("Update production date (Press Enter if you don't want to update): ", "Invalid, please input again. ", ".*");
        if(!production_month_year.isEmpty())
            this.get(code).setProduction_month_year(LocalDate.parse(production_month_year));
        
        System.out.println("This is the item after update: ");
        System.out.printf("%2s%10s%20s%13s%12s%18s%10s\n", "RAM Code", "Type", "Bus speed (MHz)", "Brand", "Quantity", "Production date", "Active");
                System.out.println(this.get(code).toString());
        System.out.println("Updated successfully.");
    }

    @Override
    public void delete() {
        String code;
        do {
            code = MyUtils.getString1("Code of RAM to delete: ", "Invalid, please input again. ", ".*").toUpperCase();
            if(!this.containsKey(code) && !code.isEmpty())
                System.err.println("RAM code doesn't exist.");
            if(!this.get(code).isActive())
                System.err.println("This item has been deleted.");
            if(code.isEmpty())
                System.err.println("Delete failed.");
        } while(!this.containsKey(code) || !this.get(code).isActive());
        System.out.println("This is the RAM item before delete: ");
        System.out.printf("%2s%10s%20s%13s%12s%18s%10s\n", "RAM Code", "Type", "Bus speed (MHz)", "Brand", "Quantity", "Production date", "Active");
        System.out.println(this.get(code).toString());
        String confirm = MyUtils.getString("Are you sure you want to delete this item? (Y/N) ", "Invalid, please input again. ", "[Yy|Nn]");
        if (confirm.equalsIgnoreCase("Y")) {
            this.get(code).setActive(false);
            System.out.println("Deleted successfully.");
        }
    }

    @Override
    public void show() {
        ArrayList<RAMItem> RAMList = new ArrayList<>();
        RAMList = sort(this.values());
        Collections.sort(RAMList, (RAMItem i1, RAMItem i2) -> {
            if (i1.getType().equals(i2.getType()) && i1.getBus() == i2.getBus())
                return i1.getBrand().compareTo(i2.getBrand());
            else if (i1.getType().equals(i2.getType()))
                return Integer.compare(i1.getBus(), i2.getBus());
            return i1.getType().compareTo(i2.getType());
        });
        if (RAMList.isEmpty()) {
            System.out.println("There is no item.");
            return;
        }
        System.out.printf("%2s%10s%20s%13s%12s%18s%10s\n", "RAM Code", "Type", "Bus speed (MHz)", "Brand", "Quantity", "Production date", "Active");
        for (RAMItem x : RAMList) {
            if(x.isActive())
                System.out.println(x.toString());
        }
    }
    
    public ArrayList<RAMItem> sort(Collection<RAMItem> liRAM) {
        ArrayList<RAMItem> RAMList = new ArrayList<>(liRAM);
        Collections.sort(RAMList);
        return RAMList;
    }
    
    public boolean loadFromFile(String url) {
        File f = new File(url);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = br.readLine();
            while (line != null) {                
                StringTokenizer st = new StringTokenizer(line, ", ");
                String code = st.nextToken();
                String type = st.nextToken().trim();
                int bus = Integer.parseInt(st.nextToken().trim());
                String brand = st.nextToken().trim();
                int quantity = Integer.parseInt(st.nextToken().trim());
                LocalDate production_month_year = LocalDate.parse(st.nextToken().trim());
                boolean active = Boolean.parseBoolean(st.nextToken().trim());
                RAMItem i = new RAMItem(code, type, bus, brand, quantity, production_month_year, active);
                this.put(code, i);                
                line = br.readLine();
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error, loading from file " + e);
            return false;
        }
    }
    
    public boolean saveToFile(String url) {
        File f = new File(url);
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f));
            for (RAMItem x : sort(this.values())) 
                osw.write(x.toString() + "\n");
            osw.flush();
            osw.close();
            return true;
        } catch (Exception e) {
            System.err.println("Error, saving to file " + e);
            return false;
        }
    }
}
