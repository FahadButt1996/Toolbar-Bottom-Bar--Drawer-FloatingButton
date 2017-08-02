package com.example.mfahad.toolbarbottombarnavigtiondrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.behaviour.BottomNavBarFabBehaviour;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener ,
        BottomNavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {
    private FloatingActionButton floatingActionButton;
    private DrawerLayout drawer;
    private BottomNavigationView bottom ;

    private ListView lvLeft;
    private ListView lvRight;

    private ArrayList<String> alLeft;

    private ArrayAdapter<String> adpLeft;
    private ArrayAdapter<String> adpRight;

    private float mPrevX;
    private float mPrevY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_bar);
        try {
            Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(tb);

            ActionBar ab = getSupportActionBar();
            ab.setDisplayShowTitleEnabled(false);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnTouchListener(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        bottom = (BottomNavigationView) findViewById(R.id.bottomBar);
        bottom.setOnNavigationItemSelectedListener(this);

        //Drawer ArrayList
        lvLeft = (ListView)findViewById(R.id.lvLeft);
        lvRight = (ListView)findViewById(R.id.lvRight);
        alLeft = new ArrayList<String>();
        alLeft.add("Home");
        alLeft.add("Main Page");

        adpLeft = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , alLeft);
        lvLeft.setAdapter(adpLeft);

        adpRight = new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1 , alLeft );
        lvRight.setAdapter(adpRight);

        lvLeft.setOnItemClickListener(this);
        lvRight.setOnItemClickListener(this);
    }


// code to move the button on the screen like messenger
    @Override
    public boolean onTouch(View v, MotionEvent event) {
            float currX,currY;
            int action = event.getAction();
            switch (action ) {
//when button pressed down
                case MotionEvent.ACTION_DOWN: {
                    mPrevX = event.getX();
                    mPrevY = event.getY();
//                    Toast.makeText(MainActivity.this, "action_down", Toast.LENGTH_SHORT).show();
                    break;
                }
                case MotionEvent.ACTION_MOVE:
                {
                    currX = event.getRawX();
                    currY = event.getRawY();
                    ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(v.getLayoutParams());
                    marginParams.setMargins((int)(currX - mPrevX), (int)(currY - mPrevY),0, 0);
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
                    v.setLayoutParams(layoutParams);
//                    Toast.makeText(MainActivity.this, "Action_move", Toast.LENGTH_SHORT).show();
                    break;
                }

                case MotionEvent.ACTION_CANCEL:
//                    Toast.makeText(MainActivity.this, "Action Cancel", Toast.LENGTH_SHORT).show();
                    break;
//when button comes up after pressing it
                case MotionEvent.ACTION_UP:
//                    Toast.makeText(MainActivity.this, "Action_UP", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.search:
                Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                break;
        }
        switch (item.getItemId()){
            case R.id.menue:
                Toast.makeText(MainActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                break;
        }
        switch (item.getItemId()){
            case R.id.favourite:
                Toast.makeText(MainActivity.this, "Favourite", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lvLeft:{
                Toast.makeText(this, alLeft.get(position), Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.lvRight:{
                Toast.makeText(this, alLeft.get(position), Toast.LENGTH_SHORT).show();
                break;
            }
            default:{
                break;
            }
        }
    }
}
