package com.apising.common.lang.util;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tianxu
 */
@Slf4j
public class PasswordUtil {
    public static final String DEFAULT_KEY = "1q2w3e4r";


    public static String getSecretPassword(String password,String key){
        if(StringUtils.isEmpty(key)){
            key = DEFAULT_KEY;
        }
        if(password == null){
            password = "";
        }
        try{
            return EncodeUtil.getMd5Code(password,key);
        }catch (Exception e){
            log.warn("getMd5Code error",e);
            return null;
        }
    }

}
