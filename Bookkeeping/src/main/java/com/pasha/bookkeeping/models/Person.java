package com.pasha.bookkeeping.models;


public class Person {
    private int person_id;
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int year) {
        this.name = name;
        this.age = year;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
