package com.test.mystruts.manager;

import com.test.mystruts.Action;

/**
 * Action管理类
 * 
 * @author user
 * 
 */
public class ActionManager {
	/**
	 * createAction方法用于获得Action实例
	 * 
	 * @param className
	 *            Action的全限定类名
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
	 * 利用反射获得类
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
