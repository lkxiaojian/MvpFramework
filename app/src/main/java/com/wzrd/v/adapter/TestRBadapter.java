package com.wzrd.v.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzrd.BR;
import com.wzrd.R;
import com.wzrd.m.been.MoviceBeen;
import com.wzrd.m.holder.BindingHolder;

import java.util.List;

/**
 * Created by lk on 2017/11/2.
 */

public class TestRBadapter extends RecyclerView.Adapter<BindingHolder> {
    private Context mContext;
    private List<MoviceBeen.ItemListBean> itemList;
    private View VIEW_FOOTER;
    private int TYPE_FOOTER = 1002;
    private int TYPE_NORMAL = 1000;
    private RecyclerView mRecyclerView;

    public TestRBadapter(Context mContext, List<MoviceBeen.ItemListBean> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;

    }


    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            ViewDataBinding dataBinding = DataBindingUtil.bind(VIEW_FOOTER);
//            ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), VIEW_FOOTER, parent, false);
            return new BindingHolder(dataBinding);
        } else {
            ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.test_item, parent, false);
            return new BindingHolder(dataBinding);
        }

    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        holder.getBinding().setVariable(BR.itembeen, itemList.get(position));
        // 立刻刷新界面
        holder.getBinding().executePendingBindings();
//        holder.getBinding().getRoot().findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext,"1234",Toast.LENGTH_SHORT).show();
//            }
//        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext,"itemclick",Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public int getItemViewType(int position) {
        if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }

    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isFooterView(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }




}
