package com.singh.grv.factly;



import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.singh.grv.factly.pojo.Joke;
import com.singh.grv.factly.pojo.Value;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private static final String API_URL = "http://api.icndb.com/jokes/random?firstName=Rajini&lastName=Kanth";
    RequestQueue requestQueue;
    private TextView textViewFact;
    GestureDetectorCompat gestureDetectorCompat;
    String fact, subString = "&quot;";
    ImageButton btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Activity full screen>>>>START
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Activity full screen>>>>END

        setContentView(R.layout.activity_main);
        textViewFact = (TextView) findViewById(R.id.textViewFact);
        this.gestureDetectorCompat = new GestureDetectorCompat(this,this);
        gestureDetectorCompat.setOnDoubleTapListener(this);

        requestQueue = Volley.newRequestQueue(this);
        btnAbout = (ImageButton) findViewById(R.id.imageButton);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });

        /*mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/


    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

        StringRequest request = new StringRequest(API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Joke joke = gson.fromJson(response, Joke.class);
                Value v = joke.getValue();
                fact = v.getJoke();
                if ((fact.toLowerCase().contains(subString.toLowerCase()))==true)
                {
                    fact.replaceAll(subString, "\"");
                }
                textViewFact.setText(fact);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toasty.error(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT, true).show();
            }
        });
        requestQueue.add(request);

        return true;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id==R.id.menu_item_about)
        {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        Toasty.info(MainActivity.this, "Fling/Swipe to see next random fact!", Toast.LENGTH_SHORT, true).show();
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Toasty.info(MainActivity.this, "Fling/Swipe to see next random fact!", Toast.LENGTH_SHORT, true).show();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }
}
