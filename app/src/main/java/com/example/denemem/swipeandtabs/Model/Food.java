package com.example.denemem.swipeandtabs.Model;


public class Food {
    private int id;
    private String name, calory, gram;

    public Food(int id, String name, String calory, String gram) {
        this.id = id;
        this.calory = calory;
        this.gram = gram;


    }

    public Food() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalory() {
        return calory;
    }

    public void setCalory(String calory) {
        this.calory = calory;
    }

    public String getGram() {
        return gram;
    }

    public void setGram(String gram) {
        this.gram = gram;
    }
}
