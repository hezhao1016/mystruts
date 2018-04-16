package com.test.mystruts.manager;

import com.test.mystruts.Action;

/**
 * Action������
 * 
 * @author user
 * 
 */
public class ActionManager {
	/**
	 * createAction�������ڻ��Actionʵ��
	 * 
	 * @param className
	 *            Action��ȫ�޶�����
	 * @return Action
	 */
	public static Action createAction(String className) {
		try {
			return (Action) loadClass(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ���÷�������
	 * 
	 * @param className
	 * @return Class<?>
	 * @throws ClassNotFoundException
	 */
	public static Class<?> loadClass(String className)
			throws ClassNotFoundException {
		Class<?> clazz = null;
		clazz = Class.forName(className);
		return clazz;
	}
}
