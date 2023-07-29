package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.ttdhitem;

public class TTDHDAO {
    public static final String TABLE_NAME = "TTDHDAO";
    public static final String SQL_TTDH = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nameProduct TEXT," +
            "imageProduct BLOB," +
            "quantity INTEGER," +
            "totalPrice DOUBLE," +
            "address TEXT," +
            "orderDate TEXT," +
            "orderTime TEXT)";
    private final SQLiteDatabase sqLiteDatabase;

    public TTDHDAO(Context context) {
        DBHelper mydatabase = new DBHelper(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }

    public List<ttdhitem> getAllTTDHItems() {
        List<ttdhitem> list = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    ttdhitem ttdhItem = extractTTDHItemFromCursor(cursor);
                    list.add(ttdhItem);
                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;
    }

    public long insertTTDHItem(ttdhitem ttdhItem) {
        ContentValues contentValues = getContentValuesFromTTDHItem(ttdhItem);
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public int updateTTDHItem(ttdhitem ttdhItem) {
        ContentValues contentValues = getContentValuesFromTTDHItem(ttdhItem);
        return sqLiteDatabase.update(TABLE_NAME, contentValues, "id = ?", new String[]{String.valueOf(ttdhItem.getId())});
    }

    public long deleteTTDHItem(int id) {
        return sqLiteDatabase.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});
    }

    private ttdhitem extractTTDHItemFromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        String nameProduct = cursor.getString(cursor.getColumnIndexOrThrow("nameProduct"));
        byte[] imageProduct = cursor.getBlob(cursor.getColumnIndexOrThrow("imageProduct"));
        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
        double totalPrice = cursor.getDouble(cursor.getColumnIndexOrThrow("totalPrice"));
        String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
        String orderDate = cursor.getString(cursor.getColumnIndexOrThrow("orderDate"));
        String orderTime = cursor.getString(cursor.getColumnIndexOrThrow("orderTime"));

        ttdhitem ttdhItem = new ttdhitem();
        ttdhItem.setId(id);
        ttdhItem.setNameProduct(nameProduct);
        ttdhItem.setImageProduct(imageProduct);
        ttdhItem.setQuantity(quantity);
        ttdhItem.setTotalPrice(totalPrice);
        ttdhItem.setAdress(address);
        ttdhItem.setOrderTime(orderTime);
        ttdhItem.setOrderDate(orderDate);
        return ttdhItem;
    }

    private ContentValues getContentValuesFromTTDHItem(ttdhitem ttdhItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameProduct", ttdhItem.getNameProduct());
        contentValues.put("imageProduct", ttdhItem.getImageProduct());
        contentValues.put("quantity", ttdhItem.getQuantity());
        contentValues.put("totalPrice", ttdhItem.getTotalPrice());
        contentValues.put("address", ttdhItem.getAdress());
        contentValues.put("orderDate", ttdhItem.getOrderDate());
        contentValues.put("orderTime", ttdhItem.getOrderTime());
        return contentValues;
    }
}
