package org.techtown.festival;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// 리사이클러뷰 어뎁터 코드
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<Review> mReviewList;

    public ReviewAdapter(List<Review> mReviewList){
        this.mReviewList = mReviewList;
    }
    /*
    private String[] re_name = {"김영한", "이수영", };
    private String[] re_star = {"4/5", "5/5", };
    private String[] re_content = {"유익한 내용", "삥빵뽕뿡삥뽕ssssssssssssssssssssssss", };

     */

    public static class ViewHolder extends RecyclerView.ViewHolder { // 홀더 설정
        public TextView mIdTextView;
        public TextView mTitleTextView;
        public TextView mStarTextView;
        public TextView mContextTextView;

        public ViewHolder(View view) {
            super(view);
            this.mIdTextView = view.findViewById(R.id.r_id);
            this.mTitleTextView = view.findViewById(R.id.r_title);
            this.mStarTextView = view.findViewById(R.id.r_star);
            this.mContextTextView = view.findViewById(R.id.r_content);
        }
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { // 레이아웃 매니저에 의해 새 아이템을 만들고 ViewHolder를 만들어준다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list, parent, false); // 레이아웃 연결
        ReviewAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolder holder, final int position) { // position에 맞는 데이터를 이용해 리스트를 채운다.
        Review data = mReviewList.get(position);
        holder.mIdTextView.setText(data.getId());
        holder.mTitleTextView.setText(data.getTitle());
        holder.mStarTextView.setText(data.getStar());
        holder.mContextTextView.setText(data.getContent());
        // 연결된 뷰들은 액션에 대한 실행을 지정할 수 있다.
    }

    @Override
    public int getItemCount() { // 리사이클러뷰의 데이터 개수를 리턴
        return mReviewList.size();
    }
}
