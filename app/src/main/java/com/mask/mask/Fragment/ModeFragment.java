package com.mask.mask.Fragment;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mask.mask.BluTools.BluManage;
import com.mask.mask.Event.BluStateEvent;
import com.mask.mask.Util.Cmd;
import com.mask.mask.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 模式控制页
 *
 *
 */
public class ModeFragment extends BaseFragment implements View.OnClickListener {

    public final static String TAG = ModeFragment.class.getSimpleName();

    public Button mBtnMode1,mBtnMode2,mBtnMode3,mBtnMode4,mBtnMode5,mBtnMode6,mBtnMode7,mBtnMode8;
    public Button mBtnMode9,mBtnMode10,mBtnMode11,mBtnMode12,mBtnMode13,mBtnMode14,mBtnMode15,mBtnMode16;
    public TextView mTvBluState;
    public ImageView mIvLogo;
    public BluManage mBluManage;

    public ModeFragment(){

    }

    public static ModeFragment newInstance(BluManage bluManage) {
        ModeFragment newFragment = new ModeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("BluManage",bluManage);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = this.getArguments();
        mBluManage = (BluManage) arguments.getSerializable("BluManage");
        EventBus.getDefault().register(this);
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
        mIvLogo = view.findViewById(R.id.mode_iv_logo);

        mIvLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBluManage.showBondedBluListView();
            }
        });

        mBtnMode1.setOnClickListener(this);
        mBtnMode2.setOnClickListener(this);
        mBtnMode3.setOnClickListener(this);
        mBtnMode4.setOnClickListener(this);
        mBtnMode5.setOnClickListener(this);
        mBtnMode6.setOnClickListener(this);
        mBtnMode7.setOnClickListener(this);
        mBtnMode8.setOnClickListener(this);
        mBtnMode9.setOnClickListener(this);
        mBtnMode10.setOnClickListener(this);
        mBtnMode11.setOnClickListener(this);
        mBtnMode12.setOnClickListener(this);
        mBtnMode13.setOnClickListener(this);
        mBtnMode14.setOnClickListener(this);
        mBtnMode15.setOnClickListener(this);
        mBtnMode16.setOnClickListener(this);

    }

    //写入数据
    public void write(String data){
        if (mBluManage== null){return;}
        mBluManage.writeData(data);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.mode_btn_1:
                write(Cmd.$Write+Cmd.comma+"04"+Cmd.comma+"01");
                break;
            case R.id.mode_btn_2:
                write("$Write");
                break;
            case R.id.mode_btn_3:
                write("$Read");
                break;
            case R.id.mode_btn_4:
                write("$OK");
                break;
            case R.id.mode_btn_5:
                write("$OK");
                break;
            case R.id.mode_btn_6:
                write("$NO");
                break;
            case R.id.mode_btn_7:
                write("$Not");
                break;
            case R.id.mode_btn_8:
                write("$Save");
                break;
            case R.id.mode_btn_9:
                write("$Cancel");
                break;
            case R.id.mode_btn_10:
                write("$Temp");
                break;
            case R.id.mode_btn_11:
                write("$Data");
                break;
            case R.id.mode_btn_12:
                write(",");
                break;
            case R.id.mode_btn_13:
                break;
            case R.id.mode_btn_14:
                break;
            case R.id.mode_btn_15:
                break;
            case R.id.mode_btn_16:
                break;
        }
    }

    /**
     * 随时监听蓝牙的状态
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBluState(BluStateEvent event){
        mTvBluState.setText(event.state);
        switch (event.bluEvent){
            case BluetoothAdapter.STATE_OFF:
                break;
            case BluetoothAdapter.STATE_ON:
                mBluManage.showBondedBluListView();
                break;
            case BluetoothAdapter.STATE_TURNING_OFF:
                break;
            case BluetoothAdapter.STATE_TURNING_ON:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
