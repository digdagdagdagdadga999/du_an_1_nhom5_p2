package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.NguoiDung;

public class NguoiDungDao {
    DBHelper dbHelper;
    SharedPreferences sharedPreferences;
    public NguoiDungDao(Context context){
        dbHelper = new DBHelper(context);
    }

    public int update(NguoiDung obj){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("hoTen", obj.getHoTen());
        values.put("soDienThoai", obj.getSoDienThoai());
        values.put("email", obj.getEmail());
        values.put("taiKhoan", obj.getTaiKhoan());
        values.put("matKhau", obj.getMatKhau());
        values.put("loaiTaiKhoan", obj.getLoaiTaiKhoan());


        return db.update("NGUOIDUNG", values, "nguoiDung_id=?", new String[]{String.valueOf(obj.getNguoiDung_id())});

    }

    //get data theo id
    public NguoiDung getID(String tk){
        String sql = "SELECT * FROM NGUOIDUNG WHERE taiKhoan=?";
        List<NguoiDung> list = getData(sql, tk);
        return list.get(0);
    }

    //get data nhieu tham so
    @SuppressLint("Range")
    private List<NguoiDung> getData(String sql, String ...selectionArgs){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<NguoiDung> list  = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            NguoiDung obj = new NguoiDung();

            obj.setNguoiDung_id(cursor.getInt(0));
            obj.setHoTen(cursor.getString(1));
            obj.setSoDienThoai(cursor.getString(2));
            obj.setEmail(cursor.getString(3));
            obj.setTaiKhoan(cursor.getString(4));
            obj.setMatKhau(cursor.getString(5));
            obj.setLoaiTaiKhoan(cursor.getInt(6));

            list.add(obj);
        }
        return list;
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
