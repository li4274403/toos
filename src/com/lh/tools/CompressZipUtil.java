package com.lh.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public abstract class CompressZipUtil {

	public static void main(String[] a) {
		File file = uzip(new File("d:\\4200062110120131009110317S.zip"));
		String s = uSecName(file.getAbsolutePath());
		System.out.println(s.length());
	}

	public static File uzip(File file) {
		String EXT_NAME = ".txt.enc";
		ZipInputStream zis = null;
		File uzipFile = null;
		try {
			String name = file.getName();
			name = name.substring(0, name.lastIndexOf("."));
			String uzipName = file.getParent() + File.separator + name
					+ "[UZIP" + System.currentTimeMillis() + "]" + EXT_NAME;
			name = name + EXT_NAME;
			ZipFile zipFile = new ZipFile(file);
			zis = new ZipInputStream(new FileInputStream(file));
			ZipEntry zipEntry = null;
			String fileName = null;
			byte[] by = new byte[1024 * 300];
			OutputStream os = null;
			InputStream is = null;
			while ((zipEntry = zis.getNextEntry()) != null) {
				try {
					fileName = zipEntry.getName();
					if (name.equals(fileName)) {
						uzipFile = new File(uzipName);
						os = new FileOutputStream(uzipFile);
						is = zipFile.getInputStream(zipEntry);
						int len = 0;
						while ((len = is.read(by)) != -1)
							os.write(by, 0, len);
						os.close();
						is.close();

					}
				} catch (Exception e) {

				} finally {
					if (os != null)
						try {
							os.close();
						} catch (Exception e) {
						}
					if (is != null)
						try {
							is.close();
						} catch (Exception e) {
						}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (zis != null)
				try {
					zis.close();
				} catch (Exception e) {
				}
		}
		return uzipFile;
	}

	public static String uSecName(String fileName) {
		String name = fileName;
		String extName = "";
		if (fileName.contains(".")) {
			name = fileName.substring(0, fileName.lastIndexOf("."));
			name = name + "[USC" + "-" + System.currentTimeMillis() + "]";
			extName = fileName.substring(fileName.lastIndexOf("."));
		}
		String allName = name + extName;
		System.out.println(allName);
		return allName;
	}
}
