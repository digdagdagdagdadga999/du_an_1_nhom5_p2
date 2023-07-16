package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Model;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    private static DataHolder instance;
    private List<HoaDonChiTiet> cartItems;
    private  double TotalPrice;

    public static void setInstance(DataHolder instance) {
        DataHolder.instance = instance;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    private DataHolder() {
        cartItems = new ArrayList<>();
    }

    public static DataHolder getInstance() {
        if (instance == null) {
            instance = new DataHolder();
        }
        return instance;
    }

    public List<HoaDonChiTiet> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<HoaDonChiTiet> cartItems) {
        this.cartItems = cartItems;
    }
}
