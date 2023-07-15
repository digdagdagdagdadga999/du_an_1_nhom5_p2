package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.KhachHang;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.adapter.KhachHangAdapter;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.KhachHangDAO;

public class KhachHangActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    EditText edTimKiem;
    ListView lvList;
    List<KhachHang> list;
    KhachHangDAO khachHangDAO;
    KhachHangAdapter khachHangAdapter;
    TextView tvKhongCoNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);

        toolbar = findViewById(R.id.toolbar_nguoi_dung);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        anhXaView();
        doDuLieu();
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(KhachHangActivity.this, ChiTietKhachHangActivity.class);
                intent.putExtra("phoneNumber", list.get(i).getSoDienThoai());
                Log.e("SĐT", "" + list.get(i).getSoDienThoai());
                startActivity(intent);
            }
        });
        edTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<KhachHang> list = khachHangDAO.timKiemKhachHang(edTimKiem.getText().toString());
                KhachHangAdapter khachHangAdapter = new KhachHangAdapter(KhachHangActivity.this, list);
                lvList.setAdapter(khachHangAdapter);
                tvKhongCoNguoiDung.setVisibility(View.INVISIBLE);
                if (edTimKiem.getText().toString().equalsIgnoreCase("")) {
                    doDuLieu();
                }
                if (list.size() <= 0) {
                    tvKhongCoNguoiDung.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void doDuLieu() {
        list = khachHangDAO.getAllKhachHang();
        khachHangAdapter = new KhachHangAdapter(this, list);
        lvList.setAdapter(khachHangAdapter);
    }

    public void NguoiDungThem(View view) {
        Intent intent = new Intent(KhachHangActivity.this, ThemKhachHangActivity.class);
        startActivity(intent);
    }

    public void anhXaView() {
        edTimKiem = findViewById(R.id.edTimKiemNguoiDung);
        lvList = findViewById(R.id.lvNguoiDung);
        list = new ArrayList<>();
        khachHangDAO = new KhachHangDAO(this);
        tvKhongCoNguoiDung = findViewById(R.id.tvKhongCoNguoiDung);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}