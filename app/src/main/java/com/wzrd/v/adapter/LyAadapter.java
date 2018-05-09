package com.wzrd.v.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wzrd.R;
import com.wzrd.m.been.MoviceBeen;
import com.wzrd.v.adapter.base.ListBaseAdapter;
import com.wzrd.v.adapter.base.SuperViewHolder;

import java.util.List;

/**
 * Created by lk on 2017/11/16.
 */

public class LyAadapter extends ListBaseAdapter<MoviceBeen.ItemListBean> {
    private List<MoviceBeen.ItemListBean> itemList;
    private Context mContext;
    public LyAadapter(Context context,List<MoviceBeen.ItemListBean> itemList) {
        super(context);
      this.mContext=context;
      this.itemList=itemList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.test_item_class;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {

        MoviceBeen.ItemListBean itemListBean = itemList.get(position);
        TextView textView= holder.getView(R.id.tv_test);
        textView.setText(itemListBean.getData().getDescription());
        ImageView imageView=holder.getView(R.id.iv_class);

        Glide.with(mContext).load(itemListBean.getData().getCover().getFeed()).into(imageView);


    }
}
