package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadByUserName(username);
        if(user == null){
            throw new RuntimeException("用户名或密码不存在");
        }

        List<String> authorities = userMapper.getAuthorities(username);


        List<GrantedAuthority> authorityList = authorities.stream().map(v -> {
            if(v.toString().equals("4")){
                return new SimpleGrantedAuthority("ROLE_"+v.toString());
            }
            return new SimpleGrantedAuthority(v.toString());
        }).collect(Collectors.toList());

        user.setAuthorities(authorityList);
        return user;
    }
}
