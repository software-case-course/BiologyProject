package com.sun.biologyproject.bean;

/**
 * Created by SUN on 2017/6/21.
 */
public class BiologyBean {

    private String name;//生物名字
    private String id;//对应的Id
    private Integer score;//水质量的评分系数
    private String environment;//生活的环境

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BiologyBean){
            BiologyBean temp = (BiologyBean) obj;
            return id.equals(temp.getId());
        }
        return false;
    }
}
