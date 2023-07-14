package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model;

public class SanPham {
    private String maSanPham, maLoai, ten;
    private double giaNhap, giaBan;
    private byte[] image;
    private int soLuong;

    public SanPham(String maSanPham, String maLoai, String ten, double giaNhap, double giaBan, byte[] image, int soLuong) {
        this.maSanPham = maSanPham;
        this.maLoai = maLoai;
        this.ten = ten;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.image = image;
        this.soLuong = soLuong;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
