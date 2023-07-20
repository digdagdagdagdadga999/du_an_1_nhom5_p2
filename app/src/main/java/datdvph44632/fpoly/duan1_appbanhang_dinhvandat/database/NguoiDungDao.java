package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.NguoiDung;

public class NguoiDungDao {
    DBHelper dbHelper;
    SharedPreferences sharedPreferences;
    public NguoiDungDao(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<NguoiDung> getDsNguoiDung(){
        ArrayList<NguoiDung> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from NGUOIDUNG", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new NguoiDung(
                        cursor.getInt(0), //nguoiDung_id
                        cursor.getString(1), //hoTen
                        cursor.getString(2), //soDienThoai
                        cursor.getString(3), //email
                        cursor.getString(4), //taiKhoan
                        cursor.getString(5), //matKhau
                        cursor.getInt(6)     //loaiTaiKhoan
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
    public boolean themTaiKhoan(String name, String phoneNumber, String email, String username, String password, int typeAccount){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", name);
        contentValues.put("soDienThoai", phoneNumber);
        contentValues.put("email", email);
        contentValues.put("taiKhoan", username);
        contentValues.put("matKhau", password);
        contentValues.put("loaiTaiKhoan", typeAccount);
        long check = sqLiteDatabase.insert("NGUOIDUNG", null, contentValues);
        return check > 0;
    }

    public boolean kiemTraDangNhap(String taikhoan, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from NGUOIDUNG where taikhoan = ? and matkhau = ?", new String[]{taikhoan, matkhau});
        if (cursor.getCount() != 0){
            return true;
        }
        return false;
    }

}
