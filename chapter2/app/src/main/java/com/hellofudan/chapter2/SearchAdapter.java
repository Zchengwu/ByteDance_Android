package com.hellofudan.chapter2;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<TextViewHolder> implements Filterable {

    private List<String> mItems = new ArrayList<>();
    private List<String> mSourceList = new ArrayList<>();

    public void initList(List<String> list){
        mSourceList = list;
        mItems = mSourceList;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_text, parent ,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() { return mItems.size(); }

    public void notifyItems(@NonNull List<String> items){
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            // 执行过滤操作
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()){
                    // 没有过滤内容
                    mItems = mSourceList;
                } else {
                    List<String> filteredList = new ArrayList<>();
                    for (String str : mItems) {
                        //这里根据需求，添加匹配规则
                        if (str.contains(charString)) {
                            filteredList.add(str);
                        }
                    }

                    mItems = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mItems = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
