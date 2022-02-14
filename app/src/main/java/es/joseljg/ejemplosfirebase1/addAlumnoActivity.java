package es.joseljg.ejemplosfirebase1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addAlumnoActivity extends AppCompatActivity {

    EditText edt_nombre = null;
    EditText edt_apellidos = null;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
        else{
            Toast.makeText(addAlumnoActivity.this, "debes autenticarte primero", Toast.LENGTH_SHORT).show();
            FirebaseUser user = mAuth.getCurrentUser();
            //updateUI(user);
            Intent intent = new Intent(addAlumnoActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alumno);
        edt_nombre = (EditText) findViewById(R.id.edt_nombrea);
        edt_apellidos = (EditText) findViewById(R.id.edt_apellidosa);
        mAuth = FirebaseAuth.getInstance();
        //--------------------------------------------------------

    }

    public void crear_alumno(View view) {

        String nombre = String.valueOf(edt_nombre.getText());
        String apellidos = String.valueOf(edt_apellidos.getText());
        Alumno a = new Alumno(nombre,apellidos);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("alumnos").push().setValue(a);
    }
}