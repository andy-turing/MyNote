package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText mTextoNota;
    private Button mGuardar;
    private Button mBorrar;

    private NotaLab mNotaLab;
    private Nota mNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextoNota = findViewById(R.id.texto_nota);

        mNotaLab = NotaLab.get(this);
        List<Nota> notas = mNotaLab.getNotas();
        if(notas.size() > 0) {
            mNota = notas.get(0);
            mTextoNota.setText(mNota.getMensaje());
        }

        mGuardar = findViewById(R.id.boton_guardar);
        mGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });

        mBorrar = findViewById(R.id.boton_borrar);
        mBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrar();
            }
        });

    }


    /**
     * Borra la nota si existe (si mNota no es null).
     */
    private void borrar() {
        if(mNota != null) {
            mNotaLab.deleteNota(mNota);
            mNota = null;
            mTextoNota.setText("");
            Toast.makeText(this, getString(R.string.nota_borrada),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.nota_no_existe),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Guarda la nota si no existe (mNota es null), o la actualiza si existe.
     */
    private void guardar() {
        String textoNota = mTextoNota.getText().toString();
        if(!textoNota.equals("")) {
            if(mNota == null) {
                mNota = new Nota();
                mNota.setMensaje(textoNota);
                mNotaLab.addNota(mNota);
                Toast.makeText(this, getString(R.string.nota_creada),
                        Toast.LENGTH_SHORT).show();
            } else {
                mNota.setMensaje(textoNota);
                mNotaLab.updateNota(mNota);
                Toast.makeText(this, getString(R.string.nota_actualizada),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, getString(R.string.crea_nota_primero),
                    Toast.LENGTH_SHORT).show();
        }
    }
}

