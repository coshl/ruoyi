package com.ruoyi.system.domain.dto.wuhua;

import lombok.Data;

/**
 * 支付共债对象 dept_payorder
 *
 * @author ruoyi
 * @date 2023-02-06
 */
@Data
public class RadarData {
    private String  mobile;
    private String  name;
    private int  loanCount;
    private int  loanSucCount;
    private int  loanPrCount;
    private int  loanSucPrCount;
    private int  repSucPrCount;
    private int  ovePrCount;
    private int  curOrderCount;
    private int  curWAuthCount;
    private int  curRefCount;
    private int  curOveCount;
    private int  monOveCount;
    private int  totalOveRepCount;
    private int  todayLoanCount;
    private int  todayRefCount;
    private int  todayLoanSucCount;
    private int  todayRepSucCount;
    private int  todayOveCount;

}
