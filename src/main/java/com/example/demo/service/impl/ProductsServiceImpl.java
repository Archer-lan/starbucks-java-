package com.example.demo.service.impl;

import com.example.demo.entity.Products;
import com.example.demo.dao.ProductsDao;
import com.example.demo.service.ProductsService;
import com.example.demo.util.ResponesCode;
import com.example.demo.util.ResponesData;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Products)表服务实现类
 *
 * @author makejava
 * @since 2022-04-05 17:01:00
 */
@Service("productsService")
public class ProductsServiceImpl implements ProductsService {
    @Resource
    private ProductsDao productsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Products queryById(Integer id) {
        return this.productsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param products 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Products> queryByPage(Products products, PageRequest pageRequest) {
        long total = this.productsDao.count(products);
        return new PageImpl<>(this.productsDao.queryAllByLimit(products, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param products 实例对象
     * @return 实例对象
     */
    @Override
    public Products insert(Products products) {
        this.productsDao.insert(products);
        return products;
    }

    /**
     * 修改数据
     *
     * @param products 实例对象
     * @return 实例对象
     */
    @Override
    public Products update(Products products) {
        this.productsDao.update(products);
        return this.queryById(products.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.productsDao.deleteById(id) > 0;
    }

    @Override
    public ResponesData getProInfos() {
        try{
            List<Products> products=productsDao.getProInfos();
            return new ResponesData(ResponesCode.SUCCESS,products);
        }catch (Exception e){
            return new ResponesData(ResponesCode.FAIL);
        }
    }

    @Override
    public ResponesData getProByType(String protype) {
        try{
            List<Products> products=productsDao.getProByType(protype);
            return new ResponesData(ResponesCode.SUCCESS,products);
        }catch (Exception e){
            return new ResponesData(ResponesCode.FAIL);
        }
    }

    @Override
    public ResponesData getProById(String id) {
        try{
            List<Products> products=productsDao.getProById(id);
            return new ResponesData(ResponesCode.SUCCESS,products);
        }catch (Exception e){
            return new ResponesData(ResponesCode.FAIL);
        }
    }
}
