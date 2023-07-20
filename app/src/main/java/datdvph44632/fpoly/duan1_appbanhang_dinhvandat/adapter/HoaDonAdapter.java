package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.DataHolder;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.GioHangItem;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.HoaDonChiTiet;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {

    private final List<HoaDonChiTiet> hoaDonChiTietList;

    public HoaDonAdapter(List<HoaDonChiTiet> hoaDonChiTietList) {
        this.hoaDonChiTietList = hoaDonChiTietList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoa_don, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietList.get(position);

        holder.txtTenSanPham.setText(hoaDonChiTiet.getTenSanPham());
        holder.txtSoLuong.setText("Số lượng: " + hoaDonChiTiet.getSoLuong());
        holder.txtmasanpham.setText("Mã hóa đơn: " + hoaDonChiTiet.getMaHoaDon());
        holder.txtngaymua.setText("Ngày mua: " + hoaDonChiTiet.getNgayMua());
        holder.txtTongTien.setText("Tổng tiền: " + hoaDonChiTiet.getTongTien() + " VNĐ");
    }

    @Override
    public int getItemCount() {
        if (hoaDonChiTietList == null) {
            return 0;
        }
        return hoaDonChiTietList.size();    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTenSanPham, txtSoLuong, txtmasanpham, txtngaymua, txtTongTien;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSanPham = itemView.findViewById(R.id.txtTenSanPham);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtmasanpham = itemView.findViewById(R.id.txtmasanpham);
            txtngaymua = itemView.findViewById(R.id.txtngaymua);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);
        }
    }
}