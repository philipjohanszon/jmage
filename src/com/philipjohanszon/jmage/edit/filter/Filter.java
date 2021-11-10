package com.philipjohanszon.jmage.edit.filter;

import com.philipjohanszon.jmage.image.Image;
import com.philipjohanszon.jmage.image.Pixel;
import com.philipjohanszon.jmage.edit.Edit;

interface FilterFunction {
    Pixel run(Pixel pixel);
}

public class Filter extends Edit {
    protected void map(Image image, FilterFunction func) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                image.setPixel(x, y, func.run(image.getPixel(x, y)));
            }
        }
    }
}
