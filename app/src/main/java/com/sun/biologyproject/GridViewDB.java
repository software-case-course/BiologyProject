package com.sun.biologyproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangyuyi 梁雨宜on 2017/4/19.
 * GridView的各个模块的图片的路径和
 */
public class GridViewDB {
    private List<int []>images;
    private List<String []>titles;

    public GridViewDB()
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
    public int [] getImages(int id)
    {
        if(id==4){
            return images.get(2);
        }
        return images.get(id-1);
    }

    public String [] getTitles(int id)
    {
        if(id==4){
            return titles.get(2);
        }
        return titles.get(id-1);
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
}
