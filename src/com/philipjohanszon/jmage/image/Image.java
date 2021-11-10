package com.philipjohanszon.jmage.image;

import com.philipjohanszon.jmage.edit.Edit;
import com.philipjohanszon.jmage.edit.filter.Filter;

import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Arrays;

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
    private ArrayList<Edit> edits = new ArrayList<>();

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
                Pixel originalPixel = new Pixel(red, green, blue, alpha);

                this.pixels[x][y] = pixel;
            }
        }

        this.originalPixels = clonePixels(pixels);
    }

    private Pixel[][] clonePixels(Pixel[][] pixels) {
        Pixel[][] newPixel = new Pixel[width][height];

        for ( int y = 0; y < height; y++ ) {
            for ( int x = 0; x < width; x++ ) {
                Pixel selectedPixel = pixels[x][y];
                newPixel[x][y] = new Pixel(selectedPixel.getRed(), selectedPixel.getGreen(), selectedPixel.getBlue(), selectedPixel.getAlpha());
            }
        }

        return newPixel;
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
        addEdit(filter);
        filter.apply(this);
    }

    private void addEdit(Edit edit) {
        this.edits.add(edit);
    }

    public void rollback() {
        this.edits.remove(edits.size() - 1);
        this.pixels = clonePixels(originalPixels);

        for(Edit edit : edits) {
            edit.apply(this);
        }
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
