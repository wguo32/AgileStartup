package com.forestry.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;

import com.forestry.dao.sys.SysUserDao;
import com.forestry.model.sys.SysUser;
import com.forestry.service.sys.SysUserService;

/**
 * 
 * 
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class SSHTest {

	@Resource
	private SysUserDao sysUserDao;

	@Resource
	private SysUserService sysUserService;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public final void testSave() {
		// System.out.println("sysUserService:::" + sysUserService);
		SysUser sysUser = sysUserService.getByProerties("userName", "admin");
	}

}
