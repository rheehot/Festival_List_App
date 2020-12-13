package org.techtown.festival;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;

public class WriteActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    String ID;
    public class Write {
        public String id;
        public String title;
        public String content;

        public Write(String id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        final Intent intent = getIntent();
        ID = intent.getStringExtra("ID");

        Button btn_upload = findViewById(R.id.upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText text_title = findViewById(R.id.id_etx);
                EditText text_content = findViewById(R.id.rating_etx);
                Write w = new Write(ID, text_title.getText().toString(), text_content.getText().toString());
                database = FirebaseDatabase.getInstance();
                databaseReference = database.getReference();
                databaseReference.child("게시판").child(ID).setValue(w);
                finish();
            }
        });
    }
}
