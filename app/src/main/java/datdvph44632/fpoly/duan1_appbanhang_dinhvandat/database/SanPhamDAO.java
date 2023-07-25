package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.SanPham;

public class SanPhamDAO {
    public static final String TABLE_NAME = "SanPham";
    public static final String SQL_SANPHAM = "Create table if not exists SanPham(" +
            "maSanPham TEXT PRIMARY KEY," +
            "maLoai TEXT," +
            "tenSanPham TEXT," +
            "soLuong INTEGER," +
            "giaNhap DOUBLE," +
            "giaBan DOUBLE," +
            "hinhAnh BLOB)";

    public static final String DATA_SANPHAM = "INSERT INTO SanPham VALUES " +
            "(01, 'áo polo', 'áo polo nam DIZI', 20, 150000, 300000, 'R.drawable.san_pham_1')," +
            "(02, 'áo thun', 'áo thun nam cao cấp', 40, 200000, 500000, 'R.drawable.san_pham_2')," +
            "(03, 'quần thun', 'quần thun nam jogger', 50, 200000, 400000, 'R.drawable.san_pham_3')";

    private final SQLiteDatabase sqLiteDatabase;

    public SanPhamDAO(Context context) {
        DBHelper mydatabase = new DBHelper(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }

    public long addSanPham(SanPham sanPham) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSanPham", sanPham.getMaSanPham());
        contentValues.put("maLoai", sanPham.getMaLoai());
        contentValues.put("tenSanPham", sanPham.getTen());
        contentValues.put("soLuong", sanPham.getSoLuong());
        contentValues.put("giaNhap", sanPham.getGiaNhap());
        contentValues.put("giaBan", sanPham.getGiaBan());
        contentValues.put("hinhAnh", sanPham.getImage());
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public long updateSanPham(SanPham sanPham, String ma) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSanPham", sanPham.getMaSanPham());
        contentValues.put("maLoai", sanPham.getMaLoai());
        contentValues.put("tenSanPham", sanPham.getTen());
        contentValues.put("soLuong", sanPham.getSoLuong());
        contentValues.put("giaNhap", sanPham.getGiaNhap());
        contentValues.put("giaBan", sanPham.getGiaBan());
        contentValues.put("hinhAnh", sanPham.getImage());
        return sqLiteDatabase.update(TABLE_NAME, contentValues, "maSanPham = ?", new String[]{ma});
    }

    public long updateSLSanPham(int soLuong, String ma) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("soLuong", soLuong);
        return sqLiteDatabase.update(TABLE_NAME, contentValues, "maSanPham = ?", new String[]{ma});
    }

    public long deleteSanPham(String ma) {
        return sqLiteDatabase.delete(TABLE_NAME, "maSanPham = ?", new String[]{ma});
    }

    public List<SanPham> getAllSanPham() {
        List<SanPham> list = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String maSP = cursor.getString(0);
                String maLoai = cursor.getString(1);
                String ten = cursor.getString(2);
                int soLuong = cursor.getInt(3);
                double giaNhap = cursor.getDouble(4);
                double giaBan = cursor.getDouble(5);
                byte[] img = cursor.getBlob(6);
                SanPham sanPham = new SanPham(maSP, maLoai, ten, giaNhap, giaBan, img, soLuong);
                list.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public SanPham getSanPhamTheoMa(String ma) {
        String query = "select * from " + TABLE_NAME + " where maSanPham = '" + ma + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        SanPham sanPham = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String maSP = cursor.getString(0);
            String maLoai = cursor.getString(1);
            String ten = cursor.getString(2);
            int soLuong = cursor.getInt(3);
            double giaNhap = cursor.getDouble(4);
            double giaBan = cursor.getDouble(5);
            byte[] img = cursor.getBlob(6);
            sanPham = new SanPham(maSP, maLoai, ten, giaNhap, giaBan, img, soLuong);
        }
        cursor.close();
        return sanPham;
    }

    public List<SanPham> getAllSanPhamTheoMa(String ma) {
        List<SanPham> list = new ArrayList<>();
        String query = "select DISTINCT * from " + TABLE_NAME + " where maSanPham like '%" + ma + "%' or tenSanPham like '%" + ma + "%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        SanPham sanPham;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String maSP = cursor.getString(0);
            String maLoai = cursor.getString(1);
            String tensp = cursor.getString(2);
            int soLuong = cursor.getInt(3);
            double giaNhap = cursor.getDouble(4);
            double giaBan = cursor.getDouble(5);
            byte[] img = cursor.getBlob(6);
            sanPham = new SanPham(maSP, maLoai, tensp, giaNhap, giaBan, img, soLuong);
            list.add(sanPham);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<SanPham> getAllSanPhamTheoTen() {
        List<SanPham> list = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME + " order by tenSanPham asc";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String maSP = cursor.getString(0);
                String maLoai = cursor.getString(1);
                String ten = cursor.getString(2);
                int soLuong = cursor.getInt(3);
                double giaNhap = cursor.getDouble(4);
                double giaBan = cursor.getDouble(5);
                byte[] img = cursor.getBlob(6);
                SanPham sanPham = new SanPham(maSP, maLoai, ten, giaNhap, giaBan, img, soLuong);
                list.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public List<SanPham> getAllSanPhamTheoGiaTangDan() {
        List<SanPham> list = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME + " order by giaBan asc";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String maSP = cursor.getString(0);
                String maLoai = cursor.getString(1);
                String ten = cursor.getString(2);
                int soLuong = cursor.getInt(3);
                double giaNhap = cursor.getDouble(4);
                double giaBan = cursor.getDouble(5);
                byte[] img = cursor.getBlob(6);
                SanPham sanPham = new SanPham(maSP, maLoai, ten, giaNhap, giaBan, img, soLuong);
                list.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public List<SanPham> getAllSanPhamTheoGiaGiamDan() {
        List<SanPham> list = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME + " order by giaBan desc";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String maSP = cursor.getString(0);
                String maLoai = cursor.getString(1);
                String ten = cursor.getString(2);
                int soLuong = cursor.getInt(3);
                double giaNhap = cursor.getDouble(4);
                double giaBan = cursor.getDouble(5);
                byte[] img = cursor.getBlob(6);
                SanPham sanPham = new SanPham(maSP, maLoai, ten, giaNhap, giaBan, img, soLuong);
                list.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

}
