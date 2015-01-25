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
	 * hashmap ά���������Ѿ�������fragment����
	 */
	private static HashMap<Integer, Fragment> hashMap = new HashMap<Integer, Fragment>();

	public static Fragment createFragment(int position) {
		BaseFragment baseFragment = null;
		// �ж���ͻ�ȡ��û����ʹ���
		if (hashMap.containsKey(position)) {
			// �������У���ֱ�Ӵӻ����л�ȡ
			if (hashMap.get(position) != null) {
				baseFragment = (BaseFragment) hashMap.get(position);
			}
		} else {
			// û�ж��󣬾�ֱ�Ӵ���,�����浽����
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
			// ���浽����
			hashMap.put(position, baseFragment);

		}
		return baseFragment;
	}

}
