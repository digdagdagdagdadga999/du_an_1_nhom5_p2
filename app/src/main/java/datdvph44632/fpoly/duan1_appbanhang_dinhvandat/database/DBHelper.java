package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "data", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SanPhamDAO.SQL_SANPHAM);
        db.execSQL(LoaiSanPhamDAO.SQL_LOAISANPHAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table "+SanPhamDAO.TABLE_NAME);
        db.execSQL("Drop table "+LoaiSanPhamDAO.TABLE_NAME);

    }
}
