package org.example;

public class StudentDto {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public StudentDto setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public StudentDto setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                "}@"+ hashCode();
    }
}
