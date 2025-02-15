package test;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @copyright：深圳依时货拉拉科技有限公司
 * @fileName: ObfuscatedString
 * @author: jayden
 * @date: 2022/8/24
 * @description:
 * @history:
 */
public class ObfuscatedString {

    private static final String UTF8 = new String(new char[]{'\u0055', '\u0054', '\u0046', '\u0038'});
    private static final Random SECURE_RANDOM = new SecureRandom();
    /**
     * The obfuscated string.
     */
    private final long[] obfuscated;

    /**
     * Constructs an obfuscated string.
     *
     * @param obfuscated The obfuscated string.
     * @throws NullPointerException           If {@code obfuscated} is
     *                                        {@code null}.
     * @throws ArrayIndexOutOfBoundsException If the provided array does not
     *                                        contain at least one element.
     */
    public ObfuscatedString(final long[] obfuscated) {
        this.obfuscated = obfuscated.clone();
        this.obfuscated[0] = obfuscated[0];
    }

    /**
     * Returns the original string.
     */
    @Override
    public String toString() {
        final int length = obfuscated.length;
        // The original UTF8 encoded string was probably not a multiple
        // of eight bytes long and is thus actually shorter than this array.
        final byte[] encoded = new byte[8 * (length - 1)];

        // Obtain the seed and initialize a new PRNG with it.
        final long seed = obfuscated[0];
        final Random prng = new Random(seed);
        // De-obfuscate.
        for (int i = 1; i < length; i++) {
            final long key = prng.nextLong();
            toBytes(obfuscated[i] ^ key, encoded, 8 * (i - 1));
        }

        final String decoded;
        try {
            decoded = new String(encoded, UTF8);
        } catch (UnsupportedEncodingException ex) {
            // UTF-8 is always supported
            throw new AssertionError(ex);
        }
        final int i = decoded.indexOf(0);
        return -1 == i ? decoded : decoded.substring(0, i);
    }

    private static void appendHexLiteral(final StringBuffer sb, final long l) {
        // obfuscation futile - too short
        sb.append("0x");
        sb.append(Long.toHexString(l).toUpperCase());
        sb.append('L');
    }

    /**
     * @param bytes The array containing the bytes to decode in little endian order.
     * @param off   The offset of the bytes in the array.
     * @return The decoded long value.
     */
    private static long toLong(final byte[] bytes, int off) {
        final int end = Math.min(bytes.length, off + 8);
        long l = 0;
        for (int i = end; --i >= off; ) {
            l <<= 8;
            l |= bytes[i] & 0xFF;
        }
        return l;
    }

    /**
     * @param l     The long value to encode.
     * @param bytes The array which holds the encoded bytes upon return.
     * @param off   The offset of the bytes in the array.
     */
    private static void toBytes(long l, byte[] bytes, int off) {
        final int end = Math.min(bytes.length, off + 8);
        for (int i = off; i < end; i++) {
            bytes[i] = (byte) l;
            l >>= 8;
        }
    }

    public static String obfuscate(final String s) {
        // Any string literal used in this method is represented as an
        // ObfuscatedString unless it's no longer than two characters.
        // This should help to prevent location of this class in the obfuscated
        // code generated by ProGuard.
        if (-1 != s.indexOf(0)) {
            // => throw "Null characters are not allowed!";
            throw new IllegalArgumentException(new ObfuscatedString(new long[]{
                    0x241005931110FC70L, 0xDCD925A88EAD9F37L, 0x19ADA1C861E2A85DL,
                    0x9A5948E700FCAD8AL, 0x2E11C83A72441DE2L
            }).toString());
        }

        // Obtain the string as a sequence of UTF-8 encoded bytes.
        final byte[] encoded;
        try {
            encoded = s.getBytes(UTF8);
        } catch (UnsupportedEncodingException ex) {
            // UTF8 is always supported
            throw new AssertionError(ex);
        }
        // seed strength is effectively 48 bits
        final long seed = SECURE_RANDOM.nextLong();
        // Create and seed a Pseudo Random Number Generator (PRNG) with a random long number.
        final Random prng = new Random();
        // randomly seeded
        prng.setSeed(seed);

        // Construct a StringBuffer to hold the generated code and append the
        // seed as the first element of the encoded array of longs.
        // The value is represented in hexadecimal in order to keep the string
        // representation as short as possible.
        final StringBuffer code = new StringBuffer(new ObfuscatedString(new long[]{
                0xA28E32BB0D3E394EL, 0xF842D1C94E549EECL, 0x7D07DFF01F907E4L,
                0x4E0BDE791ECD467CL, 0xDFF389B58DA3E44FL, 0x2477FAED0CE62C79L
        }).toString());
        // => "new ObfuscatedString(new long[] {";
        appendHexLiteral(code, seed);

        final int length = encoded.length;
        for (int i = 0; i < length; i += 8) {
            final long key = prng.nextLong();
            // Compute the value of the next array element as an obfuscated
            // version of the next eight bytes of the UTF8 encoded string.
            final long obfuscated = toLong(encoded, i) ^ key;
            code.append(", ");
            appendHexLiteral(code, obfuscated);
        }

        // => "}).toString() /* => \"";
        code.append(new ObfuscatedString(new long[]{
                0x4200B7AD6FFFF546L, 0x9B822E95FE73769DL, 0x23C2800C6CACFCE3L, 0x21C30B492D9AEF99L
        }));

        // Append the original string to the generated code comment, properly escaping quotation marks and backslashes.
        code.append(s.replaceAll("\\\\", new ObfuscatedString(new long[]{
                        /* => "\\\\\\\\" */
                        0x6D2C680D49523A01L, 0xB932F1DBD19E82CEL}).toString())
                .replaceAll("\"", new ObfuscatedString(new long[]{
                        /* => "\\\\\"" */
                        0x85E9D53EF7A9324BL, 0xB05BD65C9F19DE07L}).toString()));

        code.append(new ObfuscatedString(new long[]{
                // => "\" */"
                0xC54FFF0621E7D107L, 0x194EAD468C6FCF93L
        }));
        return code.toString();
    }
}