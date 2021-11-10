package com.philipjohanszon.jmage.edit.filter;

import com.philipjohanszon.jmage.image.Pixel;
import com.philipjohanszon.jmage.image.Image;

public class GreyFilter extends Filter {
    @Override
    public void apply(Image image) {

        FilterFunction func = (pixel) -> {
            int averageColorValue = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;

            pixel.setRed(averageColorValue);
            pixel.setGreen(averageColorValue);
            pixel.setBlue(averageColorValue);

            return pixel;
        };

        map(image, func);
    }
}
