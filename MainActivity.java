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

import com.bumptech.glide.Glide;
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

        arrayList = new ArrayList<>(); //1.আমি একটা Invisible table create করলাম

        hashMap = new HashMap<>(); // 2.এক Row বিশিষ্ট Invisible table create হলো
        hashMap.put("itemType", "BOOK"); //3.একটা unique key and values set করি। যাতে hashmap টার type বোঝা যায়।
        hashMap.put("bookName", "কফয়জুল (হার্ডকভার)");//("key","value")
        hashMap.put("writerName", " মুফতীয়ে আযম");
        hashMap.put("bookLink", "https://www.rokomari.com/book/325210/foyjul-kalam");
        hashMap.put("bookImage", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Foyjul_Kalam-Mufti_Azam_Faizullah_Rah-77538-325210.jpg");
        arrayList.add(hashMap); //4.এখন এই hashmap কে ArrayList এ assign করে দিলাম।

        //মাঝে মাঝে bookLink ,bookImage link গুলো কাজ করবে না । কারণ website owner রা ছবিগুলো বিভিন্ন ভাবে update করে ।

        hashMap = new HashMap<>(); //5.same to same as like BOOK itemType . next line : 76
        hashMap.put("itemType", "VIDEO");
        hashMap.put("videoTitle", "The Coldest Village on Earth (10 minutes outside = Death) ");
        hashMap.put("videoId", "2C8zYFArnKY");
        arrayList.add(hashMap);

        hashMap = new HashMap<>(); // এক Row বিশিষ্ট Invisible table create হলো //copy করলাম উপর থেকে এভাবে বার বার করা যাবে
        hashMap.put("itemType", "BOOK"); //একটা unique key and values set করি। যাতে hashmap টার type বোঝা যায়।
        hashMap.put("bookName", "কোথাও কেউ নেই");//("key","value")
        hashMap.put("writerName", "হুমায়ূন আহমেদ");
        hashMap.put("bookLink", "https://www.rokomari.com/book/1241/kothao-kew-nei");
        hashMap.put("bookImage", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Kothao_Kew_Nei-Humayun_Ahmed-f86bd-1241.jpg");
        arrayList.add(hashMap); //এখন এই hashmap কে ArrayList এ assign করে দিলাম।


        XAdapter adapter = new XAdapter(); //36.Adapter খাওয়াইলাম
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this)); //setLayoutManager করলাম MainActivity জন্য


    }

    //==================================
    //6.RecyclerView এর জন্য adapter create করবো এখন

    private class XAdapter extends RecyclerView.Adapter{ //7.adapter তৈরি করলাম নাম দিলাম XAdapter
        //8.Red light এ click করি এবং implement method এ click দেই OK . next line : 86

        int BOOK_ITEM =0; //20.BOOK_ITEM =1 ধরছি same as for VIDEO_ITEM . next line :123
        int VIDEO_ITEM =1;



        private class videoViewHolder extends RecyclerView.ViewHolder { //9. video জন্য ViewHolder create করলাম
            //10.Red bulb > Create constructor . next line :99

            TextView tvVideoTitle; //26.
            ImageView imgThumbnail;
            public videoViewHolder(@NonNull View itemView) {
                super(itemView);

                tvVideoTitle = itemView.findViewById(R.id.tvVideoTitle);  //27.
                imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            }
        }

        private class bookViewHolder extends RecyclerView.ViewHolder { //11. book জন্য ViewHolder create করলাম
            //12.Red bulb > Create constructor . next line : 206

            ImageView imgBook;//24.id গুলো ধরলাম . next line :109
            TextView tvBookName, tvWriterName;
            MaterialButton buttonGetBook;

            public bookViewHolder(@NonNull View itemView) {
                super(itemView);

                imgBook = itemView.findViewById(R.id.imgBook); //25. findViewById করলাম . next line :143
                tvBookName = itemView.findViewById(R.id.tvBookName);
                tvWriterName = itemView.findViewById(R.id.tvWriterName);
                buttonGetBook = itemView.findViewById(R.id.buttonGetBook);

            }
        }



        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = getLayoutInflater(); //21.LayoutInflater করবো


            if (viewType == BOOK_ITEM) { //22.getItemViewType এর মত return করবে ঐটাই দেখাইছি ==
                View myView = inflater.inflate(R.layout.item_book, parent, false);
                return new bookViewHolder(myView);
                //23.item_book layout inflate করলাম myView এর মধ্যে  এবং bookViewHolder এর মধ্যে return করলাম . next line :102
            } else {
                View myView = inflater.inflate(R.layout.item_video, parent, false);
                return new videoViewHolder(myView); //same as if condition comment
            }
//return কেটে দিচি
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            //holder & position return নিয়ে আসে

            //+++++++++++++++++++++++++++
            if (getItemViewType(position) == BOOK_ITEM) {
                //26.getItemViewType position দিয়ে কাজ করলে BOOK_ITEM return হলে
                bookViewHolder myHolder = (bookViewHolder) holder; // 27. যে holder টা পাবো সেটা হলো bookViewHolder
                //onBindViewHolder কে bookViewHolder বানাও এবং myHolder রেখে দাও
                //এখন myHolder থেকে bookViewHolder এর সব access করতে পারবো :line 99

                hashMap = arrayList.get(position); //28.position অনুযায়ী ArrayList কে call করে hashMap এ রাখলাম
                String bookName = hashMap.get("bookName"); //29.hashMap  get করে String এ convert করবো
                String writerName = hashMap.get("writerName");
                String bookLink = hashMap.get("bookLink");
                String bookImage = hashMap.get("bookImage");

                myHolder.tvBookName.setText(bookName);//30.tvBookName টেক্সট দেখানোর জায়গায় bookName ভ্যারিয়েবলের ভ্যালুটা দেখানো হবে
                myHolder.tvWriterName.setText(writerName);

                myHolder.buttonGetBook.setOnClickListener(new View.OnClickListener() { //31.buttonGetBook এই button এ click করলে কিছু ঘটবে
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), bookLink, Toast.LENGTH_SHORT).show();  //32. . next line : 172
                    }
                });

                //Picasso.get().load(bookImage).into(myHolder.imgBook); //Glide library use করবো picasso cannot work properly now


               // Glide.with(context).load(bookImage).into(myHolder.imgBook);//error:Cannot resolve symbol 'context'
                //MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> { Context context; এভাবে দিলে সমাধান হবে ।
                //but আমি এইভাবে করবো না


                //Glide.with(myHolder.imgBook.getContext()) //33.শিখার বিষয় : ভুল ভাবে .with implement করছি এখানে MainActivity.this দিতে হবে
                        //.load(bookImage)
                        //.into(myHolder.imgBook);

                Glide.with(MainActivity.this) //33.best way
                .load(bookImage)
                .into(myHolder.imgBook);


            }
            //+++++++++++++++++++++++++++

            else if (getItemViewType(position)==VIDEO_ITEM) { //34. same as if condition comment. next line :195

                videoViewHolder myHolder = (videoViewHolder) holder;

                hashMap = arrayList.get(position);
                String videoTitle = hashMap.get("videoTitle");
                String videoId = hashMap.get("videoId");
                String imgUrl = "https://img.youtube.com/vi/" + videoId + "/0.jpg";

                myHolder.tvVideoTitle.setText(videoTitle);

                Glide.with(MainActivity.this) //35.best way. next line :68
                        .load(imgUrl)
                        .into(myHolder.imgThumbnail);
            }
//====
        }
        @Override
        public int getItemCount() {
            return arrayList.size(); //16.1 arrayList.size() দিলাম . next line :211
        }

        //13.Mouse Right Button > Generate > Override Methods(Ctrl+o) >  getItemViewType
        @Override
        public int getItemViewType(int position) { //14.সবার আগে এটা call হয়। ও দেখায় item এর viewType টা কী, তারপর call হয়
            //15.এই method টা যা return করে, তা আমরা RecyclerView.ViewHolder আর int viewType এর viewType এ return পাবো line:110
            //. next line :203
            hashMap = arrayList.get(position); //16.2 position অনুযায়ী ArrayList থেকে hashmap টা দাও
            String itemType = hashMap.get("itemType");//17.hashmap এর ভিতর থেকে itemType টা নিয়ে String itemType বানালা্ম

                    if (itemType.contains("BOOK")) return BOOK_ITEM;  //18.itemType টা BOOK হলে return করো BOOK_ITEM
                    else return VIDEO_ITEM;//19.itemType টা অন্যকিছু  হলে return করো VIDEO_ITEM . next line :81
            //itemType বেশি থাকলে elseif ব্যবহার করতাম

            //এখানের return টা দরকার পড়ে না তাই কেটে দিচি
        }
    }

    

}
