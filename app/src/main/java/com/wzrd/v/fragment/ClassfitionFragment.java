package com.wzrd.v.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzrd.R;
import com.wzrd.databinding.TestCoustomBinding;
import com.wzrd.m.been.MoviceBeen;
import com.wzrd.p.ParsetPresenter;
import com.wzrd.v.fragment.base.BaseFragment;
import com.wzrd.v.view.ResultView;
import com.wzrd.v.view.recycleview.YRecycleView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by lk on 2017/10/30.
 */

public class ClassfitionFragment extends BaseFragment<ResultView, ParsetPresenter> {

    Unbinder unbinder;
    @BindView(R.id.recycle)
    YRecycleView recycle;

    private TestCoustomBinding binding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.test_coustom, container, false);
        unbinder = ButterKnife.bind(this, binding.getRoot());
        this.presenter.parsemovice("", "36");
        setlistener();
        return binding.getRoot();
    }
    private void setlistener() {
        recycle.setRefreshAndLoadMoreListener(new YRecycleView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                public void run() {
                    // 刷新数据结束时调用
                    recycle.setReFreshComplete();
                }
            }, 2500);
        }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        // 刷新数据结束时调用
                        recycle.setloadMoreComplete();
                    }
                }, 2500);
            }
        });
    }

    @Override
    public void Result(Object result, String message) {
        MoviceBeen moviceBeen = (MoviceBeen) result;
        List<MoviceBeen.ItemListBean> itemList = moviceBeen.getItemList();
        binding.setInboxdata(itemList);
    }

    @Override
    public void Errar(Object result) {
        Log.e("result", "result--" + result);

    }

    @Override
    public void Refresh(Object refresh, String message) {

    }


    @Override
    public ParsetPresenter bindPresenter() {
        return new ParsetPresenter();
    }

    @Override
    public ResultView bindView() {
        return this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
