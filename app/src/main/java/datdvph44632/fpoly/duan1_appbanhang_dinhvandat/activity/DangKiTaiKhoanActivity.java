package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.NguoiDungDao;

public class DangKiTaiKhoanActivity extends AppCompatActivity {
    private EditText edtName_frame3, edtPhoneNumber_frame3, edtEmail_frame3,
            edtUsername_frame3, edtPassword_frame3;
    private Spinner spnTypeAccount_frame3;
    private Button btnSignUp_frame3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki_tai_khoan);
        edtName_frame3 = findViewById(R.id.edtName_frame3);
        edtPhoneNumber_frame3 = findViewById(R.id.edtPhoneNumber_frame3);
        edtEmail_frame3 = findViewById(R.id.edtEmail_frame3);
        edtUsername_frame3 = findViewById(R.id.edtUsername_frame3);
        edtPassword_frame3 = findViewById(R.id.edtPassword_frame3);
        spnTypeAccount_frame3 = findViewById(R.id.spnTypeAccount_frame3);
        btnSignUp_frame3 = findViewById(R.id.btnSignUp_frame3);

        setDataSpnTypeAccount_frame3(spnTypeAccount_frame3);
        btnSignUp_frame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themTaiKhoan();
            }
        });
    }
    public void themTaiKhoan() {
        String name = edtName_frame3.getText().toString();
        String phoneNumber = edtPhoneNumber_frame3.getText().toString();
        String email = edtEmail_frame3.getText().toString();
        String username = edtUsername_frame3.getText().toString();
        String password = edtPassword_frame3.getText().toString();
        int typeAccount;
        if (spnTypeAccount_frame3.getSelectedItem().equals("Customer")){
            typeAccount = 0;
        }else {
            typeAccount = 1;
        }
        if (validateForm(name, phoneNumber, email, username, password) == true) {
            NguoiDungDao nguoiDungDAO = new NguoiDungDao(this);
            if(nguoiDungDAO.themTaiKhoan(name, phoneNumber, email, username, password, typeAccount)){
                Toast.makeText(this, "Thêm tài khoản thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DangKiTaiKhoanActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Thêm tài khoản thất bại, hãy kiểm tra lại!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean validateForm(String name, String phoneNumber, String email, String username, String password) {
        if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Không được bỏ trống!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.endsWith("@gmail.com")) {
            Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            Toast.makeText(this, "Số điện thoại không hợp lệ!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void setDataSpnTypeAccount_frame3(Spinner spinner) {
        String[] data = {"Customer", "Admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}