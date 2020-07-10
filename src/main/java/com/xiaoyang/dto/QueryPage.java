package com.xiaoyang.dto;

import java.io.Serializable;

/**
 * @author xiaoyang
 * @create 2020/7/10 1:56 下午
 */
public class QueryPage implements Serializable {

    private Integer start;
    private Integer end;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
