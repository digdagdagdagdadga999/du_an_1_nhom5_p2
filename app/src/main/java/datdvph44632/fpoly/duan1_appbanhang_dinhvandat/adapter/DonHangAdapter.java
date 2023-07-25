package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.HoaDonChiTiet;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {
    private List<HoaDonChiTiet> hoaDonChiTietList;
    public DonHangAdapter(Context context, List<HoaDonChiTiet> hoaDonChiTietList) {
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
        holder.txtTenSanPham.setText("tên sản phẩm: " +hoaDonChiTiet.getTenSanPham());
        holder.txtSoLuong.setText("Số lượng: " + hoaDonChiTiet.getSoLuong());
        holder.diachi.setText("Địa chỉ: " + hoaDonChiTiet.getAddress());
        holder.txtTongTien.setText("Tổng tiền: " + hoaDonChiTiet.getTongTien() + " VNĐ");

        byte[] image = hoaDonChiTiet.getHinhAnhSanPham();
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            holder.imghd.setImageBitmap(bitmap);
        } catch (Exception e) {
            holder.imghd.setImageResource(R.drawable.ic_sanpham1);
        }
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
        TextView txtTenSanPham, txtSoLuong, txtngaymua, txtTongTien,diachi;
        ImageView imghd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSanPham = itemView.findViewById(R.id.txtTenSanPham);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);
            diachi=itemView.findViewById(R.id.item_addres);
            imghd=itemView.findViewById(R.id.imghd);
        }
    }
}