package com.ebite.www.ebite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,ImageClickListener {
    public String TAG = MainActivity.class.getSimpleName();
    public static final int SHOW_IMAGE_CODE = 1;
    RecyclerView rvImageList;
    ImageClickListener imageClickListener;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    static int step = 20;
    FirebaseDatabase database = null;
    int lastFirstVisiblePosition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        imageClickListener = this;
//        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
//        swipeRefreshLayout.setVisibility(View.GONE);

//        swipeRefreshLayout.setOnRefreshListener(
//                new SwipeRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        Log.i(TAG, "onRefresh called from SwipeRefreshLayout");
//
//                        // This method performs the actual data-refresh operation.
//                        // The method calls setRefreshing(false) when it's finished.;
//                        step = step +10;
////                        fetchData(step);
////                        firebaseInitialization(step);
//                    }
//                }
//        );

        rvImageList = (RecyclerView) findViewById(R.id.rvImageList);
//        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
//        linearLayout.setStackFromEnd(true);
//        rvImageList.setLayoutManager(linearLayout);
        if(database == null){
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }
        setAdapter();

    }

    public void fetchData(int step){

//        myRef = database.getReference().child("ebitedb").orderByChild("systemTime").endAt(System.currentTimeMillis()).limitToLast(20);

        if(step==20){
            setAdapter();
        }else{
            firebaseRecyclerAdapter.onDataChanged();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        lastFirstVisiblePosition = ((LinearLayoutManager)rvImageList.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        ((LinearLayoutManager) rvImageList.getLayoutManager()).scrollToPositionWithOffset(lastFirstVisiblePosition,0);
    }

    private void setAdapter() {
        Query myRef = database.getReference().child("ebitedb");
        if(myRef != null){
            Log.v(TAG,myRef.toString());
        }else{
            Log.v(TAG,"Not Connecting to Database");
        }
        FirebaseRecyclerOptions<ImageViewModel> options =
                new FirebaseRecyclerOptions.Builder<ImageViewModel>()
                        .setQuery(myRef, ImageViewModel.class)
                        .build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ImageViewModel,ImageViewHolder>(options) {
            @Override
            public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.image_viewholder_item, parent, false);

                return new ImageViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ImageViewHolder holder, int position, ImageViewModel model) {
                holder.bindImageData(model,imageClickListener,position);
//                Log.v("Hello",model.toString());
            }

        };

        rvImageList.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        firebaseRecyclerAdapter.stopListening();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        firebaseRecyclerAdapter.stopListening();
    }

    @Override
    public void showImage(String url, int position) {
        Intent intent = new Intent(this,MapView.class);
        intent.putExtra("bitmap",url);
        intent.putExtra("position",position);
        startActivityForResult(intent,SHOW_IMAGE_CODE);
        Log.v("MapView","Image Clicked");
    }

    @Override
    public void shareImage(String uri) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == SHOW_IMAGE_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                int position= data.getIntExtra("position",0);
            }
        }
    }


}
