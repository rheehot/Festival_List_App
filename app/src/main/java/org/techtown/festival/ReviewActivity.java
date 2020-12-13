package org.techtown.festival;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

// 세부 정보 코드
public class ReviewActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private ReviewAdapter mAdapter;
    private List<Review> mReviewList;


    public static final int REQUEST_CODE_MENU = 102;

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        final Intent intent = getIntent();
        final String userid = intent.getStringExtra("USERID");


        // 리사이클러뷰, 어뎁터 부분
        mRecyclerView = findViewById(R.id.review_view);
        mReviewList = new ArrayList<>();



        mReviewList.add(new Review("201819174", "여수축제","진쨔 재미써용","★★★★"));
        mReviewList.add(new Review("123456789", "전북축제","날씨 맑아서 딱 좋았네용","★★★"));

        mAdapter = new ReviewAdapter(mReviewList);
        mRecyclerView.setAdapter(mAdapter);




        // main으로 돌아가는 부분
        Button btn_main = findViewById(R.id.go_main2);
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();
            }
        });


/*
        layoutManager = new LinearLayoutManager(this); // 리니어레이아웃 형태로 표시

        review_list.setLayoutManager(layoutManager); // 리사이클러뷰와 레이아웃 연결

        adapter = new ReviewAdapter(); // 어댑터 객체 생성(어댑터 자바 파일)
        review_list.setAdapter(adapter); // 리사이클러뷰와 어뎁터를 연결
*/
    }


    @Override
    public void onClick(View v) {

    }

}