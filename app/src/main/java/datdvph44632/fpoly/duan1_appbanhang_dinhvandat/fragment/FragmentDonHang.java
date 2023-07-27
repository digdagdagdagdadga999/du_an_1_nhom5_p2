
package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
 import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.HoaDonChiTiet;
        import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.activity.TTDHActivity;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.DonHangAdapter;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.DonHangChiTietDAO;

public class FragmentDonHang extends Fragment {

    private RecyclerView recyclerView;
    private Context context;
    private DonHangChiTietDAO donHangChiTietDAO;
    private DonHangAdapter hoaDonAdapter;
    private List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();
    private String currentDate;
    private String currentTime;

    public FragmentDonHang() {
    }

    public static FragmentDonHang newInstance() {
        return new FragmentDonHang();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);
        context = getContext();
        recyclerView = view.findViewById(R.id.listViewHoaDon);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        donHangChiTietDAO = new DonHangChiTietDAO(getContext());
        hoaDonChiTietList = donHangChiTietDAO.getAllHoaDonChiTiet();
        hoaDonAdapter = new DonHangAdapter(context, hoaDonChiTietList);
        hoaDonAdapter.setOnSulyClickListener(new DonHangAdapter.OnSulyClickListener() {
            @Override
            public void onSulyClick(HoaDonChiTiet hoaDonChiTiet) {
                Intent intent = new Intent(getActivity(), TTDHActivity.class);
                intent.putExtra("nameProduct", hoaDonChiTiet.getTenSanPham());
                intent.putExtra("quantity", hoaDonChiTiet.getSoLuong());
                intent.putExtra("totalPrice", hoaDonChiTiet.getTongTien());
                intent.putExtra("address", hoaDonChiTiet.getAddress());
                intent.putExtra("orderDate", hoaDonChiTiet.getOrderDate());
                intent.putExtra("orderTime", hoaDonChiTiet.getOrderTime());
                intent.putExtra("imageProduct", hoaDonChiTiet.getHinhAnhSanPham());



                intent.putExtra("orderDate", currentDate);
                intent.putExtra("orderTime", currentTime);


                startActivity(intent);
            }
        });


        recyclerView.setAdapter(hoaDonAdapter);

        return view;
    }

}
