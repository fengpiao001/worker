package com.apising.worker.wechat.service;


import com.apising.worker.wechat.model.WxOAuth;
import com.apising.worker.wechat.model.WxUserInfo;

/**
 * 网页授权接口
 * @author wangjianxin
 */
public interface WxOAuthService {

    /**
     * 获取网页授权登录的第一步URL
     * @return
     */
    String oauth2LoginUrl();

    /**
     * 获取网页授权中的accessToken
     * @return
     */
    WxOAuth oauth2AccessToken(String code);

    /**
     * 获得网页授权登录的用户详细信息
     * @return
     */
    WxUserInfo getUserInfo(WxOAuth wxOAuth);
}
