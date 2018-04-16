# mystruts
自定义struts框架 - 2015-6-26 23:45  HeZhao

### 所有类及说明

- com.test.mystruts.Action --------- 所有实现Action接口的类作为一个子控制器
- com.test.mystruts.ActionFilter --------- 总控制器
- com.test.mystruts.io.Uploadable --------- 上传文件的接口
- com.test.mystruts.manager.ActionManager --------- 创建Action对象
- com.test.mystruts.manager.ActionMappingManager --------- 解析配置文件，初始化ActionMapping集合
- com.test.mystruts.mapping.ActionMapping --------- 封装Action信息
- com.test.mystruts.mapping.ActionResult --------- 封装Result信息
- com.test.mystruts.mapping.ResultType --------- 枚举，Result返回类型
- com.test.mystruts.util.CommonUtil --------- 通用工具类
- com.test.mystruts.util.TypeConverter --------- 类型转换接口
- com.test.mystruts.exception.MyStrutsException --------- 异常类
