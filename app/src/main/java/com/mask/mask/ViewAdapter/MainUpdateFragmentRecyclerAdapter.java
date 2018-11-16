package com.mask.mask.ViewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.mask.mask.Bean.MainUpdateFragmentRecyclerItemBean;
import com.mask.mask.R;

import java.util.ArrayList;


public class MainUpdateFragmentRecyclerAdapter extends RecyclerView.Adapter {

    public Context mContext;

    ArrayList<MainUpdateFragmentRecyclerItemBean> mMainUpdateFragmentRecyclerItemBeans;

    public MainUpdateFragmentRecyclerAdapter(ArrayList<MainUpdateFragmentRecyclerItemBean> mainUpdateFragmentRecyclerItemBeans,Context context){
        mMainUpdateFragmentRecyclerItemBeans = mainUpdateFragmentRecyclerItemBeans;
        mContext =context;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView mTvTitle;
        public TextView mTvContent;
        public Button mBtnUpdate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.main_update_recycler_item_tv_update_title);
            mTvContent = itemView.findViewById(R.id.main_update_recycler_item_tv_update_content);
            mBtnUpdate = itemView.findViewById(R.id.main_update_recycler_item_btn_update);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_update_fragment_recycler_item,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MainUpdateFragmentRecyclerItemBean mainUpdateFragmentRecyclerItemBean = mMainUpdateFragmentRecyclerItemBeans.get(i);
        MyViewHolder myViewHolder = (MyViewHolder)viewHolder;
        myViewHolder.mTvTitle.setText(mainUpdateFragmentRecyclerItemBean.getTitle());
        myViewHolder.mTvTitle.setTextColor(mContext.getResources().getColor(R.color.colorMainUpdateFragmentBtnUpdate));
        myViewHolder.mBtnUpdate.setText(mainUpdateFragmentRecyclerItemBean.getBtnText());
        myViewHolder.mBtnUpdate.setBackground(mContext.getResources().getDrawable(R.drawable.main_update_fragment_btn_update));
        myViewHolder.mTvContent.setText(mainUpdateFragmentRecyclerItemBean.getContent());
        myViewHolder.mTvContent.setTextColor(mContext.getResources().getColor(R.color.colorMainUpdateFragmentBtnUpdate));
    }

    @Override
    public int getItemCount() {
        return mMainUpdateFragmentRecyclerItemBeans.size();
    }
}
