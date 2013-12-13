package com.lh.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件工具类
 * 
 * @author lihua
 * 
 */
public abstract class FileUtil {
	// 初始化文件路径
	static {
		File file = new File(getRootDir());
		if (!file.exists())
			file.mkdirs();
		file = new File(getTempDir());
		if (!file.exists())
			file.mkdirs();
		file = new File(getShareDir());
		if (!file.exists())
			file.mkdirs();
		file = new File(getLuceneDir());
		if (!file.exists())
			file.mkdirs();
	}

	/**
	 * 得到文件存放路径根目录
	 * 
	 * @return 文件路径
	 */
	public static String getRootDir() {
		return UtilConstant.ROOT_DIR;
	}

	/**
	 * 文件临时文件存放目录
	 */
	public static String getTempDir() {
		return joinDirs(getRootDir(), UtilConstant.TEMP_DIR);
	}

	/**
	 * 数据共享目录
	 */
	public static String getShareDir() {
		return joinDirs(getRootDir(), UtilConstant.SHARE_DIR);
	}

	/**
	 * lucene Index 存放目录
	 * 
	 */
	public static String getLuceneDir() {
		return joinDirs(getRootDir(), UtilConstant.LUCENE_STORE);
	}

	/**
	 * 
	 * @param paths
	 *            多个路径合并
	 * @return
	 */
	public static String joinDirs(String... paths) {
		if (paths == null || paths.length == 0)
			return null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < paths.length; i++) {
			sb.append(addFileSeparator(paths[i]));
		}
		return sb.toString();
	}

	public static boolean isEndSeparator(String str) {
		return str != null && (str.endsWith("\\") || str.endsWith("/"));
	}

	/**
	 * 文件路径后面增加目录符号
	 * 
	 * @param path
	 * @return
	 */
	private static String addFileSeparator(String path) {
		if (StringUtil.isEmpty(path))
			return "";
		if (isEndSeparator(path))
			return path;
		return path + File.separator;
	}

	/**
	 * 拷贝
	 * 
	 * @param sPath
	 *            源位置
	 * @param tPath
	 *            目标位置
	 * @throws IOException
	 */
	public static void copy(String sPath, String tPath) throws IOException {
		copy(new File(sPath), new File(tPath));
	}

	/**
	 * 拷贝
	 * 
	 * @param sPath
	 *            源文件
	 * @param tPath
	 *            目标文件
	 * @throws IOException
	 */
	public static void copy(File sFile, File tFile) throws IOException {
		if (!sFile.isDirectory()) {
			copyFile(sFile, tFile);
		} else {
			copy(sFile, tFile, "");
		}

	}

	/**
	 * 递归目录进行文件复制[私有方式禁止外部类调用]
	 * 
	 * @param sFile
	 *            源目录
	 * @param tFile
	 *            目标目录
	 * @param subPath
	 *            目录深度
	 * @throws IOException
	 */
	private static void copy(File sFile, File tFile, String subPath)
			throws IOException {
		String subP = subPath + File.separator;
		File[] files = sFile.listFiles();
		File file = null;
		for (int i = 0; i < files.length; i++) {
			file = files[i];
			if (file.isDirectory())
				copy(file, tFile, subP + file.getName());
			else {
				copyFile(file, new File(tFile, subP + file.getName()));
			}
		}
	}

	/**
	 * 复制文件,纯文件复制[私有方式禁止外部类调用]
	 * 
	 * @param sFile
	 *            源文件
	 * @param tFile
	 *            目标文件
	 * @throws IOException
	 */
	private static void copyFile(File sFile, File tFile) throws IOException {
		File pFile = tFile.getParentFile();
		if (!pFile.exists())
			pFile.mkdirs();
		if (tFile.exists())
			tFile.delete();
		byte[] by = new byte[UtilConstant.COPY_FILE_BUFFER_SIZE];
		InputStream fis = new FileInputStream(sFile);
		FileOutputStream fos = new FileOutputStream(tFile);
		int len = fis.read(by);
		while (len > 0) {
			fos.write(by, 0, len);
			len = fis.read(by);
		}
		fis.close();
		fos.flush();
		fos.close();
		by = null;
	}

	/**
	 * 清理本目录下所有文件和目录
	 * 
	 * @param dir
	 *            文件目录
	 */
	public static void delSub(File dir) {
		if (!dir.exists()) {
			return;
		}
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				del(files[i]);
			}
		} else
			dir.delete();
	}

	/**
	 * 清理本目录下所有文件和目录
	 * 
	 * @param dir
	 *            文件目录
	 */
	public static void delSub(String path) {
		delSub(new File(path));
	}

	/**
	 * 清理本当前目录和目录下所有文件与目录
	 * 
	 * @param dir
	 *            文件目录
	 */
	public static void del(String filePath) {
		del(new File(filePath));
	}

	/**
	 * 清理本当前目录和目录下所有文件与目录
	 * 
	 * @param dir
	 *            文件目录
	 */
	public static void del(File file) {
		if (!file.exists()) {
			return;
		}
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				del(files[i]);
			}
		}
		file.delete();
	}
}