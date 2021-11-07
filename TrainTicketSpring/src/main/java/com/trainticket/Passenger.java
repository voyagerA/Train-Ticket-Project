package com.trainticket;

public class Passenger implements Comparable<Passenger>{
    private String name;
    private Character gender;
    private Integer age;

    public Passenger() {
    }

    public Passenger(String name, Character gender, Integer age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Passenger o) {
        return this.getAge()-o.getAge();
    }
}
