package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 支付共债对象 dept_payorder
 * 
 * @author ruoyi
 * @date 2023-02-06
 */
public class DeptPayorder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** 共债姓名 */
    @Excel(name = "共债姓名")
    private String deptName;

    /** 1-成功;0-失败 */
    @Excel(name = "1-成功;0-失败")
    private Long status;

    /** 错误原因 */
    @Excel(name = "错误原因")
    private String failCause;

    /** 共债报告内容 */
    @Excel(name = "共债报告内容")
    private String report;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setFailCause(String failCause) 
    {
        this.failCause = failCause;
    }

    public String getFailCause() 
    {
        return failCause;
    }
    public void setReport(String report) 
    {
        this.report = report;
    }

    public String getReport() 
    {
        return report;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("deptName", getDeptName())
            .append("status", getStatus())
            .append("failCause", getFailCause())
            .append("report", getReport())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
