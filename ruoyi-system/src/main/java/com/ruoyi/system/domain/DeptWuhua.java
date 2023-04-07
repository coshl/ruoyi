package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 五花共债对象 dept_wuhua
 *
 * @author ruoyi
 * @date 2023-04-06
 */
@Data
public class DeptWuhua extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "账号id")
    private Long userId;

    /** 共债姓名 */
    @Excel(name = "共债姓名")
    private String deptName;

    /** 共债姓名 */
    @Excel(name = "共债手机号")
    private String deptMobile;

    /** 共债姓名 */
    @Excel(name = "共债身份证号")
    private String deptCard;

    /** 1-成功;0-失败 */
    @Excel(name = "1-成功;0-失败")
    private Long status;

    /** 错误原因 */
    @Excel(name = "错误原因")
    private String failCause;

    /** 共债报告内容 */
    @Excel(name = "共债报告内容")
    private String report;


}
