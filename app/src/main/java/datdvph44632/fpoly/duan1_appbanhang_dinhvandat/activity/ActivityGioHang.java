package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.GioHangItem;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.CartAdapter;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.GioHangDAO;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment.FragmentBanHang;

public class ActivityGioHang extends AppCompatActivity {
    ImageView backButton;
    List<GioHangItem> gioHangItems = new ArrayList<>();
    GioHangDAO gioHangDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        backButton=findViewById(R.id.backButton);
        RecyclerView recyclerView = findViewById(R.id.cartRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CartAdapter cartAdapter = new CartAdapter(this, gioHangItems);
//        recyclerView.setAdapter(cartAdapter);
        gioHangDAO = new GioHangDAO(this);
        gioHangItems = gioHangDAO.getAllProductsInCart();

//        recyclerView.setAdapter(cartAdapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentBanHang fragmentBanHang = new FragmentBanHang();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragmentBanHang);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}