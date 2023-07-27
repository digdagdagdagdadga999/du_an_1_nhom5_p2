package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.HoaDonChiTiet;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {
    private List<HoaDonChiTiet> hoaDonChiTietList;
    private OnItemClickListener mListener;
    private OnSulyClickListener sulyClickListener;
    public interface OnSulyClickListener {
        void onSulyClick(HoaDonChiTiet hoaDonChiTiet);
    }


    public void setOnSulyClickListener(OnSulyClickListener listener) {
        this.sulyClickListener = listener;
    }
    public DonHangAdapter(Context context, List<HoaDonChiTiet> hoaDonChiTietList) {
        this.hoaDonChiTietList = hoaDonChiTietList;
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener mahdct) {
        this.mListener = mahdct;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoa_don, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietList.get(position);
        holder.txtTenSanPham.setText("sản phẩm: " +hoaDonChiTiet.getTenSanPham());
        holder.txtSoLuong.setText("Số lượng: " + hoaDonChiTiet.getSoLuong());
        holder.txtTongTien.setText("Tổng tiền: " + hoaDonChiTiet.getTongTien() + " VNĐ");
//
//        holder.orderDate.setText("Ngày đặt hàng: " + hoaDonChiTiet.getOrderDate());
//        holder.orderTime.setText("Giờ đặt hàng: " + hoaDonChiTiet.getOrderTime());

        String address = hoaDonChiTiet.getAddress();

//        if (address != null) {
//            holder.diachi.setText("Địa chỉ: " + address);
//        } else {
//            holder.diachi.setText("Địa chỉ: N/A");
//        }
        byte[] imageByteArray = hoaDonChiTiet.getHinhAnhSanPham();
        if (imageByteArray != null && imageByteArray.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            holder.imghd.setImageBitmap(bitmap);
        } else {

            holder.imghd.setImageResource(R.drawable.ic_sanpham1);
        }
        holder.bntsuly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sulyClickListener != null) {
                    sulyClickListener.onSulyClick(hoaDonChiTiet);
                }
            }
        });
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
        TextView txtTenSanPham, txtSoLuong, txtTongTien,diachi,orderDate,orderTime;
        ImageView imghd;
        Button bntsuly;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSanPham = itemView.findViewById(R.id.txtTenSanPham);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);
//            diachi=itemView.findViewById(R.id.item_addres);
            imghd=itemView.findViewById(R.id.imghd);
            bntsuly=itemView.findViewById(R.id.bntsuly);
//
//            orderTime=itemView.findViewById(R.id.orderTime);
//            orderDate=itemView.findViewById(R.id.orderDate);
        }
    }
}