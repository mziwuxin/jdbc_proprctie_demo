package cn.leslie.dao;

import cn.leslie.util.PageUtil;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    /**
     * 新增用户
     *
     *
     */

    int add(T t);

    /**
     * 删除
     * @param t
     * @return
     */
    int delete(Serializable t);

    /**
     * 修改
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 条件查询
     * @param t
     * @return
     */
    T findByCondition(T t);

    /**
     * 查询所有
     * @return
     */
    List<T> findAll();

    /**
     * 查询行数
     * @return
     */
    int fingRownum();

    /**
     * 分页查询
     * @param util
     * @return
     */
    List<T> findAllByPage(PageUtil util);
}
