package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.Sensor;

import core.service.Service;
import core.support.QueryResult;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
public interface SensorService extends Service<Sensor> {

	List<Sensor> querySensorBySensorType(Short sensorType);

	List<Sensor> querySensorLastData();

	QueryResult<Sensor> querySensorList(Sensor sensor);

	List<Sensor> querySensorLastDataWithEpcId();

	List<Sensor> queryForestrySensorLastData();

}
