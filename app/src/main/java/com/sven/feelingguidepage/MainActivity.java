package com.sven.feelingguidepage;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewpager;
    private TextView mTvIndication;

    private int[] mImgsResId = {
            R.drawable.bg_guide_1,
            R.drawable.bg_guide_2,
            R.drawable.bg_guide_3,
            R.drawable.bg_guide_4
    };

    private List<View> viewContainter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvIndication = (TextView) findViewById(R.id.tv_indicactor);

        View rootView1 = LayoutInflater.from(this).inflate(R.layout.layout_guide_page, null);
        View rootView2 = LayoutInflater.from(this).inflate(R.layout.layout_guide_page, null);
        View rootView3 = LayoutInflater.from(this).inflate(R.layout.layout_guide_page, null);
        View rootView4 = LayoutInflater.from(this).inflate(R.layout.layout_guide_page, null);

        viewContainter.add(rootView1);
        viewContainter.add(rootView2);
        viewContainter.add(rootView3);
        viewContainter.add(rootView4);

        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mViewpager.setOffscreenPageLimit(3);
        mViewpager.setPageMargin(DensityUtil.dip2px(40));
        mViewpager.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return mImgsResId.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewContainter.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewContainter.get(position));
                final ImageView ivBackGround = (ImageView) viewContainter.get(position).findViewById(R.id.iv_page);
                ivBackGround.setImageResource(mImgsResId[position]);
                return viewContainter.get(position);
            }
        });

        mViewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        for (int i = 0; i < viewContainter.size(); i++) {
                            RelativeLayout ivBackgound = (RelativeLayout) viewContainter.get(i).findViewById(R.id.rootview);
                            if (ivBackgound != null) {
                                ivBackgound.animate()
                                        .rotation(-20)
                                        .setDuration(300)
                                        .start();
                            }
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        for (int i = 0; i < viewContainter.size(); i++) {
                            RelativeLayout ivBackgound = (RelativeLayout) viewContainter.get(i).findViewById(R.id.rootview);
                            if (ivBackgound != null) {
                                ivBackgound.animate()
                                        .rotation(0)
                                        .setDuration(300)
                                        .start();
                            }
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int oldPosition;
            private int newPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                oldPosition = mViewpager.getCurrentItem();
            }

            @Override
            public void onPageSelected(int position) {
                newPosition = position;
                if (newPosition > oldPosition) {
                    ObjectAnimator anim = ObjectAnimator.ofFloat(mTvIndication, "X", DensityUtil.dip2px(25) * oldPosition, DensityUtil.dip2px(25) * newPosition);
                    anim.setDuration(200);
                    anim.start();
                } else {
                    ObjectAnimator anim = ObjectAnimator.ofFloat(mTvIndication, "X", DensityUtil.dip2px(25) * oldPosition, DensityUtil.dip2px(25) * newPosition);
                    anim.setDuration(200);
                    anim.start();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
