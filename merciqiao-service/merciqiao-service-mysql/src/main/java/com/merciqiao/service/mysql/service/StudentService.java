package com.merciqiao.service.mysql.service;



import com.merciqiao.service.mysql.entity.StudentEntity;
import com.merciqiao.service.mysql.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MMM on 2018/01/25.
 */
@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;
    public Integer insert(StudentEntity studentEntity){
        return  studentMapper.insert(studentEntity);
    }
}
