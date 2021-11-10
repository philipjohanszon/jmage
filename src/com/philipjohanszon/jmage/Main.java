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

        boolean isRunning = true;
        while(isRunning) {
            System.out.println("Write your next command, write help for help");
            String command = scanner.next();

            switch (command) {
                case "help":
                    System.out.println("Write <stop> to stop, <negative> to add a negative filter or <grey> to add an grey filter");
                    break;
                case "stop":
                    isRunning = false;
                    break;
                case "negative":
                    image.addFilter(new NegativeFilter());
                    break;
                case "grey":
                    image.addFilter(new GreyFilter());
                    break;
            }
        }

        image.export();
    }
}
