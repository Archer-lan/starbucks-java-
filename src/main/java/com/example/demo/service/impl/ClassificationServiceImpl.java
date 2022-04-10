package com.example.demo.service.impl;

import com.example.demo.entity.Classification;
import com.example.demo.dao.ClassificationDao;
import com.example.demo.service.ClassificationService;
import com.example.demo.util.ResponesCode;
import com.example.demo.util.ResponesData;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Classification)表服务实现类
 *
 * @author makejava
 * @since 2022-04-07 19:43:44
 */
@Service("classificationService")
public class ClassificationServiceImpl implements ClassificationService {
    @Resource
    private ClassificationDao classificationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Classification queryById(Integer id) {
        return this.classificationDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param classification 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Classification> queryByPage(Classification classification, PageRequest pageRequest) {
        long total = this.classificationDao.count(classification);
        return new PageImpl<>(this.classificationDao.queryAllByLimit(classification, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param classification 实例对象
     * @return 实例对象
     */
    @Override
    public Classification insert(Classification classification) {
        this.classificationDao.insert(classification);
        return classification;
    }

    /**
     * 修改数据
     *
     * @param classification 实例对象
     * @return 实例对象
     */
    @Override
    public Classification update(Classification classification) {
        this.classificationDao.update(classification);
        return this.queryById(classification.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.classificationDao.deleteById(id) > 0;
    }

    @Override
    public ResponesData getClassifications() {
        try{
            List<Classification> classification=classificationDao.getClassifications();
            return new ResponesData(ResponesCode.SUCCESS,classification);
        }catch (Exception e){
            return new ResponesData(ResponesCode.FAIL);
        }
    }
}
