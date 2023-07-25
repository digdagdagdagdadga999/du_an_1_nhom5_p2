
package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment;

import android.content.Context;
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
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.SanPham;
        import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
        import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.CartAdapter;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.DonHangAdapter;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.HoaDonChiTietDAO;

public class FragmentDonHang extends Fragment {

    private RecyclerView recyclerView;
    private Context context;
    private HoaDonChiTietDAO hoaDonChiTietDAO;
    private DonHangAdapter hoaDonAdapter;
    private List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();

    private static SanPham selectedProduct;
    public static SanPham getSelectedProduct() {
        return selectedProduct;
    }

    public static void setSelectedProduct(SanPham product) {
        selectedProduct = product;
    }
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

        hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
        hoaDonChiTietList = hoaDonChiTietDAO.getAllHoaDonChiTiet();

        hoaDonAdapter = new DonHangAdapter(context, hoaDonChiTietList);
        recyclerView.setAdapter(hoaDonAdapter);


        return view;
    }

}
