package com.merciqiao.service.mongo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by samli on 2017/8/24.
 */
@Setter
@Getter
public class ThirdPartyQueryHistory {

    @Id
    private String id;

    private String interfaceId;

    private String interfaceName;

    private String url;

    private Date requestTime;

    private String param;

    private Date responseTime;

    private String returnCode;

    private String returnState;

    private String message;

    private String customerId;
    /**
     * 第三方返回结果
     */
    private String result;
    /**
     * 第三方查询状态,成功与否
     */
    private boolean state;
    //商户号
    private String secretId;
    //商户名
    private String secretName;
}
