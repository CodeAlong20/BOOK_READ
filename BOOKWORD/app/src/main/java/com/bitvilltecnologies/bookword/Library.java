package com.bitvilltecnologies.bookword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.bitvilltecnologies.bookword.Authtentications.LoginActivity;
import com.bitvilltecnologies.bookword.DataHandler.Model;
import com.bitvilltecnologies.bookword.DataHandler.ViewHolder;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.google.firebase.database.Query;


public class Library extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    SwipeRefreshLayout swipeRefreshLayout;
    //menu
       private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.libray:

                    return true;

                case R.id.mybooks:
                    Intent intent =new Intent(Library.this,MyBoooks.class);
                    finish();
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;

                case R.id.addnote:
                    Intent intent2 =new Intent(Library.this,NEWBOOK.class);
                    finish();
                    startActivity(intent2);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;

                case R.id.search:
                    Intent intent3 =new Intent(Library.this,SearchActivity.class);
                    finish();
                    startActivity(intent3);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.libray);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("SCRIBE");

        recyclerView = findViewById(R.id.library_recycleView);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        firebaseDatabase =FirebaseDatabase.getInstance();



        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.canScrollVertically();
        recyclerView.setHasFixedSize(true);


        ApdaterMethod();

swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
});


    }

    public void ApdaterMethod() {

        Query query = FirebaseDatabase.getInstance().getReference().child("Users/Library").limitToLast(50);
        FirebaseRecyclerOptions<Model>options=new FirebaseRecyclerOptions.Builder<Model>().setQuery(query,Model.class).build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Model,ViewHolder>(options) {


            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
                return  new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int position, @NonNull Model model) {
                viewHolder.setDetails(model.getTitle(),model.getNote(),model.getImage(),model.getDescription(),model.getAuthor());


            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater MENU = getMenuInflater();
        MENU.inflate(R.menu.profileandexit,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.exit:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case  R.id.profile:
               // startActivity(new Intent(this, ProfilActivity.class));
        }
        return true;
    }
}



