package nazmul.firstfirebaseandroid;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseService {
    //https://firebase.google.com/docs/firestore/quickstart?authuser=0
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<MyRow> items = new ArrayList<>();
    private MainActivity mainActivity;

    public FirebaseService(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        startListener();
    }

    public void addNote(String text){
        /*// Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        // Add a new document with a generated ID
        db.collection("notes")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("SuccessTag", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FailureTag", "Error adding document", e);
                    }
                });*/
        DocumentReference ref = db.collection("notes").document();
        Map<String,String> map = new HashMap<>();
        map.put("text", text);
        ref.set(map);
    }

    public void editNote(String text, String id){
        DocumentReference ref = db.collection("notes").document(id);
        Map<String,String> map = new HashMap<>();
        map.put("text", text);
        ref.set(map);
    }

    public void deleteNote(String id){
        DocumentReference ref = db.collection("notes").document(id);
        ref.delete();
    }

    public void startListener(){
        db.collection("notes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                items.add( new MyRow(document.getData().get("text").toString()));
                            }
                            mainActivity.adapter.notifyDataSetChanged(); // will make the adapter reload
                        } else {
                            Log.w("ReadingDocumentF", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
