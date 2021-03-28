package com.processor.dithering.dither;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class Dither {

    public static BufferedImage process(BufferedImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                int oldPixel = image.getRGB(x, y);
                int newPixel = getClosestColor(oldPixel).getRGB();

                image.setRGB(x, y, newPixel);

                Pixel oldValue = new Pixel(oldPixel);
                Pixel newValue = new Pixel(newPixel);

                QuantizationError err = new QuantizationError(oldValue, newValue);

                if (x < image.getWidth() - 1) {
                    Pixel pixel = new Pixel(image.getRGB(x + 1, y));
                    pixel.adjust(err, 7);
                    image.setRGB(x + 1, y, pixel.getPixel());

                    if (y + 1 < image.getHeight()) {
                        Pixel nearPixel = new Pixel(image.getRGB(x + 1, y + 1));
                        pixel.adjust(err, 1);
                        image.setRGB(x + 1, y + 1, nearPixel.getPixel());
                    }
                }

                if (y < image.getHeight() - 1) {
                    Pixel pixel = new Pixel(image.getRGB(x, y + 1));
                    pixel.adjust(err, 5);
                    image.setRGB(x, y + 1, pixel.getPixel());

                    if (x - 1 >= 0) {
                        Pixel nearPixel = new Pixel(image.getRGB(x - 1, y + 1));
                        pixel.adjust(err, 3);
                        image.setRGB(x - 1, y + 1, nearPixel.getPixel());
                    }
                }
            }
        }
        return image;
    }

    private static Color getClosestColor(int color) {
        Color source = new Color(color);
        List<Color> colors = Arrays.asList(Color.black, Color.white);
        Color result = colors.get(0);
        int distance = distance(colors.get(0), source);
        for (int i = 1; i < colors.size(); i++) {
            if (distance(colors.get(i), source) < distance) {
                result = colors.get(i);
            }
        }
        return result;
    }

    private static int distance(Color a, Color b) {
        return (int) Math.sqrt(Math.pow(a.getRed() - b.getRed(), 2)
                + Math.pow(a.getGreen() - b.getGreen(), 2)
                + Math.pow(a.getBlue() - b.getBlue(), 2));
    }


}
