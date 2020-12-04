package org.techtown.festival;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private String[] re_name = {"김영한", "이수영", };
    private String[] re_star = {"4/5", "5/5", };
    private String[] re_content = {"유익한 내용", "삥빵뽕뿡삥뽕ssssssssssssssssssssssss", };

    public class ViewHolder extends RecyclerView.ViewHolder { // 홀더 설정
        public TextView r_name;
        public TextView r_star;
        public TextView r_content;

        public ViewHolder(View view) {
            super(view);
            this.r_name = view.findViewById(R.id.r_name);
            this.r_star = view.findViewById(R.id.r_star);
            this.r_content = view.findViewById(R.id.r_content);
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
        holder.r_name.setText(re_name[position]);
        holder.r_star.setText(re_star[position]);
        holder.r_content.setText(re_content[position]);
        // 연결된 뷰들은 액션에 대한 실행을 지정할 수 있다.
    }

    @Override
    public int getItemCount() { // 리사이클러뷰의 데이터 개수를 리턴
        return re_name.length;
    }
}
