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

import java.util.ArrayList;

// 여러 개의 클릭 처리를 위해 implements -> OnClickListener를 해준다.
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    String id;
    private TextView[] view_list = new TextView[17];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //로그인에서 넘어옴
        Intent intent = getIntent();
        id = intent.getStringExtra("ID"); // id가져오기
        Toast.makeText(getApplicationContext(), " 로그인 성공!", Toast.LENGTH_SHORT).show();


        // 메인으로 이동 인텐트
        view_list[0] = findViewById(R.id.tv_seoul);
        view_list[1] = findViewById(R.id.tv_Busan);
        view_list[2] = findViewById(R.id.tv_Daegu);
        view_list[3] = findViewById(R.id.tv_Incheon);
        view_list[4] = findViewById(R.id.tv_Gwangju);
        view_list[5] = findViewById(R.id.tv_Daejeon);
        view_list[6] = findViewById(R.id.tv_Ulsan);
        view_list[7] = findViewById(R.id.tv_Sejong);
        view_list[8] = findViewById(R.id.tv_Gyeonggi);
        view_list[9] = findViewById(R.id.tv_Gangwon);
        view_list[10] = findViewById(R.id.tv_Chungbuk);
        view_list[11] = findViewById(R.id.tv_Chungnam);
        view_list[12] = findViewById(R.id.tv_Jeonbuk);
        view_list[13] = findViewById(R.id.tv_Jeonnam);
        view_list[14] = findViewById(R.id.tv_Gyeongbuk);
        view_list[15] = findViewById(R.id.tv_Gyeongnam);
        view_list[16] = findViewById(R.id.tv_Jeju);

        // 클릭리스너 등록 및 클릭시 가져올 index 저장
        for(int i=0 ; i<17 ; i++) {
            view_list[i].setTag(i); // 배열에서의 index를 태그로 저장
            view_list[i].setOnClickListener(this); // 클릭 리스너 등록
        }
    }

    @Override
    public void onClick(View v) {
        TextView choice = (TextView) v; // 클릭된 뷰를 가져온다.
        for(TextView tempView : view_list) {
            if(tempView == choice) {
                int pos = (Integer)v.getTag();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("USER_ID", ""+id); // ID 보내주기
                intent.putExtra("INDEX", pos);
                startActivity(intent);
                finish();
            }
        }
    }
}
