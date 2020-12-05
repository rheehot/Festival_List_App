package org.techtown.festival;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView = null ;
    RecyclerTextAdapter mAdapter = null ;
    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        mRecyclerView = findViewById(R.id.recycler_v) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new RecyclerTextAdapter(mList) ;
        mRecyclerView.setAdapter(mAdapter) ;

        // 리사이클러뷰에 LinearLayoutManager 지정. (vertical)
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // 아이템 추가.
        addItem("동화 축제",
                "광진구", "5.4~5.6 (3일간)") ;
        // 두 번째 아이템 추가.
        addItem("관악 강감찬축제",
                "관악구", "10.16~10.18 (3일간)") ;
        // 세 번째 아이템 추가.
        addItem("2020 이태원 지구촌 축제",
                "용산구", "10월중") ;

        mAdapter.notifyDataSetChanged() ;

    }

    public void addItem(String name, String location, String period) {
        RecyclerItem item = new RecyclerItem();

        item.setName(name);
        item.setLocation(location);
        item.setPeriod(period);

        mList.add(item);
    }

}

