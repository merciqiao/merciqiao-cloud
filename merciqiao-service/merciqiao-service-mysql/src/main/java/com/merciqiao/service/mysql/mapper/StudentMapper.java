package com.merciqiao.service.mysql.mapper;


import com.merciqiao.service.mysql.entity.StudentEntity;
import org.springframework.stereotype.Component;

/**
 * Created by MMM on 2018/02/11.
 */
@Component
public interface StudentMapper {
    int insert(StudentEntity studentEntity);
}
