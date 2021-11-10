package com.philipjohanszon.jmage;
import com.philipjohanszon.jmage.filter.GreyFilter;
import com.philipjohanszon.jmage.filter.NegativeFilter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in) ;

        System.out.println("Please enter the filename:");
        String filename = scanner.next();

        Image image = new Image();
        image.load(filename);

        image.addFilter(new NegativeFilter());

        image.export();
    }
}
