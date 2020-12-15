package com.active4j.hr.longche.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.longche.entity.MaintenanceEntity;
import com.active4j.hr.longche.service.MaintenanceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/rest/maintenance")
public class RestMaintenanceController2 {
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson list() {
		//拼接查询条件
		QueryWrapper<MaintenanceEntity> queryWrapper = new QueryWrapper<MaintenanceEntity>();
		queryWrapper.eq("state", 1);
		//执行查询
		List<MaintenanceEntity> lstResult = maintenanceService.list(queryWrapper);
		//输出结果
		AjaxJson j = new AjaxJson();
		j.setObj(lstResult);
		j.setSuccess(true);
		j.setMsg("返回数据");
		return j;
	}
}
