package com.lh.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

class CompressJar {
	private JarOutputStream out = null;
	private File sourceFile, targetFile;
	private static final int JAR_BUFFER_SIZE = 1024 * 100;

	public CompressJar(File sourceFile, File targetFile) throws IOException {
		this.sourceFile = sourceFile;
		this.targetFile = targetFile;
	}

	public CompressJar(String sourceFile, String targetFile) throws IOException {
		this.sourceFile = new File(sourceFile);
		this.targetFile = new File(targetFile);
	}

	public void uzip() throws IOException {
		if (!targetFile.exists())
			targetFile.mkdirs();
		byte[] by = new byte[JAR_BUFFER_SIZE];
		JarFile jarFile = new JarFile(sourceFile);
		Enumeration<JarEntry> enumJars = jarFile.entries();
		JarEntry jarEntry = null;
		FileOutputStream fos = null;
		InputStream is = null;
		String name = null;
		int len = 0;
		File file = null;
		while (enumJars.hasMoreElements()) {
			jarEntry = enumJars.nextElement();
			name = jarEntry.getName();
			if (jarEntry.isDirectory()) {
				new File(targetFile, name).mkdirs();
			} else {
				file = new File(targetFile, name);
				file.getParentFile().mkdirs();
				fos = new FileOutputStream(file);
				is = jarFile.getInputStream(jarEntry);
				while ((len = is.read(by)) > 0) {
					fos.write(by, 0, len);
					len = is.read(by);
				}
				is.close();
				fos.close();
			}
		}
		jarFile.close();
	}

	public void zip() throws IOException {
		if (targetFile.exists())
			targetFile.delete();
		FileOutputStream fos = new FileOutputStream(targetFile);
		out = new JarOutputStream(fos);
		zip("", sourceFile);
		out.close();
	}

	private void zip(String base, File currentFile) throws IOException {
		if (currentFile.isDirectory()) {
			File[] fl = currentFile.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(base + fl[i].getName(), fl[i]);
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			byte[] by = new byte[JAR_BUFFER_SIZE];
			FileInputStream in = new FileInputStream(currentFile);
			int len = 0;
			while ((len = in.read(by)) > 0) {
				out.write(by, 0, len);
			}
			in.close();
		}
	}
}
