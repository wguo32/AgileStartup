package com.forestry.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forestry.dao.sys.SensorDao;
import com.forestry.model.sys.Sensor;
import com.forestry.service.sys.SensorService;

import core.service.BaseService;
import core.support.QueryResult;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
@Service
public class SensorServiceImpl extends BaseService<Sensor> implements SensorService {

	private SensorDao sensorDao;

	@Resource
	public void setSensorDao(SensorDao sensorDao) {
		this.sensorDao = sensorDao;
		this.dao = sensorDao;
	}

	@Override
	public List<Sensor> querySensorBySensorType(Short sensorType) {
		return sensorDao.querySensorBySensorType(sensorType);
	}

	@Override
	public List<Sensor> querySensorLastData() {
		return sensorDao.querySensorLastData();
	}

	@Override
	public QueryResult<Sensor> querySensorList(Sensor sensor) {
		return sensorDao.querySensorList(sensor);
	}

	@Override
	public List<Sensor> querySensorLastDataWithEpcId() {
		return sensorDao.querySensorLastDataWithEpcId();
	}

	@Override
	public List<Sensor> queryForestrySensorLastData() {
		return sensorDao.queryForestrySensorLastData();
	}

}
