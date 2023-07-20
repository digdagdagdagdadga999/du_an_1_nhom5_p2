    package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter;

    import android.annotation.SuppressLint;
    import android.content.Context;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.Button;
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

    public class CartAdapter extends  RecyclerView.Adapter<CartAdapter.ViewHolder>{
        private Context context;
        private List<GioHangItem> gioHangItems;
        private GioHangDAO gioHangDAO;
        private OnQuantityChangeListener quantityChangeListener;


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
                byte[] image = gioHangItem.getImageSanPham();
                try {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                    holder.imgSanPham.setImageBitmap(bitmap);
                }catch (Exception e){
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

            holder.btngiam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentQuantity = gioHangItem.getQuantity();
                    if (currentQuantity > 1) {
                        int newQuantity = currentQuantity - 1;
                        gioHangItem.setQuantity(newQuantity);
                        gioHangDAO.updateProductQuantity(gioHangItem.getId(), newQuantity);
                        holder.productSo.setText(String.valueOf(newQuantity));
                        if (quantityChangeListener != null) {
                            quantityChangeListener.onQuantityDecrease(position, newQuantity);
                        }
                    }
                }
            });

            holder.btntang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentQuantity = gioHangItem.getQuantity();
                    int newQuantity = currentQuantity + 1;
                    gioHangItem.setQuantity(newQuantity);
                    gioHangDAO.updateProductQuantity(gioHangItem.getId(), newQuantity);
                    holder.productSo.setText(String.valueOf(newQuantity));
                    if (quantityChangeListener != null) {
                        quantityChangeListener.onQuantityIncrease(position, newQuantity);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return gioHangItems.size();
        }

        public interface OnQuantityChangeListener {
            void onQuantityIncrease(int position,int newQuantity);
            void onQuantityDecrease(int position,int newQuantity);

        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTenSanPham;
            TextView tvGiaSanPham;
            ImageView imgSanPham;
            ImageView btnXoa;
            Button btngiam,btntang;
            TextView productSo;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTenSanPham = itemView.findViewById(R.id.productName);
                tvGiaSanPham = itemView.findViewById(R.id.productPrice);
                imgSanPham = itemView.findViewById(R.id.productImage);
                btnXoa = itemView.findViewById(R.id.btnDelete);
                btngiam=itemView.findViewById(R.id.btngiam);
                btntang=itemView.findViewById(R.id.btntang);
                productSo = itemView.findViewById(R.id.productso);


            }
        }
    }
