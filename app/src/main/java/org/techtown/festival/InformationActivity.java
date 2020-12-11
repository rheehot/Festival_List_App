package org.techtown.festival;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
// 세부 정보 코드
public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // ID를 받는다.
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID"); // id가져오기
        Toast.makeText(getApplicationContext(), "Id: "+id, Toast.LENGTH_SHORT).show();

        // ID에 해당하는 데이터 베이스에 접근해서 해당 축제 내용을 나타내준다.
        // 메인 액티비티에서 축제를 나열하는 방식과 똑같다.


        // 메인 액티비티로 돌아간다.
        Button backbtn = findViewById(R.id.go_home);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}