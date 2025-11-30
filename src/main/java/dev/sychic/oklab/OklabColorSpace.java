package dev.sychic.oklab;

import org.ejml.data.DMatrixRMaj;

import java.awt.color.ColorSpace;

import static dev.sychic.oklab.utils.Utils.convertDoubleArrayToFloatArray;
import static dev.sychic.oklab.utils.Utils.convertFloatArrayToDoubleArray;
import static dev.sychic.oklab.utils.Utils.fromLinearSRGB;
import static dev.sychic.oklab.utils.Utils.toLinearSRGB;
import static org.ejml.dense.row.CommonOps_DDRM.apply;
import static org.ejml.dense.row.CommonOps_DDRM.invert;
import static org.ejml.dense.row.CommonOps_DDRM.mult;

public class OklabColorSpace extends ColorSpace {
    public static final OklabColorSpace INSTANCE = new OklabColorSpace();

    private static final DMatrixRMaj XYZ_TO_LMS = new DMatrixRMaj(
            3, 3, true,
            0.8189330101, 0.3618667424, -0.1288597137,
            0.0329845436, 0.9293118715,  0.0361456387,
            0.0482003018, 0.2643662691, 0.6338517070
    );
    private static final DMatrixRMaj LMS_TO_XYZ = new DMatrixRMaj();
    static {
        invert(XYZ_TO_LMS, LMS_TO_XYZ);
    }
    private static final DMatrixRMaj LMS_TO_LAB = new DMatrixRMaj(
            3, 3, true,
            0.2104542553,  0.7936177850, -0.0040720468,
            1.9779984951, -2.4285922050,  0.4505937099,
            0.0259040371,  0.7827717662, -0.8086757660
    );
    private static final DMatrixRMaj LAB_TO_LMS = new DMatrixRMaj();
    static {
        invert(LMS_TO_LAB, LAB_TO_LMS);
    }
    private static final DMatrixRMaj RGB_TO_LMS = new DMatrixRMaj(
            3, 3, true,
            0.4122214708, 0.5363325363, 0.0514459929,
            0.2119034982, 0.6806995451, 0.1073969566,
            0.0883024619, 0.2817188376, 0.6299787005
    );
    public static final DMatrixRMaj LMS_TO_RGB = new DMatrixRMaj();
    static  {
        invert(RGB_TO_LMS, LMS_TO_RGB);
    }

    protected OklabColorSpace() {
        super(ColorSpace.TYPE_Lab, 3);
    }

    @Override
    public float[] toRGB(float[] colorvalue) {
        DMatrixRMaj matrix = new DMatrixRMaj(3, 1, true, convertFloatArrayToDoubleArray(colorvalue));
        matrix = mult(LAB_TO_LMS, matrix, null);
        matrix = apply(matrix, e -> Math.pow(e, 3), null);
        matrix = mult(LMS_TO_RGB, matrix, null);
        double[] sRGB = matrix.data;
        for (int i = 0; i < sRGB.length; i++) {
            sRGB[i] = fromLinearSRGB(sRGB[i]);
        }
        return convertDoubleArrayToFloatArray(sRGB);
    }

    @Override
    public float[] fromRGB(float[] rgbvalue) {
        double[] sRGB = convertFloatArrayToDoubleArray(rgbvalue);
        for (int i = 0; i < sRGB.length; i++) {
            sRGB[i] = toLinearSRGB(sRGB[i]);
        }
        DMatrixRMaj matrix = new DMatrixRMaj(3, 1, true, sRGB);
        matrix = mult(RGB_TO_LMS, matrix, null);
        matrix = apply(matrix, Math::cbrt, null);
        matrix = mult(LMS_TO_LAB, matrix, null);
        return convertDoubleArrayToFloatArray(matrix.data);
    }

    @Override
    public float[] toCIEXYZ(float[] colorvalue) {
        DMatrixRMaj matrix = new DMatrixRMaj(3, 1, true, convertFloatArrayToDoubleArray(colorvalue));
        matrix = mult(LAB_TO_LMS, matrix, null);
        matrix = apply(matrix, e -> Math.pow(e, 3), null);
        matrix = mult(LMS_TO_XYZ, matrix, null);
        return convertDoubleArrayToFloatArray(matrix.data);
    }

    @Override
    public float[] fromCIEXYZ(float[] colorvalue) {
        DMatrixRMaj matrix = new DMatrixRMaj(3, 1, true, convertFloatArrayToDoubleArray(colorvalue));
        matrix = mult(XYZ_TO_LMS, matrix, null);
        matrix = apply(matrix, Math::cbrt, null);
        matrix = mult(LMS_TO_LAB, matrix, null);
        return convertDoubleArrayToFloatArray(matrix.data);
    }
}
