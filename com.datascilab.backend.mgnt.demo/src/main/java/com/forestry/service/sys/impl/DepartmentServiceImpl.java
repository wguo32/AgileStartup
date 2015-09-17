package com.forestry.service.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forestry.dao.sys.DepartmentDao;
import com.forestry.model.sys.Department;
import com.forestry.service.sys.DepartmentService;

import core.service.BaseService;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
@Service
public class DepartmentServiceImpl extends BaseService<Department> implements DepartmentService {

	private DepartmentDao departmentDao;

	@Resource
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
		this.dao = departmentDao;
	}

}
