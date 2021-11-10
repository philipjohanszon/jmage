package com.philipjohanszon.jmage.filter;

import com.philipjohanszon.jmage.Pixel;

public class NegativeFilter extends Filter {
    @Override
    public Pixel[][] apply(Pixel[][] pixels, int width, int height) {

        FilterFunction func = (pixel) -> {
            pixel.setRed(255 - pixel.getRed());
            pixel.setGreen(255 - pixel.getGreen());
            pixel.setBlue(255 - pixel.getBlue());

            return pixel;
        };

        return map(pixels, width, height, func);
    }
}
