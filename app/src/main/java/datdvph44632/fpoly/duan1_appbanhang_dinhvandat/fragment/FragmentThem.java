package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.activity.LoaiSanPhamActivity;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.activity.SanPhamActivity;


public class FragmentThem extends Fragment {

    TextView tvMatHang, tvPhanLoai, tvNguoiDung;
    ImageView imgMatHang, imgPhanLoai, imgNguoiDung;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_them, container, false);
        anhXaView(view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_thoat){
                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                    homeIntent.addCategory( Intent.CATEGORY_HOME );
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                }

                return false;
            }
        });
        //chuyển sang sản phẩm activity

        tvMatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenAct(SanPhamActivity.class);
            }
        });

        //chuyển sang loại sản phẩm activity

        tvPhanLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenAct(LoaiSanPhamActivity.class);
            }
        });




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXaView(view);
    }

    public void anhXaView(View view) {
        tvMatHang = view.findViewById(R.id.tvMatHang);
        tvPhanLoai = view.findViewById(R.id.tvPhanLoai);
        tvNguoiDung = view.findViewById(R.id.tvNguoiDung);
        toolbar = view.findViewById(R.id.toolbar_thong_tin);
        drawerLayout = view.findViewById(R.id.drawerLayoutThem);
        navigationView = view.findViewById(R.id.NavigationViewThem);
    }

    public void chuyenAct(Class aClass) {
        Intent intent = new Intent(getActivity(), aClass);
        startActivity(intent);
    }
}