package com.example.demo.controller;

import com.example.demo.entity.Orders;
import com.example.demo.service.OrdersService;
import com.example.demo.util.ResponesData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Orders)表控制层
 *
 * @author makejava
 * @since 2022-04-05 20:24:36
 */
@RestController
@RequestMapping("orders")
public class OrdersController {
    /**
     * 服务对象
     */
    @Resource
    private OrdersService ordersService;

    /**
     * 分页查询
     *
     * @param orders 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Orders>> queryByPage(Orders orders, PageRequest pageRequest) {
        return ResponseEntity.ok(this.ordersService.queryByPage(orders, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Orders> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.ordersService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param orders 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Orders> add(Orders orders) {
        return ResponseEntity.ok(this.ordersService.insert(orders));
    }

    /**
     * 编辑数据
     *
     * @param orders 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Orders> edit(Orders orders) {
        return ResponseEntity.ok(this.ordersService.update(orders));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.ordersService.deleteById(id));
    }

    @GetMapping("/getOrdersByOpenid")
    public ResponesData getOrdersByOpenid(String openid){
        return ordersService.getOrdersByOpenid(openid);
    }

    @GetMapping("/getOrdersByState")
    public ResponesData getOrdersByState(String token,Integer orderstate){
        System.out.println(token+orderstate);
        return ordersService.getOrdersByState(token,orderstate);
    }

    @PostMapping("/createOrders")
    public ResponesData createOrders(Orders orders,String token){
        return ordersService.createOrders(orders,token);
    }
}

