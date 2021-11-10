package com.philipjohanszon.jmage.filter;

import com.philipjohanszon.jmage.Pixel;

public class GreyFilter extends Filter {
    @Override
    public Pixel[][] apply(Pixel[][] pixels, int width, int height) {


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel pixel = pixels[x][y];

                int averageColorValue = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;

                pixel.setRed(averageColorValue);
                pixel.setGreen(averageColorValue);
                pixel.setBlue(averageColorValue);

                pixels[x][y] = pixel;
            }
        }

        return pixels;
    }
}
