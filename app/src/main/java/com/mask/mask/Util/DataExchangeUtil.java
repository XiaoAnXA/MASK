package com.mask.mask.Util;

/**
 * 数据转换工具类
 */
public class DataExchangeUtil {

    /**
    * 将指定byte数组转换成String
     * 如：0xEF --> "EF"
    * @param b byte[]
     * @return void
     */
    public static void HexToHexString( byte[] b) {
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase());
        }
    }

    /**
     * 数组的实际长度
     * @param data
     * @return
     */
    public static int byteArrayLength(byte[] data) {
        int i = 0;
        for (; i < data.length; i++) {
            if (data[i] == '\0')
                break;
        }
        return i;
    }

    /**
     * @param byteArray byte[]
     * @return String
     */
    public static String HexToString(byte[] byteArray) {
        byte[] bytes = new byte[byteArrayLength(byteArray)];
        for (int i =0 ; i < byteArrayLength(byteArray);i++)
        {
            bytes[i] = byteArray[i];
        }
        String str = new String(bytes);
        return str;
    }

    /**
    * 将两个ASCII字符合成一个字节；
    * 如："EF"--> 0xEF
    * @param src0 byte
     *@param src1 byte
     * @return byte
     */
    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue();
        _b0 = (byte)(_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
        byte ret = (byte)(_b0 ^ _b1);
        return ret;
    }

    /**
     * 将指定字符串src，以每两个字符分割转换为16进制形式
     * 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF, 0xD9}
     * @param src String
     * @return byte[]
     */
    public static byte[] HexString2Bytes(String src){
        byte[] ret = new byte[src.length()/2];
        byte[] tmp = src.getBytes();
        for(int i=0; i<src.length()/2; i++){
            ret[i] = uniteBytes(tmp[i*2], tmp[i*2+1]);
        }
        return ret;
    }
}
