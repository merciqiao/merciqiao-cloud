package com.merciqiao.service.mongo.service;

import com.merciqiao.service.mongo.entity.QueryResult;
import com.merciqiao.service.mongo.repository.QueryResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoService {
    @Autowired
    QueryResultRepository queryResultRepository;
    public QueryResult findFirstByInterfaceId(String interfaceId){
        return queryResultRepository.findFirstByInterfaceId(interfaceId);
    }
}
