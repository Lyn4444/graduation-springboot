package com.graduation.stringboot.service;

import com.graduation.stringboot.entity.Result;
import com.graduation.stringboot.entity.Userinfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户信息登录和注册
 *
 * @author Lyn
 * @date 2022/01/29
 */
public interface UserInfoService {

    /**
     * 通过 UserName 查找用户名是否存在
     *
     * @param name 名字
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String, Object> findByUserName(@Param("UserName") String name);

    /**
     * 通过 email 查找用户名是否存在
     *
     * @param email 电子邮件
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String, Object> findByEmail(@Param("Email") String email);

    /**
     * 通过 PhoneNum 查找用户名是否存在
     *
     * @param phoneNum 电话
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String, Object> findByPhoneNum(@Param("PhoneNum") String phoneNum);

    /**
     * 判断是否是用户
     *
     * @param uid uid
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String, Object> isUser(@Param("Uid") Long uid);

    /**
     * 改用户名
     *
     * @param newName 新用户名
     * @param uid     uid
     * @return int
     */
    int reUserName(@Param("UserName") String newName, @Param("Uid") Long uid);

    /**
     * 改电话号码
     *
     * @param newPhoneNum 新手机号码
     * @param uid         uid
     * @return int
     */
    int rePhoneNum(@Param("PhoneNum") String newPhoneNum, @Param("Uid") Long uid);

    /**
     * 改电子邮件
     *
     * @param newEmail 新邮件
     * @param uid      uid
     * @return int
     */
    int reEmail(@Param("Email") String newEmail, @Param("Uid") Long uid);

    /**
     * 通过id获取注册日期
     *
     * @param uid uid
     * @return {@link Date}
     */
    Date getDateById(@Param("Uid") Long uid);

    /**
     * 添加用户信息
     *
     * @param name     用户名
     * @param salt     盐值
     * @param pwdHash  pwdHash
     * @param phoneNum 电话号码
     * @param email    电子邮件
     * @param date     注册日期
     * @return int
     */
    int addUserInfo(@Param("UserName") String name, @Param("Salt") String salt, @Param("PwdHash") String pwdHash,
                    @Param("PhoneNum") String phoneNum, @Param("Email") String email, @Param("Date") Date date);


    /**
     * 通过id获取用户信息（测试样例）
     *
     * @param uid uid
     * @return {@link Userinfo}
     */
    Userinfo getUserInfoById(@Param("Uid") Long uid);

    /**
     * 得到用户信息名字（测试样例）
     *
     * @param name 名字
     * @return {@link Userinfo}
     */
    Userinfo getUserInfoByName(@Param("UserName") String name);

    /**
     * 得到所有信息（测试样例）
     *
     * @return {@link List}
     */
    Result getAll();

}
