package com.zns.controller;

import com.zns.model.User;
import com.zns.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/user/login/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map> login(String username, String password) {
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        map.put("errCode", 3);
        map.put("errMsg", "用户名或密码错误");

        User user = userService.findUserByName(username);
        if (null == user) {
            return new ResponseEntity<Map>(map, HttpStatus.OK);
        }

        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return new ResponseEntity<Map>(map, HttpStatus.OK);
        }
        // TODO jwt
//        try {
//            Algorithm algorithm = Algorithm.HMAC256("secret");
//            String token = JWT.create()
//                    .withIssuer("auth0")
//                    .withExpiresAt(new Date())
//                    .withIssuedAt(new Date())
//                    .sign(algorithm);
//            System.out.println(token);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        map.put("errCode", 0);
        map.put("errMsg", "登录成功");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/register/", method = RequestMethod.POST)
    public ResponseEntity<Map> register(@RequestBody User user) {
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        if (!userService.isUserExit(user)) {
            map.put("errCode", 1);
            map.put("errMsg", user.getUsername() + " 该用户名已经注册！");
            return new ResponseEntity<Map>(map, HttpStatus.OK);
        }
        user.setRegTime(new Timestamp(System.currentTimeMillis()));
        user.setLoginTime(new Timestamp(System.currentTimeMillis()));
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setToken("");
        try {
            userService.addUser(user);
            map.put("errCode", 0);
            map.put("errMsg", "注册成功！");
            return new ResponseEntity<Map>(map, HttpStatus.OK);
        } catch (Exception e) {
            map.put("errCode", 2);
            map.put("errMsg", "注册失败！");
            return new ResponseEntity<Map>(map, HttpStatus.OK);
        }
    }
}
