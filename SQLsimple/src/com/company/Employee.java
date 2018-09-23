package com.company;

public class Employee {

    int Id;
    String FirstName;
    String LastName;

    public Employee(int Id, String FirstName, String LastName){
        this.Id = Id;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    @Override
    public String toString() {
        return Id + ": " + FirstName + " " + LastName;
    }
}
