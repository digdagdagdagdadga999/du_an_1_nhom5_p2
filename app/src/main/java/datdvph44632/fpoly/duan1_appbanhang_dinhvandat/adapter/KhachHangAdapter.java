package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.KhachHang;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;

public class KhachHangAdapter extends BaseAdapter {

    final Context context;
    final List<KhachHang> list;

    public KhachHangAdapter(Context context, List<KhachHang> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_thiet_lap_khach_hang, parent, false);
            viewHolder.imgHinhAnh = convertView.findViewById(R.id.imgNguoiDung);
            viewHolder.tvTen = convertView.findViewById(R.id.tvTenKhachHang);
            viewHolder.tvSDT = convertView.findViewById(R.id.tvSDT);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        KhachHang khachHang = list.get(position);
        viewHolder.tvTen.setText("Họ tên : "+khachHang.getTen());
        viewHolder.tvSDT.setText("Số điện thoại : "+khachHang.getSoDienThoai());
        viewHolder.imgHinhAnh.setImageResource(R.drawable.profile);
        return convertView;
    }

    private static class ViewHolder{
        ImageView imgHinhAnh;
        TextView tvTen,tvSDT;
    }
}
