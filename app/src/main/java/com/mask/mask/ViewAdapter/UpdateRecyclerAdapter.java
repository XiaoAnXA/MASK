package com.mask.mask.ViewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.mask.mask.Bean.UpdateItemBean;
import com.mask.mask.R;

import java.util.ArrayList;


/**
 * 更新固件程序中的Recycler的适配器
 */
public class UpdateRecyclerAdapter extends RecyclerView.Adapter {

    public Context mContext;

    ArrayList<UpdateItemBean> mUpdateItemBeans;

    public UpdateRecyclerAdapter(ArrayList<UpdateItemBean> updateItemBeans, Context context){
        mUpdateItemBeans = updateItemBeans;
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
        UpdateItemBean updateItemBean = mUpdateItemBeans.get(i);
        MyViewHolder myViewHolder = (MyViewHolder)viewHolder;
        myViewHolder.mTvTitle.setText(updateItemBean.getTitle());
        myViewHolder.mTvTitle.setTextColor(mContext.getResources().getColor(R.color.colorMainUpdateFragmentBtnUpdate));
        myViewHolder.mBtnUpdate.setText(updateItemBean.getBtnText());
        myViewHolder.mBtnUpdate.setBackground(mContext.getResources().getDrawable(R.drawable.main_update_fragment_btn_update));
        myViewHolder.mTvContent.setText(updateItemBean.getContent());
        myViewHolder.mTvContent.setTextColor(mContext.getResources().getColor(R.color.colorMainUpdateFragmentBtnUpdate));
    }

    @Override
    public int getItemCount() {
        return mUpdateItemBeans.size();
    }
}
