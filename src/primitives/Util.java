package primitives;

/**
 * Util class is used for some internal utilities, e.g., controlling accuracy and performing numerical checks.
 * This class provides static utility methods for handling numerical operations and checks.
 *
 * Authors: Noga Jacobs and Noa
 */
public abstract class Util {
    // It is binary, equivalent to ~1/1,000,000,000,000 in decimal (12 digits)
    private static final int ACCURACY = -40;

    // ***************** Constructor ********************** //

    /**
     * Empty private constructor to hide the public one
     */
    private Util() {}

    // ***************** Getter ********************** //

    /**
     * Extracts the exponent of the double-precision floating-point number.
     *
     * @param num The number to extract the exponent from
     * @return The exponent of the number
     */
    private static int getExp(double num) {
        // 1. doubleToRawLongBits: "convert" the stored number to set of bits
        // 2. Shift all 52 bits to the right (removing mantissa)
        // 3. Zero the sign of number bit by mask 0x7FF
        // 4. "De-normalize" the exponent by subtracting 1023
        return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
    }

    // ***************** Methods ********************** //

    /**
     * Checks whether the given number is [almost] zero.
     *
     * @param number The number to check
     * @return True if the number is zero or almost zero, false otherwise
     */
    public static boolean isZero(double number) {
        return getExp(number) < ACCURACY;
    }

    /**
     * Aligns the number to zero if it is almost zero.
     *
     * @param number The number to align
     * @return 0.0 if the number is very close to zero, the number itself otherwise
     */
    public static double alignZero(double number) {
        return getExp(number) < ACCURACY ? 0.0 : number;
    }

    /**
     * Checks whether two numbers have the same sign.
     *
     * @param n1 The first number
     * @param n2 The second number
     * @return True if the numbers have the same sign, false otherwise
     */
    public static boolean checkSign(double n1, double n2) {
        return (n1 < 0 && n2 < 0) || (n1 > 0 && n2 > 0);
    }

    /**
     * Generates a random number within the specified range [min, max).
     *
     * @param min The minimum value (included)
     * @param max The maximum value (excluded)
     * @return The random value within the specified range
     */
    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

}
