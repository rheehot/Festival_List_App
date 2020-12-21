package org.techtown.festival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

// 세부 정보 코드
public class InformationActivity extends AppCompatActivity {
    RecyclerView mRecyclerView = null ;
    Information_RecyclerAdapter mAdapter = null ;
    ArrayList<Information_RecyclerItem> mList = new ArrayList<Information_RecyclerItem>(); // 객체를 담을 어레이 리스트(어댑터 쪽으로)

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // ID를 받는다.
        Intent intent = getIntent();
        id = intent.getStringExtra("ID"); // id가져오기
        //Toast.makeText(getApplicationContext(), "Id: "+id, Toast.LENGTH_SHORT).show();

        // ID에 해당하는 데이터 베이스에 접근해서 해당 축제 내용을 나타내준다.
        // 메인 액티비티에서 축제를 나열하는 방식과 똑같다.
        // database를 이용해 리사이클러뷰 구현 부분
        mRecyclerView = findViewById(R.id.information_recycler) ;
        mRecyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // 리사이클러뷰에 LinearLayoutManager 지정. (vertical)

        database = FirebaseDatabase.getInstance(); // firebase 데이터베이스 연동
        databaseReference = database.getReference(id); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // firebase 데이터베이스의 데이터를 받아오는 곳
                mList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    Information_RecyclerItem info = snapshot.getValue(Information_RecyclerItem.class); // 만들어뒀던 객체에 데이터를 담는다.
                    mList.add(info); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                mAdapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 디비를 가져오던 중 에러 발생 시
                Log.e("InformationActivity", String.valueOf(error.toException())); // 에러 출력
            }
        });

        // 리사이클러뷰에 Adapter 객체 지정.
        mAdapter = new Information_RecyclerAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);


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