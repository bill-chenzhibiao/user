package com.bill.user.controller;

import com.bill.user.entity.User;
import com.bill.user.pojo.request.UserRequest;
import com.bill.user.repository.UserRepository;
import com.bill.user.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public Response register(@RequestBody UserRequest request){
        User user = new User();
        BeanUtils.copyProperties(request,user);
        userRepository.save(user);
        logger.debug("user register success,id;{}",user.getId());
        return Response.builder().build();
    }

    @PostMapping("/login")
    public Response login(@RequestBody UserRequest request){
        User user = userRepository.findByUsername(request.getUsername());
        logger.debug("login success,id:{}",user.getId());
        return Response.builder().data(user).build();
    }
}
