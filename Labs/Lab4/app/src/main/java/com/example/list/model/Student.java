package com.example.list.model;

public class Student {
    private String firstName;
    private String lastName;

    public Student(){
        // Default constructor
    }

    public Student(String name, String lastName) {
        this.firstName = name;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName(){
        return firstName ;
    }

    public String getLastName(){
        return lastName;
    }

}