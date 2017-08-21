package com.zns.cache;

import com.zns.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.Jedis;

@Component
public class UserCache {

    @Autowired
    private Jedis redis;

    /**
     * 添加缓存
     *
     * @param user
     */
    public void addUser(User user) {
        if (!this.redis.hexists("login", user.getUsername())) {
            this.redis.hset("login", user.getUsername(), DigestUtils.md5DigestAsHex(user.getUsername().getBytes()));
        }
    }

    /**
     * 检查登录
     *
     * @param username
     * @param token
     * @return
     */
    public boolean checkLogin(String username, String token) {
        if (this.redis.hexists("login", username) && this.redis.hget("login", username).equals(token)) {
            return true;
        }
        return false;
    }
}
