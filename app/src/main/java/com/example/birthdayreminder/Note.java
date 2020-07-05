package com.example.birthdayreminder;

public class Note {
    private String name;
    private String date;

    public Note(){

    }

    public Note(String name, String date){
        this.name = name;
        this.date = date;
    }
    public String getName(){
        return name;
    }
    public String getDate(){
        return date;
    }
}
