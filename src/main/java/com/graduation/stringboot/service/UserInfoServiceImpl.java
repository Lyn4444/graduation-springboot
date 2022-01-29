package com.graduation.stringboot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.graduation.stringboot.entity.Result;
import com.graduation.stringboot.entity.Userinfo;
import com.graduation.stringboot.mapper.UserInfoMapper;
import com.graduation.stringboot.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoMapper userInfoMapper;


    /**
     * 通过 UserName 查找用户名是否存在
     *
     * @param name 名字
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @Override
    public Map<String, Object> findByUserName(String name) {
        return null;
    }

    /**
     * 通过 email 查找用户名是否存在
     *
     * @param email 电子邮件
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @Override
    public Map<String, Object> findByEmail(String email) {
        return null;
    }

    /**
     * 通过 PhoneNum 查找用户名是否存在
     *
     * @param phoneNum 电话
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @Override
    public Map<String, Object> findByPhoneNum(String phoneNum) {
        return null;
    }

    /**
     * 判断是否是用户
     *
     * @param uid uid
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @Override
    public Map<String, Object> isUser(Long uid) {
        return null;
    }

    /**
     * 改用户名
     *
     * @param newName 新用户名
     * @param uid     uid
     * @return int
     */
    @Override
    public int reUserName(String newName, Long uid) {
        return 0;
    }

    /**
     * 改电话号码
     *
     * @param newPhoneNum 新手机号码
     * @param uid         uid
     * @return int
     */
    @Override
    public int rePhoneNum(String newPhoneNum, Long uid) {
        return 0;
    }

    /**
     * 改电子邮件
     *
     * @param newEmail 新邮件
     * @param uid      uid
     * @return int
     */
    @Override
    public int reEmail(String newEmail, Long uid) {
        return 0;
    }

    /**
     * 通过id获取注册日期
     *
     * @param uid uid
     * @return {@link Date}
     */
    @Override
    public Date getDateById(Long uid) {
        return null;
    }

    /**
     * 通过id获取用户信息（测试样例）
     *
     * @param uid uid
     * @return {@link Userinfo}
     */
    @Override
    public Userinfo getUserInfoById(Long uid) {
        return null;
    }

    /**
     * 得到用户信息名字（测试样例）
     *
     * @param name 名字
     * @return {@link Userinfo}
     */
    @Override
    public Userinfo getUserInfoByName(String name) {
        return null;
    }

    /**
     * 得到所有信息（测试样例）
     *
     * @return {@link List}
     */
    @Override
    public Result getAll() {
        List<Userinfo> list = userInfoMapper.getAll();
        if (!list.isEmpty()) {
            Map<String, Object> res = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> tmp = new HashMap<>();
                Userinfo userinfo = list.get(i);
                tmp.put("uid", userinfo.getUid());
                tmp.put("UserName", userinfo.getUserName());
                tmp.put("Salt", userinfo.getSalt());
                tmp.put("PwdHash", userinfo.getPwdHash());
                tmp.put("PhoneNum", userinfo.getPhoneNum());
                tmp.put("Email", userinfo.getEmail());
                tmp.put("Date", userinfo.getDate());
                res.put(String.valueOf(i),tmp);
            }
            return ResultUtil.success(res);
        } else {
            return ResultUtil.error("null");
        }
    }
}
