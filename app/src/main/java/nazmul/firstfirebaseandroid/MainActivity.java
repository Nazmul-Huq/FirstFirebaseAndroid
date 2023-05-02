package nazmul.firstfirebaseandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    ArrayAdapter<MyRow> adapter;
    private FirebaseService fs;
    private ListView listView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fs = new FirebaseService(this);
        adapter = new ArrayAdapter<>(this, R.layout.myrow, R.id.rowTextView, fs.items);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        editText = findViewById(R.id.newNoteText);

        listView.setOnItemClickListener((listView, linearLayout, position, id)->{
            //TextView textView = linearLayout.findViewById(R.id.textView);
            // navigate to next screen (Activity)
            Intent intent = new Intent(this,NoteDetail.class);
            // provide the data as well.
            intent.putExtra("thekey", position);
            startActivity(intent);
        });

    }

    public void readAndAddNoteToFirestore(View view) {
        String noteText = editText.getText().toString().trim();
        if (noteText != null) {
            fs.addNote(noteText);
            adapter.clear();
            fs.startListener();
        }
    }
}