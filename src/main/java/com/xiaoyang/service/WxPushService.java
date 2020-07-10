package com.xiaoyang.service;

/**
 * @author xiaoyang
 * @create 2020/7/8 11:34 下午
 */
public interface WxPushService {


    /**
     * 微信通知
     * @param content
     * @param uid
     */
    public void wxPush(String content, String uid);

}
