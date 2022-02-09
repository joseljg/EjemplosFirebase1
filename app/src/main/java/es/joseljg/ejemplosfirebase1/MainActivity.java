package es.joseljg.ejemplosfirebase1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
     //   myRef.setValue("Hello, World!");
          myRef.child("hijo1").setValue("valorhijo1");
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
}