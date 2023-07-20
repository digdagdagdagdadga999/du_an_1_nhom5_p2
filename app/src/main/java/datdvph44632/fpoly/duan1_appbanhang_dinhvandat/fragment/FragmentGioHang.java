package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.GioHangItem;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.HoaDonChiTiet;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.CartAdapter;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.GioHangDAO;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.HoaDonChiTietDAO;


public class FragmentGioHang extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<GioHangItem> gioHangItems;
    private  List<HoaDonChiTiet> listhd;
    private TextView totalTextView;
    private  Button checkoutButton;
    public FragmentGioHang() {
    }

    public static FragmentGioHang newInstance() {
        FragmentGioHang fragment = new FragmentGioHang();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);

        recyclerView = view.findViewById(R.id.cartRecyclerView);
        checkoutButton=view.findViewById(R.id.btndat);

        gioHangItems = new ArrayList<>();
        cartAdapter = new CartAdapter(requireContext(), gioHangItems);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));





        GioHangDAO gioHangDAO = new GioHangDAO(requireContext());
        gioHangItems = gioHangDAO.getAllProductsInCart();
        cartAdapter.updateCartItems(gioHangItems);

        totalTextView = view.findViewById(R.id.totalTextView); // Gán totalTextView từ layout
        updateTotalPriceTextView();

        cartAdapter.setQuantityChangeListener(new CartAdapter.OnQuantityChangeListener() {
            @Override
            public void onQuantityIncrease(int position,int newQuantity) {
                GioHangItem gioHangItem = gioHangItems.get(position);
                gioHangItem.increaseQuantity();
                gioHangDAO.updateProductInCart(gioHangItem);
                gioHangItems.set(position, gioHangItem);
                cartAdapter.notifyDataSetChanged();
                updateTotalPriceTextView();
                updateTotalPriceAfterQuantityChange();
            }
            @Override
            public void onQuantityDecrease(int position,int newQuantity) {
                GioHangItem gioHangItem = gioHangItems.get(position);
                gioHangItem.decreaseQuantity();
                gioHangDAO.updateProductInCart(gioHangItem);
                gioHangItems.set(position, gioHangItem);
                cartAdapter.notifyDataSetChanged();

                updateTotalPriceTextView();
                updateTotalPriceAfterQuantityChange();
            }
        });
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<HoaDonChiTiet> hoaDonChiTietList = convertToHoaDonChiTietList(gioHangItems);
                HoaDonChiTietDAO hdct = new HoaDonChiTietDAO(requireContext());
                long orderId = hdct.addHDCT(hoaDonChiTietList);

                if (orderId != -1) {
                    gioHangItems.clear();
                    cartAdapter.notifyDataSetChanged();
                    updateTotalPriceTextView();
                    updateTotalPriceAfterQuantityChange();

                    Toast.makeText(requireContext(), "Đặt hàng thành công. Mã đơn hàng: " + orderId, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Đặt hàng thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view;
    }
    private List<HoaDonChiTiet> convertToHoaDonChiTietList(List<GioHangItem> gioHangItems) {
        List<HoaDonChiTiet> resultHoaDonChiTietList = new ArrayList<>();

        String uniqueMaHoaDon = UUID.randomUUID().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = sdf.format(new Date());

        for (GioHangItem gioHangItem : gioHangItems) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();

            hoaDonChiTiet.setMaHoaDon(uniqueMaHoaDon);
            hoaDonChiTiet.setNgayMua(currentDate);
            hoaDonChiTiet.setTongTien(gioHangItem.getGiaSanPham() * gioHangItem.getQuantity());
            hoaDonChiTiet.setSoLuong(gioHangItem.getQuantity());
            hoaDonChiTiet.setGiaSanPham(gioHangItem.getGiaSanPham());
            hoaDonChiTiet.setHinhAnhSanPham(gioHangItem.getImageSanPham());
            hoaDonChiTiet.setTenSanPham(gioHangItem.getTenSanPham());

            resultHoaDonChiTietList.add(hoaDonChiTiet);
        }
        return resultHoaDonChiTietList;
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
    private void updateTotalPriceAfterQuantityChange() {
        double totalPrice = calculateTotalPrice(gioHangItems);
        totalTextView.setText("Tổng số tiền: " + totalPrice + " VNĐ");
    }


}