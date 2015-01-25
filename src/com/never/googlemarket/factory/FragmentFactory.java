package com.never.googlemarket.factory;

import java.util.HashMap;

import android.support.v4.app.Fragment;

import com.never.googlemarket.base.BaseFragment;
import com.never.googlemarket.fragment.AppFragment;
import com.never.googlemarket.fragment.CategoryFragment;
import com.never.googlemarket.fragment.GameFragment;
import com.never.googlemarket.fragment.HomeFragment;
import com.never.googlemarket.fragment.HotFragment;
import com.never.googlemarket.fragment.RecommendFragment;
import com.never.googlemarket.fragment.SubjectFragment;

public class FragmentFactory {

	/**
	 * hashmap 维护缓存中已经创建的fragment对象
	 */
	private static HashMap<Integer, Fragment> hashMap = new HashMap<Integer, Fragment>();

	public static Fragment createFragment(int position) {
		BaseFragment baseFragment = null;
		// 有对象就获取，没对象就创建
		if (hashMap.containsKey(position)) {
			// 集合中有，就直接从缓存中获取
			if (hashMap.get(position) != null) {
				baseFragment = (BaseFragment) hashMap.get(position);
			}
		} else {
			// 没有对象，就直接创建,并缓存到本地
			switch (position) {
			case 0:
				baseFragment = new HomeFragment();
				break;
			case 1:
				baseFragment = new AppFragment();
				break;
			case 2:
				baseFragment = new GameFragment();
				break;
			case 3:
				baseFragment = new SubjectFragment();
				break;
			case 4:
				baseFragment = new RecommendFragment();
				break;
			case 5:
				baseFragment = new CategoryFragment();
				break;
			case 6:
				baseFragment = new HotFragment();
				break;
			}
			// 缓存到本地
			hashMap.put(position, baseFragment);

		}
		return baseFragment;
	}

}
