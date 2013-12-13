package com.lh.tools;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ReflectUtil {

	/**
	 * 得到变成参数，的数据类型
	 * 
	 * @param clazz
	 *            变长参数中其中一个参数类型<br/>
	 *            如:String... args 为getVarray(String.class)
	 * @return
	 */
	public static Class<?> getVarrayClass(Class<?> clazz) {
		Object obj = Array.newInstance(clazz, 0);
		return obj.getClass();
	}

	/**
	 * 获取所有子类与父类的所有属性
	 * 
	 * @param clazz
	 * @return
	 */
	public static Collection<Field> getField(Class<?> clazz) {
		List<Class<?>> list = new ArrayList<Class<?>>();
		Map<String, Field> map = new HashMap<String, Field>();
		readSupperClass(list, clazz);
		Class<?> clazzTemp = null;
		Field[] fields = null;
		Field f = null;
		int i = list.size();
		for (; i > -1; i--) {
			clazzTemp = list.get(i);
			fields = clazzTemp.getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				f = fields[j];
				map.put(f.getName(), f);
			}
		}
		return map.values();
	}

	private static void readSupperClass(List<Class<?>> list, Class<?> clazz) {
		if (!Object.class.getName().equals(clazz.getName())) {
			list.add(clazz);
		}
	}

}
