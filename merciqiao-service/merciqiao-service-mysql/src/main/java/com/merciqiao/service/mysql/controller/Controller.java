package com.merciqiao.service.mysql.controller;


import com.github.pagehelper.PageInfo;
import com.merciqiao.service.mysql.entity.CourseEntity;
import com.merciqiao.service.mysql.entity.StudentEntity;
import com.merciqiao.service.mysql.service.CourseService;
import com.merciqiao.service.mysql.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by MMM on 2018/01/29.
 * 反欺诈rest接口
 */
@RestController
@RequestMapping(value="/merciqiao-mysql-api")
@Slf4j
public class Controller {
    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;

    @ApiOperation(value="mybatis插入学生表",notes="返回结果,SUCCESS:100,FAILED:200",httpMethod = "POST")
    @RequestMapping(value = "/insertStudent",method = RequestMethod.POST)
    public boolean insertStudent(@RequestBody StudentEntity studentEntity) throws Exception {

        try {

            studentEntity.setName(studentEntity.getName()+"mybatis");
            studentService.insert(studentEntity);

            return true;
        }
        catch (Exception ex){
            log.error(ex.getMessage(),ex);
            return false;
        }

    }
    @ApiOperation(value="mybatis分页查询课程表",notes="返回结果,SUCCESS:100,FAILED:200",httpMethod = "POST")
    @RequestMapping(value = "/selectCourcePager",method = RequestMethod.POST)
    public List<CourseEntity> selectCourcePager(@RequestParam Integer pageIndex,@RequestParam Integer pageSize) throws Exception {
        List<CourseEntity> result = new ArrayList<>();
        try {

            PageInfo<CourseEntity> pageInfo= courseService.selectPager(pageIndex,pageSize);
            result=pageInfo.getList();
            return result;
        }
        catch (Exception ex){
            log.error(ex.getMessage(),ex);

            return result;
        }

    }
}
