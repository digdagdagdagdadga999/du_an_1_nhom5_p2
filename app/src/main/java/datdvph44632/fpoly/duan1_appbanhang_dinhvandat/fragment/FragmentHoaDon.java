package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.HoaDonChiTiet;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.SanPham;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.HoaDonChiTietDAO;

public class FragmentHoaDon extends Fragment {

    private RecyclerView recyclerView;
    private HoaDonAdapter hoaDonAdapter;
    private List<HoaDonChiTiet> hoaDonChiTietList;
    private HoaDonChiTietDAO hoaDonChiTietDAO;
    private static SanPham selectedProduct;
    public static SanPham getSelectedProduct() {
        return selectedProduct;
    }

    public static void setSelectedProduct(SanPham product) {
        selectedProduct = product;
    }
    public FragmentHoaDon() {
    }

    public static FragmentHoaDon newInstance() {
        return new FragmentHoaDon();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);

        recyclerView = view.findViewById(R.id.listViewHoaDon);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
        hoaDonChiTietList = hoaDonChiTietDAO.getAllHoaDonChiTiet();
        hoaDonAdapter = new HoaDonAdapter(hoaDonChiTietList);
        recyclerView.setAdapter(hoaDonAdapter);

        return view;
    }

    private class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonViewHolder> {
        private List<HoaDonChiTiet> hoaDonList;

        public HoaDonAdapter(List<HoaDonChiTiet> hoaDonList) {
            this.hoaDonList = hoaDonList;
        }

        @NonNull
        @Override
        public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoa_don, parent, false);
            return new HoaDonViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
            HoaDonChiTiet hoaDonChiTiet = hoaDonList.get(position);
            holder.txtTenSanPham.setText("tên sản phẩm: " +hoaDonChiTiet.getTenSanPham());
            holder.txtSoLuong.setText("Số lượng: " + hoaDonChiTiet.getSoLuong());
            holder.txtmasanpham.setText("Mã hóa đơn: " + hoaDonChiTiet.getMaHoaDon());
            holder.txtngaymua.setText("Ngày mua: " + hoaDonChiTiet.getNgayMua());
            holder.txtngaymua.setText("diachi: " + hoaDonChiTiet.getAddress());
            holder.txtTongTien.setText("Tổng tiền: " + hoaDonChiTiet.getTongTien() + " VNĐ");
        }

        @Override
        public int getItemCount() {
            return hoaDonList.size();
        }

        public class HoaDonViewHolder extends RecyclerView.ViewHolder {
            TextView txtTenSanPham, txtSoLuong, txtmasanpham, txtngaymua, txtTongTien,diachi;

            public HoaDonViewHolder(View itemView) {
                super(itemView);
                txtTenSanPham = itemView.findViewById(R.id.txtTenSanPham);
                txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
                txtmasanpham = itemView.findViewById(R.id.txtmasanpham);
                txtngaymua = itemView.findViewById(R.id.txtngaymua);
                txtTongTien = itemView.findViewById(R.id.txtTongTien);
                diachi=itemView.findViewById(R.id.item_addres);
            }
        }
    }
}
