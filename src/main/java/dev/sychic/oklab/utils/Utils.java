package dev.sychic.oklab.utils;

public class Utils {
    public static float[] convertDoubleArrayToFloatArray(double[] array) {
        float[] floatArray = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            floatArray[i] = (float) array[i];
        }
        return floatArray;
    }

    public static double[] convertFloatArrayToDoubleArray(float[] array) {
        double[] doubleArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            doubleArray[i] = array[i];
        }
        return doubleArray;
    }

    public static double clamp(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    public static double toLinearSRGB(double f) {
        if (f >= 0.04045) {
            return Math.pow((f + 0.055) / (1 + 0.055), 2.4);
        } else {
            return f / 12.92;
        }
    }

    public static double fromLinearSRGB(double f) {
        if (f >= 0.0031308) {
            return 1.055 * Math.pow(f, 1.0/2.4) - 0.055;
        } else {
            return 12.92 * f;
        }
    }
}
