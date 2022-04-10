package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.util.ResponesData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Image)表服务接口
 *
 * @author makejava
 * @since 2022-04-05 16:29:32
 */
public interface ImageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Image queryById(Integer id);

    /**
     * 分页查询
     *
     * @param image 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Image> queryByPage(Image image, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param image 实例对象
     * @return 实例对象
     */
    Image insert(Image image);

    /**
     * 修改数据
     *
     * @param image 实例对象
     * @return 实例对象
     */
    Image update(Image image);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过图片类型查找图片
     *
     * @param imagetype 图片类型
     * @return 是否成功
     */
    ResponesData queryByType(String imagetype);
}
