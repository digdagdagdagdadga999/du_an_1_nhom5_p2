package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.BitmapUtility;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.HoaDonChiTiet;

public class HoaDonChiTietDAO {
    public static final String TABLE_NAME = "HoaDonChiTiet";
    public static final String SQL_HDCT = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" +
            "maHDCT INTEGER PRIMARY KEY AUTOINCREMENT," +
            "maHoaDon TEXT," +
            "ngayMua TEXT," +
            "tongTien DOUBLE," +
            "soLuong INTEGER," +
            "giaSanPham DOUBLE," +
            "hinhAnhSanPham BLOB" +
            ")";
    private final SQLiteDatabase sqLiteDatabase;

    public HoaDonChiTietDAO(Context context) {
        DBHelper mydatabase = new DBHelper(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }

    public List<HoaDonChiTiet> getAllHoaDonChiTiet() {
        List<HoaDonChiTiet> list = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int maHDCT = cursor.getInt(0);
                String maHd = cursor.getString(1);
                String ngayMua = cursor.getString(2);
                double tongTien = cursor.getDouble(3);
                int soLuong = cursor.getInt(4);
                double giaSanPham = cursor.getDouble(5);
                byte[] hinhAnhSanPham = cursor.getBlob(6);

                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setMaHDCT(maHDCT);
                hoaDonChiTiet.setMaHoaDon(maHd);
                hoaDonChiTiet.setNgayMua(ngayMua);
                hoaDonChiTiet.setTongTien(tongTien);
                hoaDonChiTiet.setSoLuong(soLuong);
                hoaDonChiTiet.setGiaSanPham(giaSanPham);
                hoaDonChiTiet.setHinhAnhSanPham(hinhAnhSanPham);

                list.add(hoaDonChiTiet);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return list;
    }

    public int updateHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon", hoaDonChiTiet.getMaHoaDon());
        contentValues.put("ngayMua", hoaDonChiTiet.getNgayMua());
        contentValues.put("tongTien", hoaDonChiTiet.getTongTien());
        contentValues.put("soLuong", hoaDonChiTiet.getSoLuong());
        contentValues.put("giaSanPham", hoaDonChiTiet.getGiaSanPham());
        contentValues.put("hinhAnhSanPham", hoaDonChiTiet.getHinhAnhSanPham());

        return sqLiteDatabase.update(TABLE_NAME, contentValues, "maHDCT = ?", new String[]{String.valueOf(hoaDonChiTiet.getMaHDCT())});
    }



    public long insertHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon", hoaDonChiTiet.getMaHoaDon());
        contentValues.put("ngayMua", hoaDonChiTiet.getNgayMua());
        contentValues.put("tongTien", hoaDonChiTiet.getTongTien());
        contentValues.put("soLuong", hoaDonChiTiet.getSoLuong());
        contentValues.put("giaSanPham", hoaDonChiTiet.getGiaSanPham());
        contentValues.put("hinhAnhSanPham", hoaDonChiTiet.getHinhAnhSanPham());
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public long updateHDCT(HoaDonChiTiet hoaDonChiTiet, String maHDCT) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon", hoaDonChiTiet.getMaHoaDon());
        contentValues.put("ngayMua", hoaDonChiTiet.getNgayMua());
        contentValues.put("tongTien", hoaDonChiTiet.getTongTien());
        contentValues.put("soLuong", hoaDonChiTiet.getSoLuong());
        contentValues.put("giaSanPham", hoaDonChiTiet.getGiaSanPham());
        contentValues.put("hinhAnhSanPham", hoaDonChiTiet.getHinhAnhSanPham());

        return sqLiteDatabase.update(TABLE_NAME, contentValues, "maHDCT = ?", new String[]{maHDCT});
    }

    public long deleteHDCT(String maHDCT) {
        return sqLiteDatabase.delete(TABLE_NAME, "maHDCT = ?", new String[]{maHDCT});
    }

    public List<HoaDonChiTiet> getAllHDCT(String maHD) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        String query = " SELECT * FROM " + TABLE_NAME + " WHERE maHoaDon = '" + maHD + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int maHDCT = cursor.getInt(0);
                String maHd = cursor.getString(1);
                String ngayMua = cursor.getString(2);
                double tongTien = cursor.getDouble(3);
                int soLuong = cursor.getInt(4);
                double giaSanPham = cursor.getDouble(5);
                byte[] hinhAnhSanPham = cursor.getBlob(6);

                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setMaHDCT(maHDCT);
                hoaDonChiTiet.setMaHoaDon(maHd);
                hoaDonChiTiet.setNgayMua(ngayMua);
                hoaDonChiTiet.setTongTien(tongTien);
                hoaDonChiTiet.setSoLuong(soLuong);
                hoaDonChiTiet.setGiaSanPham(giaSanPham);
                hoaDonChiTiet.setHinhAnhSanPham(hinhAnhSanPham);

                list.add(hoaDonChiTiet);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return list;
    }
}