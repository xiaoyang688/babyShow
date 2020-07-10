package com.xiaoyang.service.impl;

import com.xiaoyang.service.WxPushService;
import com.zjiecode.wxpusher.client.WxPusher;
import com.zjiecode.wxpusher.client.bean.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyang
 * @create 2020/7/8 11:36 下午
 */
@Service
public class WxPushServiceImpl implements WxPushService {
    public static Message message = new Message();

    @Value("${APP_TOKEN}")
    private String APP_TOKEN;

    @Override
    public void wxPush(String content, String uid) {
        message.setAppToken(APP_TOKEN);
        message.setContentType(Message.CONTENT_TYPE_TEXT);
        message.setContent(content);
        message.setUid(uid);
        WxPusher.send(message);
    }
}
