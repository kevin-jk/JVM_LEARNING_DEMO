package com.kun.learning.jvm.brige;

/**
 * Created by jrjiakun on 2018/12/17
 */
public class Dog extends Animal{
    public Double wight(){
        return 5.0d;
    }

    public static void main(String[]args){
        Dog dog = new Dog();
        System.out.println(dog.wight());
    }
}
