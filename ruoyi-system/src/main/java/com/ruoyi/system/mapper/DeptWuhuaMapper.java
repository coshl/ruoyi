package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DeptWuhua;

import java.util.List;

/**
 * 五花共债Mapper接口
 *
 * @author ruoyi
 * @date 2023-04-06
 */
public interface DeptWuhuaMapper
{
    /**
     * 查询五花共债
     *
     * @param id 五花共债主键
     * @return 五花共债
     */
    public DeptWuhua selectDeptWuhuaById(Long id);

    /**
     * 查询五花共债列表
     *
     * @param deptWuhua 五花共债
     * @return 五花共债集合
     */
    public List<DeptWuhua> selectDeptWuhuaList(DeptWuhua deptWuhua);

    /**
     * 新增五花共债
     *
     * @param deptWuhua 五花共债
     * @return 结果
     */
    public int insertDeptWuhua(DeptWuhua deptWuhua);

    /**
     * 修改五花共债
     *
     * @param deptWuhua 五花共债
     * @return 结果
     */
    public int updateDeptWuhua(DeptWuhua deptWuhua);

    /**
     * 删除五花共债
     *
     * @param id 五花共债主键
     * @return 结果
     */
    public int deleteDeptWuhuaById(Long id);

    /**
     * 批量删除五花共债
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeptWuhuaByIds(String[] ids);
}
