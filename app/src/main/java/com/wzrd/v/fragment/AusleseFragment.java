package com.wzrd.v.fragment;


import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzrd.R;
import com.wzrd.databinding.AusleseFragmnetBinding;
import com.wzrd.m.been.Loginbeen;
import com.wzrd.p.ParsetPresenter;
import com.wzrd.v.fragment.base.BaseLazyFragment;
import com.wzrd.v.view.MyLoadingDialog;
import com.wzrd.v.view.ResultView;


/**
 * Created by lk on 2017/10/30.
 */

public class AusleseFragment extends BaseLazyFragment<ResultView, ParsetPresenter> {


    private View view;
    private Dialog loadingDialog;
    private AusleseFragmnetBinding dataBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        dataBinding = DataBindingUtil.inflate(inflater, R.layout.auslese_fragmnet, container, false);
        loadingDialog = MyLoadingDialog.createLoadingDialog(getActivity(), getActivity().getString(R.string.login_loading), true);
        loadingDialog.show();
        isPrepared = true;

        lazyLoad();



//      UserFormManager userFormManager=new UserFormManager(getActivity());
//        this.presenter.parse("aqzjj", "1234", "");
        return dataBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @Override
    public void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        Log.e("AusleseFragment","AusleseFragment");
        this.presenter.parse("aqzjj", "1234", "");
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
        Loginbeen loginbeen = (Loginbeen) result;
        loginbeen.getDataobj().get(0).setType("http://img.kaiyanapp.com/a1a1bf7ed3ac906ee4e8925218dd9fbe.png");
        dataBinding.setDataobj(loginbeen.getDataobj().get(0));
        loadingDialog.dismiss();

    }

    @Override
    public void Errar(Object result) {
        loadingDialog.dismiss();
    }

    @Override
    public void Refresh(Object refresh, String message) {

    }
}
