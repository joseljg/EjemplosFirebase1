package es.joseljg.ejemplosfirebase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BorrarAlumnoActivity extends AppCompatActivity {

    EditText edt_clave = null;
    private Alumno alumno1;
    private List<Alumno> alumnos1;
    private List<String> keys1;
    private TextView txt_resultadofirebase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_alumno);
        edt_clave = findViewById(R.id.edt_clave_borrar);
        txt_resultadofirebase = findViewById(R.id.txt_resultadofirebase);
        //----------------------------------------------------
        alumnos1 = new ArrayList<Alumno>();
        keys1 = new ArrayList<String>();
        alumno1 = new Alumno();
        //----------------------------------------------------
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        String alumnoId = "-MvUHUTCPZPobl5fj8zL";
        leerAlumno(alumnoId, new MyAlumno() {
            @Override
            public void leerAlumno(Alumno a) {
                alumno1=a;
                Log.i("firebasedb", "el alumno unico leido vale " + alumno1);
            }
        });

        //----------------------------------------------------------------------------------
        leerAlumnos(new MyAlumnos() {
            @Override
            public void leeralumnos(List<String> keys, List<Alumno> alumnos) {
                   keys1 = keys;
                   alumnos1 = alumnos;
                Log.i("firebasedb", "claves leidas");
                for(String k: keys1)
                {
                    Log.i("firebasedb", "clave leida " + k);
                   // txt_resultadofirebase.setText(String.valueOf(txt_resultadofirebase.getText())+" -> " + k);

                }
                Log.i("firebasedb", "usuarios leidos");
                for(Alumno a: alumnos1)
                {
                    Log.i("firebasedb", "alumno leido " + a.toString());
                //    txt_resultadofirebase.setText(String.valueOf(txt_resultadofirebase.getText())+" -> " + a.toString());
                }
            }
        });

    }

    public void borrar_alumno_firebase(View view) {
        String key = String.valueOf(edt_clave.getText());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("alumnos").child(key).removeValue();
        Toast.makeText(this,"borrado correcto ", Toast.LENGTH_LONG).show();
    }

    //---------------------------------------------------------------------------------------------------
    public interface MyAlumno{
        void leerAlumno(Alumno a);
    }

    public void leerAlumno(String key, MyAlumno myalumno)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("alumnos").child(key).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.i("firebasedb", "Error getting data", task.getException());
                }
                else {
                    myalumno.leerAlumno(task.getResult().getValue(Alumno.class));
                }
            }
        });
    }

    //--------------------------------------------------------------------------------------
    public interface MyAlumnos{
        void leeralumnos(List<String> keys, List<Alumno> alumnos);
    }

    public void leerAlumnos(MyAlumnos misAlumnos)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("alumnos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> keys = new ArrayList<String>();
                List<Alumno> alumnos = new ArrayList<Alumno>();
                for(DataSnapshot keynode: snapshot.getChildren()) {
                    keys.add(keynode.getKey());
                    alumnos.add(keynode.getValue(Alumno.class));
                }
                Log.i("firebasedb", "claves leidas");
                for(String k: keys1)
                {
                    Log.i("firebasedb", "clave leida " + k);
                }
                Log.i("firebasedb", "usuarios leidos");
                for(Alumno a: alumnos1)
                {
                    Log.i("firebasedb", "alumno leido " + a.toString());
                }
               misAlumnos.leeralumnos(keys,alumnos);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}