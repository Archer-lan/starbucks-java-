package com.example.demo.service;

import com.example.demo.entity.Orders;
import com.example.demo.util.ResponesData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Orders)表服务接口
 *
 * @author makejava
 * @since 2022-04-05 20:24:36
 */
public interface OrdersService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Orders queryById(Integer id);

    /**
     * 分页查询
     *
     * @param orders 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Orders> queryByPage(Orders orders, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    Orders insert(Orders orders);

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    Orders update(Orders orders);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    ResponesData getOrdersByOpenid(String openid);

    ResponesData createOrders(Orders orders, String token);

    ResponesData getOrdersByState(String token, Integer orderstate);

//    ResponesData getOrderByState(String token, Integer orderstate);

//    ResponesData getOrderByState(String token, Integer orderstate);

}
