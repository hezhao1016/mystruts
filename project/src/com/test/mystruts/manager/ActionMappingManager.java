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
 * 配置文件管理类
 * 
 * @author user
 * 
 */
public class ActionMappingManager {

	/**
	 * ActionMapping 集合
	 */
	private static Map<String, ActionMapping> actionMappings = new HashMap<String, ActionMapping>();

	/**
	 * 读取配置文件，完成初始化 ActionMapping 集合
	 * 
	 * @param configureFileName
	 * @throws MyStrutsException
	 */
	public void init(String configureFileName) throws MyStrutsException {
		try {
			if (configureFileName == null || configureFileName.isEmpty()) {
				throw new MyStrutsException("mystruts配置文件未找到。");
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
			throw new MyStrutsException("mystruts配置文件未找到:[" + configureFileName
					+ "],可能原因:未配置mystruts配置文件或config属性配置错误。");
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
	 * 根据actionName获取ActionMapping对象
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
			throw new MyStrutsException("未找到name为:[" + actionName
					+ "]的Action，可能原因:Action配置错误或表单提交地址与配置文件不一致。");
		}
		return mapping;
	}
}
