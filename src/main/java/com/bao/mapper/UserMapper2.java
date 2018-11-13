package com.bao.mapper;

import com.bao.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper2 {
    //@Select("select  user_id userId,  user_name, password, phone from t_user")
    @Select("select  user_id,  user_name, password, phone from t_user")
    List<User> findAllUsers();
}
