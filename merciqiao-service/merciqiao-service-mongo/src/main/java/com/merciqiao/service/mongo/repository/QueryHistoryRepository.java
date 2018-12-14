package com.merciqiao.service.mongo.repository;

import com.merciqiao.service.mongo.entity.QueryHistory;
import com.mongodb.WriteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * MongoTemplate做简单操作
 */
@Repository
public class QueryHistoryRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    /**
     *按条件和时间范围查询
     * @param status
     * @param createTime
     * @return
     * @throws Exception
     */
    public List<QueryHistory> findAllByStatusAndCreateTimeLt(String status, Date createTime) throws Exception {
        try {
            Query query = new Query(Criteria.where("Status").is(status).and("create_time").lt(createTime));
            List<QueryHistory> result = mongoTemplate.find(query, QueryHistory.class);

            return result;
        } catch (Exception ex) {
            String str = ex.getMessage();
            return null;
        }
    }

    /**
     * 按条件更新数据
     * @param globalId
     * @param status
     * @param newStatus
     * @return
     */
    public long updateProcessingStatus(String globalId,String status,String newStatus) {
        Query query = new Query(Criteria.where("global_id").is(globalId).and("Status").is(status));
        Update update = new Update();
        update.set("Status", newStatus);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, QueryHistory.class);
        return updateResult.getModifiedCount();
    }
}
