package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.MonitorLog;

import core.service.Service;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
public interface MonitorLogService extends Service<MonitorLog> {

	List<MonitorLog> querySensorMonitorLog();

}
