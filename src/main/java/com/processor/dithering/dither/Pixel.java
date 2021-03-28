package com.processor.dithering.dither;

import lombok.Getter;
import lombok.Setter;

/**
 * RGBA pixel representation
 */
@Getter
@Setter
public class Pixel {

    private int pixel;

    private int a;

    private int r;

    private int g;

    private int b;

    public Pixel(int pixel) {
        this.pixel = pixel;
        a = (pixel >> 24) & 0xff;
        r = (pixel >> 16) & 0xff;
        g = (pixel >> 8) & 0xff;
        b = pixel & 0xff;
    }

    public void adjust(QuantizationError err, int coeff) {
        a += err.getErrA() * coeff / Constants.DIVIDER;
        r += err.getErrR() * coeff / Constants.DIVIDER;
        g += err.getErrG() * coeff / Constants.DIVIDER;
        b += err.getErrB() * coeff / Constants.DIVIDER;

        a = normalize(a);
        r = normalize(r);
        g = normalize(g);
        b = normalize(b);

        pixel = (a << 24) | (r << 16) | (g << 8) | b;
    }

    private int normalize(int value) {
        if (value < 0)
            return 0;
        return Math.min(value, Constants.WHITE);
    }

}
