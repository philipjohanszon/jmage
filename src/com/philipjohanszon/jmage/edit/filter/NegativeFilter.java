package com.philipjohanszon.jmage.edit.filter;

import com.philipjohanszon.jmage.image.Pixel;
import com.philipjohanszon.jmage.image.Image;

public class NegativeFilter extends Filter {
    @Override
    public void apply(Image image) {

        FilterFunction func = (pixel) -> {
            pixel.setRed(255 - pixel.getRed());
            pixel.setGreen(255 - pixel.getGreen());
            pixel.setBlue(255 - pixel.getBlue());

            return pixel;
        };

       map(image, func);
    }
}
