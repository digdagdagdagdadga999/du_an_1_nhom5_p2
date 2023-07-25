package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.GioHangItem;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.GioHangDAO;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private List<GioHangItem> gioHangItems;
    private GioHangDAO gioHangDAO;
    private OnQuantityChangeListener quantityChangeListener;
    private OnCheckBoxChangeListener checkBoxChangeListener;

    // Interface để xử lý sự kiện thay đổi trạng thái checkbox
    public interface OnCheckBoxChangeListener {
        void onCheckBoxChanged(int position, boolean isChecked);
    }

    // Thiết lập bộ lắng nghe sự kiện thay đổi checkbox
    public void setCheckBoxChangeListener(OnCheckBoxChangeListener listener) {
        this.checkBoxChangeListener = listener;
    }

    // Interface để xử lý sự kiện thay đổi số lượng sản phẩm
    public interface OnQuantityChangeListener {
        void onQuantityIncrease(int position, int newQuantity);
        void onQuantityDecrease(int position, int newQuantity);
    }

    // Thiết lập bộ lắng nghe sự kiện thay đổi số lượng sản phẩm
    public void setQuantityChangeListener(OnQuantityChangeListener listener) {
        this.quantityChangeListener = listener;
    }

    public CartAdapter(Context context, List<GioHangItem> gioHangItems) {
        this.context = context;
        this.gioHangItems = gioHangItems;
        gioHangDAO = new GioHangDAO(context);
    }

    public void updateCartItems(List<GioHangItem> gioHangItems) {
        this.gioHangItems = gioHangItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (gioHangItems == null || gioHangItems.size() <= position) {
            return;
        }
        GioHangItem gioHangItem = gioHangItems.get(position);
        holder.tvTenSanPham.setText(gioHangItem.getTenSanPham());
        holder.tvGiaSanPham.setText(String.valueOf(gioHangItem.getGiaSanPham()));
        holder.productSo.setText(String.valueOf(gioHangItem.getQuantity()));


        holder.checkBox.setChecked(gioHangItem.isSelected());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = holder.checkBox.isChecked();
                gioHangItem.setSelected(isChecked);
                if (checkBoxChangeListener != null) {
                    checkBoxChangeListener.onCheckBoxChanged(position, isChecked);
                }
            }
        });

        byte[] image = gioHangItem.getImageSanPham();
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            holder.imgSanPham.setImageBitmap(bitmap);
        } catch (Exception e) {
            holder.imgSanPham.setImageResource(R.drawable.ic_sanpham1);
        }

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHangDAO gioHangDAO = new GioHangDAO(context);
                gioHangDAO.deleteProductFromCart(gioHangItem.getId());
                gioHangItems.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Sản phẩm đã được xóa khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });

        int soLuongDaMua = gioHangDAO.getSoLuongDaMua(gioHangItem.getTenSanPham());

        if (soLuongDaMua >= gioHangItem.getQuantity()) {
            holder.btntang.setEnabled(false);
        } else {
            holder.btntang.setEnabled(true);
        }
        holder.btntang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = gioHangItem.getQuantity() + 1;
                holder.productSo.setText(String.valueOf(newQuantity));
                if (quantityChangeListener != null) {
                    quantityChangeListener.onQuantityIncrease(position, newQuantity);
                }
            }
        });



        holder.btngiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = gioHangItem.getQuantity() - 1;
                if (newQuantity < 1) {
                    return;
                }
                holder.productSo.setText(String.valueOf(newQuantity));
                if (quantityChangeListener != null) {
                    quantityChangeListener.onQuantityDecrease(position, newQuantity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gioHangItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSanPham;
        TextView tvGiaSanPham;
        ImageView imgSanPham;
        ImageView btnXoa;
        Button btngiam, btntang;
        TextView productSo;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSanPham = itemView.findViewById(R.id.productName);
            tvGiaSanPham = itemView.findViewById(R.id.productPrice);
            imgSanPham = itemView.findViewById(R.id.productImage);
            btnXoa = itemView.findViewById(R.id.btnDelete);
            btngiam = itemView.findViewById(R.id.btngiam);
            btntang = itemView.findViewById(R.id.btntang);
            productSo = itemView.findViewById(R.id.productso);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
