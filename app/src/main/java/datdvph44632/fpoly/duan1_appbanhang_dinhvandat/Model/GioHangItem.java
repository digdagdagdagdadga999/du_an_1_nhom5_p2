package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model;

import java.util.Arrays;

public class GioHangItem {
    private int id;
    private String tenSanPham;
    private double giaSanPham;
    private byte[] imageSanPham;
    private int quantity;
    private double price;
    private double totalPrice;
    private boolean isSelected;
    private String adress;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public GioHangItem() {
    }

    public GioHangItem(int id, String tenSanPham, double giaSanPham, byte[] imageSanPham) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.imageSanPham = imageSanPham;
        this.quantity = 1;
        this.price = giaSanPham;
        this.totalPrice = giaSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public byte[] getImageSanPham() {
        return imageSanPham;
    }

    public void setImageSanPham(byte[] imageSanPham) {
        this.imageSanPham = imageSanPham;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "GioHangItem{" +
                "id=" + id +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", giaSanPham=" + giaSanPham +
                ", imageSanPham=" + Arrays.toString(imageSanPham) +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public void increaseQuantity() {
        quantity++;
        calculateTotalPrice();
    }

    public void decreaseQuantity() {
        if (quantity > 1) {
            quantity--;
            calculateTotalPrice();
        }
    }

    private void calculateTotalPrice() {
        totalPrice = price * quantity;
    }


}
