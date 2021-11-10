package com.jmage;
import com.filter.GreyFilter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in) ;

        System.out.println("Please enter the filename:");
        String filename = scanner.next();

        Image image = new Image();
        image.load(filename);

        image.addFilter(new GreyFilter());

        image.export();
    }
}
