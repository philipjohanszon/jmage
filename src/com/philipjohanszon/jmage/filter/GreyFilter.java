package com.philipjohanszon.jmage.filter;

import com.philipjohanszon.jmage.image.Pixel;

public class GreyFilter extends Filter {
    @Override
    public Pixel[][] apply(Pixel[][] pixels, int width, int height) {

        FilterFunction func = (pixel) -> {
            int averageColorValue = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;

            pixel.setRed(averageColorValue);
            pixel.setGreen(averageColorValue);
            pixel.setBlue(averageColorValue);

            return pixel;
        };

        return map(pixels, width, height, func);
    }
}
