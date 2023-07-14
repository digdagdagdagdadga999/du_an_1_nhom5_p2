package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.SanPham;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;

public class SanPhamAdapter extends BaseAdapter {
    final Context context;
    List<SanPham> list;

    public SanPhamAdapter(Context context, List<SanPham> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_san_pham, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvMa = convertView.findViewById(R.id.tvMa_ItemSp);
            viewHolder.tvSanPham = convertView.findViewById(R.id.tvTenSanPham_ItemSp);
            viewHolder.tvGia = convertView.findViewById(R.id.tvGiaBan_ItemSp);
            viewHolder.tvSoluong = convertView.findViewById(R.id.tvCon_ItemSp);
            viewHolder.imgSanPham = convertView.findViewById(R.id.imageView_ItemSp);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SanPham sanPham = list.get(position);
        viewHolder.tvMa.setText("Mã : "+sanPham.getMaSanPham());
        viewHolder.tvSanPham.setText("Tên sản phẩm : "+sanPham.getTen());
        viewHolder.tvGia.setText("Giá bán : "+Math.round(sanPham.getGiaBan())+ " VNĐ");
        viewHolder.tvSoluong.setText("Còn : "+sanPham.getSoLuong());
        byte[] image = sanPham.getImage();
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            viewHolder.imgSanPham.setImageBitmap(bitmap);
        }catch (Exception e){
            viewHolder.imgSanPham.setImageResource(R.drawable.ic_sanpham1);
        }
        return convertView;
    }
    private static class ViewHolder{
        TextView tvMa, tvSanPham,tvGia, tvSoluong;
        ImageView imgSanPham;
    }
}
