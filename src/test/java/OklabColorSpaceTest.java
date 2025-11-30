import dev.sychic.oklab.OklabColorSpace;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class OklabColorSpaceTest {
    private static final float TOLERANCE = 1e-3f;

    /**
     * Test values taken from
     * <a href="https://bottosson.github.io/posts/oklab/#table-of-example-xyz-and-oklab-pairs">Oklab Blog Post</a>
     * under MIT License
     */
    @Test
    public void testFromCIEXYZ() {
        OklabColorSpace okcs = OklabColorSpace.INSTANCE;

        float[] black = okcs.fromCIEXYZ(new float[]{0f, 0f, 0f});
        assertArrayEquals(new float[]{0f, 0f, 0f}, black, TOLERANCE);

        float[] white = okcs.fromCIEXYZ(new float[]{0.950f, 1f, 1.089f});
        assertArrayEquals(new float[]{1f, 0f, 0f}, white, TOLERANCE);

        float[] red = okcs.fromCIEXYZ(new float[]{1f, 0f, 0f});
        assertArrayEquals(new float[]{0.450f, 1.236f, -0.019f}, red, TOLERANCE);

        float[] green = okcs.fromCIEXYZ(new float[]{0f, 1f, 0f});
        assertArrayEquals(new float[]{0.922f, -0.671f, 0.263f}, green, TOLERANCE);

        float[] blue = okcs.fromCIEXYZ(new float[]{0f, 0f, 1f});
        assertArrayEquals(new float[]{0.1526f, -1.415f, -0.449f}, blue, TOLERANCE);
    }

    @Test
    public void testToCIEXYZ() {
        OklabColorSpace okcs = OklabColorSpace.INSTANCE;

        float[] black = okcs.toCIEXYZ(new float[]{0f, 0f, 0f});
        assertArrayEquals(new float[]{0f, 0f, 0f}, black, TOLERANCE);

        float[] white = okcs.toCIEXYZ(new float[]{1f, 0f, 0f});
        assertArrayEquals(new float[]{0.950f, 1f, 1.089f}, white, TOLERANCE);

        float[] red = okcs.toCIEXYZ(new float[]{0.450f, 1.236f, -0.019f});
        assertArrayEquals(new float[]{1f, 0f, 0f}, red, TOLERANCE);

        float[] green = okcs.toCIEXYZ(new float[]{0.922f, -0.671f, 0.263f});
        assertArrayEquals(new float[]{0f, 1f, 0f}, green, TOLERANCE);

        float[] blue = okcs.toCIEXYZ(new float[]{0.1526f, -1.415f, -0.449f});
        assertArrayEquals(new float[]{0f, 0f, 1f}, blue, TOLERANCE);
    }

    @Test
    public void testFromRGB() {
        OklabColorSpace okcs = OklabColorSpace.INSTANCE;

        float[] black = okcs.fromRGB(new float[]{0f, 0f, 0f});
        assertArrayEquals(new float[]{0f, 0f, 0f}, black, TOLERANCE);

        float[] white = okcs.fromRGB(new float[]{1f, 1f, 1f});
        assertArrayEquals(new float[]{1f, 0f, 0f}, white, TOLERANCE);

        float[] red = okcs.fromRGB(new float[]{1f, 0f, 0f});
        assertArrayEquals(new float[]{0.628f, 0.225f, 0.126f}, red, TOLERANCE);

        float[] green = okcs.fromRGB(new float[]{0f, 1f, 0f});
        assertArrayEquals(new float[]{0.866f, -0.234f, 0.179f}, green, TOLERANCE);

        float[] blue = okcs.fromRGB(new float[]{0f, 0f, 1f});
        assertArrayEquals(new float[]{0.452f, -0.032f, -0.312f}, blue, TOLERANCE);
    }

    @Test
    public void testToRGB() {
        OklabColorSpace okcs = OklabColorSpace.INSTANCE;

        float[] black = okcs.toRGB(new float[]{0f, 0f, 0f});
        assertArrayEquals(new float[]{0f, 0f, 0f}, black, TOLERANCE);

        float[] white = okcs.toRGB(new float[]{1f, 0f, 0f});
        assertArrayEquals(new float[]{1f, 1f, 1f}, white, TOLERANCE);

        float[] red = okcs.toRGB(new float[]{0.62796f, 0.22486f, 0.12585f});
        assertArrayEquals(new float[]{1f, 0f, 0f}, red, TOLERANCE);

        float[] green = okcs.toRGB(new float[]{0.86644f, -0.23389f, 0.1795f});
        assertArrayEquals(new float[]{0f, 1f, 0f}, green, TOLERANCE);

        float[] blue = okcs.toRGB(new float[]{0.45201f, -0.03246f, -0.31153f});
        assertArrayEquals(new float[]{0f, 0f, 1f}, blue, TOLERANCE);

    }
}
