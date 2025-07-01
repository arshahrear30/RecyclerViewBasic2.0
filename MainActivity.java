package com.example.animsplash;

// ২ অথবা তার বেশি item নিয়ে কাজ করা শিখবো আজ

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        arrayList = new ArrayList<>();

        hashMap = new HashMap<>();
        hashMap.put("itemType", "BOOK");
        hashMap.put("bookName", "কোথাও কেউ নেই (হার্ডকভার)");
        hashMap.put("writerName", "হুমায়ূন আহমেদ");
        hashMap.put("bookLink", "https://www.rokomari.com/book/1241/kothao-kew-nei");
        hashMap.put("bookImage", "https://ds.rokomari.store/rokomari118/ProductNew20190903/260X372/Kothao_Kew_Nei-Humayun_Ahmed-f86bd-1241.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "VIDEO");
        hashMap.put("videoTitle", "Usaid Zahid Siddique - Assubhu Bada - New Naat - Allah Hu Allah");
        hashMap.put("videoId", "T1bnRJFwjic");
        arrayList.add(hashMap);


        XAdapter adapter = new XAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }

    //==================================
    private class XAdapter extends RecyclerView.Adapter{

        int BOOK_ITEM =1;
        int VIDEO_ITEM =1;



        private class videoViewHolder extends RecyclerView.ViewHolder {

            TextView tvVideoTitle;
            ImageView imgThumbnail;
            public videoViewHolder(@NonNull View itemView) {
                super(itemView);

                tvVideoTitle = itemView.findViewById(R.id.tvVideoTitle);
                imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            }
        }

        private class bookViewHolder extends RecyclerView.ViewHolder {

            ImageView imgBook;
            TextView tvBookName, tvWriterName;
            MaterialButton buttonGetBook;
            public bookViewHolder(@NonNull View itemView) {
                super(itemView);

                imgBook = itemView.findViewById(R.id.imgBook);
                tvBookName = itemView.findViewById(R.id.tvBookName);
                tvWriterName = itemView.findViewById(R.id.tvWriterName);
                buttonGetBook = itemView.findViewById(R.id.buttonGetBook);

            }
        }



        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater =getLayoutInflater();


            if (viewType == BOOK_ITEM) {
                View myView = inflater.inflate(R.layout.item_book, parent, false);
                return new bookViewHolder(myView);
            } else {
                View myView = inflater.inflate(R.layout.item_video, parent, false);
                return new videoViewHolder(myView);
            }
//return কেটে দিচি
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == BOOK_ITEM) {
                bookViewHolder myHolder = (bookViewHolder) holder;

                hashMap = arrayList.get(position);
                String bookName = hashMap.get("bookName");
                String writerName = hashMap.get("writerName");
                String bookLink = hashMap.get("bookLink");
                String bookImage = hashMap.get("bookImage");

                myHolder.tvBookName.setText(bookName);
                myHolder.tvWriterName.setText(writerName);

                myHolder.buttonGetBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), bookLink, Toast.LENGTH_SHORT).show();
                    }
                });

                Glide.with(context).load(bookImage).into(myHolder.imgBook);

            }

            //===

            else if (getItemViewType(position)==VIDEO_ITEM) {

                videoViewHolder myHolder = (videoViewHolder) holder;

                hashMap = arrayList.get(position);
                String videoTitle = hashMap.get("videoTitle");
                String videoID = hashMap.get("videoID");
                String imgUrl = "https://img.youtube.com/vi/" + videoID + "/0.jpg";

                myHolder.tvVideoTitle.setText(videoTitle);
                Glide.with(context).load(imgUrl).into(myHolder.imgThumbnail);


            }

//====
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        //Mouse Right Button Generate > Override Methods >  getItemViewType


        @Override
        public int getItemViewType(int position) {
            hashMap = arrayList.get(position);
            String itemType = hashMap.get("itemType");

                    if (itemType.contains("BOOK")) return BOOK_ITEM;
                    else return VIDEO_ITEM;

            //এখানের return টা দরকার পড়ে না তাই কেটে দিচি
        }
    }




}
