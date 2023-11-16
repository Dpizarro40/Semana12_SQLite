package com.example.semana12_sqlite.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.semana12_sqlite.R;

public class MainActivity extends AppCompatActivity {
    Button registro;
    Button mantener;
    Button listar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registro = findViewById(R.id.btnRegister);
        mantener = findViewById(R.id.btnMaintenance);
        listar = findViewById(R.id.btnListar);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        mantener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MaintenanceActivity.class);
                startActivity(intent);
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListarActivity.class);
                startActivity(intent);
            }
        });
    }
}