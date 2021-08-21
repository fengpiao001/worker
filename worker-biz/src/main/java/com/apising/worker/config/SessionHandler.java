package com.apising.worker.config;

import com.apising.common.lang.session.SessionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * session缓存类
 * 当前指定一个ConcurrentHashMap作为容器
 * 后续换成redis
 * @author
 */
@Slf4j
@Component
public class SessionHandler {
    private Map<String, SessionDto> sessionMap = new ConcurrentHashMap<>();
    @Autowired
    private RedisHandler redisHandler;

    private final static String SESSION_PREFIX = "SESSION_";

    private final static int DEFAULT_TIME = 60*60*24*7;

    /**
     * 获取session
     * @param sessionId
     * @return
     */
    public SessionDto getSessionById(String sessionId){
        String key = SESSION_PREFIX + sessionId;
        SessionDto sessionDto = null;
        try{
            sessionDto = redisHandler.get(key,SessionDto.class);
        }catch (Exception e){
            log.error("redis get error ",e);
            sessionDto = sessionMap.get(key);
        }
        return sessionDto;
    }

    /**
     * session放入缓存
     * @param sessionDto
     */
    public void setSession(SessionDto sessionDto){
        String key = SESSION_PREFIX + sessionDto.getSessionId();
        try{
            redisHandler.set(key,sessionDto,DEFAULT_TIME);
        }catch (Exception e){
            log.error("redis set error ",e);
            sessionMap.put(key,sessionDto);
        }
    }
}
