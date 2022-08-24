package test;

/**
 * @copyright：深圳依时货拉拉科技有限公司
 * @fileName: ObfuscatedStringTest
 * @author: jayden
 * @date: 2022/8/24
 * @description:
 * @history:
 */
public class ObfuscatedStringTest {

    public static void main(String[] args) {

        System.out.println(ObfuscatedString.obfuscate("args"));
        String result = new ObfuscatedString(new long[] {0xE80511430961BB62L, 0x1F2294F1CB628734L}).toString();
        System.out.println(result);
        assert("_sign".equals(result));
    }
}
