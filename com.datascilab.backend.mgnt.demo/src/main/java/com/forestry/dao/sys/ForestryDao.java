package com.forestry.dao.sys;

import java.util.List;

import com.forestry.model.sys.Forestry;

import core.dao.Dao;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
public interface ForestryDao extends Dao<Forestry> {

	List<Object[]> queryExportedForestry(Long[] ids);

}
