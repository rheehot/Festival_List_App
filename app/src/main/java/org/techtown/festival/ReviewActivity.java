package org.techtown.festival;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// 세부 정보 코드
public class ReviewActivity extends AppCompatActivity {
    RecyclerView mRecyclerView = null ;
    Review_RecyclerAdapter mAdapter = null ;
    ArrayList<Review_RecyclerItem> mList = new ArrayList<Review_RecyclerItem>(); // 객체를 담을 어레이 리스트(어댑터 쪽으로)

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    public static final int REQUEST_CODE_MENU = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        final Intent intent = getIntent();
        final String userid = intent.getStringExtra("USERID");

        // database를 이용해 리사이클러뷰 구현 부분
        mRecyclerView = findViewById(R.id.review_view) ;
        mRecyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // 리사이클러뷰에 LinearLayoutManager 지정. (vertical)

        Button btn_update = findViewById(R.id.update_review);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance(); // firebase 데이터베이스 연동
                databaseReference = database.getReference("게시판"); // DB 테이블 연결
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // firebase 데이터베이스의 데이터를 받아오는 곳
                        mList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                            Review_RecyclerItem info = snapshot.getValue(Review_RecyclerItem.class); // 만들어뒀던 객체에 데이터를 담는다.
                            mList.add(info); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                        }
                        mAdapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // 디비를 가져오던 중 에러 발생 시
                        Log.e("ReviewActivity", String.valueOf(error.toException())); // 에러 출력
                    }
                });

                // 리사이클러뷰에 Adapter 객체 지정.
                mAdapter = new Review_RecyclerAdapter(mList);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        // main으로 돌아가는 부분
        Button btn_main = findViewById(R.id.go_main2);
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // 글쓰기 액티비티로 넘어가는 부분
        Button btn_write = findViewById(R.id.write_review);
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent write_intent = new Intent(getApplicationContext(), WriteActivity.class);
                write_intent.putExtra("ID", ""+userid); // ID 보내주기
                startActivity(write_intent);
            }
        });

    }
}