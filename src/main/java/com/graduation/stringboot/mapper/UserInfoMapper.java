package com.graduation.stringboot.mapper;

import com.graduation.stringboot.entity.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "UserInfoMapper")
public interface UserInfoMapper {

    /**
     * 得到所有信息（测试样例）
     *
     * @return {@link List}<{@link Userinfo}>
     */
    @Select(value = "select * from userinfo")
    List<Userinfo> getAll();

}
