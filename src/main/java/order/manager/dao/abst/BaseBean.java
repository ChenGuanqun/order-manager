/*
 * @(#) BaseBean.java 2016年8月6日
 * 
 * Copyright 2010 NetEase.com, Inc. All rights reserved.
 */
package order.manager.dao.abst;

import lombok.Data;

@Data
public class BaseBean{
    private String creator;
    private Long insertTime;
    private String lastOperator;
    private Long lastModifyTime;
    private String description;
}
