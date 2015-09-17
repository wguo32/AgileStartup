package com.forestry.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.ConfigDao;
import com.forestry.model.sys.Config;

import core.dao.BaseDao;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
@Repository
public class ConfigDaoImpl extends BaseDao<Config> implements ConfigDao {

	public ConfigDaoImpl() {
		super(Config.class);
	}

}
