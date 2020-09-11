package com.bitvilltecnologies.bookword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bitvilltecnologies.bookword.DataHandler.Model;
import com.bitvilltecnologies.bookword.DataHandler.ViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchActivity extends AppCompatActivity {
    ImageButton searchBtN;
    EditText searchEditText;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    SwipeRefreshLayout swipeRefreshLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) { switch (item.getItemId()) {
                case R.id.libray:
                    Intent intent =new Intent(SearchActivity.this, Library.class);
                    finish();
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;

                case R.id.mybooks:
                    Intent intent2 =new Intent(SearchActivity.this,MyBoooks.class);
                    finish();
                    startActivity(intent2);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;

                case R.id.addnote:
                    Intent intent3 =new Intent(SearchActivity.this,NEWBOOK.class);
                    finish();
                    startActivity(intent3);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;

                case R.id.search:

                    return true;

            }return false; }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        BottomNavigationView navigation =(BottomNavigationView)findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.search);

        searchBtN=(ImageButton)findViewById(R.id.searchbtn);
        searchEditText=(EditText)findViewById(R.id.searchtextedittext);
        recyclerView = findViewById(R.id.search_recycleView);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.srefresh_layout);

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.canScrollVertically();
        recyclerView.setHasFixedSize(true);

        firebaseDatabase =FirebaseDatabase.getInstance();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
swipeRefreshLayout.setRefreshing(false);

            }

        });

        searchBtN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
               firebaseSearch();
            }
        });


    }



    private void firebaseSearch() {
        final DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users/Library");
        String searchItem = searchEditText.getText().toString().toUpperCase();
        String searchItemL = searchEditText.getText().toString().toLowerCase();
        Toast.makeText(SearchActivity.this,"Searching...",Toast.LENGTH_LONG).show();

        Query query = databaseReference.orderByChild("title").startAt(searchItem).endAt(searchItemL+"\uf8ff");
        FirebaseRecyclerOptions<Model> options=new FirebaseRecyclerOptions.Builder<Model>().setQuery(query,Model.class).build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {


            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search,parent,false);
                return  new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int position, @NonNull Model model) {
                viewHolder.setSearch(model.getTitle(),model.getDescription(),model.getImage(),model.getAuthor(),model.getNote());


            }
        };

        adapter.startListening();

        swipeRefreshLayout.setRefreshing(false);
        //recyclerView.setAdapter(null);
        adapter.notifyItemRangeChanged(0,50);
        adapter.notifyDataSetChanged();
        recyclerView.removeAllViewsInLayout();
        recyclerView.setAdapter(adapter);


    }
}
