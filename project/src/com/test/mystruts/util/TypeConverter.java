package com.test.mystruts.util;

/**
 * ����ת���ӿ�
 * 
 * @author hezhao
 * 
 */
public interface TypeConverter {
	public Object convert(Class<?> elemType, String value) throws Exception;
}
