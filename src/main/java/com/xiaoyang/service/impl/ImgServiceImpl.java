package com.xiaoyang.service.impl;

import com.xiaoyang.service.ImgService;
import com.xiaoyang.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoyang
 * @create 2020/7/8 8:36 下午
 */
@Service
public class ImgServiceImpl implements ImgService {

    @Autowired
    private RequestService requestService;

    @Override
    public List<String> getImage() {

        List<String> imageList = requestService.getImageList();
        return imageList;
    }


}
