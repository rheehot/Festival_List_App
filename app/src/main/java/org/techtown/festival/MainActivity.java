package org.techtown.festival;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kakao.usermgmt.response.model.User;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView = null ;
    RecyclerTextAdapter mAdapter = null ;
    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>(); // 객체를 담을 어레이 리스트(어댑터 쪽으로)

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    String DBheader;

    public static final int REQUEST_CODE_MENU = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 로그인 인텐트 부분(코드 병합하고 MainActivity로 옮겨주기)
        Intent intent = getIntent();
        final String id = intent.getStringExtra("ID"); // id가져오기
        Toast.makeText(getApplicationContext(), id+" 로그인 성공!", Toast.LENGTH_SHORT).show();

        // 마이페이지로 이동 인텐트
        Button btn_page = findViewById(R.id.go_mypage);
        btn_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page_intent = new Intent(getApplicationContext(), MypageActivity.class);
                page_intent.putExtra("USERID", ""+id); // ID 보내주기
                startActivityForResult(page_intent, REQUEST_CODE_MENU);
            }
        });

        //후기 게시판으로 이동 인텐트
        Button btn_review = findViewById(R.id.go_review);
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent review_intent = new Intent(getApplicationContext(), ReviewActivity.class);
                review_intent.putExtra("USERID", ""+id); // ID 보내주기
                startActivityForResult(review_intent, REQUEST_CODE_MENU);
                //startActivity(review_intent);
            }
        });

        //스피너 part
        Spinner spinner = (Spinner)findViewById(R.id.spinner_rg);

        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this, R.array.region, android.R.layout.simple_spinner_dropdown_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(monthAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DBheader = String.valueOf(adapterView.getItemAtPosition(i)); // 글자는 adapterView, i는 index
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


        // database를 이용해 리사이클러뷰 구현 부분
        mRecyclerView = findViewById(R.id.recycler_v) ;
        mRecyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // 리사이클러뷰에 LinearLayoutManager 지정. (vertical)

        database = FirebaseDatabase.getInstance(); // firebase 데이터베이스 연동

        Button btncom = findViewById(R.id.complete);
        btncom.setOnClickListener(new View.OnClickListener() { // 확인 버튼을 클릭시
            @Override
            public void onClick(View v) {

                databaseReference = database.getReference(DBheader); // DB 테이블 연결
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // firebase 데이터베이스의 데이터를 받아오는 곳
                        mList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                            RecyclerItem info = snapshot.getValue(RecyclerItem.class); // 만들어뒀던 InformationData 객체에 데이터를 담는다.
                            mList.add(info); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                        }
                        mAdapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // 디비를 가져오던 중 에러 발생 시
                        Log.e("MainActivity", String.valueOf(error.toException())); // 에러 출력
                    }
                });

                // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
                mAdapter = new RecyclerTextAdapter(mList);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }
}

