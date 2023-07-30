package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.database.BaoCaoDAO;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.databinding.FragmentBaoCaoBinding;


public class FragmentBaoCao extends Fragment {
    private FragmentBaoCaoBinding binding;


    public FragmentBaoCao() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        FragmentBaoCao fragment = new FragmentBaoCao();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBaoCaoBinding.inflate(inflater, container, false);

        Calendar calendar = Calendar.getInstance();

        binding.edDayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String ngay = "";
                        ngay = (i2 < 10) ? "0" + i2 : String.valueOf(i2);
                        String thang = "";
                        thang = ((i1 + 1) < 10) ? "0" + (i1 + 1) : String.valueOf(i1 + 1);

                        binding.edDayStart.setText(i + "/" + thang + "/" + ngay);
                    }

                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        binding.edDayEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String ngay = "";
                        ngay = (i2 < 10) ? "0" + i2 : String.valueOf(i2);
                        String thang = "";
                        thang = ((i1 + 1) < 10) ? "0" + (i1 + 1) : String.valueOf(i1 + 1);

                        binding.edDayEnd.setText(i + "/" + thang + "/" + ngay);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });

        binding.btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaoCaoDAO dao = new BaoCaoDAO(getContext());
                String ngaybatdau = binding.edDayStart.getText().toString();
                String ngayketthuc = binding.edDayEnd.getText().toString();
                int doanhthu = dao.getDoanhThu(ngaybatdau, ngayketthuc);
                binding.tvResult.setText(doanhthu + "VND");
            }
        });

        return binding.getRoot();
    }
}