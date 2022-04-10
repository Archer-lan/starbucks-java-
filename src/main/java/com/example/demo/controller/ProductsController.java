package com.example.demo.controller;

import com.example.demo.entity.Products;
import com.example.demo.service.ProductsService;
import com.example.demo.util.ResponesData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Products)表控制层
 *
 * @author makejava
 * @since 2022-04-05 17:00:50
 */
@RestController
@RequestMapping("products")
public class ProductsController {
    /**
     * 服务对象
     */
    @Resource
    private ProductsService productsService;

    /**
     * 分页查询
     *
     * @param products 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Products>> queryByPage(Products products, PageRequest pageRequest) {
        return ResponseEntity.ok(this.productsService.queryByPage(products, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Products> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.productsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param products 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Products> add(Products products) {
        return ResponseEntity.ok(this.productsService.insert(products));
    }

    /**
     * 编辑数据
     *
     * @param products 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Products> edit(Products products) {
        return ResponseEntity.ok(this.productsService.update(products));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.productsService.deleteById(id));
    }

    @GetMapping("/getProInfos")
    public ResponesData getProInfos() {
        return productsService.getProInfos();
    }

    @GetMapping("/getProByType")
    public ResponesData getProByType(String protype){
        return productsService.getProByType(protype);
    }
    @GetMapping("/getProById")
    public ResponesData getProById(String id){
        return productsService.getProById(id);
    }
}

