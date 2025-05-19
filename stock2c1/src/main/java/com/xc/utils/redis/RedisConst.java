package com.xc.utils.redis;


public class RedisConst {
    public static String getAdminRedisKey(String sessionId) {
        return "ADMIN" + sessionId;
    }

    public static String getAgentRedisKey(String sessionId) {
        return "AGENT" + sessionId;
    }

    public static String getUserRedisKey(String sessionId) {
        return "USER" + sessionId;
    }
}
