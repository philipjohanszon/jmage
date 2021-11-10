package com.philipjohanszon.jmage;
import com.philipjohanszon.jmage.edit.filter.GreyFilter;
import com.philipjohanszon.jmage.edit.filter.NegativeFilter;
import com.philipjohanszon.jmage.image.Image;

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
                case "help" -> System.out.println("Write <stop> to stop, <rollback> to remove the last edit, <negative> to add a negative filter or <grey> to add an grey filter");
                case "stop" -> isRunning = false;
                case "negative" -> image.addFilter(new NegativeFilter());
                case "grey" -> image.addFilter(new GreyFilter());
                case "rollback" -> image.rollback();
            }
        }

        image.export();
    }
}
