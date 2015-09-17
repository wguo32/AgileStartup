package com.forestry.model.sys.param;

import core.extjs.ExtJSBaseParameter;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
public class ConfigParameter extends ExtJSBaseParameter {

	private static final long serialVersionUID = -2197040433315922797L;
	private String configTypeName;

	public String getConfigTypeName() {
		return configTypeName;
	}

	public void setConfigTypeName(String configTypeName) {
		this.configTypeName = configTypeName;
	}

}
