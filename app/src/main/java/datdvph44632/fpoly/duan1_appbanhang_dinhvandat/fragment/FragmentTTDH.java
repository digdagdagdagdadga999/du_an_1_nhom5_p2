package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;

public class FragmentTTDH extends Fragment {
    public FragmentTTDH() {
    }


    public static FragmentTTDH newInstance(String param1) {
        FragmentTTDH fragment = new FragmentTTDH();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_t_t_d_h, container, false);
        anhxattdh(view);


        return view;

    }

    private void anhxattdh(View view) {

        TextView nameProduct,quantity,totalPrice,adress,orderDate,orderTime;
        ImageView imageProduct;



    }
}