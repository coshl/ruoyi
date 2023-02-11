package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DeptRechange;
import org.apache.ibatis.annotations.Param;

/**
 * 公债充值Mapper接口
 *
 * @author ruoyi
 * @date 2023-02-04
 */
public interface DeptRechangeMapper
{

    /**
     * 查询计费
     */
    Long selectDeptBilling(Long id);

    /**
     * 查询计费
     */
    void insertSysUserBilling(@Param("id") Long id, @Param("price") Long price);

    /**
     * 查询公债充值
     *
     * @param id 公债充值主键
     * @return 公债充值
     */
    public DeptRechange selectDeptRechangeById(Long id);

    /**
     * 查询公债充值列表
     *
     * @param deptRechange 公债充值
     * @return 公债充值集合
     */
    public List<DeptRechange> selectDeptRechangeList(DeptRechange deptRechange);

    /**
     * 新增公债充值
     *
     * @param deptRechange 公债充值
     * @return 结果
     */
    public int insertDeptRechange(DeptRechange deptRechange);

    /**
     * 修改公债充值
     *
     * @param deptRechange 公债充值
     * @return 结果
     */
    public int updateDeptRechange(DeptRechange deptRechange);

    /**
     * 删除公债充值
     *
     * @param id 公债充值主键
     * @return 结果
     */
    public int deleteDeptRechangeById(Long id);

    /**
     * 批量删除公债充值
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeptRechangeByIds(String[] ids);
}
