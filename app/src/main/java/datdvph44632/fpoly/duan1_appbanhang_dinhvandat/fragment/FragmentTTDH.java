package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;

public class FragmentTTDH extends Fragment {
    public FragmentTTDH() {
    }


    public static FragmentTTDH newInstance(String param1, String param2) {
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
        return inflater.inflate(R.layout.fragment_t_t_d_h, container, false);
    }
}