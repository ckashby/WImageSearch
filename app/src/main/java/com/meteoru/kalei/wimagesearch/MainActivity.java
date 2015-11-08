package com.meteoru.kalei.wimagesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String IMAGE_URL = "url";
    private EditText etQuery;
    private Button btSearch;
    private GridView gvResults;
    private ArrayList<ImageResult> imageResults;
    private ImageResultAdapter aImageResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupViews();
    }

    public void setupViews(){
        etQuery = (EditText) findViewById(R.id.etQuery);
        btSearch = (Button) findViewById(R.id.btSearch);
        gvResults = (GridView) findViewById(R.id.gvResults);
        // Create the data source
        imageResults = new ArrayList<ImageResult>();
        // Attach the data source to the adapter
        aImageResult = new ImageResultAdapter(this, imageResults);
        // Link the adapter to the GridView
        gvResults.setAdapter(aImageResult);
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, WebViewActivity.class);
                ImageResult imageResult = imageResults.get(position);
                i.putExtra(IMAGE_URL, imageResult.getUrl());
                startActivity(i);
            }
        });
    }

    public void onSearch(View view){
        String query = etQuery.getText().toString();
        String searchUrl = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + query + "&rsz=8";
        Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(searchUrl, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        Log.d(TAG, response.toString());
                        try {
                            JSONArray imageResultsJsonArray = response.getJSONObject("responseData").getJSONArray("results");
//                            Log.d(TAG, imageResultsJsonArray.toString());
                            imageResults.clear();
                            // Use adapter's addAll() to update ArrayList<ImageResult>
                            aImageResult.addAll(ImageResult.fromJson(imageResultsJsonArray));
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        // TODO: handle failure
//                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
