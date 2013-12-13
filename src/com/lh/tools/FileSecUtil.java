package com.lh.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class FileSecUtil {

	/**
	 * 字节加密或解密
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @param keyStr
	 *            密码
	 * @param secOp
	 *            true 加密操作，false解密操作
	 * @throws IOException
	 */
	private static void usec(InputStream is, OutputStream os, String keyStr,
			boolean secOp) throws IOException {
		byte[] keybyte = keyStr.getBytes(UtilConstant.DEFAULT_CHARSET);
		int keySize = keybyte.length;
		byte[] temp = new byte[UtilConstant.SEC_FILE_BUFFER_SIZE];
		int len = 0;
		while ((len = is.read(temp)) > 0) {
			for (int i = 0; i < len; i++) {
				if (secOp)
					temp[i] = (byte) (temp[i] + keybyte[i % keySize]);
				else
					temp[i] = (byte) (temp[i] - keybyte[i % keySize]);
			}
			os.write(temp, 0, len);
		}
		os.flush();
	}

	/**
	 * 操作字节加密
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @param keyStr
	 *            密码
	 * @throws IOException
	 */
	public static void usec(InputStream is, OutputStream os, String keyStr)
			throws IOException {
		usec(is, os, keyStr, false);
	}

	/**
	 * 操作字节解密
	 * 
	 * @param is
	 *            输入流
	 * @param os
	 *            输出流
	 * @param keyStr
	 *            密码
	 * @throws IOException
	 */
	public static void sec(InputStream is, OutputStream os, String keyStr)
			throws IOException {
		usec(is, os, keyStr, true);
	}

	public static void main(String[] sdf) throws IOException {
		long time = System.currentTimeMillis();
		String file = "d:\\ECPClientV1.9.2 总装";
		String ext = ".rar";
		String key = "本文测试操作...~!@!#$%^&*()_)+)(*&(*";
		InputStream is = new FileInputStream(file + ext);
		OutputStream out = new FileOutputStream(file + "_sec" + time + ext);
		sec(is, out, key);
		is.close();
		out.close();
		is = new FileInputStream(file + "_sec" + time + ext);
		out = new FileOutputStream(file + "_usec" + time + ext);
		usec(is, out, key);
		is.close();
		out.close();
	}
}
