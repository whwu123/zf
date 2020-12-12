package com.active4j.hr.zf.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.active4j.hr.zf.dao.DraftDao;
import com.active4j.hr.zf.entity.DraftEntity;
import com.active4j.hr.zf.service.DraftService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("draftService")
@Transactional
public class DraftServiceImpl extends ServiceImpl<DraftDao , DraftEntity > implements DraftService{

}
