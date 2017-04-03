package com.example.daniel.feedtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainListActivity extends AppCompatActivity implements HustlerListAdapter.OnItemClickListener {

    private static final String TAG = MainListActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rvHustlers)
    RecyclerView rvHustlers;

    private HustlerListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mAdapter = new HustlerListAdapter(HustlerListAdapter.ORIENTARION_VERTICAL, this);

        SpaceItemDecoration dividerItemDecoration = new SpaceItemDecoration(this, R.dimen.vertical_list_spacing);

        rvHustlers.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvHustlers.setLayoutManager(layoutManager);
        rvHustlers.addItemDecoration(dividerItemDecoration);
        rvHustlers.setAdapter(mAdapter);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setTitle("");
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onHustlerClick(Hustler hustler) {
        Log.d(TAG, "onHustlerClick()");
    }

    @Override
    public void onSeeMoreClick() {

    }
}
