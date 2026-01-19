package com.exhale.messageboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exhale.messageboard.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
