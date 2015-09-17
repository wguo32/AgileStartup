package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.Attachment;

import core.service.Service;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
public interface AttachmentService extends Service<Attachment> {

	List<Object[]> queryFlowerList(String epcId);

	void deleteAttachmentByForestryTypeId(Long forestryTypeId);

}
