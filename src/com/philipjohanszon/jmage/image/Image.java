package com.philipjohanszon.jmage.image;

import com.philipjohanszon.jmage.filter.Filter;

import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Image {
    //image stuff
    private boolean isCreated = false;
    private String filename;
    private File file;
    private BufferedImage image;

    private Pixel[][] pixels;
    //will be used for rollback of filters and stuff
    private Pixel[][] originalPixels;

    private int width;
    private int height;

    //added stuff
    private ArrayList<Filter> filters = new ArrayList<>();

    public void create(String filename, int height, int width) {
        if (!this.isCreated) {
            this.filename = filename;

            this.height = height;
            this.width = width;

            this.initPixels();

            this.isCreated = true;
        }
    }

    public void load(String filename) {
        if (!this.isCreated) {
            this.filename = filename;
            try {
                this.file = new File(this.filename);

                this.image = ImageIO.read(this.file);
                this.width = image.getWidth();
                this.height = image.getHeight();

                loadPixels();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void initPixels() {
        this.pixels = new Pixel[width][height];
        this.originalPixels = new Pixel[width][height];
    }

    private void loadPixels() {
        initPixels();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = image.getRGB(x, y);

                int red = (color & 0xff0000) >> 16;
                int green = (color & 0xff00) >> 8;
                int blue = color & 0xff;
                int alpha = (color & 0xff000000) >>> 24;

                Pixel pixel = new Pixel(red, green, blue, alpha);

                this.pixels[x][y] = pixel;
            }
        }

        this.originalPixels = this.pixels;
    }

    public void export() {
        setPixels();

        try
        {
            ImageIO.write(image, "jpg", file);
            System.out.println("Successfully exported");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public void addFilter(Filter filter) {
        this.filters.add(filter);

        this.pixels = filter.apply(this.pixels, width, height);
    }

    //sets the pixel values in the image variable
    private void setPixels() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel pixel = getPixel(x, y);
                int color = ( pixel.getAlpha()<<24 ) | ( pixel.getRed() << 16) | ( pixel.getGreen() << 8 ) | (pixel.getBlue());

                image.setRGB(x, y, color);
            }
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Pixel getPixel(int x, int y) {
        return pixels[x][y];
    }

    public void setPixel(int x, int y, Pixel pixel) {
        this.pixels[x][y] = pixel;
    }
}
