package com.wzrd.v.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzrd.R;
import com.wzrd.m.been.MoviceBeen;
import com.wzrd.m.utils.Constants;
import com.wzrd.p.ParsetPresenter;
import com.wzrd.p.Rypresenter;
import com.wzrd.v.adapter.TestRBadapter;
import com.wzrd.v.fragment.base.BaseLazyFragment;
import com.wzrd.v.view.RecycleResult;
import com.wzrd.v.view.ResultView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by lk on 2017/10/30.
 */

public class HostFragment extends BaseLazyFragment<ResultView, ParsetPresenter> implements RecycleResult {

    Unbinder unbinder;
    @BindView(R.id.recycle)
    RecyclerView recycle;
//    @BindView(R.id.epl)
//    EasyPullLayoutJ epl;
    private View view;
    private      TestRBadapter testRBadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.host_fragmnet, null);


        isPrepared = true;

        unbinder = ButterKnife.bind(this, view);
        Rypresenter rypresenter = new Rypresenter(this);
        rypresenter.getlast(recycle, "123");

        return view;
    }

    @Override
    public void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        this.presenter.parsemovice("", "36");
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
    public void Result(Object result, String message) {
        Log.e("HostFragment", "result---" + result.toString());
        MoviceBeen moviceBeen = (MoviceBeen) result;

        List<MoviceBeen.ItemListBean> itemList = moviceBeen.getItemList();
         testRBadapter = new TestRBadapter(getActivity(), itemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(testRBadapter);
//        ViewDataBinding bind = DataBindingUtil.bind(LayoutInflater.from(getActivity()).inflate(R.layout.foot_itme, null));

        testRBadapter.addFooterView(LayoutInflater.from(getActivity()).inflate(R.layout.foot_itme, null));


    }

    @Override
    public void Errar(Object result) {
        Log.e("HostFragment", "result---" + result.toString());

    }

    @Override
    public void Refresh(Object refresh, String message) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void last(String message) {
        Log.e("1111", "message-->" + message);
        Constants.isshow=false;
        testRBadapter.notifyDataSetChanged();
    }

}
