package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.activity.DangNhapActivity;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.activity.DoiMkActivity;


public class FragmentSetting extends Fragment {

    Button btnDx, btnDmk;

    public FragmentSetting() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        btnDmk=v.findViewById(R.id.btnDoiMatKhau);
        btnDx=v.findViewById(R.id.btnDangXuat);

        btnDx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), DangNhapActivity.class);
                startActivity(i);
            }
        });

        btnDmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), DoiMkActivity.class);
                startActivity(i);
            }
        });

        return v;
    }
}