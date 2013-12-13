package com.lh.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public abstract class TomcatUtil {

	public static void main(String[] args) throws Exception {
		String basePath = "C:/Users/lihua/Desktop/";
		clearPublish(basePath + "dfrrt.war", basePath
				+ "server/tomcat-share-session/tomcat-node#/");
	}

	public static void clearPublish(String webAppPath, String serverBasePathTemp)
			throws IOException {
		String appName = new File(webAppPath).getName();
		String serverBasePath = null;
		String jspContext = readJsp(webAppPath, "index.jsp");

		for (int i = 1; i < 3; i++) {
			serverBasePath = serverBasePathTemp.replace("#", i + "");
			String appPath = serverBasePath + "webapps/"
					+ new File(webAppPath).getName();
			String tempAppPath = serverBasePath + "webapps/"
					+ appName.substring(appName.indexOf("."));
			TomcatUtil.clearCopy(webAppPath, serverBasePath);
			CompressJarUtil.uzipJar(appPath, tempAppPath);
			replaceJsp(new File(tempAppPath, "index.jsp"),
					jspContext.replace("#NAME#", i + ""));
			CompressJarUtil.zipJar(appPath, tempAppPath);
			FileUtil.del(tempAppPath);
		}
	}

	public static String readJsp(String warPath, String subPath)
			throws IOException {
		InputStream is = null;
		JarFile currentJar = new JarFile(warPath);
		JarEntry dbEntry = currentJar.getJarEntry(subPath);
		is = currentJar.getInputStream(dbEntry);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String temp = null;
		while ((temp = br.readLine()) != null) {
			if (sb.length() == 0)
				sb.append(temp);
			else
				sb.append("\n" + temp);
		}
		is.close();
		currentJar.close();
		return sb.toString();
	}

	public static void replaceJsp(File tempFile, String jspContext)
			throws IOException {
		if (tempFile.exists())
			tempFile.delete();
		FileOutputStream fos = new FileOutputStream(tempFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos,
				Charset.forName("UTF-8"));
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(jspContext);
		bw.flush();
		bw.close();
	}

	public static void replaceFile(File appFile, File repFile)
			throws IOException {
		FileUtil.copy(repFile, appFile);
	}

	public static void clearCopy(String webAppPath, String serverBasePath)
			throws IOException {
		String appName = new File(webAppPath).getName();
		String appPath = serverBasePath + "webapps/"
				+ new File(webAppPath).getName();
		String tempAppPath = serverBasePath + "webapps/"
				+ appName.substring(appName.indexOf("."));
		FileUtil.delSub(serverBasePath + "logs/");
		FileUtil.delSub(serverBasePath + "temp/");
		FileUtil.delSub(serverBasePath + "work/");
		FileUtil.del(new File(appPath));
		FileUtil.del(new File(tempAppPath));
		FileUtil.copy(webAppPath, appPath);
	}
}
