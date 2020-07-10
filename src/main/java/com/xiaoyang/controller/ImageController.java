package com.xiaoyang.controller;

import com.xiaoyang.dto.QueryPage;
import com.xiaoyang.service.ImgService;
import com.xiaoyang.util.LocalCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiaoyang
 * @create 2020/7/8 8:49 下午
 */
@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private ImgService imgService;

    @PostMapping("/image")
    public List<String> image(@RequestBody QueryPage queryPage) {
        Integer start = queryPage.getStart();
        Integer end = queryPage.getEnd();

        if (start > end) {
            return null;
        }

        String image = LocalCache.get("image");
        if (image == null) {
            List<String> imageList = imgService.getImage();
            if (imageList.size() != 0) {
                image = String.join(" ", imageList);
                LocalCache.put("image", image, 6000);
            }
        }

        List<String> images = Arrays.asList(image.split(" "));
        List<String> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            String img = images.get(i);
            result.add(img);
        }
        return result;
    }

}
