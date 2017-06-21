package com.sun.biologyproject.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * Created by SUN on 2017/6/21.
 */
public class RecordBean extends BmobObject {

    private String userName;//属于哪个用户
    private String watersName;//水域名称
    private BmobDate time;//收集的时间
    private Integer score;//水质量评分
    private List<String> tools;//使用的工具
    private List<BiologyBean> biologyBeanList;//收集的生物列表

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String location;//地理位置



    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getWatersName() {
        return watersName;
    }

    public void setWatersName(String watersName) {
        this.watersName = watersName;
    }

    public BmobDate getTime() {
        return time;
    }

    public void setTime(BmobDate time) {
        this.time = time;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<String> getTools() {
        return tools;
    }

    public void setTools(List<String> tools) {
        this.tools = tools;
    }

    public List<BiologyBean> getBiologyBeanList() {
        return biologyBeanList;
    }

    public void setBiologyBeanList(List<BiologyBean> biologyBeanList) {
        this.biologyBeanList = biologyBeanList;
    }

}
