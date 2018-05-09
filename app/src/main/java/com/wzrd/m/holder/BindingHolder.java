package com.wzrd.m.holder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by lk on 2017/11/3.
 */

public  class BindingHolder extends RecyclerView.ViewHolder {
    ViewDataBinding binding;

    public BindingHolder( ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }


    public ViewDataBinding getBinding() {
        return binding;
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }
}
