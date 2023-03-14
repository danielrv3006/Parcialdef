package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcial1.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    EditText usuario,contraseña;
    Button btn_iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario=findViewById(R.id.usuario);
        contraseña=findViewById(R.id.contraseña);

        btn_iniciar=findViewById(R.id.btn_iniciar);

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(usuario.getText().toString(),contraseña.getText().toString());
                startActivity(new Intent(MainActivity.this, Listado.class));
            }
        });
    }

    private void guardar (String Usuario, String Contraseña){
        DbHelper helper = new DbHelper(this,"demo",null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
        try{
            ContentValues c=new ContentValues();
            c.put("Usuario",Usuario);
            c.put("Contraseña",Contraseña);
            db.insert("Persona",null,c);
            Toast.makeText(this,"Usuario Encontrado",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }

    }
}