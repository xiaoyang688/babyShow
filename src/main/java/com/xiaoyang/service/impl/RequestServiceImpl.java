package com.xiaoyang.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyang.service.RequestService;
import com.xiaoyang.service.WxPushService;
import com.xiaoyang.util.LocalCache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyang
 * @create 2020/7/8 8:37 下午
 */
@Service
public class RequestServiceImpl implements RequestService {

    public static OkHttpClient client = new OkHttpClient();

    @Value("${SESSION_URL}")
    private String SESSION_URL;

    @Value("${IMAGE_URL}")
    private String IMAGE_URL;

    @Autowired
    private WxPushService wxPushService;

    @Override
    public String getSessionId() {
        String session = login();
        return session;
    }

    /**
     * 获取请求
     *
     * @return
     */
    @Override
    public String login() {

        Request request = null;
        Response response = null;
        request = new Request.Builder()
                .url(SESSION_URL)
                .build();

        try {
            response = client.newCall(request).execute();
            String resp = response.body().string();
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<String> getImageList() {

        List<String> imageList = new ArrayList<>();
        LocalCache.put("session", "0l242g518tlli656ubpmlc0jt3");
        try {
            Request request = new Request.Builder()
                    .url(IMAGE_URL)
                    .addHeader("session", LocalCache.get("session"))
                    .addHeader("User-Agent", "Android/ONEPLUS A5010,4.5.6")
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSONArray actions = jsonObject.getJSONObject("data").getJSONArray("actions");
            for (Object action : actions) {
                JSONObject act = (JSONObject) action;
                JSONArray images = act.getJSONArray("images");
                for (Object image : images) {
                    JSONObject img = (JSONObject) image;
                    String bigImageUrl = img.getString("bigImageUrl");
                    imageList.add(bigImageUrl);
                }
                JSONArray videos = act.getJSONArray("videos");
                for (Object video : videos) {
                    JSONObject vid = (JSONObject) video;
                    String videoUrl = vid.getString("videoUrl");
                    imageList.add(videoUrl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String sessionId = getSessionId();
            LocalCache.put("session", sessionId.trim());
            wxPushService.wxPush("session失效，快去重新抓取", "UID_iAB4BFMt7quFBtA4eFOeQl117fbZ");
        }

        return imageList;
    }
}
