package com.test.mystruts.manager;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.test.mystruts.exception.MyStrutsException;
import com.test.mystruts.mapping.ActionMapping;
import com.test.mystruts.mapping.ActionResult;

/**
 * �����ļ�������
 * 
 * @author user
 * 
 */
public class ActionMappingManager {

	/**
	 * ActionMapping ����
	 */
	private static Map<String, ActionMapping> actionMappings = new HashMap<String, ActionMapping>();

	/**
	 * ��ȡ�����ļ�����ɳ�ʼ�� ActionMapping ����
	 * 
	 * @param configureFileName
	 * @throws MyStrutsException
	 */
	public void init(String configureFileName) throws MyStrutsException {
		try {
			if (configureFileName == null || configureFileName.isEmpty()) {
				throw new MyStrutsException("mystruts�����ļ�δ�ҵ���");
			}
			InputStream is = this.getClass().getResourceAsStream(
					"/" + configureFileName);
			Document doc = new SAXReader().read(is);
			Element root = doc.getRootElement();
			Iterator<Element> actionsIt = root.elements("actions").iterator();
			Element actions = actionsIt.next();
			for (Iterator<Element> actionIt = actions.elementIterator("action"); actionIt
					.hasNext();) {
				Element action = actionIt.next();
				ActionMapping mapping = new ActionMapping();
				mapping.setName(action.attributeValue("name"));
				mapping.setClassName(action.attributeValue("class"));
				for (Iterator<Element> resultIt = action
						.elementIterator("result"); resultIt.hasNext();) {
					Element resultElement = resultIt.next();
					ActionResult actionResult = new ActionResult();
					String name = resultElement.attributeValue("name");
					if (name == null || "".equals(name)) {
						name = "success";
					}
					actionResult.setName(name);
					String resultType = resultElement
							.attributeValue("resultType");
					actionResult.setResultType(resultType);
					actionResult.setValue(resultElement.getText());
					mapping.addResult(name, actionResult);
				}
				actionMappings.put(mapping.getName(), mapping);
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyStrutsException("mystruts�����ļ�δ�ҵ�:[" + configureFileName
					+ "],����ԭ��:δ����mystruts�����ļ���config�������ô���");
		}
	}

	public ActionMappingManager() {

	}

	public ActionMappingManager(String... configureFileNames) {
		for (String configureFileName : configureFileNames) {
			try {
				init(configureFileName);
			} catch (MyStrutsException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ����actionName��ȡActionMapping����
	 * 
	 * @param actionName
	 * @return ActionMapping
	 * @throws Exception
	 */
	public ActionMapping getActionMapping(String actionName) throws Exception {
		if (actionName == null || actionName.isEmpty()) {
			return null;
		}
		ActionMapping mapping = this.actionMappings.get(actionName);
		if (mapping == null) {
			throw new MyStrutsException("δ�ҵ�nameΪ:[" + actionName
					+ "]��Action������ԭ��:Action���ô������ύ��ַ�������ļ���һ�¡�");
		}
		return mapping;
	}
}
