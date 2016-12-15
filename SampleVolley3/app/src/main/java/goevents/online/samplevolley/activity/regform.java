package goevents.online.samplevolley.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import goevents.online.samplevolley.R;
import goevents.online.samplevolley.app.AppController;
import goevents.online.samplevolley.models.Apc;
import goevents.online.samplevolley.utils.Config;
import goevents.online.samplevolley.utils.CustomJsonRequest;
import goevents.online.samplevolley.utils.EndPoints;

public class regform extends AppCompatActivity {

    private ProgressDialog loading;
    private List<Apc> apcList;
    private EditText editName, editID, editEmail, editBook, editDate;
    private Button buttonAdd;
    private Spinner spinner;

    String [] type = {"Student", "Staff", "Faculty"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regform);
        editName = (EditText) findViewById(R.id.textName2);
        editID = (EditText) findViewById(R.id.textID);
        editEmail = (EditText) findViewById(R.id.textEmail2);
        editBook = (EditText) findViewById(R.id.book);
        editDate = (EditText) findViewById(R.id.date);

        spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                loading = ProgressDialog.show(regform.this, null, "Loading...", true, true);
                loading.setCancelable(false);
                loading.show();

                final String name = editName.getText().toString();
                Log.d("debug", name );
                final String type = String.valueOf(spinner.getSelectedItemPosition()+1);
                Log.d("debug", type);
                final String id_number = editID.getText().toString();
                Log.d("debug", id_number);
                final String email = editEmail.getText().toString();
                Log.d("debug", email);
                final String book = editBook.getText().toString();
                Log.d("debug", book);
                final String date = editDate.getText().toString();
                Log.d("debug", date);

                apcList = new ArrayList<>();

                CustomJsonRequest jsonReq = new CustomJsonRequest(Request.Method.POST, EndPoints.URL_DB_REGISTER,
                        null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.d("debug", "dbConnect: " + response.toString(4));
                            //parse json feed and store it in a model
                            parseJsonFeed(response);

                        } catch (JSONException e) {

                            e.printStackTrace();
                            Toast.makeText(regform.this, "Error parsing data", Toast.LENGTH_SHORT).show();

                        }

                        loading.cancel();

                    }  //end of onResponse

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(regform.this, "Can't reach database", Toast.LENGTH_SHORT).show();
                        loading.cancel();
                    } //end of onErrorResponse

                }   ){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", name);
                        params.put("user_id", id_number);
                        params.put("email", email);
                        params.put("type", type);
                        params.put("book", book);
                        params.put("date", date);
                        return params;

                    } //end of getParams()

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return super.getHeaders();
                    } //end of getHeaders()

                };


                // Setting timeout to volley request as some connection
                // request takes sometime when slow internet connection
                int socketTimeout = 60000;
                RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                jsonReq.setRetryPolicy(policy);

                AppController.getInstance().addToRequestQueue(jsonReq);
            } //end of onClick
        }); //end of onClickListener



    }  //END OF onCreate

    private void parseJsonFeed(JSONObject jsonObject) {

        boolean isError = Boolean.parseBoolean(jsonObject.optString(Config.KEY_ERROR));

        if (isError) {
            Toast.makeText(regform.this, "Error", Toast.LENGTH_SHORT).show();
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
                person.setIdNumber(feedObj.getString(Config.KEY_ID_NUMBER));
                person.setType(feedObj.getString(Config.KEY_TYPE));
                person.setType(feedObj.getString(Config.KEY_BOOK));
                person.setType(feedObj.getString(Config.KEY_DATE));
                apcList.add(person);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(regform.this, "Successfully added!", Toast.LENGTH_SHORT).show();
    } //end of parseJsonFeed

    }

