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
import android.widget.Toast;

import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.GioHangItem;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.SanPham;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.GioHangDAO;

public class SanPhamAdapter extends BaseAdapter {
    final Context context;
    List<SanPham> list;
    GioHangDAO gioHangDAO;
    public void updateProductList(List<SanPham> updatedList) {
        this.list = updatedList;
        notifyDataSetChanged();
    }
    public SanPhamAdapter(Context context, List<SanPham> listSanPham) {
        this.context = context;
        this.list = listSanPham;
        gioHangDAO = new GioHangDAO(context);
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
            viewHolder.carta=convertView.findViewById(R.id.carta);
            viewHolder.carta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SanPham sanPham = list.get(position);
                    String tenSanPham = sanPham.getTen();
                    double giaSanPham = sanPham.getGiaBan();
                    byte[] imageSanPham = sanPham.getImage();

                    GioHangDAO gioHangDAO = new GioHangDAO(context);
                    if (gioHangDAO.isProductInCart(tenSanPham)) {
                        Toast.makeText(context, "Sản phẩm này đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                    } else {
                        long insertedId = gioHangDAO.addProductToCart(tenSanPham, giaSanPham, imageSanPham);
                        if (insertedId != -1) {
                            Toast.makeText(context, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm sản phẩm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SanPham sanPham = list.get(position);
        viewHolder.tvMa.setText("Mã : "+sanPham.getMaSanPham());
        viewHolder.tvSanPham.setText("Tên sản phẩm : "+sanPham.getTen());
        viewHolder.tvGia.setText("Giá bán : "+Math.round(sanPham.getGiaBan())+ " VNĐ");
        int soLuongConLai = sanPham.getSoLuong() - gioHangDAO.getSoLuongDaMua(sanPham.getTen());
        viewHolder.tvSoluong.setText("Còn : "+soLuongConLai);
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
        ImageView imgSanPham,carta;
    }
}
