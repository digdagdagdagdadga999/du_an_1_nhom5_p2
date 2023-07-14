package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.SanPham;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.LoaiSanPhamDAO;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.SanPhamDAO;

public class ThemSanPhamActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgThemAnh;
    Spinner spnDanhMuc;
    EditText edMa, edTen, edSoLuong, edGiaBan, edGiaNhap;
    String ma, ten, soLuong, giaBan, giaNhap, theLoai;
    List<String> listTheLoai;
    SanPhamDAO sanPhamDAO;
    LinearLayout lnThem;
    byte[] hinhAnh;
    final int REQUEST_CODE_FOLDER = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);

        anhXaView();
        toolbar = findViewById(R.id.toolbar_them_san_pham);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sanPhamDAO = new SanPhamDAO(this);
        //Đổ dữu liệu cho spinner:

        listTheLoai = new ArrayList<>();
        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(this);
        listTheLoai = loaiSanPhamDAO.getAllTenLoaiSanPham();
        ArrayAdapter adapterTheLoai = new ArrayAdapter(this, R.layout.spinner_item, listTheLoai);
        spnDanhMuc.setAdapter(adapterTheLoai);

        spnDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                theLoai = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //sự kiện load ảnh
        imgThemAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(ThemSanPhamActivity.this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_FOLDER);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            InputStream inputStream;
            try {
                inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgThemAnh.setImageBitmap(bitmap);
                //
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //sự kiên action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(ThemSanPhamActivity.this, SanPhamActivity.class));
    }

    //ánh xạ
    public void anhXaView() {
        imgThemAnh = findViewById(R.id.imgThemMatHang);
        edMa = findViewById(R.id.edSuaMaMatHang);
        edTen = findViewById(R.id.edThemTenMatHang);
        edSoLuong = findViewById(R.id.edThemSoLuong);
        edGiaBan = findViewById(R.id.edThemGiaBan);
        edGiaNhap = findViewById(R.id.edThemGiaNhap);
        spnDanhMuc = findViewById(R.id.spnThemDanhMuc);
        lnThem = findViewById(R.id.lnThem);


    }


    public void ThemSanPhamLuu(View view) {
        ma = edMa.getText().toString();
        ten = edTen.getText().toString();
        soLuong = edSoLuong.getText().toString();
        giaBan = edGiaBan.getText().toString();
        giaNhap = edGiaNhap.getText().toString();
        if (ma.equalsIgnoreCase("") || ten.equalsIgnoreCase("") || soLuong.equalsIgnoreCase("")
                || giaBan.equalsIgnoreCase("") || giaNhap.equalsIgnoreCase("")) {
            Snackbar snackbar = Snackbar
                    .make(lnThem, "Vui lòng điền chính xác thông tin", Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        try {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imgThemAnh.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray);
            hinhAnh = byteArray.toByteArray();

            if (hinhAnh.length > 2000000) {
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
                Bitmap resized = Bitmap.createScaledBitmap(bitmap1, (int) (bitmap1.getWidth() * 0.1), (int) (bitmap1.getHeight() * 0.1), true);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                resized.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                hinhAnh = stream.toByteArray();
            }
        } catch (Exception ignored) {

        }
        SanPham sanPham = new SanPham(ma, theLoai, ten, Double.parseDouble(giaNhap), Double.parseDouble(giaBan), hinhAnh, Integer.parseInt(soLuong));
        long chk = sanPhamDAO.addSanPham(sanPham);
        if (chk > 0) {
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            finish();
            Intent intent = new Intent(this, SanPhamActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Thêm thất bại , mã sản phẩm đã tồn tại", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_FOLDER) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                //noinspection deprecation
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        }
    }


}