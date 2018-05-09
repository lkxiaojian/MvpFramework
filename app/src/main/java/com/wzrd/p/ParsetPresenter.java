package com.wzrd.p;


import com.wzrd.m.result.HttpResult;
import com.wzrd.m.result.MoviceResult;
import com.wzrd.p.impl.AbsPresenter;
import com.wzrd.v.view.ResultView;

/**
 * Created by lk on 2017/9/30.
 */

public class ParsetPresenter extends AbsPresenter<ResultView> {
    private HttpResult httpResult;
private MoviceResult moviceResult;



    public ParsetPresenter() {
        this.httpResult=new HttpResult();
        this.moviceResult=new MoviceResult();

    }


    public void parse(String name,String passward,String message){

        this.httpResult.parse(getView(),message,name,passward);
    }

    public void parsemovice(String message,String id){
        this.moviceResult.getmovice(getView(),message,id);
    }









}
