package com.philipjohanszon.jmage.image;

public class Pixel {
    private int red;
    private int blue;
    private int green;
    private int alpha;

    public Pixel(int r, int b, int g, int alpha) {
        this.red = r;
        this.blue = b;
        this.green = g;
        this.alpha = alpha;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
}
