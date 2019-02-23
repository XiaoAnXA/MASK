package com.mask.mask.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mask.mask.BluTools.BluManage;
import com.mask.mask.ViewAdapter.UpdateRecyclerAdapter;
import com.mask.mask.Bean.UpdateItemBean;
import com.mask.mask.R;

import java.util.ArrayList;

/**
 * 更新硬件页面
 */
public class UpdateFragment extends BaseFragment {

    public RecyclerView mRecyclerView;


    public UpdateRecyclerAdapter mUpdateRecyclerAdapter;
    private BluManage mBluManage;

    public UpdateFragment(){

    }

    public static UpdateFragment newInstance(BluManage bluManage) {
        UpdateFragment newFragment = new UpdateFragment();
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
        mUpdateRecyclerAdapter = new UpdateRecyclerAdapter(getArrayList(),getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.main_update_fragment,null);
        mRecyclerView = view.findViewById(R.id.main_update_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mUpdateRecyclerAdapter);
        return view;
    }

    public ArrayList<UpdateItemBean> getArrayList(){
        ArrayList<UpdateItemBean> updateItemBeans = new ArrayList<>();
        for (int i = 0;i < 10;i++ ){
            updateItemBeans.add(new UpdateItemBean("可用更新2018.11.12","更新说明：本次更新针对下巴处按摩流程作科学调整，按摩时间为8分钟，重点放松下额处肌肉",
                    "更新至模式",R.drawable.main_update_fragment_btn_update));
        }
        return updateItemBeans;
    }
}
