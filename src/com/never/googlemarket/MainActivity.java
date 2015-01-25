package com.never.googlemarket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.never.googlemarket.base.BaseFragment;
import com.never.googlemarket.factory.FragmentFactory;
import com.never.googlemarket.ui.weidget.PagerTab;
import com.never.googlemarket.utils.UIUtils;

public class MainActivity extends BaseActivity {

	/**
	 * 主界面上部的指针
	 */
	private PagerTab pagerTab;
	/**
	 * 主界面下部的viewPager
	 */
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pagerTab = (PagerTab) findViewById(R.id.pagertab);
		viewPager = (ViewPager) findViewById(R.id.viewpager);

		viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

		// 绑定指针和viewPager
		pagerTab.setViewPager(viewPager);
		// 顶部指针页面变换监听
		pagerTab.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				BaseFragment baseFragment = (BaseFragment) FragmentFactory
						.createFragment(arg0);
				baseFragment.show();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	class MyPagerAdapter extends FragmentPagerAdapter {
		/**
		 * 顶部指针条目名
		 */
		private String[] tab_names;

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			tab_names = UIUtils.getStringArray(R.array.tab_names);
		}

		@Override
		public Fragment getItem(int arg0) {
			// 返回fragment对象，通过FragmentFactory创建
			return FragmentFactory.createFragment(arg0);
		}

		@Override
		public int getCount() {
			return tab_names.length;
		}

		// 获得指针的名字
		@Override
		public CharSequence getPageTitle(int position) {
			return tab_names[position];
		}
	}
}
