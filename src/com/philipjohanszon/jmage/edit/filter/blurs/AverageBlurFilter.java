package com.philipjohanszon.jmage.edit.filter.blurs;

import com.philipjohanszon.jmage.image.Image;
import com.philipjohanszon.jmage.image.Pixel;

import com.philipjohanszon.jmage.edit.filter.*;

public class AverageBlurFilter extends Filter {
    
    int size;

    public AverageBlurFilter(int size) {
        this.size = size;
    }

    @Override
    public void apply(Image image) {
        Image result = image;

        int kernelSize = 3 + 2 * (size - 1);

        for(int x = 0; x < image.getWidth(); x++) {
            for(int y = 0; y < image.getHeight(); y++) {

                int pixels = 0;
                int pRed = 0;
                int pGreen = 0;
                int pBlue = 0;
                int pAlpha = 0;

                for(int kx = x - kernelSize; kx < x + kernelSize; kx++) {
                    for(int ky = y - kernelSize; ky < y + kernelSize; ky++) {
                        if(kx < 0 || ky < 0 || kx >= image.getWidth() || ky >= image.getHeight())
                            continue;

                        pixels++;

                        Pixel pixel = image.getPixel(kx, ky);

                        pRed += pixel.getRed();
                        pGreen += pixel.getGreen();
                        pBlue += pixel.getBlue();
                        pAlpha += pixel.getBlue();
                    }
                }

                result.setPixel(x, y, new Pixel(pRed / pixels, pGreen / pixels, pBlue / pixels, pAlpha / pixels));

            }

        }

        for(int x = 0; x < image.getWidth(); x++) {
            for(int y = 0; y < image.getHeight(); y++) {
                image.setPixel(x, y, result.getPixel(x, y));
            }
        }
    }
}
