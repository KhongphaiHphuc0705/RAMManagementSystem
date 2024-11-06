package ui;

import utilities.MyUtils;
import java.util.ArrayList;

public class Menu implements IMenu {
    private ArrayList<String> options = new ArrayList<>();
    private String title;

    public Menu(String title) {
        this.title = title;
    }
    
    @Override
    public void addNewOption(String newOption) {
        options.add(newOption);
    }
    
    @Override
    public void printMenu() {
        System.out.println("----------------------------------------------------");
        System.out.println("Welcome to " + title);
        int count = 0;
        for (String x : options) {
            count++;
            System.out.println(count + ". " + x);
        }
    }
    
    @Override
    public void printSubMenu() {
        System.out.println("----------------------------------------------------");
        int count = 0;
        for (String x : options) {
            count++;
            System.out.println(count + ". " + x);
        }
    }
    
    @Override
    public int getChoice() {
        return MyUtils.getInteger("Choose 1 - " + options.size() + ": "
                , "Invalid. Please input choice again. ", 1, options.size());
    }
}
