package com.lh.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public abstract class SecUuidUtil {
	private static List<Integer> CHARS_ORDER = new ArrayList<Integer>();
	static {
		synchronized (CHARS_ORDER) {
			if (CHARS_ORDER.size() == 0) {
				for (char s = '0'; s <= '9';) {
					CHARS_ORDER.add((int) s);
					s = (char) ((int) s + 1);
				}
				for (char s = 'a'; s <= 'z';) {
					CHARS_ORDER.add((int) s);
					s = (char) ((int) s + 1);
				}
				for (char s = 'A'; s <= 'Z';) {
					CHARS_ORDER.add((int) s);
					s = (char) ((int) s + 1);
				}
				CHARS_ORDER.add((int) '-');
				CHARS_ORDER.add((int) '_');
			}
		}
	}

	private static String sec(String context, String key, boolean sec) {
		int site = 0;
		int keySize = key.length();
		int CHARS_ORDERSize = CHARS_ORDER.size();
		StringBuffer sb = new StringBuffer();
		int ctxtSite = 0;
		int keySite = 0;
		for (int i = 0; i < context.length(); i++) {
			ctxtSite = CHARS_ORDER.indexOf((int) (context.charAt(i)));
			keySite = CHARS_ORDER.indexOf((int) (i >= keySize ? key.charAt(i
					% keySize) : key.charAt(i)));
			if (sec) {
				site = (ctxtSite + keySite) % CHARS_ORDERSize;
			} else {
				site = (ctxtSite - keySite);
				if (site < 0)
					site = CHARS_ORDERSize + site;
			}
			sb.append((char) (CHARS_ORDER.get(site).intValue()));
		}
		return sb.toString();
	}

	public static String sec(String context, String key) {
		return sec(context, key, true);
	}

	public static String usec(String context, String key) {
		return sec(context, key, false);
	}

	public static String getSecKey(Integer len) {
		int min = 0;
		int max = CHARS_ORDER.size() - 1;
		StringBuilder sb = new StringBuilder();
		int size = len == null ? 10 : len.intValue();
		int site = 0;
		for (int i = 0; i < size; i++) {
			site = new Random().nextInt(max) % (max - min + 1) + min;
			sb.append((char) (CHARS_ORDER.get(site).intValue()));
		}
		return sb.toString();
	}

	public static void main(String[] sf) throws Exception {
		String context = UUID.randomUUID().toString();
		System.out.println("context : " + context);
		String key = getSecKey(8);
		System.out.println("key     : " + key);
		String secStr = sec(context, key);
		System.out.println("secStr  : " + secStr);
		String usecStr = usec(secStr, key);
		System.out.println(usecStr.equals(context));
		for (int i = 33; i < 127; i++) {
			System.out.print((char) i + ",");
		}
	}
}
