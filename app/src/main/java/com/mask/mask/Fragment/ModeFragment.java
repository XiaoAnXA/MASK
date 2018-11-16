package com.mask.mask.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mask.mask.R;


public class ModeFragment extends BaseFragment implements View.OnClickListener {

    public Button mBtnMode1,mBtnMode2,mBtnMode3,mBtnMode4,mBtnMode5,mBtnMode6,mBtnMode7,mBtnMode8;
    public Button mBtnMode9,mBtnMode10,mBtnMode11,mBtnMode12,mBtnMode13,mBtnMode14,mBtnMode15,mBtnMode16;
    public TextView mTvBluState;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_mode_fragment,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBtnMode1 = view.findViewById(R.id.mode_btn_1);
        mBtnMode1.setOnClickListener(this);
        mBtnMode2= view.findViewById(R.id.mode_btn_2);
        mBtnMode3 = view.findViewById(R.id.mode_btn_3);
        mBtnMode4 = view.findViewById(R.id.mode_btn_4);
        mBtnMode5 = view.findViewById(R.id.mode_btn_5);
        mBtnMode6 = view.findViewById(R.id.mode_btn_6);
        mBtnMode7 = view.findViewById(R.id.mode_btn_7);
        mBtnMode8 = view.findViewById(R.id.mode_btn_8);
        mBtnMode9 = view.findViewById(R.id.mode_btn_9);
        mBtnMode10 = view.findViewById(R.id.mode_btn_10);
        mBtnMode11 = view.findViewById(R.id.mode_btn_11);
        mBtnMode12 = view.findViewById(R.id.mode_btn_12);
        mBtnMode13 = view.findViewById(R.id.mode_btn_13);
        mBtnMode14 = view.findViewById(R.id.mode_btn_14);
        mBtnMode15 = view.findViewById(R.id.mode_btn_15);
        mBtnMode16 = view.findViewById(R.id.mode_btn_16);
        mTvBluState = view.findViewById(R.id.mode_tv_blu_state);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mode_btn_1:
                break;
        }
    }
}
