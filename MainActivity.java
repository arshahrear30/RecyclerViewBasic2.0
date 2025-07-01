package com.example.animsplash;

// ২ অথবা তার বেশি item নিয়ে কাজ করা শিখবো আজ

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HashMap<String,String> hashMap;
    ArrayList< HashMap<String,String> > arrayList;

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


    }

    //==================================


}
