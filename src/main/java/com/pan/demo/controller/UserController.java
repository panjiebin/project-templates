package com.pan.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pan.demo.dto.UserDto;
import com.pan.demo.dto.UserQueryDto;
import com.pan.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author panjb
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable String id) {
        return userService.queryByUserId(id);
    }

    @GetMapping
    public List<UserDto> queryUsers(UserQueryDto dto, Page<UserDto> page) {
        return userService.queryByPage(page, dto);
    }
}
