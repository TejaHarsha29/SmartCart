package com.example.mycart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateActivity extends AppCompatActivity {


    TextInputEditText name,quantity,price;

    Button generateQr;

    ImageView qr_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);


        name=findViewById(R.id.textInputEdittext_name);
        quantity=findViewById(R.id.textInputEdittext_quantity);
        price=findViewById(R.id.textInputEdittext_price);

        generateQr=findViewById(R.id.gen_btn);

        qr_img=findViewById(R.id.qr_img);


        generateQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = name.getText().toString()+"@@@"+quantity.getText().toString()+"@@@"+price.getText().toString();

                if(text.isEmpty()){
                    Toast.makeText(GenerateActivity.this, "text can't be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    QRGEncoder qrgEncoder=new QRGEncoder(text,null, QRGContents.Type.TEXT,700);

                    Bitmap bitmap;

                    try {
                        // Getting QR-Code as Bitmap
                        bitmap = qrgEncoder.getBitmap();
                        // Setting Bitmap to ImageView
                        qr_img.setImageBitmap(bitmap);
                    } catch (Exception e) {

                    }





                }

            }
        });



    }
}