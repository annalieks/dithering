package com.processor.dithering.dither;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Quantization error for rgba color model
 */
@Getter
public class QuantizationError {

    private final int errA;

    private final int errR;

    private final int errG;

    private final int errB;

    public QuantizationError(Pixel oldValue, Pixel newValue) {
        errA = oldValue.getA() - newValue.getA();
        errR = oldValue.getR() - newValue.getR();
        errG = oldValue.getG() - newValue.getG();
        errB = oldValue.getB() - newValue.getB();
    }

}