package es.joseljg.ejemplosfirebase1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdateAlumnoActivity extends AppCompatActivity {

    EditText edt_clave = null;
    EditText edt_nombre = null;
    EditText edt_apellidos = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_alumno);
        edt_clave = (EditText) findViewById(R.id.edt_clavea);
        edt_nombre = (EditText) findViewById(R.id.edt_nombrea);
        edt_apellidos = (EditText) findViewById(R.id.edt_apellidosa);
    }

    public void actualizar_alumno(View view) {
        String clave = String.valueOf(edt_clave.getText());
        String nombre = String.valueOf(edt_nombre.getText());
        String apellidos = String.valueOf(edt_apellidos.getText());
        Alumno a = new Alumno(nombre,apellidos);
        Map<String, Object> nuevoAlumno = new HashMap<String,Object>();
        nuevoAlumno.put(clave,a);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("alumnos").updateChildren(nuevoAlumno);
        Toast.makeText(this,"actualizacion correcta",Toast.LENGTH_LONG).show();
    }
}