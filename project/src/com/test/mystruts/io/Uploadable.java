package com.test.mystruts.io;

import javax.servlet.http.Part;

/**
 * ֧���ļ��ϴ��Ľӿ�
 * @author hezhao
 *
 */
public interface Uploadable {
	
	/**
	 * �����ļ���
	 * @param filenames �ļ���������
	 */
	public void setFilenames(String[] filenames);
	
	/**
	 * �����ϴ��ĸ���
	 * @param parts ����������
	 */
	public void setParts(Part[] parts);
	
}
