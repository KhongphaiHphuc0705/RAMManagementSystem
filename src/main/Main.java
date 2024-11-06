package main;

import java.util.Scanner;
import management.RAMManagement;
import ui.Menu;
import utilities.MyUtils;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        RAMManagement RAMList = new RAMManagement();
        RAMList.loadFromFile(".\\RAMModules.txt");
        Menu mainMenu = new Menu("Laptop RAM Management System");
        mainMenu.addNewOption("Add an item");
        mainMenu.addNewOption("Search an item");
        mainMenu.addNewOption("Update an item");
        mainMenu.addNewOption("Delete an item");
        mainMenu.addNewOption("Show all items");
        mainMenu.addNewOption("Save to file");
        mainMenu.addNewOption("Quit");
        
        boolean saved = true;
        while (true) {
            mainMenu.printMenu();
            switch(mainMenu.getChoice()) {
                case 1:
                    RAMList.add();
                    saved = false;
                    System.out.println("Press Enter to go back to the main menu.");
                    sc.nextLine();
                    break;
                case 2:
                    RAMList.search();
                    System.out.println("Press Enter to go back to the main menu.");
                    sc.nextLine();
                    break;
                case 3:
                    RAMList.update();
                    saved = false;
                    System.out.println("Press Enter to go back to the main menu.");
                    sc.nextLine();
                    break;
                case 4:
                    RAMList.delete();
                    saved = false;
                    System.out.println("Press Enter to go back to the main menu.");
                    sc.nextLine();
                    break;
                case 5:
                    RAMList.show();
                    System.out.println("Press Enter to go back to the main menu.");
                    sc.nextLine();
                    break;
                case 6:
                    RAMList.saveToFile(".\\RAMModules.txt");
                    System.out.println("Saved successfully.");
                    saved = true;
                    System.out.println("Press Enter to go back to the main menu.");
                    sc.nextLine();
                    break;
                case 7:
                    if(saved) {
                        System.out.println("Thanks for stopping by. See you again!");
                        return;
                    }
                    String confirm = MyUtils.getString("Are you sure you want to exit without saving? (Y/N) ", "Invalid, please input again. ", "[Yy|Nn]");
                    if (confirm.equalsIgnoreCase("Y")) {
                        System.out.println("Thanks for stopping by. See you again!");
                        return;
                    }
                    break;
            }
        }
    }
}
