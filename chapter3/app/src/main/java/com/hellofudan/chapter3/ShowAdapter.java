package com.hellofudan.chapter3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter{

    private List<DataBaseContract.InfoTable> mItems = new ArrayList<>();
    private SQLiteDatabase db;
    private Context mContext;

    public void initList(List<DataBaseContract.InfoTable> list){
        mItems = list;
    }

    public ShowAdapter(Context context, SQLiteDatabase db) {
        this.mContext = context;
        this.db = db;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_combobox, parent, false);
        return new TextViewHolder(itemView);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TextViewHolder textViewHolder = (TextViewHolder) holder;
        textViewHolder.bind(mItems.get(position).getContent(), mItems.get(position).getTime(), mItems.get(position).getStatus());

        textViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the view now checked?
                boolean checked = ((CheckBox) view).isChecked();

                // Check which checkbox was clicked
                switch(view.getId()) {
                    case R.id.checkbox:
                        if (checked) {
                            db.execSQL("update Info set status = ? where content = ?", new String[]{
                                    "1", mItems.get(position).getContent()
                            });
                            Log.d("AAAAA", String.valueOf(position));
                            updateDataFromDB();

                        }
                        else {
                            // Remove the meat
                            db.execSQL("update Info set status = ? where content = ?", new String[]{
                                    "0", mItems.get(position).getContent()
                            });
                            updateDataFromDB();
                            Log.d("AAAAA", "bbbbb");
                        }
                        break;
                }

            }
        });

        textViewHolder.cross.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                db.execSQL("delete from Info where content = ?", new String[]{
                        mItems.get(position).getContent()
                });
                updateDataFromDB();
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() { return mItems.size(); }

    public void notifyItems(@NonNull List<DataBaseContract.InfoTable> items){
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void updateDataFromDB(){
        // load data from db
        List<DataBaseContract.InfoTable> data = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor = db.rawQuery("select * from Info", null);
            while(cursor.moveToNext()) {
                String content = cursor.getString(cursor.getColumnIndex(DataBaseContract.InfoEntry.COLUMN_CONTENT));
                long time = cursor.getLong(cursor.getColumnIndex(DataBaseContract.InfoEntry.COLUMN_TIME));
                boolean status = cursor.getInt(cursor.getColumnIndex(DataBaseContract.InfoEntry.COLUMN_STATUS)) > 0;

                DataBaseContract.InfoTable dataItem = new DataBaseContract.InfoTable();
                dataItem.setContent(content);
                dataItem.setTime(time);
                dataItem.setStatus(status);
                data.add(dataItem);
            }
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        this.mItems = data;
        notifyDataSetChanged();
    }

    void updateData(){
        updateDataFromDB();

    }


    public class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView_Content;
        private TextView mTextView_Time;
        private CheckBox checkBox;
        private ImageView cross;

        public TextViewHolder(@NonNull View itemView){
            super(itemView);
            
            mTextView_Content = itemView.findViewById(R.id.content);
            mTextView_Time = itemView.findViewById(R.id.time);
            checkBox = itemView.findViewById(R.id.checkbox);
            cross = itemView.findViewById(R.id.cross);
        }

        public void bind(String content, long time, boolean status) {
            mTextView_Content.setText(content);
            Date date = new Date(time);
            mTextView_Time.setText(date.toString());
            if(status == true){
                checkBox.setChecked(true);
                mTextView_Content.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );
            }
            else{
                checkBox.setChecked(false);
                mTextView_Content.setPaintFlags(mTextView_Content.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        }
    }



}
