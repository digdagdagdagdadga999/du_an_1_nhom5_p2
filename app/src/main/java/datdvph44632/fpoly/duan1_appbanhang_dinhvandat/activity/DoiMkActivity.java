package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.NguoiDung;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.NguoiDungDao;

public class DoiMkActivity extends AppCompatActivity {

    TextInputEditText edPassOld, edPass, edRePass;
    Button btnSave, btnCancel;
    NguoiDungDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mk);

        dao = new NguoiDungDao(getApplicationContext());
        edPassOld = findViewById(R.id.edPassOld);
        edPass = findViewById(R.id.edPassChange);
        edRePass = findViewById(R.id.edRePassChange);
        btnSave = findViewById(R.id.btnSaveUserChange);
        btnCancel = findViewById(R.id.btnCancelUserChange );
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref =getSharedPreferences("account", Context.MODE_PRIVATE);
                String user = pref.getString("taikhoan", "");
                if (validate() > 0){
                    NguoiDung nguoiDung = dao.getID(user);
                    nguoiDung.setMatKhau(edPass.getText().toString());
                    if (dao.update(nguoiDung) > 0){
                        Toast.makeText(DoiMkActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                        finish();
                    }else {
                        Toast.makeText(DoiMkActivity.this, "Thay đổi mật khẩu thất bại ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private int validate(){
        int check = 1;
        if (edPassOld.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0){
            Toast.makeText(DoiMkActivity.this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            // Đọc user, pass trong SharedPreferences
            SharedPreferences pref = getSharedPreferences("account", Context.MODE_PRIVATE);
            String passOld = pref.getString("matkhau", "");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (! passOld.equals(edPassOld.getText().toString())){
                Toast.makeText(DoiMkActivity.this, "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (! pass.equals(rePass)){
                Toast.makeText(DoiMkActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}