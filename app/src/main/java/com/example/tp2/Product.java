package com.example.tp2;

public class Product {
    private final int image;
    private final String name;
    private final String category;
    private final double price;
    private int count = 0;
    private double total;
    private Boolean selected = false;
    private Boolean opened = false;
    public Product(int image, String name, String category, double price) {
        this.image = image;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
    public void incrementCount(){
        this.count+=1;
        if (this.count > 0 ) {
            if (!selected)
                MainActivity.incrementCounter();
            this.selected=true;
        }
    }
    public void decrementCount(){
        if (this.count == 0 && selected) {
            this.selected=false;
            MainActivity.decrementCounter();
        }
     if (this.count>0){
            this.count-=1;
            if (this.count == 0 ) {
                this.selected=false;
                MainActivity.decrementCounter();
            }
        }
    }

    public int getCount() {
        return count;
    }
    public void calculateTotal(){
        this.total = price * count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public double getTotal() {
        return total;
    }
    public void toggleSelect() {
        this.selected = !this.selected;
    }

    public void toggleOpened() {
        this.opened = !this.opened;
    }
    public boolean selected(){
        return selected;
    }
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
    public boolean opened(){
        return opened;
    }
}

