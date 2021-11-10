package com.philipjohanszon.jmage.filter;

import com.philipjohanszon.jmage.Pixel;

interface FilterFunction {
    Pixel run(Pixel pixel);
}

public class Filter {
    public Pixel[][] apply(Pixel[][] pixels, int width, int height) {
        return new Pixel[0][0];
    }

    protected Pixel[][] map(Pixel[][] pixels, int width, int height, FilterFunction func) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x][y] = func.run(pixels[x][y]);
            }
        }
        return pixels;
    }
}
