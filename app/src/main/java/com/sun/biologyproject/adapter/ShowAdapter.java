package com.sun.biologyproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sun.biologyproject.R;
import com.sun.biologyproject.bean.GridViewDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 49485 on 2017/6/18.
 */
public class ShowAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    //private List<Picture> pictures;
    private List <Integer> pictures;
    //上下文对象
    private Context context;
    //构造方法
    public ShowAdapter(Context context, int id,int id2)
    {
        this.context=context;
        pictures=new ArrayList<Integer>();
        inflater = LayoutInflater.from(context);
        //从数据库获取数据
        GridViewDB gridViewDB= GridViewDB.getInstance();
        //获取一组图片
//        int []image=gridViewDB.getImages(id);
//        for (int i = 0; i < image.length; i++) {
//            pictures.add(image[i]);
//        }

        //获取一张图
        pictures.add(gridViewDB.getPictureId(id,id2));
       // pictures.add(gridViewDB.getPictureId(id,id2+1));
    }
    //获得数量
    @Override
    public int getCount()
    {
        if (null != pictures)
        {
            return pictures.size();
        } else
        {
            return 0;
        }
    }
    //获得当前选项
    @Override
    public Object getItem(int position)
    {
        return pictures.get(position);
    }
    //获得当前选项id
    @Override
    public long getItemId(int position)
    {
        return position;
    }

    //创建View方法
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder2 viewHolder;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.show_picture, null);
            viewHolder = new ViewHolder2();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.show_image);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder2) convertView.getTag();
        }
        viewHolder.image.setImageResource(pictures.get(position));
        return convertView;
    }

}

class ViewHolder2
{
    public ImageView image;
}

