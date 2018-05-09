package com.wzrd.p;

import com.wzrd.m.been.Loginbeen;
import com.wzrd.m.been.MoviceBeen;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lk on 2017/9/18.
 */

public interface Apiservers {

    @GET("pm/interfacesManager.do?method=getSafeUserLogin")
    Observable<Loginbeen> getData(@Query("loginname") String loginname, @Query("password") String password, @Query("appName") String appName);

    //    http://aqbj.cscec2b.cn:5003/pm/pm/interfacesManager.do?method=getSafeUserLogin&loginname=aqzjj&password=4321&appName=cscec2bsafe
    @GET("v4/categories/videoList")
    Observable<MoviceBeen> getmovice(@Query("id") String loginname);
//    http://baobab.kaiyanapp.com/api/v4/categories/videoList?id=36
//    @GET("v4/categories/videoList")
//    Observable<T> getCategoryItemList(@Query("id") Long id);
//
//    @GET("v4/categories/detail/tab?")
//    Observable<T> gettabdetail(@Query("id") Long id);
////    http://baobab.wandoujia.com/api/v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83
//    @GET("v2/feed")
//    Observable<AusleseBeen> getdeatail(@Query("num") int num, @Query("udid") String udid, @Query("vc") int vc);
//
////    http://baobab.kaiyanapp.com/api/v1/search?num=10&query=xxx&start=10
//    @GET("v1/search")
//    Observable<Seachbeen> getseach(@Query("num") int num, @Query("query") String meesage, @Query("start") int start);
//
////    http://baobab.kaiyanapp.com/api/v4/categories/detail/tab?id=36//分类详情
//    @GET("v4/categories/detail/tab")
//    Observable<Classificationbeen> getclassification(@Query("id") int id);
//
//    @POST("v4")
//    Observable<T> post(@Field("data") String data);


}
