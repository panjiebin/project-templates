package com.pan.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pan.demo.dto.UserDto;
import com.pan.demo.dto.UserQueryDto;
import com.pan.demo.entity.User;

import java.util.List;

/**
 * @author panjb
 */
public interface UserMapper extends BaseMapper<User> {

    List<UserDto> selectByPage(IPage<UserDto> page, UserQueryDto dto);

}
