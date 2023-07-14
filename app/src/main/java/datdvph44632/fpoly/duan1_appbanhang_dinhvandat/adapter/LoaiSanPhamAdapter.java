package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.LoaiSanPham;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.LoaiSanPhamDAO;

public class LoaiSanPhamAdapter extends BaseAdapter {

    final Context context;
    final List<LoaiSanPham> list;
    EditText edMa, edTen;

    public LoaiSanPhamAdapter(Context context, List<LoaiSanPham> list) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_loai_san_pham, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgDelete = convertView.findViewById(R.id.imgDeleteLSP);
            viewHolder.imgUpdate = convertView.findViewById(R.id.imgUpdateLSP);
            viewHolder.tvTen = convertView.findViewById(R.id.tvTenLSP);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(context);
        final LoaiSanPham loaiSanPham = list.get(position);
        viewHolder.tvTen.setText("" + loaiSanPham.getTenLoai());
        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                b.setTitle("Xác nhận");
                b.setMessage("Bạn có đồng ý xóa loại hàng này không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        long chk = loaiSanPhamDAO.deleteLoaiSanPham(list.get(position).getMaLoai());
                        if (chk > 0) {
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            list.remove(position);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                b.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });
        viewHolder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context, android.R.style.Theme_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.activity_sua_loai_san_pham);
                dialog.show();
                edMa = dialog.findViewById(R.id.edSuaMaMatHang);
                edTen = dialog.findViewById(R.id.edSuaTenMatHang);
                edMa.setText("" + list.get(position).getMaLoai());
                edTen.setText("" + list.get(position).getTenLoai());
                ImageView imgLuu = dialog.findViewById(R.id.imgLuuSuaLSP);
                imgLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ma = edMa.getText().toString();
                        String ten = edTen.getText().toString();
                        if (ma.equalsIgnoreCase("") || ten.equalsIgnoreCase("")) {
                            Toast.makeText(context, "Dữ liệu không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        LoaiSanPham loaiSanPham1 = new LoaiSanPham(ma, ten);
                        try {
                            loaiSanPhamDAO.updateLoaiSanPham(loaiSanPham1, list.get(position).getMaLoai());
                            Toast.makeText(context, "Lưu thành công", Toast.LENGTH_SHORT).show();
                            list.set(position, loaiSanPham1);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(context, "Lưu không thành công, mã loại đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        TextView tvTen;
        ImageView imgDelete, imgUpdate;
    }
}
