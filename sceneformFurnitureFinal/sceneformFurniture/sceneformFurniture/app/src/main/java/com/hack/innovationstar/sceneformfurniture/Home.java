package com.hack.innovationstar.sceneformfurniture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ImageView imageView;
    RecyclerView list;
    ArrayList<Item> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageView = findViewById(R.id.imageView);
        list = findViewById(R.id.list);

        items.add(new Item(1,"chair",700));
        items.add(new Item(2,"couch",4000));
        items.add(new Item(3,"lamp",400));
        items.add(new Item(4,"dresser",800));
        items.add(new Item(5,"couch2",20010));
        items.add(new Item(6,"ladder",809));
        items.add(new Item(7,"table",500));
        items.add(new Item(8,"Nightstand",300));
        items.add(new Item(9,"Paper Towel",10));
        items.add(new Item(10,"Kitchen Table",2000));
        items.add(new Item(11,"dj",2100));
        items.add(new Item(12,"desk set",2000));


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_SHORT).show();
            }
        });



        ListAdapter myAdapter = new ListAdapter(this,items);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(myAdapter);
    }
}
