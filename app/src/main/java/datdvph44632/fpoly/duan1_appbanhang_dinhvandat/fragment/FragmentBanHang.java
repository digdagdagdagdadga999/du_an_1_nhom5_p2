package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;


public class FragmentBanHang extends Fragment {



    public FragmentBanHang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ban_hang, container, false);
    }
}