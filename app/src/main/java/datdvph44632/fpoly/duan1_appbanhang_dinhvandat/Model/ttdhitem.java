package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model;

import java.util.Date;

public class ttdhitem {
    private int id;
    private String nameProduct;
    private byte[] imageProduct;
    private int quantity;
    private double totalPrice;
    private String adress;
    private String  orderDate;
    private String orderTime;

    public ttdhitem(int id, String nameProduct, byte[] imageProduct, int quantity, double totalPrice, String adress, String orderDate, String orderTime) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.imageProduct = imageProduct;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.adress = adress;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
    }

    public ttdhitem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public byte[] getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(byte[] imageProduct) {
        this.imageProduct = imageProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }


}
