package com.example.mycart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.mycart.Adapters.CartAdapter;
import com.google.zxing.Result;

import java.util.ArrayList;

public class ScanActivity extends AppCompatActivity {

    private CodeScanner mCodeScanner;

    ArrayList<String> list;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        CodeScannerView scannerView = findViewById(R.id.scanner_view);

        recyclerView=findViewById(R.id.recycler);

        list = new ArrayList<>();

        CartAdapter cartAdapter=new CartAdapter(list,ScanActivity.this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cartAdapter);



        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(list.contains(result.getText())){
                            Toast.makeText(ScanActivity.this,"Already added", Toast.LENGTH_SHORT).show();
                            mCodeScanner.startPreview();
                        }
                        else{
                            list.add(result.getText());
                            cartAdapter.notifyDataSetChanged();
                            mCodeScanner.startPreview();


                        }

                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });







    }
    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}