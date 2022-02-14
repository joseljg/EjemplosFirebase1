package es.joseljg.ejemplosfirebase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

   // Button bt_loguerarse = null;
    EditText edt_email = null;
    EditText edt_clave = null;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_clave = (EditText) findViewById(R.id.edt_password);
        mAuth = FirebaseAuth.getInstance();
      //  bt_loguerarse = (Button) findViewById(R.id.bt_entrar);
  /*      bt_loguerarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
     //   myRef.setValue("Hello, World!");
      //    myRef.child("hijo1").setValue("valorhijo1");
      //  myRef.push().setValue("valorhijo2");
      //  DatabaseReference myRef2 = database.getReference();
      //  myRef2.push().setValue("prueba123123");
      //  Alumno a1 = new Alumno("jose", "jimenez");
      //  myRef.push().setValue(a1);
      //  myRef.child("dni1").setValue(a1);
      //  Alumno a2 = new Alumno("antonio", "jimenez");
      //  Map<String, Alumno> alumnos = new HashMap<>();
      //  alumnos.put("clave1",a2 );
      //  alumnos.put("clave2",a2 );
      //  alumnos.put("clave3",a2 );
      //  alumnos.put("clave4",a2 );
      //  alumnos.put("clave5",a2 );
      //  myRef.child("pruebahashmap1").setValue(alumnos);

     //   ArrayList<Alumno> alumnos1 = new ArrayList<>();
     //   alumnos1.add(new Alumno("antonio1", "jimenez"));
     //   alumnos1.add(new Alumno("antonio2", "jimenez"));
     //   alumnos1.add(new Alumno("antonio3", "jimenez"));
     //   myRef.child("pruebaArrayList1").setValue(alumnos1);

     //    myRef.child("pruebaArrayList1").setValue(null);
     //    myRef.child("pruebahashmap1").removeValue();
    //    myRef.child("prueba_actualizar").setValue("valor1");
        //----------------------------------------------------------------
    //   String v = "valor2";
    //    Map<String, Object> nuevoMap = new HashMap<String,Object>();
    //    nuevoMap.put("prueba_actualizar",v);
     //   myRef.updateChildren(nuevoMap);

    }

    public void ir_a_add_alumno(View view) {
        Intent intent = new Intent(this, addAlumnoActivity.class);
        startActivity(intent);
    }

    public void borrar_alumno(View view) {
        Intent intent = new Intent(this, BorrarAlumnoActivity.class);
        startActivity(intent);
    }

    public void ir_a_update_alumno(View view) {
        Intent intent = new Intent(this, UpdateAlumnoActivity.class);
        startActivity(intent);
    }

    public void entrar_al_sistema(View view) {
        String email = String.valueOf(edt_email.getText());
        String password = String.valueOf(edt_clave.getText());
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("firebasedb", "signInWithEmail:success");
                            Toast.makeText(MainActivity.this, "signInWithEmail:success", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Intent intent = new Intent(MainActivity.this, addAlumnoActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("firebasedb", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "signInWithEmail:failure", Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }

    public void registrar_correo(View view) {
        String email = String.valueOf(edt_email.getText()).trim();
        String password = String.valueOf(edt_clave.getText());
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("firebasedb", "createUserWithEmail:success");
                            Toast.makeText(MainActivity.this, "createUserWithEmail:success", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            // updateUI(user);
                            Intent intent = new Intent(MainActivity.this, addAlumnoActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("firebasedb", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //  updateUI(null);
                        }
                    }
                });
    }
}