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

    /**
     * 罗静老师ID
     */
    private String TEACHER_LUO_ID = "131669";

    private String TEACHER_GUO_ID = "131273";

    @Override
    public String getSession() {
        String session = LocalCache.get("session");
        return session;
    }

    /**
     * 获取请求
     *
     * @return
     */
    @Override
    public String saveSession(String session) {
        LocalCache.put("session", session);
        return session;
    }

    @Override
    public List<String> getImageList() {

        List<String> imageList = new ArrayList<>();
        try {
            String session = LocalCache.get("session");
            Request request = new Request.Builder()
                    .url(IMAGE_URL)
                    .addHeader("session", session)
                    .addHeader("User-Agent", "Android/ONEPLUS A5010,4.5.6")
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSONArray actions = jsonObject.getJSONObject("data").getJSONArray("actions");
            for (Object action : actions) {
                JSONObject act = (JSONObject) action;
                String publishTeacherId = act.getString("publishTeacherId");
                // 过滤TEACHER_ID
                if (TEACHER_LUO_ID.equals(publishTeacherId) || TEACHER_GUO_ID.equals(publishTeacherId)) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
            wxPushService.wxPush("session失效，快去重新抓取", "UID_iAB4BFMt7quFBtA4eFOeQl117fbZ");
        }

        return imageList;
    }
}
