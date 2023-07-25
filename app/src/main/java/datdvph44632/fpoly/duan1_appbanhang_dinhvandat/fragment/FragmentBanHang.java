package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.GioHangItem;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.SanPham;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.CartAdapter;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.SanPhamAdapter;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.GioHangDAO;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.HoaDonChiTietDAO;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.SanPhamDAO;


public class FragmentBanHang extends Fragment {

    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    EditText edTimKiem;
    ListView lvList;
    Spinner spnLocDanhSach;
    final String[] danhSachLC = {"Theo tên", "Giá ↑", "Giá ↓"};
    ImageView imageView;
    List<SanPham> list;
    SanPhamDAO sanPhamDAO;
    SanPhamAdapter sanPhamAdapter;
    TextView tvNull;
    int soLuong;
    static int tong = 0;
    TextView tvSoLuongBanHang;
    ImageView cart;


    public FragmentBanHang() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ban_hang, container, false);

//        toolbar = view.findViewById(R.id.toolbar_ban_hang);
//        imageView = view.findViewById(R.id.imgBanHang);
        navigationView = view.findViewById(R.id.NavigationViewBanHang);
        drawerLayout = view.findViewById(R.id.drawerLayoutBanHang);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXaView(view);
        sanPhamDAO = new SanPhamDAO(getActivity());
        list = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(getActivity(), list);
        setHasOptionsMenu(true);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, danhSachLC);
        spnLocDanhSach.setAdapter(adapter);
        lvList.setAdapter(sanPhamAdapter); // Set the adapter to the ListView
        doDuLieuTheoSpinner();
        timKiem();
    }


    private void timKiem() {
        edTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<SanPham> list = sanPhamDAO.getAllSanPhamTheoMa(edTimKiem.getText().toString());
                SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(getActivity(), list);
                lvList.setAdapter(sanPhamAdapter);
                tvNull.setVisibility(View.INVISIBLE);
                if (edTimKiem.getText().toString().equalsIgnoreCase("")) {
                    doDuLieuTheoSpinner();
                }
                if (list.size() <= 0) {
                    tvNull.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void anhXaView(View view) {
        edTimKiem = view.findViewById(R.id.edTimKiemSanPham);
        lvList = view.findViewById(R.id.lvListMatHang);
        spnLocDanhSach = view.findViewById(R.id.spnLocTimKiem);
        tvNull = view.findViewById(R.id.tvNull);
        tvSoLuongBanHang = view.findViewById(R.id.tvSoLuongBanHang);
    }

    public void doDuLieuTheoSpinner() {
        spnLocDanhSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<SanPham> updatedList;
                if (adapterView.getItemAtPosition(i) == adapterView.getItemAtPosition(0)) {
                    updatedList = sanPhamDAO.getAllSanPham();
                } else if (adapterView.getItemAtPosition(i) == adapterView.getItemAtPosition(1)) {
                    updatedList = sanPhamDAO.getAllSanPhamTheoGiaTangDan();
                } else {
                    updatedList = sanPhamDAO.getAllSanPhamTheoGiaGiamDan();
                }
                sanPhamAdapter = new SanPhamAdapter(getActivity(), updatedList);
                lvList.setAdapter(sanPhamAdapter);
                tvNull.setVisibility(View.INVISIBLE);
                if (updatedList.size() <= 0) {
                    tvNull.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
