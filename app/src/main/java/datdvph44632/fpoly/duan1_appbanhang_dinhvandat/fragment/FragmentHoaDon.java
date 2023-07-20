package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.GioHangItem;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.HoaDonChiTiet;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.HoaDonAdapter;



public class FragmentHoaDon extends Fragment {

    private List<HoaDonChiTiet> hoaDonChiTietList;

    public FragmentHoaDon() {
    }

    public static FragmentHoaDon newInstance(List<HoaDonChiTiet> hoaDonChiTietList) {
        FragmentHoaDon fragment = new FragmentHoaDon();
        Bundle args = new Bundle();
        args.putSerializable("hoaDonChiTietList", (Serializable) hoaDonChiTietList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);

        if (getArguments() != null) {
            hoaDonChiTietList = (List<HoaDonChiTiet>) getArguments().getSerializable("hoaDonChiTietList");
        }
        RecyclerView recyclerView = view.findViewById(R.id.listViewHoaDon);
        HoaDonAdapter hoaDonAdapter = new HoaDonAdapter(hoaDonChiTietList);
        recyclerView.setAdapter(hoaDonAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    }
}
