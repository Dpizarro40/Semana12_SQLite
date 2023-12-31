package com.example.semana12_sqlite.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.semana12_sqlite.Controlador.ConexionHelper;
import com.example.semana12_sqlite.Controlador.Utility;
import com.example.semana12_sqlite.R;

public class MaintenanceActivity extends AppCompatActivity {
    EditText txtid, txtnombre, txtcorreo;
    Button btnconsultar, btnupdate, btndelete;
    ConexionHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);

        conn = new ConexionHelper(getApplicationContext(), "bd_usuarios", null, 1);

        txtid = findViewById(R.id.txtId);
        txtnombre = findViewById(R.id.txtNombre);
        txtcorreo = findViewById(R.id.txtCorreo);
        btnconsultar = findViewById(R.id.btnBuscar);
        btnupdate = findViewById(R.id.btnActualizar);
        btndelete = findViewById(R.id.btnEliminar);

        btnconsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarUsuario();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarUsuario();
            }
        });
    }

    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {txtid.getText().toString()};
        try{
            Cursor cursor = db.rawQuery("SELECT " + Utility.CAMPO_NOMBRE + "," + Utility.CAMPO_CORREO +
                    " FROM " + Utility.TABLA_USUARIO + " WHERE " + Utility.CAMPO_ID + "=? ", parametros);
            cursor.moveToFirst();
            txtnombre.setText(cursor.getString(0));
            txtcorreo.setText(cursor.getString(1));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "ATENCIÓN, Usuario no existe.", Toast.LENGTH_SHORT).show();
        }
    }
    private void actualizarUsuario(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {txtid.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utility.CAMPO_NOMBRE, txtnombre.getText().toString());
        values.put(Utility.CAMPO_CORREO, txtcorreo.getText().toString());

        db.update(Utility.TABLA_USUARIO, values, Utility.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "ATENCIÓN, se actualizó el usuario", Toast.LENGTH_SHORT).show();
        limpiar();
        db.close();
    }

    private void eliminarUsuario(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {txtid.getText().toString()};

        db.delete(Utility.TABLA_USUARIO, Utility.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "ATENCIÓN, se eliminó el usuario",Toast.LENGTH_SHORT).show();
        txtid.setText("");
        limpiar();
        db.close();
    }

    private void limpiar(){
        txtnombre.setText("");
        txtcorreo.setText("");
    }
}