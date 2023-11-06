package com.usongon.driverFriend.bean.param;

import lombok.Data;

/**
 * @author zhangdehua
 * @date 2020/4/28
 */
@Data
public class DemandListQueryParams {
    private String issueId;
    private String issueType;
    private String demandStart;
    private String demandDestination;
}
