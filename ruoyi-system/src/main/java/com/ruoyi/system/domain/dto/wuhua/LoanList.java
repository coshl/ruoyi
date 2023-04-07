package com.ruoyi.system.domain.dto.wuhua;

import lombok.Data;

/**
 * 支付共债对象 dept_payorder
 *
 * @author ruoyi
 * @date 2023-02-06
 */
@Data
public class LoanList {
    private String mobile;
    private String name;
    private String companyName;
    private String amount;
    private String createTime;
    private String statusLabel;
}
