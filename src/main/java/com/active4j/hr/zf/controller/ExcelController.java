package com.active4j.hr.zf.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.active4j.hr.core.shiro.ShiroUtils;
import com.active4j.hr.system.model.ActiveUser;
import com.active4j.hr.zf.entity.DraftEntity;
import com.active4j.hr.zf.entity.DraftTypeEntity;
import com.active4j.hr.zf.service.DraftService;
import com.active4j.hr.zf.service.DraftTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/excel")
public class ExcelController {
	
	@Autowired
	private DraftTypeService draftTypeService;
	@Autowired
	private DraftService draftService;
	
	@RequestMapping(value = "/export2003", method = RequestMethod.GET)
	public void export2003(HttpServletRequest request, HttpServletResponse response,String createNickname) {
		
		QueryWrapper<DraftEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("create_nickname", createNickname);
		List<DraftEntity> list = new ArrayList<DraftEntity>();
		list = draftService.list(queryWrapper);
		
		List<DraftTypeEntity> draftTypeEntities = draftTypeService.list();
		
		
		HSSFWorkbook wb = null;
		try {
			// excel模板路径
			String basePath = request.getSession().getServletContext().getRealPath("/");
			String excel = basePath + "excel\\model.xls";
			File fi = new File(excel);
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
			// 读取excel模板
			wb = new HSSFWorkbook(fs);
			// 读取了模板内所有sheet内容
			HSSFSheet sheet = wb.getSheetAt(0);
			// 在相应的单元格进行赋值
			int rowIndex = 2;
			for (DraftEntity draftEntity : list) {
				if(rowIndex ==2 && !createNickname.isEmpty()) {
					HSSFRow row0 = sheet.getRow(0);
					HSSFCell cell01 = row0.getCell(1);
					if (null == cell01) {
						cell01 = row0.createCell(1);
					}
					cell01.setCellValue(list.get(0).getCreateNickname());
				}
				HSSFRow row = sheet.getRow(rowIndex);
				if (null == row) {
					row = sheet.createRow(rowIndex);
				}
				HSSFCell cell0 = row.getCell(0);
				if (null == cell0) {
					cell0 = row.createCell(0);
				}
				for (int i = 0; i < draftTypeEntities.size(); i++) {
					if(draftTypeEntities.get(i).getId().equals(draftEntity.getTypeId())) {
						cell0.setCellValue(draftTypeEntities.get(i).getName());
						break;
					}
					
				}
				
				HSSFCell cell1 = row.getCell(1);
				if (null == cell1) {
					cell1 = row.createCell(1);
				}
				cell1.setCellValue(draftEntity.getTitle());
 
				HSSFCell cell2 = row.getCell(2);
				if (null == cell2) {
					cell2 = row.createCell(2);
				}
				cell2.setCellValue(draftEntity.getDraftUrl());
 
				HSSFCell cell3 = row.getCell(3);
				if (null == cell3) {
					cell3 = row.createCell(3);
				}
				if(draftEntity.getTaskType().equals("1")) {
					cell3.setCellValue("自主报题");
				}else if(draftEntity.getTaskType().equals("2")) {
					cell3.setCellValue("指派任务");
				}
				HSSFCell cell4 = row.getCell(4);
				if (null == cell4) {
					cell4 = row.createCell(4);
				}
				cell4.setCellValue(draftEntity.getGrade());
				HSSFCell cell5 = row.getCell(5);
				if (null == cell5) {
					cell4 = row.createCell(5);
				}
				cell4.setCellValue(draftEntity.getRemark());
				rowIndex++;
			}
 
			String fileName = "稿件统计";
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			ServletOutputStream sout = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
 
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(sout);
				byte[] buff = new byte[2048];
				int bytesRead;
				// Simple read/write loop.
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (Exception e) {
				log.error("导出excel出现异常:" + e.getMessage());
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
 
		} catch (Exception e) {
			log.error("导出excel出现异常:" + e.getMessage());
		}
 
	
	}

}
