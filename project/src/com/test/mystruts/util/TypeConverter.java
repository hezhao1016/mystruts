package com.test.mystruts.util;

/**
 * 类型转换接口
 * 
 * @author hezhao
 * 
 */
public interface TypeConverter {
	public Object convert(Class<?> elemType, String value) throws Exception;
}
