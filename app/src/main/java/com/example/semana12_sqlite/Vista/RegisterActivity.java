package com.example.semana12_sqlite.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.semana12_sqlite.Controlador.ConexionHelper;
import com.example.semana12_sqlite.Controlador.Utility;
import com.example.semana12_sqlite.R;

public class RegisterActivity extends AppCompatActivity {
    EditText textid;
    EditText txtnombre;
    EditText txtcorreo;
    Button btnregistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textid = findViewById(R.id.txtId);
        txtnombre = findViewById(R.id.txtNombre);
        txtcorreo = findViewById(R.id.txtCorreo);
        btnregistrar = findViewById(R.id.btnRegistrar);

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuarios();
            }
        });
    }

    private void registrarUsuarios(){
        ConexionHelper conn = new ConexionHelper(this,"bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Utility.CAMPO_ID,textid.getText().toString());
        contentValues.put(Utility.CAMPO_NOMBRE,txtnombre.getText().toString());
        contentValues.put(Utility.CAMPO_CORREO,txtcorreo.getText().toString());

        Long idResultante = db.insert(Utility.TABLA_USUARIO,Utility.CAMPO_ID,contentValues);
        Toast.makeText(getApplicationContext(), "ATENCION, id Registrado..." + idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }
}