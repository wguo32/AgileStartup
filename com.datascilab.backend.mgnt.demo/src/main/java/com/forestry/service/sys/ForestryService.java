package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.Forestry;

import core.service.Service;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
public interface ForestryService extends Service<Forestry> {

	List<Forestry> getForestryList(List<Forestry> resultList);

	List<Object[]> queryExportedForestry(Long[] ids);

}
