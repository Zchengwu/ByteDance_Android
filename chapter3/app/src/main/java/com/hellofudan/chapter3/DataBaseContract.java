package com.hellofudan.chapter3;

import android.provider.BaseColumns;

public class DataBaseContract {

    private DataBaseContract(){};

    public static class InfoEntry implements BaseColumns{
        public static final String TABLE_NAME = "Info";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_STATUS = "status";
    }

    public static class InfoTable{
        private int id;
        private String content;
        private long time;
        private boolean status;

        int getId(){
            return this.id;
        }

        String getContent(){
            return this.content;
        }

        long getTime(){
            return this.time;
        }

        boolean getStatus() {
            return this.status;
        }

        void setId(int id){
            this.id = id;
        }

        void setContent(String curContent){
            this.content = curContent;
        }

        void setTime(long time){
            this.time = time;
        }

        void setStatus(boolean status) { this.status = status; }

    }

}
