package com.gaecoli.datavis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaecoli.datavis.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {
    User getUserByEmail(@Param("email") String email);
}
