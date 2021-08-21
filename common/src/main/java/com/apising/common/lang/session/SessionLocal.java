package com.apising.common.lang.session;

import com.apising.common.lang.exception.XException;
import org.springframework.core.NamedThreadLocal;

/**
 * 保存请求各种参数的ThreadLocal
 *
 * @author lilin
 *         <p>
 *         2017年3月2日下午11:47:13
 */
public class SessionLocal {

	private static ThreadLocal<SessionDto> sessionLocal = new NamedThreadLocal<SessionDto>("session");

	@SuppressWarnings("unchecked")
	public static <T extends SessionDto> T getSession() {
		SessionDto session = sessionLocal.get();
		T t = (T) session;
		return t;
	}

	public static <T extends SessionDto> void setSession(T session) {
		sessionLocal.set(session);
	}

	public static Long getUserId() {
		SessionDto session = sessionLocal.get();
		if (null == session) {
			return null;
		}
		return session.getUserId();
	}

	public static String getUserName() {
		SessionDto session = sessionLocal.get();
		if (null == session) {
			return null;
		}
		return session.getUserName();
	}

	/**
	 * 获取工人id
	 * @return
	 */
	public static Long getRequireWorkerId(){
		SessionDto session = sessionLocal.get();
		if(session == null){
			throw new XException().setErrorCode(600).setShowText("登录已过期，请重新登录");
		}
		if(!Integer.valueOf(1).equals(session.getUserType())){
			throw new XException().setErrorCode(601).setShowText("用户类型不正确，请重新登录");
		}
		return session.getUserId();
	}


	public static String getMobile(){
		SessionDto session = sessionLocal.get();
		if(session == null){
			return null;
		}
		return session.getMobile();
	}
}
