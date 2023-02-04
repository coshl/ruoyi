package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公债充值对象 dept_rechange
 * 
 * @author ruoyi
 * @date 2023-02-04
 */
public class DeptRechange extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 商户id */
    @Excel(name = "商户id")
    private Long userId;

    /** 充值人姓名 */
    @Excel(name = "充值人姓名")
    private String rechangeName;

    /** 充值金额 */
    @Excel(name = "充值金额")
    private BigDecimal rechangeAmount;

    /** 充值时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "充值时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rechangeDate;

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
    public void setRechangeName(String rechangeName) 
    {
        this.rechangeName = rechangeName;
    }

    public String getRechangeName() 
    {
        return rechangeName;
    }
    public void setRechangeAmount(BigDecimal rechangeAmount) 
    {
        this.rechangeAmount = rechangeAmount;
    }

    public BigDecimal getRechangeAmount() 
    {
        return rechangeAmount;
    }
    public void setRechangeDate(Date rechangeDate) 
    {
        this.rechangeDate = rechangeDate;
    }

    public Date getRechangeDate() 
    {
        return rechangeDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("rechangeName", getRechangeName())
            .append("rechangeAmount", getRechangeAmount())
            .append("rechangeDate", getRechangeDate())
            .toString();
    }
}
