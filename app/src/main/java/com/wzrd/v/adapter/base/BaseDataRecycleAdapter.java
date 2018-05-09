package com.wzrd.v.adapter.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzrd.BR;
import com.wzrd.m.holder.BindingHolder;
import com.wzrd.m.holder.ViewHolder;

import java.util.List;

/**
 * Created by lk on 2018/5/7.
 */

public abstract class BaseDataRecycleAdapter<T> extends RecyclerView.Adapter<BindingHolder> {
    protected Context mContext;
    protected List<T> data;
    protected OnItemClickListener mOnItemClickListener;

    public BaseDataRecycleAdapter(Context mContext, List<T> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = ViewHolder.createViewHolder(mContext, parent, getLayoutId());
        setListener(holder, viewType);
        if (isMoreType()) {//多类型
            return ViewHolderConvert(parent, viewType);
        }
        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), getLayoutId(), parent, false);
        return new BindingHolder(dataBinding);
    }

    /**
     * 多类型的话，重写此方法和isMoreType()返回true,
     *
     * @param parent
     * @param viewType
     * @return
     */
    protected BindingHolder ViewHolderConvert(ViewGroup parent, int viewType) {

        return ViewHolderConvert(parent, viewType);
    }

    /**
     * item的点击监听 和长按监听  根据需要来继承，databinding一般不需要
     *
     * @param viewHolder
     * @param viewType
     */
    protected void setListener(final ViewHolder viewHolder, int viewType) {
        //来判断某个item点击是否有效,默认是无效的
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(view, viewHolder, position);
                }
            }
        });


        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return mOnItemClickListener.onItemLongClick(view, viewHolder, position);
                }
                return false;
            }
        });

    }

    /**
     * 重写此方法，来判断某个item点击是否有效
     *
     * @param viewType
     * @return
     */
    protected boolean isEnabled(int viewType) {
        return true;
    }

    /**
     * 重写此方法，来判断是否多类型的 默认是单类型
     *
     * @return
     */
    protected boolean isMoreType() {
        return false;
    }


    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {
        holder.getBinding().setVariable(BR.bean, data.get(position));
        // 立刻刷新界面
        holder.getBinding().executePendingBindings();
        convert(holder, data.get(position));

    }

    /**
     * 把onBindViewHolder映射出去，每个adapter可以重写此方法
     *
     * @param holder
     * @param t
     */
    public void convert(BindingHolder holder, T t) {

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    //获取data
    public List<T> getDatas() {
        return data;
    }

    // 获取布局文件
    public abstract int getLayoutId();

    /**
     * 删除某条数据
     *
     * @param position
     */
    public void removeData(int position) {
        notifyItemRemoved(position + 1);
        if (position != data.size()) {
            if (position == 0) {
                data.remove(position);
                notifyDataSetChanged();
            } else if (position == (data.size() - 1)) {
                data.remove(position);
                notifyItemRangeChanged(position, 0);
            } else {
                data.remove(position);
                notifyItemRangeChanged(position, data.size() - position + 1);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);

        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }

}
