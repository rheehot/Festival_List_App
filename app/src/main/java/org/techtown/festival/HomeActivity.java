package org.techtown.festival;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MENU = 110;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //로그인에서 넘어옴
        Intent intent = getIntent();
        final String id = intent.getStringExtra("ID"); // id가져오기
        Toast.makeText(getApplicationContext(), id+" 로그인 성공!", Toast.LENGTH_SHORT).show();




        // 메인으로 이동 인텐트 (클릭 뷰 이름 바꿔줘)
        TextView home_bt = findViewById(R.id.seoul_tx);
        home_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("ID_tomain", ""+id); // ID 보내주기
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });



    }
}
