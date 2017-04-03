package com.example.daniel.feedtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements HustlerListAdapter.OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rvPopular)
    RecyclerView rvPopular;
    @Bind(R.id.rvRecommended)
    RecyclerView rvRecommended;
    @Bind(R.id.rvNearby)
    RecyclerView rvNearby;

    private HustlerListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mAdapter = new HustlerListAdapter(HustlerListAdapter.ORIENTATION_HORIZONTAL, this);

        SpaceItemDecoration dividerItemDecoration = new SpaceItemDecoration(this, R.dimen.horizontal_list_spacing);

        rvPopular.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvPopular.setLayoutManager(layoutManager1);
        rvPopular.addItemDecoration(dividerItemDecoration);
        rvPopular.setAdapter(mAdapter);

        rvRecommended.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvRecommended.setLayoutManager(layoutManager2);
        rvRecommended.addItemDecoration(dividerItemDecoration);
        rvRecommended.setAdapter(mAdapter);

        rvNearby.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this);
        layoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNearby.setLayoutManager(layoutManager3);
        rvNearby.addItemDecoration(dividerItemDecoration);
        rvNearby.setAdapter(mAdapter);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle("");
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @SuppressLint("NewApi")
    private int getSoftButtonsBarHeight() {
        // getRealMetrics is only available with API 17 and +
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }
        return 0;
    }

    boolean hasNavBar(Context context) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            return resources.getBoolean(id);
        } else {    // Check for keys
            boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
            boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            return !hasMenuKey && !hasBackKey;
        }
    }

    public int getStatusBarHeight() {
        int height = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

    @Override
    public void onHustlerClick(Hustler hustler) {
        Log.d(TAG, "onHustlerClick()");
    }

    @Override
    public void onSeeMoreClick() {
        Intent intent = new Intent(this, MainListActivity.class);

        Bundle bundle = new Bundle();
        // TODO: 30/3/2017 - Send section title
        bundle.putString("extra_section_title", "");

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
