package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.Config;

import core.service.Service;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
public interface ConfigService extends Service<Config> {

	List<Config> getConfigList(List<Config> resultList);

}
