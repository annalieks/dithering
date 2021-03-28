package com.processor.dithering.dither;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Quantization error for rgba color model
 */
@Getter
@Setter
public class QuantizationError {

    private int errA;

    private int errR;

    private int errG;

    private int errB;

    public QuantizationError(Pixel oldValue, Pixel newValue) {
        errA = oldValue.getA() - newValue.getA();
        errR = oldValue.getR() - newValue.getR();
        errG = oldValue.getG() - newValue.getG();
        errB = oldValue.getB() - newValue.getB();
    }

}