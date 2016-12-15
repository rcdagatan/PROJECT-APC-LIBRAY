package goevents.online.samplevolley.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import goevents.online.samplevolley.R;
import goevents.online.samplevolley.adapter.CustomListAdapter;
import goevents.online.samplevolley.app.AppController;
import goevents.online.samplevolley.models.Apc;
import goevents.online.samplevolley.utils.Config;
import goevents.online.samplevolley.utils.CustomJsonRequest;
import goevents.online.samplevolley.utils.EndPoints;

public class view extends AppCompatActivity {

    private ProgressDialog loading;
    private ArrayList<Apc> apcList;
    private ListView listview;
    private CustomListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        loading = ProgressDialog.show(this, null, "Loading...", true, true);
        loading.setCancelable(false);
        loading.show();
        listview = (ListView) findViewById(R.id.listV2);
        apcList = new ArrayList<>();
      /*  listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(view.this, apcList.get(position).getName() + apcList.get(position).getID() +
                        " has been removed from the list.", Toast.LENGTH_SHORT).show();
                apcList.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        }); */

        CustomJsonRequest jsonReq = new CustomJsonRequest(Request.Method.GET, EndPoints.URL_DB_GET_ALL,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    Log.d("debug", "dbConnect: " + response.toString(4));


                    //parse json feed and store it in a model
                    parseJsonFeed(response);

                } catch (JSONException e) {

                    e.printStackTrace();
                    Toast.makeText(view.this, "Error parsing data", Toast.LENGTH_SHORT).show();

                }

                loading.cancel();

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(view.this, "Can't reach database", Toast.LENGTH_SHORT).show();
                loading.cancel();
            }

        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("key", "data");
//                return params;
                return super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }

        };


        // Setting timeout to volley request as some connection
        // request takes sometime when slow internet connection
        //
        //        int socketTimeout = 60000;
        //        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
        //                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
        //                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        //        jsonReq.setRetryPolicy(policy);

        AppController.getInstance().addToRequestQueue(jsonReq);

    }

    private void parseJsonFeed(JSONObject jsonObject) {

        boolean isError = Boolean.parseBoolean(jsonObject.optString(Config.KEY_ERROR));

        if (isError) {
            //add codes here if response error is true
            return;
        }

        try {

            JSONArray feedArray = jsonObject.getJSONArray(Config.KEY_DATABASE);

            for (int i = 0; i < feedArray.length(); i++) {

                JSONObject feedObj = (JSONObject) feedArray.get(i);
                Apc person = new Apc();
                person.setID(feedObj.getString(Config.KEY_ID));
                person.setName(feedObj.getString(Config.KEY_NAME));
                person.setEmail(feedObj.getString(Config.KEY_EMAIL));
                person.setBook(feedObj.getString(Config.KEY_BOOK));
                person.setDate(feedObj.getString(Config.KEY_DATE));
                person.setIdNumber(feedObj.getString(Config.KEY_ID_NUMBER));
                person.setType(feedObj.getString(Config.KEY_TYPE_NAME));
                apcList.add(person);

            }
            adapter = new CustomListAdapter(view.this, apcList);
            listview.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
