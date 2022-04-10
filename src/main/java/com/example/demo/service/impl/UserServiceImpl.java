package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Products;
import com.example.demo.entity.User;
import com.example.demo.dao.UserDao;
import com.example.demo.service.UserService;
import com.example.demo.util.HttpClientUtil;
import com.example.demo.util.ResponesCode;
import com.example.demo.util.ResponesData;
import com.example.demo.util.StringUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-04-05 19:28:37
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public ResponesData userRegister(User user) {
        String username = user.getUsername();
        String phone = user.getPhone();
        String password = user.getPassword();

        if(StringUtil.isNull(username)){
            return new ResponesData(ResponesCode.ERROR01);
        }
        if(StringUtil.isNull(phone)){
            return new ResponesData(ResponesCode.ERROR02);
        }
        if(StringUtil.isNull(password)){
            return new ResponesData(ResponesCode.ERROR03);
        }

        User userByuserName=userDao.queryUserByuserName(username);
        if(userByuserName!=null){
            return new ResponesData(ResponesCode.ERROR04);
        }
        //密码加密
        Md5Hash md5Hash=new Md5Hash(password,"xkd",10);
        String newPassword=md5Hash.toString();
        user.setPassword(newPassword);
        userDao.insert(user);
        return new ResponesData(ResponesCode.SUCCESS01);
    }


    @Override
    public ResponesData userLogin(String code, String phone, String password) {
        //非空校验
        if (StringUtil.isNull(phone)){
            return new ResponesData(ResponesCode.ERROR02);
        }
        if (StringUtil.isNull(password)){
            return new ResponesData(ResponesCode.ERROR03);
        }

        //把我们的我的密码转化为加密后的密码，方便和数据库里的加密后密码做对比判断是否相等
        Md5Hash md5Hash = new Md5Hash(password, "xkd", 10);
        String newPassword = md5Hash.toString();

        //如果能拿到user对象，说明手机号和密码正确，如果user没有拿到（null），说明手机号和密码错误
        User user = userDao.queryUserByPhoneAndPassword(phone, newPassword);

        if (user == null){
            return new ResponesData(ResponesCode.ERROR05);
        }

        try {
            //调用微信的登录接口获取session_key和openid
            String url ="https://api.weixin.qq.com/sns/jscode2session?appid=（微信appid）&secret=(微信aapid密钥)&js_code="+code+"&grant_type=authorization_code";
            //模拟get访问url
            String result = HttpClientUtil.doGet(url);
            System.out.println(result);
            JSONObject jsonResult = (JSONObject) JSONObject.parse(result);
            String session_key = (String) jsonResult.get("session_key");
            String openid = (String) jsonResult.get("openid");

            Md5Hash md5HashToken = new Md5Hash(openid, session_key, 10);
            String token = md5HashToken.toString();

            user.setOpenid(openid);
            user.setSessionkey(session_key);
            user.setToken(token);
            userDao.update(user);
            return new ResponesData(ResponesCode.SUCCESS02,token);
        }catch (Exception e){
            return new ResponesData(ResponesCode.FAIL);
        }
    }

    @Override
    public ResponesData getUserNameAndPhone(String token) {
        try{
            List<User> user=userDao.getUserNameAndPhone(token);
            return new ResponesData(ResponesCode.SUCCESS,user);
        }catch (Exception e){
            return new ResponesData(ResponesCode.FAIL);
        }
    }
}
