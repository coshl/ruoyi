package com.ruoyi.system.domain.dto;

import lombok.Data;

/**
 * 支付共债对象 dept_payorder
 *
 * @author ruoyi
 * @date 2023-02-06
 */
@Data
public class PayOrderDto {
    private String bankCode;
    private String amount;
    private String phone;
    private String lastUpdateDate;
    private String name;
    private String merchant;
    private String orderDate;
    private String createDate;
    private String status;
    private Integer id;

    private int monthLoanOrder;
    private int dayhRepayOrder;
    private int monthRepayOrder;
    private int dayLoanOrder;
}
