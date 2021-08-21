package com.apising.worker.wechat;
import com.alibaba.fastjson.JSONObject;
import com.apising.worker.wechat.model.WxOAuth;
import com.apising.worker.wechat.model.WxUserInfo;
import com.apising.worker.wechat.service.WxBaseService;
import com.apising.worker.wechat.service.WxOAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wx")
public class WxController extends HttpServlet {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WxBaseService wxBaseService;
    @Autowired
    private WxOAuthService wxOAuthService;

    /**
     * 获得accessToken接口
     * @return
     */
    @RequestMapping("/base/getAccessToken")
    @ResponseBody
    public String wx(){
        return wxBaseService.getAccessToken();
    }

    /**
     * 获取网页授权登录的第一步地址
     * @return
     */
    @RequestMapping("/oauth/getOAuth2FirstLoginUrl")
    @ResponseBody
    public String getOAuth2FirstLoginUrl(){
        return wxOAuthService.oauth2LoginUrl();
    }

    /**
     * 同意授权后，微信主动回掉的地址，在application中配置
     * 接口返回微信用户用户详细信息
     * @param code
     * @return
     */
    @RequestMapping("/oauth/redirect/url")
    @ResponseBody
    public String redirectUrl(String code){
        logger.info("first code ={}",code);
        WxOAuth wxOAuth = wxOAuthService.oauth2AccessToken(code);
        WxUserInfo wxUserInfo = wxOAuthService.getUserInfo(wxOAuth);
        return wxUserInfo.toString();
    }

    @RequestMapping("/test")
    public String test(HttpServletRequest request){
        return request.getParameter("echostr");
    }



}
