package com.active4j.hr.zf.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

@TableName("draft_type")
@Getter
@Setter
public class DraftTypeEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3440862564325838838L;

	/**
	 * 状态 （0 删除  正常）
	 */
	@TableField("state")
	private int state;
	
	@TableField("name")
	private String name;
	
	@TableField("sort")
	private int sort;

}
