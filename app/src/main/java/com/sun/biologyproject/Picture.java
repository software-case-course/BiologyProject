package com.sun.biologyproject;

/**
 * Created by liangyuyi梁雨宜 on 2017/4/19.
 * GridView的一个块的数据结构
 */
public class Picture {
    private String title;
    private int imageId;

    public Picture()
    {
        super();
    }

    public Picture(String title, int imageId)
    {
        super();
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getImageId()
    {
        return imageId;
    }

    public void setImageId(int imageId)
    {
        this.imageId = imageId;
    }
}

