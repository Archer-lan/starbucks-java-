package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.Orders;
import com.example.demo.entity.User;
import com.example.demo.dao.OrdersDao;
import com.example.demo.service.OrdersService;
import com.example.demo.util.ResponesCode;
import com.example.demo.util.ResponesData;
import com.example.demo.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Orders)表服务实现类
 *
 * @author makejava
 * @since 2022-04-05 20:24:36
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersDao ordersDao;
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Orders queryById(Integer id) {
        return this.ordersDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param orders 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Orders> queryByPage(Orders orders, PageRequest pageRequest) {
        long total = this.ordersDao.count(orders);
        return new PageImpl<>(this.ordersDao.queryAllByLimit(orders, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Override
    public Orders insert(Orders orders) {
        this.ordersDao.insert(orders);
        return orders;
    }

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Override
    public Orders update(Orders orders) {
        this.ordersDao.update(orders);
        return this.queryById(orders.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.ordersDao.deleteById(id) > 0;
    }

    @Override
    public ResponesData getOrdersByOpenid(String openid) {
        try{
            List<Orders> orders=ordersDao.getOrdersByOpenid(openid);
            return new ResponesData(ResponesCode.SUCCESS,orders);
        }catch (Exception E){
            return new ResponesData(ResponesCode.FAIL);
        }
    }

//    @Override
//    public ResponesData getOrderByState(String token, Integer orderstate) {
//        //根据token查询openid
//        String openid = userDao.queryOpenidByToken(token);
//        //根据我的openid和orderstate订单状态查询我的指定订单
//        List<Orders> orders = ordersDao.queryOrder(openid,orderstate);
//        return new ResponesData(ResponesCode.SUCCESS,orders);
//    }

    @Override
    public ResponesData createOrders(Orders orders, String token) {
        String username = orders.getUsername();
        String usertell = orders.getUsertell();
        if (StringUtil.isNull(username)||StringUtil.isNull(usertell)) {
            return new ResponesData(ResponesCode.ERROR06);
        }
        try {
            //根据我的token来拿我的openid
//            String openid = userDao.queryOpenidByToken(token);
            String openid = userDao.queryOpenidByToken(token);
            //下单的时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String placedate = simpleDateFormat.format(new Date());

            orders.setOpenid(openid);
            orders.setPlacedate(placedate);
            orders.setOrderstate(1);
            //生成订单
            ordersDao.insert(orders);
            return new ResponesData(ResponesCode.SUCCESS);
        }catch (Exception e){
            return new ResponesData(ResponesCode.FAIL);
        }
    }

    @Override
    public ResponesData getOrdersByState(String token, Integer orderstate) {
        //根据token查询openid
        String openid = userDao.queryOpenidByToken(token);
        //根据我的openid和orderstate订单状态查询我的指定订单
        List<Orders> orders = ordersDao.queryOrder(openid,orderstate);
        return new ResponesData(ResponesCode.SUCCESS,orders);
    }

//    @Override
//    public ResponesData getOrderByState(String token, Integer orderstate) {
//        //根据token查询openid
//        String openid = userDao.queryOpenidByToken(token);
//        //根据我的openid和orderstate订单状态查询我的指定订单
//        List<Orders> orders = ordersDao.queryOrder(openid,orderstate);
//        return new ResponesData(ResponesCode.SUCCESS,orders);
//    }

//    @Override
//    public ResponesData getOrderByState(String token, Integer orderstate) {
//        //根据token查询openid
//        String openid = userDao.queryOpenidByToken(token);
//        //根据我的openid和orderstate订单状态查询我的指定订单
//        List<Orders> orders = ordersDao.queryOrder(openid,orderstate);
//        return new ResponesData(ResponesCode.SUCCESS,orders);
//    }
//

}
