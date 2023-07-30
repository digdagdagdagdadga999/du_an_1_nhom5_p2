package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BaoCaoDAO {

    DBHelper dbHelper;
    private Context context;
    SQLiteDatabase db;

    public BaoCaoDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //    thống kê doanh thu
    public int getDoanhThu(String dayStart, String dayEnd) {
        dayStart = dayStart.replace("/", "");
        dayEnd = dayEnd.replace("/", "");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(tongTien) FROM HoaDonChiTiet WHERE substr(orderDate,7)||substr(orderDate,4,2)||substr(orderDate,1,2) between ? and ?", new String[]{dayStart, dayEnd});

        if (c.getCount() != 0) {
            c.moveToFirst();
            return c.getInt(0);
        }
        return 0;
    }

    //        Cursor c = db.rawQuery("SELECT SUM(tongTien) FROM HoaDonChiTiet WHERE substr(ngay,7)||substr(ngay,4,2)||substr(ngay,1,2) between ? and ?", new String[]{dayStart, dayEnd});
}
