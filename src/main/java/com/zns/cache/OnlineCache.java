package com.zns.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class OnlineCache {

    @Autowired
    private Jedis redis;

    public void add() {

    }

}
