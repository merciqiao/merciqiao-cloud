package com.merciqiao.service.mongo.repository;

import com.merciqiao.service.mongo.entity.QueryResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * MongoRepository<xxx,yyy> xxx代表要操作的实体类，yyy代表实体类的主键类型
 * jpa方式查询数据
 */
@Repository
public interface QueryResultRepository extends MongoRepository<QueryResult,String>{
    QueryResult findFirstByInterfaceId(String interfaceId);

    /**
     * 按时间降序查询
     * @param customerId
     * @param interfaceId
     * @return
     */
    QueryResult findFirstByCustomerIdAndAndInterfaceIdOrderByCreateTimeDesc(String customerId,String interfaceId);

    /**
     * 时间区间查询集合
     * @param beginTime
     * @param endTime
     * @return
     */
    List<QueryResult> findAllByCreateTimeBetween(Date beginTime, Date endTime);
    /**
     * 时间降序查询queryresult,时间区间,取第一
     * @param customerId
     * @param interfaceId
     * @param beginTime
     * @param endTime
     * @return
     */
    QueryResult findFirstByCustomerIdAndInterfaceIdAndCreateTimeBetweenOrderByCreateTimeDesc(String customerId,String interfaceId,Date beginTime, Date endTime);

    /**
     * 基于mongodb原本查询语句的查询
     * @param name
     * @param ageFrom
     * @param ageTo
     * @param page
     * @return
     */
    @Query(value="{\"username\":{\"$regex\":?0},\"age\":{\"$gte\":?1,\"$lte\": ?2}}",fields="{\"username\" : 1, \"age\" : 1}")
    Page<QueryResult> findByNameAndAgeRange3(String name, int ageFrom, int ageTo, Pageable page);
}
