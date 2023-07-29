package datdvph44632.fpoly.duan1_appbanhang_dinhvandat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.ViewPageAdapter;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment.FragmentBanHang;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment.FragmentSetting;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment.FragmentGioHang;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment.FragmentDonHang;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment.FragmentThem;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    public TabLayout tabLayout;

//    public static List<GioHang> gioHangList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.TabLayout);
        viewPager = findViewById(R.id.viewPage);
//
//        if (gioHangList == null) {
//            gioHangList = new ArrayList<>();
//        }

        Intent i = getIntent();
        String user = i.getStringExtra("user");

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());

        if (user.equalsIgnoreCase("admin")) {
            viewPageAdapter.addFragment(new FragmentDonHang(), "");
            viewPageAdapter.addFragment(new FragmentSetting(), "");
            viewPageAdapter.addFragment(new FragmentThem(), "");

            viewPager.setAdapter(viewPageAdapter);

            tabLayout.setupWithViewPager(viewPager);

            tabLayout.getTabAt(0).setIcon(R.drawable.iconhoadonactivity);
            tabLayout.getTabAt(1).setIcon(R.drawable.img);
            tabLayout.getTabAt(2).setIcon(R.drawable.user);
        }else{

            viewPageAdapter.addFragment(new FragmentBanHang(), "");
            viewPageAdapter.addFragment(new FragmentGioHang(), "");
            viewPageAdapter.addFragment(new FragmentDonHang(), "");
            viewPageAdapter.addFragment(new FragmentSetting(), "");


            viewPager.setAdapter(viewPageAdapter);

            tabLayout.setupWithViewPager(viewPager);

            tabLayout.getTabAt(0).setIcon(R.drawable.iconbanhangactivity);
            tabLayout.getTabAt(1).setIcon(R.drawable.iconthemactivity);
            tabLayout.getTabAt(2).setIcon(R.drawable.iconhoadonactivity);
            tabLayout.getTabAt(3).setIcon(R.drawable.img);


        }






    }
}