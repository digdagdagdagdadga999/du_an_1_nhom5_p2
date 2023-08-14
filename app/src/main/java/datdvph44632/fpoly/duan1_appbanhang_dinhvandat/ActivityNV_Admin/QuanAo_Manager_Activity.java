package datdvph44632.fpoly.duan1_appbanhang_dinhvandat.ActivityNV_Admin;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.DAO.QuanAoDAO;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Entity.QuanAo;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.R;
import datdvph44632.fpoly.duan1_appbanhang_dinhvandat.Support.ChangeType;

public class QuanAo_Manager_Activity extends AppCompatActivity {

    Context context = this;
    FrameLayout uploadImageQuanAo;
    Spinner hangQuanAoSpinner;
    ImageView imageView_anhQuanAo, imageView_AddQuanAo;
    TextInputLayout til_TenQuanAoMoi, til_GiaTien, til_SoLuong;
    AppCompatButton buttonThemQuanAoNgay;
    QuanAoDAO quanAoDAO;
    ChangeType changeType = new ChangeType();
    int REQUEST_CODE = 2;
    String currentPhotoPath;
    private static final int SELECT_PHOTO = 100;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    String TAG = "Laptop_Manager_Activity_____";
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanao_manager);
        uploadImageQuanAo = findViewById(R.id.upload_Image_quanao);
        imageView_anhQuanAo = findViewById(R.id.imageView_QuanAo);
        imageView_AddQuanAo = findViewById(R.id.imageView_AddQuanao);
        til_TenQuanAoMoi = findViewById(R.id.textInput_Tenquanao);
        hangQuanAoSpinner = findViewById(R.id.spinner_HangQuanao);

        til_GiaTien = findViewById(R.id.textInput_GiaTien);
        til_SoLuong = findViewById(R.id.textInput_SoLuong);
        buttonThemQuanAoNgay = findViewById(R.id.button_quanao_Manager);

//        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
//                R.array.ram_array, android.R.layout.simple_spinner_item);
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        ramSpinner.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.loai_quan_ao_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hangQuanAoSpinner.setAdapter(adapter2);

        uploadImageQuanAo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                View dialogV = LayoutInflater.from(context).inflate(R.layout.dialog_pick_image, null);
                dialog.setContentView(dialogV);
                dialog.show();

                Button camera = dialogV.findViewById(R.id.openCamera);
                Button gallery = dialogV.findViewById(R.id.openGallery);

                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            Log.d(TAG, "cameraOnClick: Đã cấp quyền chụp ảnh");
                            openCamera();
                            dialog.cancel();
                        } else {
                            Log.d(TAG, "cameraOnClick: Chưa cấp quyền chụp ảnh");
                            ActivityCompat.requestPermissions(QuanAo_Manager_Activity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
                            Toast.makeText(context, "Cần cấp quyền để thực hiện", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                            Log.d(TAG, "galleryOnClick: Chưa cấp quyền đọc bộ nhớ");
                            ActivityCompat.requestPermissions(QuanAo_Manager_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
                            Toast.makeText(context, "Cần cấp quyền để thực hiện", Toast.LENGTH_LONG).show();
                        } else {
                            Log.d(TAG, "galleryOnClick: Đã cấp quyền đọc bộ nhớ");
                            openGallery();
                            dialog.cancel();
                        }
                    }
                });
            }
        });

        useToolbar();
        addLaptopNew();
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Normal));
        TextView titleToolbar = findViewById(R.id.textView_Title_Toolbar);
        titleToolbar.setText("Thêm Quần áo");
        ImageButton back = findViewById(R.id.imageButton_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void addLaptopNew() {
        quanAoDAO = new QuanAoDAO(this);

        buttonThemQuanAoNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInputLaptop() == 1) {
                    String tenLaptop = til_TenQuanAoMoi.getEditText().getText().toString();
                    String hangLaptop = (String) hangQuanAoSpinner.getSelectedItem();
                    String tien = til_GiaTien.getEditText().getText().toString();
                    int soluong = Integer.parseInt(til_SoLuong.getEditText().getText().toString());

                    Bitmap bm = getBitmap();
                    if (bm != null) {
                        Log.d(TAG, "onClick: here?");
                        QuanAo quanAo = new QuanAo("LP", "L" + hangLaptop, tenLaptop, changeType.stringToStringMoney(tien), soluong,
                                0, changeType.checkByteInput(changeType.bitmapToByte(bm)));
                        quanAoDAO.insertQuanAo(quanAo);
                        finish();
                    } else {
                        Log.d(TAG, "onClick: or here?");
                        Toast.makeText(context, "Ảnh không được thiếu!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d(TAG, "onClick: or maybe here?");
                }
            }

        });

    }

    private int checkInputLaptop() {
        int check = 1;
        String tenLaptop = til_TenQuanAoMoi.getEditText().getText().toString();
        String tien = til_GiaTien.getEditText().getText().toString();
        String soluong = til_SoLuong.getEditText().getText().toString();

        if (tenLaptop.isEmpty()) {
            check = -1;
            til_TenQuanAoMoi.setError("Tên  không được bỏ trống!");
            til_TenQuanAoMoi.setErrorEnabled(true);
        } else {
            til_TenQuanAoMoi.setError("");
            til_TenQuanAoMoi.setErrorEnabled(false);
        }

        if (tien.isEmpty()) {
            check = -1;
            til_GiaTien.setError("Giá tiền không được bỏ trống!");
            til_GiaTien.setErrorEnabled(true);
        } else {
            if (Integer.parseInt(tien) > 0) {
                til_GiaTien.setError("");
                til_GiaTien.setErrorEnabled(false);
            } else {
                check = -1;
                til_GiaTien.setError("Giá tiền phải lớn hơn 0!");
                til_GiaTien.setErrorEnabled(true);
            }
        }

        if (soluong.isEmpty()) {
            check = -1;
            til_SoLuong.setError("Số lượng không được bỏ trống!");
            til_SoLuong.setErrorEnabled(true);
        } else {
            if (Integer.parseInt(soluong) > 0) {
                til_SoLuong.setError("");
                til_SoLuong.setErrorEnabled(false);
            } else {
                check = -1;
                til_SoLuong.setError("Số lượng phải lớn hơn 0!");
                til_SoLuong.setErrorEnabled(true);
            }
        }

        return check;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (requestCode == SELECT_PHOTO) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = imageReturnedIntent.getData();
                InputStream imageStream = null;
                try {
                    imageStream = context.getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Bitmap bm = BitmapFactory.decodeStream(imageStream);
                if (bm != null) {
                    setBitmap(bm);
                    imageView_anhQuanAo.setImageBitmap(bm);
                    imageView_AddQuanAo.setVisibility(View.GONE);
                }
            }
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bm = BitmapFactory.decodeFile(currentPhotoPath);
            if (bm != null) {
                setBitmap(bm);
                imageView_anhQuanAo.setImageBitmap(bm);
                imageView_AddQuanAo.setVisibility(View.GONE);
            }
        }
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(QuanAo_Manager_Activity.this.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(QuanAo_Manager_Activity.this,
                        "com.nhom5.quanlylaptop.fileprovider", photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = QuanAo_Manager_Activity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}