package com.company;

public class Main {
    public static void main(String[] args){
        TakeScreenshot thread = new TakeScreenshot();
        thread.start();
    }
}