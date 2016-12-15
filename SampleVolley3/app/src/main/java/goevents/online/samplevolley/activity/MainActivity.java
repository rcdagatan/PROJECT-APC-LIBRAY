package goevents.online.samplevolley.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
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
import goevents.online.samplevolley.RegisterActivity;
import goevents.online.samplevolley.app.AppController;
import goevents.online.samplevolley.models.Apc;
import goevents.online.samplevolley.utils.Config;
import goevents.online.samplevolley.utils.CustomJsonRequest;
import goevents.online.samplevolley.utils.EndPoints;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ProgressDialog loading;
    private List<Apc> apcList;
    private EditText editName, editID, editEmail;
    private Button b1,b2,b3,b4,b5;
    private Spinner spinner;

    String [] type = {"Student", "Staff", "Faculty", "Director"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(this);
        b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(this);
        b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(this);
        b4 = (Button) findViewById(R.id.button4);
        b4.setOnClickListener(this);
        b5 = (Button) findViewById(R.id.button5);
        b5.setOnClickListener(this);

    }  //END OF onCreate



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button){
            Toast.makeText(this,"Loading --Regform--",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,regform.class);
            startActivity(intent);
            //finish();
        }
        else if (v.getId() == R.id.button2){
            Toast.makeText(this,"Loading --View--",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,view.class);
            startActivity(intent);
            //finish();
        }
        else if (v.getId() == R.id.button3){
            Toast.makeText(this,"Loading --Update--",Toast.LENGTH_SHORT).show();
           Intent intent=new Intent(this,update.class);
           startActivity(intent);
            finish();
        }
        else if (v.getId() == R.id.button4){
            Toast.makeText(this,"Loading --Delete--",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,delete.class);
           startActivity(intent);
            finish();
        }
        else if (v.getId() == R.id.button5){
            Toast.makeText(this,"Loading --Register--",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
} //end of class
