package com.merciqiao.service.mongo.controller;

import com.merciqiao.service.mongo.entity.QueryResult;
import com.merciqiao.service.mongo.service.MongoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MMM on 2018/01/29.
 * 反欺诈rest接口
 */
@RestController
@RequestMapping(value="/merciqiao-mongo-api")
@Slf4j
@Api(description = "整合mongo接口")
public class Controller {
    @Autowired
    MongoService mongoService;


    @ApiOperation(value="mongo查询接口",notes="返回结果,SUCCESS:100,FAILED:200",httpMethod = "POST")
    @RequestMapping(value = "/findFirstByInterfaceId",method = RequestMethod.POST)
    public QueryResult findFirstByInterfaceId(@RequestParam String interfaceId) throws Exception {
        QueryResult result = new QueryResult();
        try {
            result= mongoService.findFirstByInterfaceId(interfaceId);

            return result;
        }
        catch (Exception ex){
            log.error(ex.getMessage(),ex);

            return result;
        }

    }
}
