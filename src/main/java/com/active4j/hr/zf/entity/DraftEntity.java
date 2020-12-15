package com.active4j.hr.zf.entity;

import java.util.Date;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@TableName("draft")
@Getter
@Setter
public class DraftEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3440862564325838838L;

	/**
	 * 状态 （0 删除  正常）
	 */
	@TableField("state")
	private int state;
	
	@TableField("title")
	@QueryField(queryColumn="title", condition=QueryCondition.like)
	private String title;
	
	@TableField("sort")
	private int sort;
	
	@TableField("draft_url")
	private String draftUrl;
	
	@TableField("task_type")
	@QueryField(queryColumn="task_type", condition=QueryCondition.eq)
	private String taskType;
	
	@TableField("grade")
	@QueryField(queryColumn="grade", condition=QueryCondition.eq)
	private String grade;

	@TableField("remark")
	private String remark;
	
	@TableField(value="issue_date")
	@QueryField(queryColumn="issue_date", condition=QueryCondition.range)
	private Date issueDate;
	
	@TableField("type_id")
	@QueryField(queryColumn="type_id", condition=QueryCondition.eq)
	private String typeId;
	
	@TableField("create_nickname")
	@QueryField(queryColumn="create_nickname", condition=QueryCondition.like)
	private String createNickname;
	
	@TableField("file_path")
	private String filePath;
	

}
