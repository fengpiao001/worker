package com.apising.common.lang.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 *
 */
@Slf4j
public class SessionUtil {
	
	public static final String SessionPrefix = "SES_";

	public static final String SessionIdKey = "sessionToken";

	private static final String DefaultSessionMd5Key = "1q2w3e";

	public static String createCacheKey(String sessionId) {
		return SessionUtil.SessionPrefix + sessionId;
	}

	/**
	 * 创建用户的sessionId
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public static String createSessionId(String userName, String key) {
		if (null == userName) {
			userName = "";
		}
		if (null == key) {
			key = DefaultSessionMd5Key;
		}
		String fromStr = DateUtil.getNowDateTimeStr(DateUtil.Format_8);
		fromStr = userName + "_" + fromStr;
		try{
			return EncodeUtil.getMd5Code(fromStr, key);
		}catch (Exception e){
			log.warn("getMd5Code error",e);
			return "noSessionId";
		}
	}
}
