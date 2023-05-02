package nazmul.firstfirebaseandroid;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NoteDetail extends AppCompatActivity {
    private EditText editTextDetail;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_detail);
        editTextDetail = findViewById(R.id.editTextDetail);


    }
}
