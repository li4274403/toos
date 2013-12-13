package com.lh.tools;

import java.io.File;
import java.nio.charset.Charset;

public abstract class UtilConstant {
	// 根目录
	static final String ROOT_DIR = File.separator + "file_store"
			+ File.separator;
	// 共享目录
	static final String SHARE_DIR = "share";
	// lucene目录
	static final String LUCENE_STORE = "lucene_store";
	// 临时目录
	static final String TEMP_DIR = "temp";
	// 拷文件缓存大小
	static final int COPY_FILE_BUFFER_SIZE = 1024 * 300;
	// 加减密文件缓存大小
	static final int SEC_FILE_BUFFER_SIZE = 1024 * 400;
	// 默认字符集
	static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	// 默认加密字符串
	static final String TEST_SEC_CODE = "testRecord!!@#%$@()sdfsreame!@";
	// 十六进制排序
	static final String TEST_SEC_REP_CODE = "03BC4671829DEF5A";
	// 数据转汉字操作
	static final String[] NUM_UNITS = { "", "十", "百", "千", "万", "十", "百", "千",
			"亿", "十", "百", "千" };
	static final String[] NUM_UPPER_CASE = { "零", "壹", "贰", "叁", "肆", "伍", "陆",
			"柒", "捌", "玖" };
	// 常用日期格式
	static final String[] df = new String[] { "yyyy-MM-dd", "yyyyMMdd",
			"yyyy-MM-dd HH:mm ss" };

	enum DbOperator {
		insert, delete, update, select, callInsert, calldelete, callUpdate, callselect
	}

	enum RegexKey {
		

	}
}
