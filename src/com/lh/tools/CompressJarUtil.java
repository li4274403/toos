package com.lh.tools;

import java.io.File;
import java.io.IOException;

public abstract class CompressJarUtil {

	public static void zipJar(String jarPath, String sourcePath)
			throws IOException {
		new CompressJar(sourcePath, jarPath).zip();
	}

	public static void zipJar(File jar, File source) throws IOException {
		new CompressJar(source, jar).zip();
	}

	public static void uzipJar(String jar, String targetPath)
			throws IOException {
		new CompressJar(jar, targetPath).uzip();
	}

	public static void uzipJar(File jar, File targetPath) throws IOException {
		new CompressJar(jar, targetPath).uzip();
	}
}
