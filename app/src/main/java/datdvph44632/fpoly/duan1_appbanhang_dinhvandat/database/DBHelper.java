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
                    "(1, 'Nguyen Thanh Son', '0963943774', 'hoasua050399@mail.com','thanhson539','123456', 1)," +
                    "(2, 'Nguyen Duy Tien', '0123456789', 'nguyenduytienbgg3@gmail.com','duytienbgg3','123456', 0)," +
                    "(3, 'Phi Dinh Long', '0123456789', 'philongpdl@gmail.com','philongpdl','123456', 0)," +
                    "(4, 'Hoang Minh Quan', '0123456789', 'mquann139@gmail.com','mquann139','123456', 0)," +
                    "(5, 'Ha Manh Dung', '0123456789', 'hmdung26@gmail.com','hmdung26','123456', 0)");

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
