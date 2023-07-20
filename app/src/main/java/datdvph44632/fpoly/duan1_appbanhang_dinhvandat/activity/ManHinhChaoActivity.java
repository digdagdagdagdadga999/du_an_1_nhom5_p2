package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;

public class ManHinhChaoActivity extends AppCompatActivity {
    ImageView imgStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        imgStart=findViewById(R.id.imgViewstart);
        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), DangNhapActivity.class);
                startActivity(it);
            }
        });
    }
}