package com.active4j.hr.zf.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.active4j.hr.longche.dao.ArticleDao;
import com.active4j.hr.longche.entity.ArticleEntity;
import com.active4j.hr.longche.service.ArticleService;
import com.active4j.hr.zf.dao.DraftTypeDao;
import com.active4j.hr.zf.entity.DraftTypeEntity;
import com.active4j.hr.zf.service.DraftTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("draftTypeService")
@Transactional
public class DraftTypeServiceImpl extends ServiceImpl<DraftTypeDao , DraftTypeEntity > implements DraftTypeService{

}
