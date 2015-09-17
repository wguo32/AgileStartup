package com.forestry.dao.sys;

import java.util.List;

import com.forestry.model.sys.Authority;

import core.dao.Dao;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
public interface AuthorityDao extends Dao<Authority> {

	List<Authority> queryByParentIdAndRole(Short role);

	List<Authority> queryChildrenByParentIdAndRole(Long parentId, Short role);

}
