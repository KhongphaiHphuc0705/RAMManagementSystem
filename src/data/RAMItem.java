package data;

import java.time.LocalDate;

public class RAMItem implements Comparable<RAMItem> {
    private String code;
    private String type;
    private int bus;
    private String brand;
    private int quantity;
    private LocalDate production_month_year;
    private boolean active = true;

    public RAMItem(String code, String type, int bus, String brand, int quantity, LocalDate production_month_year, boolean active) {
        this.code = code.toUpperCase();
        this.type = type.toUpperCase();
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.production_month_year = production_month_year;
        this.active = active;
    }

    public String getCode() {
        return code.toUpperCase();
    }

    public void setCode(String code) {
        this.code = code.toUpperCase();
    }

    public String getType() {
        return type.toUpperCase();
    }

    public void setType(String type) {
        this.type = type.toUpperCase();
    }

    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getProduction_month_year() {
        return production_month_year;
    }

    public void setProduction_month_year(LocalDate production_month_year) {
        this.production_month_year = production_month_year;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("%2s%10s%20s%13s%12s%18s%10s", code.toUpperCase(), type.toUpperCase(), bus, brand, quantity, production_month_year, active);
    }
    
    @Override
    public int compareTo(RAMItem that) {
        return this.code.compareToIgnoreCase(that.code);
    }
    
    public String searchByTypeResult() {
        return String.format("%2s%10s%18s%12s", code.toUpperCase(), type.toUpperCase(), production_month_year, quantity); 
    }
    
    public String searchByBusResult() {
        return String.format("%2s%20s%18s%12s", code.toUpperCase(), bus, production_month_year, quantity); 
    }
    
    public String searchByBrandResult() {
        return String.format("%2s%13s%18s%12s", code.toUpperCase(), brand, production_month_year, quantity); 
    }
}
