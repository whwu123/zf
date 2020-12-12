package com.active4j.hr.zf.controller;


import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.active4j.hr.base.controller.BaseController;
import com.active4j.hr.core.annotation.Log;
import com.active4j.hr.core.beanutil.MyBeanUtils;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.core.model.LogType;
import com.active4j.hr.core.query.QueryUtils;
import com.active4j.hr.core.shiro.ShiroUtils;
import com.active4j.hr.core.util.ResponseUtil;
import com.active4j.hr.core.web.tag.model.DataGrid;
import com.active4j.hr.longche.entity.ArticleEntity;
import com.active4j.hr.longche.service.ArticleService;
import com.active4j.hr.system.model.ActiveUser;
import com.active4j.hr.zf.entity.DraftTypeEntity;
import com.active4j.hr.zf.service.DraftTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/draftrType")
public class DraftTypeController extends BaseController{
	
	@Autowired
	private DraftTypeService draftTypeService;
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		
		return "zf/draftrType/list";
	}

	/**
	 * 
	 * @description
	 *  	表格数据显示
	 * @return void
	 * @author 麻木神
	 * @time 2020年1月25日 下午9:46:12
	 */
	@RequestMapping("/datagrid")
	public void datagrid(DraftTypeEntity draftTypeEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//拼接查询条件
		QueryWrapper<DraftTypeEntity> queryWrapper = QueryUtils.installQueryWrapper(draftTypeEntity, request.getParameterMap(), dataGrid);
		
		//执行查询
		IPage<DraftTypeEntity> lstResult = draftTypeService.page(new Page<DraftTypeEntity>(dataGrid.getPage(), dataGrid.getRows()), queryWrapper);
		
		//输出结果
		ResponseUtil.writeJson(response, dataGrid, lstResult);
		
	}
	
	@RequestMapping("/addorupdate")
	public ModelAndView addorupdate(DraftTypeEntity draftTypeEntity, HttpServletRequest req) {
		ModelAndView view = new ModelAndView("zf/draftrType/add");
		if(StringUtils.isEmpty(draftTypeEntity.getId())) {
			//新增
			draftTypeEntity = new DraftTypeEntity();
			view.addObject("draftTypeEntity", draftTypeEntity);
		}else {
			draftTypeEntity = draftTypeService.getById(draftTypeEntity.getId());
			view.addObject("draftTypeEntity", draftTypeEntity);
		}
		return view;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	@Log(type = LogType.save, name = "保存稿件类型信息", memo = "新增或编辑稿件类型信息")
	public AjaxJson saveSales(HttpServletRequest req, DraftTypeEntity draftTypeEntity) {
		AjaxJson j = new AjaxJson();
		try {
			
			if(StringUtils.isEmpty(draftTypeEntity.getId())) {
				//新增保存
				//状态
				//salesReturnEntity.setVersions(1);
				draftTypeEntity.setCreateDate(new Date());
				draftTypeEntity.setUpdateDate(new Date());
				draftTypeEntity.setState(1);
				draftTypeService.save(draftTypeEntity);
			}else {
				//编辑保存
				DraftTypeEntity tmp = draftTypeService.getById(draftTypeEntity.getId());
				tmp.setUpdateDate(new Date());
				MyBeanUtils.copyBeanNotNull2Bean(draftTypeEntity, tmp);
				draftTypeService.saveOrUpdate(tmp);
			}
			
		}catch(Exception e) {
			log.error("保存稿件类型信息报错，错误信息:" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存稿件类型错误");
			e.printStackTrace();
		}
		
		return j;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	@Log(type = LogType.del, name = "删除稿件类型信息", memo = "删除了稿件类型信息")
	public AjaxJson del(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isEmpty(id)) {
				j.setSuccess(false);
				j.setMsg("请选择需要删除的稿件类型");
				return j;
			}
			ActiveUser user = ShiroUtils.getSessionUser();
			//删除用户
			draftTypeService.removeById(id);
			log.info("用户：" + user.getUserName() + "删除了id为：" + id + "的稿件类型信息");
		}catch(Exception e) {
			log.error("删除稿件类型信息报错，错误信息：{}", e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除稿件类型信息错误");
			e.printStackTrace();
		}
		return j;
	}
}
