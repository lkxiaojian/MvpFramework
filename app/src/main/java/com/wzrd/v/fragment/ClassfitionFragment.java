package com.wzrd.v.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wzrd.R;
import com.wzrd.m.been.MoviceBeen;
import com.wzrd.p.ParsetPresenter;
import com.wzrd.v.adapter.LyAadapter;
import com.wzrd.v.fragment.base.BaseFragment;
import com.wzrd.v.view.ResultView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by lk on 2017/10/30.
 */

public class ClassfitionFragment extends BaseFragment<ResultView, ParsetPresenter> {

    Unbinder unbinder;


    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.test, null);
        unbinder = ButterKnife.bind(this, view);
        this.presenter.parsemovice("", "36");
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Thread.currentThread();
                        try {
                            Thread.sleep(2000);
//                            srl.setRefreshing(false);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        });
        return view;
    }

    @Override
    public void Result(Object result, String message) {

        MoviceBeen moviceBeen = (MoviceBeen) result;
        Log.e("moviceBeen", "moviceBeen-->" + moviceBeen.getNextPageUrl());
        List<MoviceBeen.ItemListBean> itemList = moviceBeen.getItemList();
        LyAadapter testRBadapter = new LyAadapter(getActivity(), itemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());



    }

    @Override
    public void Errar(Object result) {

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
