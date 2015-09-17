package com.forestry.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.ForestryTypeDao;
import com.forestry.model.sys.ForestryType;

import core.dao.BaseDao;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
@Repository
public class ForestryTypeDaoImpl extends BaseDao<ForestryType> implements ForestryTypeDao {

	public ForestryTypeDaoImpl() {
		super(ForestryType.class);
	}

}
