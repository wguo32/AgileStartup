package com.forestry.controller.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forestry.core.ForestryBaseController;
import com.forestry.model.sys.SensorLastData;
import com.forestry.service.sys.SensorLastDataService;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
@Controller
@RequestMapping("/sys/sensorlastdata")
public class SensorLastDataController extends ForestryBaseController<SensorLastData> {

	@Resource
	private SensorLastDataService sensorLastDataService;

}
