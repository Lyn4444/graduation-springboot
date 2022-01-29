package com.graduation.stringboot.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @author Lyn
 * @date 2022/01/19
 * @column uid, username, salt, pwdhash, phonenum, email, date
 */
public class Userinfo implements Serializable {

    private long uid;
    private String userName;
    private String salt;
    private String pwdHash;
    private long phoneNum;
    private String email;
    private java.sql.Date date;

    public Userinfo() {
    }

    public Userinfo(long uid, String userName, String salt, String pwdHash, long phoneNum, String email, java.sql.Date date) {
        this.uid = uid;
        this.userName = userName;
        this.salt = salt;
        this.pwdHash = pwdHash;
        this.phoneNum = phoneNum;
        this.email = email;
        this.date = date;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }


    public long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getDate() {
        return new Date(date.getTime());
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", salt='" + salt + '\'' +
                ", pwdHash='" + pwdHash + '\'' +
                ", phoneNum=" + phoneNum +
                ", email='" + email + '\'' +
                ", date=" + date;
    }
}
