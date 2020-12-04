package org.techtown.festival;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InformationActivity extends AppCompatActivity {
    private RecyclerView review_list; // 리사이클러뷰 변수 선언
    public RecyclerView.LayoutManager layoutManager; //  변수와 레이아웃을 연결하는 매니저 변수 선언
    private RecyclerView.Adapter adapter; // 어뎁터 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        review_list = findViewById(R.id.review_view);
        review_list.setHasFixedSize(true); // 크기를 가변적으로 바꿔줌줌

        layoutManager = new LinearLayoutManager(this); // 리니어레이아웃 형태로 표시

        review_list.setLayoutManager(layoutManager); // 리사이클러뷰와 레이아웃 연결

        adapter = new ReviewAdapter(); // 어뎁터 객체 생성(어뎁터 자바 파일)
        review_list.setAdapter(adapter); // 리사이클러뷰와 어뎁터를 연결
    }
}