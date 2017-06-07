package com.sun.biologyproject.bean;

import com.sun.biologyproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangyuyi 梁雨宜on 2017/4/19.
 * GridView的各个模块的图片的路径和图片名称
 */
public class GridViewDB {//使用单例模式，避免类被重复创建，消耗系统时间
    private List<int []>images;
    private List<String []>titles;

    private static GridViewDB instance=new GridViewDB();//类句柄

    private GridViewDB()//避免类直接new一个对象
    {
        images=new ArrayList<int []>();
        titles=new ArrayList<String[]>();
        images.add(images0);
        images.add(images1);
        images.add(images3);

        titles.add(titles0);
        titles.add(titles1);
        titles.add(titles3);
    }

    public static GridViewDB getInstance(){//创建对象时只能通过 GridViewDB gv=GridViewDB.getInstance()的方式
        return instance;
    }

    public int [] getImages(int id)
    {
        if(id==3){
            return images.get(2);
        }
        return images.get(id);
    }

    public String [] getTitles(int id)
    {
        if(id==3){
            return titles.get(2);
        }
        return titles.get(id);
    }

    //图片id数组
    private int[] images0=new int[]{
            R.mipmap.img_identification_have_a_shell1_thumb,
            R.mipmap.img_identification_have_a_shell2_thumb,
            R.mipmap.img_identification_have_a_shell3_thumb,
            R.mipmap.img_identification_have_a_shell4_thumb,
            R.mipmap.img_identification_have_a_shell5_thumb
    };
    private int[] images1=new int[]{
            R.mipmap.img_identification_worm1_thumb,
            R.mipmap.img_identification_worm2_thumb,
            R.mipmap.img_identification_worm3_thumb,
            R.mipmap.img_identification_worm4_thumb,
            R.mipmap.img_identification_worm5_thumb,
            R.mipmap.img_identification_worm6_thumb,
            R.mipmap.img_identification_worm7_thumb,
            R.mipmap.img_identification_worm8_thumb,
            R.mipmap.img_identification_worm9_thumb
    };
    private int[] images3=new int[]{
            R.mipmap.img_identification_more_than_six_legs2_thumb,
            R.mipmap.img_identification_more_than_six_legs3_thumb,
            R.mipmap.img_identification_more_than_six_legs4_thumb,
            R.mipmap.img_identification_more_than_six_legs5_thumb
    };

    private String[] titles0 = new String[]
            { "袋状螺", "扁泥甲若虫", "蛤蚌或河蚌", "棱角螺", "有壳石蛾"};
    private String[]titles1=new String[]{
            "水生蠕虫","蠓幼虫","大蚊幼虫","蚋幼虫","水蛭","长角泥甲幼虫","午虻幼虫","涡虫","蚊子幼虫"
    };
    private String[]titles3=new String[]{
            "飞毛腿","小龙虾","鱼蛉"," 鼠妇"
    };

    /**
     * 获取图片的接口
     * @param i 大类选择的id
     * @param y 小类选择的id
     * @return 图片的地址
     */
    public int getPictureId(int i,int y){
        int []a=images.get(i);
        return a[y];
    }

}
