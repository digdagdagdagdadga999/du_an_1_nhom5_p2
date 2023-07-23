package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.HoaDonChiTiet;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    private List<HoaDonChiTiet> hoaDonChiTietList;
    public HoaDonAdapter(Context context, List<HoaDonChiTiet> hoaDonChiTietList) {
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
        holder.txtTenSanPham.setText("Tên ao:" + hoaDonChiTiet.getTenSanPham());
        holder.txtSoLuong.setText("Số lượng: " + hoaDonChiTiet.getSoLuong());
        holder.txtmasanpham.setText("Mã hóa đơn: " + hoaDonChiTiet.getMaHoaDon());
        holder.txtngaymua.setText("Ngày mua: " + hoaDonChiTiet.getNgayMua());
        holder.txtDiaChi.setText("Địa chỉ: " + hoaDonChiTiet.getAddress());
        holder.txtTongTien.setText("Tổng tiền: " + hoaDonChiTiet.getTongTien() + " VNĐ");
    }

    @Override
    public int getItemCount() {
        if (hoaDonChiTietList == null) {
            return 0;
        }
        return hoaDonChiTietList.size();    }
    public void updateHoaDonChiTiet(List<HoaDonChiTiet> hoaDonChiTietList) {
        if (this.hoaDonChiTietList == null) {
            this.hoaDonChiTietList = new ArrayList<>();
        }
        this.hoaDonChiTietList.clear();
        this.hoaDonChiTietList.addAll(hoaDonChiTietList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTenSanPham, txtSoLuong, txtmasanpham, txtngaymua, txtTongTien,txtDiaChi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSanPham = itemView.findViewById(R.id.txtTenSanPham);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtmasanpham = itemView.findViewById(R.id.txtmasanpham);
            txtngaymua = itemView.findViewById(R.id.txtngaymua);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);
            txtDiaChi=itemView.findViewById(R.id.item_addres);
        }
    }
}