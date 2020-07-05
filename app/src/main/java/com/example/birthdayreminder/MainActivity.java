package com.example.birthdayreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";

    private EditText editName;
    private EditText editDate;
    private TextView textViewData;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference reminderRef = db.collection("Reminder");
    private DocumentReference noteRef = db.collection("Reminder").document("My First Note");
   // private DocumentReference reminder = db.collection("Reminder").document("My First Note");





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.etName);
        editDate = findViewById(R.id.etDate);
        textViewData = findViewById(R.id.tvData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchview,menu);
        MenuItem menuItem = menu.findItem(R.id.searchIcon);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        reminderRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    return;
                }
                String data = "";
                for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    Note note = documentSnapshot.toObject(Note.class);

                    String name = note.getName();
                    String date = note.getDate();

                    data += "Name:" + name + "\nDate:" + date + "\n\n";
                }
                textViewData.setText(data);

            }
        });

    }


    public void addDate(View v){
        String name = editName.getText().toString();
        String date = editDate.getText().toString();

        Note note = new Note(name, date);

        reminderRef.add(note);
    }


   // public void DeleteNote(View v){
    //   reminder.delete();

   // }

    public void LoadDate(View v){
       reminderRef.get()
               .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                   @Override
                   public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                       String data = "";
                       for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                           Note note = documentSnapshot.toObject(Note.class);

                           String name = note.getName();
                           String date = note.getDate();

                           data += "Name:" + name + "\nDate:" +date+"\n\n";


                       }
                       textViewData.setText(data);

                   }
               });
    }
}
