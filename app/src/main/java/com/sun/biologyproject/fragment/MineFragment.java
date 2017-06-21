package com.sun.biologyproject.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sun.biologyproject.R;
import com.sun.biologyproject.activity.LoginActivity;
import com.sun.biologyproject.bean.User;

import cn.bmob.v3.BmobUser;

/**
 * Created by SUN on 2017/6/21.
 */
public class MineFragment extends Fragment {

    private TextView mTvName;
    private TextView mTvPhone;
    private Button mBtnSignOut;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.mine_fragment_layout, null);
        initView(contentView);
        return contentView;
    }

    private void initView(View v){
        User user = BmobUser.getCurrentUser(User.class);

        mTvName = (TextView) v.findViewById(R.id.tv_name);
        mTvName.setText(user.getNickName());

        mTvPhone = (TextView) v.findViewById(R.id.tv_phone);
        mTvPhone.setText(user.getUsername());

        mBtnSignOut = (Button) v.findViewById(R.id.btn_signOut);
        mBtnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser.logOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

}
