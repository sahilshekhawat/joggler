package project.is.joggler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.plus.PlusClient;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks {

    private Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private RecyclerView.LayoutManager mLinearLayoutManager;
    RecyclerView mRecycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_topdrawer);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mRecycleView = (RecyclerView) findViewById(R.id.listView);
        mRecycleView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLinearLayoutManager);

        //setting data in it.
        ArrayList<MainActivityListItem> mainActivityList = new ArrayList<MainActivityListItem>();
        MainActivityListItem mainActivityListItem1 = new MainActivityListItem("Love Songs");
        MainActivityListItem mainActivityListItem2 = new MainActivityListItem("Dancing all night");
        MainActivityListItem mainActivityListItem3 = new MainActivityListItem("Mood");
        MainActivityListItem mainActivityListItem4 = new MainActivityListItem("Party");
        MainActivityListItem mainActivityListItem5 = new MainActivityListItem("Classic");
        MainActivityListItem mainActivityListItem6 = new MainActivityListItem("Romantic");
        MainActivityListItem mainActivityListItem7 = new MainActivityListItem("Dancing all night");
        MainActivityListItem mainActivityListItem8 = new MainActivityListItem("Love Songs");
        mainActivityList.add(mainActivityListItem1);
        mainActivityList.add(mainActivityListItem2);
        mainActivityList.add(mainActivityListItem3);
        mainActivityList.add(mainActivityListItem4);
        mainActivityList.add(mainActivityListItem5);
        mainActivityList.add(mainActivityListItem6);
        mainActivityList.add(mainActivityListItem7);
        mainActivityList.add(mainActivityListItem8);
        MainActivityListAdapter listAdapter = new MainActivityListAdapter(this, mainActivityList);
        mRecycleView.setAdapter(listAdapter);
        //To Add Floating Action bar when we will have the listview ready.
        /*RecyclerView recyclerView = (RecyclerView) findViewById(android.R.id.list);
        */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToRecyclerView(mRecycleView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), UploadSong.class);
                startActivity(intent);
            }
        });

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SignOut signOut = new SignOut(PlusBaseActivity.mPlusClient);
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.signOut:
                signOut.signOut();
                goBackToLogin();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }

    public void goBackToLogin(){
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
    }
}
