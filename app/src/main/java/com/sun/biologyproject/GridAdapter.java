package com.sun.biologyproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangyuyi on 2017/4/19.
 * GridView 的适配器
 */
public class GridAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private List<Picture> pictures;


    //图片id数组
    private int[] images=new int[]{
            R.mipmap.img_identification_have_a_shell1_thumb,
            R.mipmap.img_identification_have_a_shell2_thumb,
            R.mipmap.img_identification_have_a_shell3_thumb,
            R.mipmap.img_identification_have_a_shell4_thumb,
            R.mipmap.img_identification_have_a_shell5_thumb
    };
    private String[] titles = new String[]
            { "Pouch snail", "Water Penny", "Clam or Mussel", "Gilled Snail", "Casemaker Caddisfly"};
    //上下文对象
    private Context context;
    //构造方法
    public GridAdapter(Context context)
    {
        this.context=context;
        pictures=new ArrayList<Picture>();
        inflater = LayoutInflater.from(context);

//        int picRoot;
//        String title;
//        dbHelper=new GridViewDB(context,"GridDB.db",null,1);
//        Cursor cursor=db.query("Grid",null,null,null,null,null,null);
//        while(true)
//        {
//            if(cursor.moveToFirst()) {//添加数据
//                do{
//                    picRoot=cursor.getInt(cursor.getColumnIndex("picRoot"));
//                    title=cursor.getString(cursor.getColumnIndex("text"));
//                    Picture picture = new Picture(title,picRoot);
//                    pictures.add(picture);
//                }while (cursor.moveToNext());
//                Log.d("Tag","加载本地数据库");
//                break;
//            }
//            else{//当本地数据库没有数据时
//                GridDBInit gridDBInit=new GridDBInit();
//                gridDBInit.init(context);
//                Log.d("Tag","初始化数据库");
//            }
//        }
//
//        cursor.close();
        for (int i = 0; i < images.length; i++) {
            Picture picture = new Picture(titles[i], images[i]);
            pictures.add(picture);
        }
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
    public View getView(int position,View convertView,ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.picture_item, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(pictures.get(position).getTitle());
        viewHolder.image.setImageResource(pictures.get(position).getImageId());
        return convertView;
    }

}

class ViewHolder
{
    public TextView title;
    public ImageView image;
}
