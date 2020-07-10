package com.xiaoyang.service;

import java.util.List;

/**
 * @author xiaoyang
 * @create 2020/7/8 8:37 下午
 */
public interface RequestService {

    /**
     * 获取sessionID
     *
     * @return
     */
    String getSessionId();

    /**
     * 获取请求
     * @return
     */
    String login();

    /**
     * 获取照片
     * @return
     */
    List<String> getImageList();

}
