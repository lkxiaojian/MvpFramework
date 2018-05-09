package com.wzrd.v.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.wzrd.R;
import com.wzrd.v.adapter.ViewPagerFragmentAdapter;
import com.wzrd.v.fragment.AusleseFragment;
import com.wzrd.v.fragment.ClassfitionFragment;
import com.wzrd.v.fragment.HostFragment;
import com.wzrd.v.view.BannerTabHost;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tab_auslese)
    BannerTabHost tabAuslese;
    @BindView(R.id.tab_classfition)
    BannerTabHost tabClassfition;
    @BindView(R.id.tab_host)
    BannerTabHost tabHost;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private List<Fragment> mList = new ArrayList<>();
    private List<BannerTabHost> bannerList = new ArrayList<>();
    private AusleseFragment ausleseFragment;
    private ClassfitionFragment classfitionFragment;
    private HostFragment hostFragment;
    private int preItem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        findview();
        initBannerTabHost();
        initView();
        viewPager.setCurrentItem(0);


    }


    private void initView() {
        ausleseFragment = new AusleseFragment();
        classfitionFragment = new ClassfitionFragment();
        hostFragment = new HostFragment();
        mList.add(ausleseFragment);
        mList.add(classfitionFragment);
        mList.add(hostFragment);
//        viewPager.setNoScroll(true);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), mList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        bannerList.get(preItem).setRbtLeftIconChecked(false);
                        tabAuslese.setRbtLeftIconChecked(true);
                        preItem = 0;
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        bannerList.get(preItem).setRbtLeftIconChecked(false);
                        tabClassfition.setRbtLeftIconChecked(true);
                        preItem = 1;
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        bannerList.get(preItem).setRbtLeftIconChecked(false);
                        tabHost.setRbtLeftIconChecked(true);
                        preItem = 2;
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initBannerTabHost() {
        bannerList.add(tabAuslese);
        bannerList.add(tabClassfition);
        bannerList.add(tabHost);

    }


    @OnClick({R.id.tab_auslese, R.id.tab_classfition, R.id.tab_host})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_auslese:
                viewPager.setCurrentItem(0);
                bannerList.get(preItem).setRbtLeftIconChecked(false);
                tabAuslese.setRbtLeftIconChecked(true);

                preItem = 0;
                break;
            case R.id.tab_classfition:
                viewPager.setCurrentItem(1);
                bannerList.get(preItem).setRbtLeftIconChecked(false);
                tabClassfition.setRbtLeftIconChecked(true);
                preItem = 1;
                break;
            case R.id.tab_host:
                viewPager.setCurrentItem(2);
                bannerList.get(preItem).setRbtLeftIconChecked(false);
                tabHost.setRbtLeftIconChecked(true);
                preItem = 2;
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
        }
        return false;
    }

    private void back() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setMessage("确定退出程序");
        dialog.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        dialog.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
