package com.devansh;

import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class TestLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        TestLombok test = new TestLombok();
        test.setName("Devansh");
        test.setAge(25);
        System.out.println(test.getName() + " is " + test.getAge() + " years old.");
    }
}
