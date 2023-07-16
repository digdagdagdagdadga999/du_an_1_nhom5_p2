package datdvph44632.fpoly.duan1_appbanhang_dinhvandat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.ViewPageAdapter;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment.FragmentBanHang;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment.FragmentBaoCao;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment.FragmentGioHang;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment.FragmentHoaDon;
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

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new FragmentBanHang(), "BÁN HÀNG");
        viewPageAdapter.addFragment(new FragmentGioHang(), "Giỏ HÀNG");
        viewPageAdapter.addFragment(new FragmentHoaDon(), "HÓA ĐƠN");
        viewPageAdapter.addFragment(new FragmentBaoCao(), "SETTING");
        viewPageAdapter.addFragment(new FragmentThem(), "ADMIN");
        viewPager.setAdapter(viewPageAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.iconbanhangactivity);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconhoadonactivity);
        tabLayout.getTabAt(2).setIcon(R.drawable.baseline_settings);
        tabLayout.getTabAt(3).setIcon(R.drawable.iconthemactivity);
    }
}