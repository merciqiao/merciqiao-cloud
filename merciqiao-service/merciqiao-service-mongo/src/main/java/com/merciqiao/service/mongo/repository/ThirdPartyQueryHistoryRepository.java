package com.merciqiao.service.mongo.repository;

import com.merciqiao.service.mongo.entity.ThirdPartyQueryHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
@Repository
public class ThirdPartyQueryHistoryRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    /**
     * 第三方调用表,统计接口查询次数
     * 简单统计
     * @return
     * @throws Exception
     */
    public Map<String, Long> getThirdPartyQueryCount() throws Exception {
        List<Map> mapList = null;
        Map<String, Long> mapResult = new HashMap();
        TypedAggregation<ThirdPartyQueryHistory> aggregation =
                newAggregation(ThirdPartyQueryHistory.class,
                        group(
                                "interfaceId", "interfaceName"
                        ).count().as("count")

                );
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, Map.class);
        if (results != null && results.getMappedResults() != null) {
            mapList = results.getMappedResults();
        }
        for (Map map : mapList) {
            if (!mapResult.containsKey(map.get("interfaceId"))) {
                mapResult.put(map.getOrDefault("interfaceId", "").toString(),
                        Long.parseLong(map.getOrDefault("count", 0).toString()));
            }

        }
        return mapResult;

    }
    /**
     * 根据时间区间和接口id集合查询第三方接口统计信息
     * 带多查询条件统计
     * @param beginTime
     * @param endTime
     * @param interfaceIdList
     * @return
     * @throws Exception
     */
    public Map<String, Long> getThirdPartyQueryCountByTimeAndInterfaceId(Date beginTime, Date endTime, List<String> interfaceIdList) throws Exception {
        List<Map> mapList = null;
        Map<String, Long> mapResult = new HashMap();
        //条件集合
        List<AggregationOperation> aggregationOperations = new ArrayList<AggregationOperation>();
        //条件--时间段--begin
        Criteria requestTime = Criteria.where("requestTime");
        if (beginTime != null) {
            requestTime.gte(beginTime);
        }
        if (endTime != null) {
            requestTime.lte(endTime);
        }
        if (null != beginTime || null != endTime) {
            aggregationOperations.add(Aggregation.match(requestTime));
        }
        //条件--时间段---end
        //条件--接口id---start
        Criteria paramInterfaceIds=Criteria.where("interfaceId");
        if(interfaceIdList!=null&&interfaceIdList.size()>0){
            paramInterfaceIds.in(interfaceIdList);
            aggregationOperations.add(Aggregation.match(paramInterfaceIds));
        }
        //条件--接口id---end

        GroupOperation groupOperation = Aggregation.group("interfaceId", "interfaceName").count().as("count");
        aggregationOperations.add(groupOperation);
        Aggregation aggregation = Aggregation.newAggregation(ThirdPartyQueryHistory.class, aggregationOperations);
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, ThirdPartyQueryHistory.class, Map.class);
        if (results != null && results.getMappedResults() != null) {
            mapList = results.getMappedResults();
        }
        for (Map map : mapList) {
            if (!mapResult.containsKey(map.get("interfaceId"))) {
                mapResult.put(map.getOrDefault("interfaceId", "").toString(),
                        Long.parseLong(map.getOrDefault("count", 0).toString()));
            }

        }
        return mapResult;
    }
}
