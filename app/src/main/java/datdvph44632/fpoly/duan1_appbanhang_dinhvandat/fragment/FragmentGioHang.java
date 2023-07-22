package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment;

import static java.util.Collections.*;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.GioHangItem;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.HoaDonChiTiet;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.SanPham;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.CartAdapter;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.HoaDonAdapter;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.GioHangDAO;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.HoaDonChiTietDAO;


public class FragmentGioHang extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<GioHangItem> gioHangItems;
    private List<SanPham> danhSachSanPhamDaMua;

    private TextView totalTextView;
    private Button checkoder;
    private OnOrderPlacedListener orderPlacedListener;

    public interface OnOrderPlacedListener {
        void onOrderPlaced();
    }

    public void setOnOrderPlacedListener(OnOrderPlacedListener listener) {
        this.orderPlacedListener = listener;
    }
    public FragmentGioHang() {
    }

    public static FragmentGioHang newInstance() {
        FragmentGioHang fragment = new FragmentGioHang();
        return fragment;
    }
    public static FragmentGioHang newInstance(List<SanPham> danhSachSanPhamDaMua) {
        FragmentGioHang fragment = new FragmentGioHang();
//        Bundle args = new Bundle();
//        args.putParcelableArrayList("danhSachSanPhamDaMua", new ArrayList<>(Collections.<SanPham>unmodifiableList(danhSachSanPhamDaMua)));
//        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);

        checkoder = view.findViewById(R.id.checkoder);
        recyclerView = view.findViewById(R.id.cartRecyclerView);

        gioHangItems = new ArrayList<>();
        cartAdapter = new CartAdapter(requireContext(), gioHangItems);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        GioHangDAO gioHangDAO = new GioHangDAO(requireContext());
        gioHangItems = gioHangDAO.getAllProductsInCart();
        cartAdapter.updateCartItems(gioHangItems);

        totalTextView = view.findViewById(R.id.totalTextView);
        updateTotalPriceTextView();

        checkoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gioHangItems.size() > 0) {
                    GioHangItem gioHangItem = gioHangItems.get(0);
                    showOrderDialog(gioHangItem.getTenSanPham(), gioHangItem.getGiaSanPham(), gioHangItem.getQuantity(), gioHangItem.getTotalPrice());
                } else {
                    Toast.makeText(requireContext(), "No items in the cart.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Bundle args = getArguments();
        if (args != null) {
            List<SanPham> danhSachSanPhamDaMua = unmodifiableList(args.getParcelableArrayList("danhSachSanPhamDaMua"));
        }


        cartAdapter.setQuantityChangeListener(new CartAdapter.OnQuantityChangeListener() {
            @Override
            public void onQuantityIncrease(int position, int newQuantity) {
                GioHangItem gioHangItem = gioHangItems.get(position);
                gioHangItem.increaseQuantity();
                gioHangDAO.updateProductInCart(gioHangItem);
                gioHangItems.set(position, gioHangItem);
                cartAdapter.notifyDataSetChanged();
                updateTotalPriceTextView();
            }

            @Override
            public void onQuantityDecrease(int position, int newQuantity) {
                GioHangItem gioHangItem = gioHangItems.get(position);
                gioHangItem.decreaseQuantity();
                gioHangDAO.updateProductInCart(gioHangItem);
                gioHangItems.set(position, gioHangItem);
                cartAdapter.notifyDataSetChanged();
                cartAdapter.notifyDataSetChanged();
                updateTotalPriceTextView();
            }
        });

        return view;
    }

    private void updateTotalPriceTextView() {
        double totalPrice = calculateTotalPrice(gioHangItems);
        totalTextView.setText("Tổng số tiền: " + totalPrice + " VNĐ");
    }

    private double calculateTotalPrice(List<GioHangItem> gioHangItems) {
        double totalPrice = 0;
        for (GioHangItem gioHangItem : gioHangItems) {
            totalPrice += gioHangItem.getGiaSanPham() * gioHangItem.getQuantity();
        }
        return totalPrice;
    }

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    private void showOrderDialog(String productName, double price, int quantity, double total) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.oder_hoadon, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        TextView productNameTextView = view.findViewById(R.id.productNameTextView);
        productNameTextView.setText(productName);

        TextView priceTextView = view.findViewById(R.id.priceTextView);
        priceTextView.setText("Price: " + String.valueOf(price) + " đ");

        TextView quantityTextView = view.findViewById(R.id.quantityTextView);
        quantityTextView.setText("Quantity: " + String.valueOf(quantity));

        TextView totalTextView = view.findViewById(R.id.totalTextView);
        totalTextView.setText("Total: " + String.valueOf(total) + " đ");

        TextInputLayout addressTextInputLayout = view.findViewById(R.id.address);
        Button oderButton = view.findViewById(R.id.btnoder);
        oderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText addressTextInputEditText = view.findViewById(R.id.addres);
                String address = addressTextInputEditText.getText().toString().trim();
                if (TextUtils.isEmpty(address)) {
                    addressTextInputLayout.setError("Please enter the address");
                } else {
                    addressTextInputLayout.setError(null);
                    Toast.makeText(requireContext(), "Order placed successfully!", Toast.LENGTH_SHORT).show();

                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                    hoaDonChiTiet.setTenSanPham(productName);
                    hoaDonChiTiet.setGiaSanPham(price);
                    hoaDonChiTiet.setSoLuong(quantity);
                    hoaDonChiTiet.setTongTien(total);
                    hoaDonChiTiet.setAddress(address);

                    GioHangDAO gioHangDAO = new GioHangDAO(requireContext());

                    List<GioHangItem> gioHangItems = gioHangDAO.getAllProductsInCart();

                    HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(requireContext());
                    hoaDonChiTietDAO.insertHoaDonChiTiet(hoaDonChiTiet);

                    if (!gioHangItems.isEmpty()) {
                        gioHangDAO.deleteProductFromCart(gioHangItems.get(0).getId());
                    }
                    dialog.dismiss();
                    cartAdapter.updateCartItems(new ArrayList<>());

                    if (orderPlacedListener != null) {
                        orderPlacedListener.onOrderPlaced();
                    }

                }
                if (orderPlacedListener != null) {
                    orderPlacedListener.onOrderPlaced();
                }

                dialog.dismiss();
                cartAdapter.updateCartItems(new ArrayList<>());
            }
        });
        dialog.show();
    }
}
