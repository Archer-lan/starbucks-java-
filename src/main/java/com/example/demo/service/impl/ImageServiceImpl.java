package com.example.demo.service.impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.demo.entity.Image;
import com.example.demo.dao.ImageDao;
import com.example.demo.service.ImageService;
import com.example.demo.util.ResponesCode;
import com.example.demo.util.ResponesData;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Image)表服务实现类
 *
 * @author makejava
 * @since 2022-04-05 16:29:32
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService {
    @Resource
    private ImageDao imageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Image queryById(Integer id) {
        return this.imageDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param image 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Image> queryByPage(Image image, PageRequest pageRequest) {
        long total = this.imageDao.count(image);
        return new PageImpl<>(this.imageDao.queryAllByLimit(image, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param image 实例对象
     * @return 实例对象
     */
    @Override
    public Image insert(Image image) {
        this.imageDao.insert(image);
        return image;
    }

    /**
     * 修改数据
     *
     * @param image 实例对象
     * @return 实例对象
     */
    @Override
    public Image update(Image image) {
        this.imageDao.update(image);
        return this.queryById(image.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.imageDao.deleteById(id) > 0;
    }

    @Override
    public ResponesData queryByType(String imagetype) {
        try{
            List<Image> images = imageDao.queryImageByType(imagetype);
            return new ResponesData(ResponesCode.SUCCESS,images);
        }catch (Exception e){
            return new ResponesData(ResponesCode.FAIL);
        }
    }
}
