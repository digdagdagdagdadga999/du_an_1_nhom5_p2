package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.CookieHandler;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment.FragmentGioHang;

public class TTDHActivity extends AppCompatActivity {

    public static final String EXTRA_PRODUCT_NAME = "nameProduct";
    public static final String EXTRA_QUANTITY = "quantity";
    public static final String EXTRA_TOTAL_PRICE = "totalPrice";
    public static final String EXTRA_ADDRESS = "address";
    public static final String EXTRA_ORDER_DATE = "orderDate";
    public static final String EXTRA_ORDER_TIME = "orderTime";




     private TextView nameProduct,quantity,totalPrice,adress,orderDate,orderTime;
     private ImageView imageProduct;
     private Button huy,back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttdhactivity);
        nameProduct = findViewById(R.id.ttdhtensanpham);
        quantity = findViewById(R.id.txtSoLuong);
        totalPrice = findViewById(R.id.tong);
        adress = findViewById(R.id.ttdhadress);
        orderDate = findViewById(R.id.idngay);
        orderTime = findViewById(R.id.idgio);
        imageProduct = findViewById(R.id.imgttdh);


        huy=findViewById(R.id.bnthuyttdh);
        back=findViewById(R.id.back);

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCancelDialog();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentGioHang fragmentGioHang = (FragmentGioHang) getSupportFragmentManager().findFragmentById(R.id.container);
                if (fragmentGioHang != null) {
                    fragmentGioHang.orderCancelled();
                }

            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String productName = intent.getStringExtra(EXTRA_PRODUCT_NAME);
            int productQuantity = intent.getIntExtra(EXTRA_QUANTITY, 0);
            double productTotalPrice = intent.getDoubleExtra(EXTRA_TOTAL_PRICE, 0.0);
            String productAddress = intent.getStringExtra(EXTRA_ADDRESS);
            String orderDateValue = intent.getStringExtra(EXTRA_ORDER_DATE);
            String orderTimeValue = intent.getStringExtra(EXTRA_ORDER_TIME);

            nameProduct.setText("Sản phẩm: " + productName);
            quantity.setText("Số lượng: " + productQuantity);
            totalPrice.setText("Tổng tiền: " + productTotalPrice + " VNĐ");
            adress.setText("Địa chỉ: " + productAddress);
            orderDate.setText( orderDateValue);
            orderTime.setText(orderTimeValue);

            byte[] imageByteArray = intent.getByteArrayExtra("imageProduct");
            if (imageByteArray != null && imageByteArray.length > 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
                imageProduct.setImageBitmap(bitmap);
            } else {
                imageProduct.setImageResource(R.drawable.ic_sanpham1);
            }
        }
    }

    private void showCancelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận hủy đơn hàng");
        builder.setMessage("Bạn có chắc muốn hủy đơn hàng này không?");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FragmentGioHang fragmentGioHang = (FragmentGioHang) getSupportFragmentManager().findFragmentById(R.id.container);
                if (fragmentGioHang != null) {
                    fragmentGioHang.orderCancelled();
                }
                Toast.makeText(TTDHActivity.this, "Đơn hàng đã được hủy.", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

}