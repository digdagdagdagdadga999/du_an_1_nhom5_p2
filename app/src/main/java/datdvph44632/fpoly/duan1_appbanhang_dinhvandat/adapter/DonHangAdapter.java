package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.GioHang;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;

public class DonHangAdapter extends BaseAdapter {
    final Context context;
    final List<GioHang> list;

    public DonHangAdapter(Context context, List<GioHang> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_don_hang, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvSanPham = convertView.findViewById(R.id.tvSanPhamGH);
            viewHolder.tvGia = convertView.findViewById(R.id.tvGiaGH);
            viewHolder.tvSoluong = convertView.findViewById(R.id.tvSoLuongGH);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GioHang gioHang = list.get(position);
        viewHolder.tvSanPham.setText(""+gioHang.getTen());
        viewHolder.tvGia.setText(""+Math.round(gioHang.getSoLuong()*gioHang.getGia()));
        viewHolder.tvSoluong.setText(""+gioHang.getSoLuong());
        return convertView;
    }

    private static class ViewHolder{
        TextView tvSanPham,tvSoluong,tvGia;
    }
}
