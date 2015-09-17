package com.forestry.dao.sys;

import java.util.List;

import com.forestry.model.sys.Attachment;

import core.dao.Dao;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
public interface AttachmentDao extends Dao<Attachment> {

	List<Object[]> queryFlowerList(String epcId);

	void deleteAttachmentByForestryTypeId(Long forestryTypeId);

}
