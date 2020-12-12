package org.techtown.festival;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerTextAdapter extends RecyclerView.Adapter<RecyclerTextAdapter.ViewHolder> {
    private ArrayList<RecyclerItem> mData;
    public static final int REQUEST_CODE_MENU = 102;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public RecyclerTextAdapter(ArrayList<RecyclerItem> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false) ;
        RecyclerTextAdapter.ViewHolder vh = new RecyclerTextAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        RecyclerItem item = mData.get(position) ;

        holder.name.setText(item.getName()); ;
        holder.location.setText(item.getLocation()) ;
        holder.period.setText(item.getPeriod()) ;
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name ;
        TextView location ;
        TextView period ;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            name = itemView.findViewById(R.id.name_tv) ;
            location = itemView.findViewById(R.id.location_tv) ;
            period = itemView.findViewById(R.id.period_tv) ;

            // 리사이클러뷰 클릭시 정보 액티비티로 넘어가게끔 구현
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        // 데이터 리스트로부터 아이템 데이터 참조
                        RecyclerItem item = mData.get(pos);
                        String ID = item.getId();
                        //Toast.makeText(v.getContext(), pos + "번째 ID : " + ID, Toast.LENGTH_SHORT).show();

                        // 정보 액티비티로 ID를 가지고 넘어간다.
                        Intent intent = new Intent(v.getContext(), InformationActivity.class);
                        intent.putExtra("ID", ""+ID);
                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}