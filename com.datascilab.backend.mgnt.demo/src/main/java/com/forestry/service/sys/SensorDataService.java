package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.SensorData;

import core.service.Service;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
public interface SensorDataService extends Service<SensorData> {

	List<Object[]> doGetSensorDataStatistics(Short sensorType);

	List<Object[]> doGetEnhanceSensorDataStatistics(List<Object[]> list);

}
