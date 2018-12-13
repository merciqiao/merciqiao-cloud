package com.merciqiao.service.mysql.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.merciqiao.service.mysql.entity.CourseEntity;
import com.merciqiao.service.mysql.mapper.CourseEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MMM on 2018/02/27.
 */
@Service
public class CourseService{
    @Autowired
    CourseEntityMapper courseEntityMapper;
    public PageInfo<CourseEntity> selectPager(Integer pageIndex, Integer pageSize){
        //分页查询
        PageHelper.startPage(2, 3);
        List<CourseEntity> list = courseEntityMapper.selectByExample(null);
        PageInfo<CourseEntity> pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
