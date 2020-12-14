package org.techtown.festival;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// 리사이클러뷰 어뎁터 코드
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private ArrayList<Review> mData;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public ReviewAdapter(ArrayList<Review> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.review_list, parent, false) ;
        ReviewAdapter.ViewHolder vh = new ReviewAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolder holder, int position) {

        Review item = mData.get(position) ;

        holder.id.setText(item.getId());
        holder.title.setText(item.getTitle());
        holder.content.setText(item.getContent());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id ;
        TextView title;
        TextView content;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            id = itemView.findViewById(R.id.r_id) ;
            title = itemView.findViewById(R.id.r_title) ;
            content = itemView.findViewById(R.id.r_content) ;
        }
    }
}
