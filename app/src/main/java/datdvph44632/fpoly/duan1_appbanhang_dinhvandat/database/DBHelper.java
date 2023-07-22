    package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database;

    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    import androidx.annotation.Nullable;

    import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model.HoaDonChiTiet;


    public class DBHelper extends SQLiteOpenHelper {
        public DBHelper(@Nullable Context context) {
            super(context, "aa", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SanPhamDAO.SQL_SANPHAM);
            db.execSQL(LoaiSanPhamDAO.SQL_LOAISANPHAM);
            db.execSQL(KhachHangDAO.SQL_NGUOIDUNG);
            db.execSQL(GioHangDAO.SQL_GIOHANG);
            db.execSQL(HoaDonChiTietDAO.SQL_HDCT);
            String createNguoiDung = "create table NGUOIDUNG(" +
                    "nguoiDung_id integer primary key autoincrement," +
                    "hoTen text not null," +
                    "soDienThoai text not null," +
                    "email text not null," +
                    "taiKhoan text not null," +
                    "matKhau text not null," +
                    "loaiTaiKhoan integer not null)";
            db.execSQL(createNguoiDung);
            db.execSQL("insert into NGUOIDUNG values" +
                    "(1, 'Nguyen Chi Thanh', '0339463318', 'thanh21052002@mail.com','thanh21052002','123456', 1)," +
                    "(2, 'Nguyen Van A', '0123456789', 'nguyenvana@gmail.com','abcd1','123456', 0)," +
                    "(3, 'Nguyen Van B', '0123456789', 'nguyenvanb@gmail.com','abcd2','123456', 0)," +
                    "(4, 'Nguyen Van C', '0123456789', 'nguyenvanc@gmail.com','abcd3','123456', 0)," +
                    "(5, 'Nguyen Van D', '0123456789', 'nguyenvand@gmail.com','abcd4','123456', 0)");

            db.execSQL(SanPhamDAO.DATA_SANPHAM);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists NGUOIDUNG");
            db.execSQL("Drop table "+SanPhamDAO.TABLE_NAME);
            db.execSQL("Drop table "+LoaiSanPhamDAO.TABLE_NAME);
            db.execSQL("Drop table "+KhachHangDAO.TABLE_NAME);
            db.execSQL("Drop table "+GioHangDAO.TABLE_NAME);
            db.execSQL("Drop table "+ HoaDonChiTietDAO.TABLE_NAME);
            onCreate(db);
        }
    }
