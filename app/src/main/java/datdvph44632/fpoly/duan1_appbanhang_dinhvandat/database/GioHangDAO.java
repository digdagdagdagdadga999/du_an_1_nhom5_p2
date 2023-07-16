package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.GioHangItem;

public class GioHangDAO {
    public static final String TABLE_NAME = "GioHang";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEN_SANPHAM = "tenSanPham";
    public static final String COLUMN_GIA_SANPHAM = "giaSanPham";
    public static final String COLUMN_HINHANH_SANPHAM = "hinhAnhSanPham";

    public static final String SQL_GIOHANG = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_TEN_SANPHAM + " TEXT," +
            COLUMN_GIA_SANPHAM + " REAL," +
            COLUMN_HINHANH_SANPHAM + " BLOB" +
            ")";

    private SQLiteDatabase database;

    public GioHangDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }



    public long addProductToCart(String tenSanPham, double giaSanPham, byte[] hinhAnhSanPham) {
        if (isProductInCart(tenSanPham)) {
            return -1;
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEN_SANPHAM, tenSanPham);
        values.put(COLUMN_GIA_SANPHAM, giaSanPham);
        values.put(COLUMN_HINHANH_SANPHAM, hinhAnhSanPham);
        return database.insert(TABLE_NAME, null, values);
    }

    public void updateProductQuantity(int id, int quantity) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_GIA_SANPHAM, quantity);
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        database.update(TABLE_NAME, values, selection, selectionArgs);
    }


        private double calculateTotalPrice(List<GioHangItem> gioHangItems) {
            double totalPrice = 0;
            for (GioHangItem gioHangItem : gioHangItems) {
                totalPrice += gioHangItem.getGiaSanPham() * gioHangItem.getQuantity();
            }
            return totalPrice;
        }
    public void updateProductInCart(GioHangItem gioHangItem) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEN_SANPHAM, gioHangItem.getTenSanPham());
        values.put(COLUMN_GIA_SANPHAM, gioHangItem.getGiaSanPham());
        values.put(COLUMN_HINHANH_SANPHAM, gioHangItem.getImageSanPham());
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(gioHangItem.getId())};
        database.update(TABLE_NAME, values, selection, selectionArgs);
    }


    public void deleteProductFromCart(int id) {
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        database.delete(TABLE_NAME, selection, selectionArgs);
    }

    public List<GioHangItem> getAllProductsInCart() {
        List<GioHangItem> gioHangItems = new ArrayList<>();
        Cursor cursor = database.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenSanPham = cursor.getString(1);
            double giaSanPham = cursor.getDouble(2);
            byte[] hinhAnhSanPham = cursor.getBlob(3);
            gioHangItems.add(new GioHangItem(id, tenSanPham, giaSanPham, hinhAnhSanPham));
        }
        cursor.close();

        return gioHangItems;

    }
    public boolean isProductInCart(String tenSanPham) {
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_TEN_SANPHAM + " = ?";
        String[] selectionArgs = {tenSanPham};

        Cursor cursor = database.query(
                TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean isProductExists = cursor.moveToFirst();
        cursor.close();
        return isProductExists;
    }

}

