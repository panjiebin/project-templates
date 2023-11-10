package com.pan.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pan.demo.dto.UserDto;
import com.pan.demo.dto.UserQueryDto;
import com.pan.demo.entity.User;

import java.util.List;

/**
 * @author panjb
 */
public interface UserService extends IService<User> {

    UserDto queryByUserId(String id);

    List<UserDto> queryByPage(IPage<UserDto> page, UserQueryDto dto);
}
