package com.example.apcp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
//FragmentPagerAdapter
public class FragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Новости", "Курсы валют"};
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
 //   private Context context;

//    public FragmentAdapter(FragmentManager fm, Context context) {
public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override public int getCount() {
    //return PAGE_COUNT;
        return mFragmentTitleList.size();
    }

    @Override public Fragment getItem(int position) {
         return mFragmentList.get(position);
        //return PageFragment_one.newInstance(position + 1);
    }

    @Override public CharSequence getPageTitle(int position) {
        // генерируем заголовок в зависимости от позиции
        return mFragmentTitleList.get(position);
        //return tabTitles[position];
    }

    public void addFragment(Fragment pageFragment, String title) {
        mFragmentList.add(pageFragment);
        mFragmentTitleList.add(title);
    }

}
