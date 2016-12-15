package goevents.online.samplevolley;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import goevents.online.samplevolley.activity.view;

/**
 * Created by Aaron on 12/13/2016.
 */

public class usermain extends AppCompatActivity implements View.OnClickListener {

    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermain);
        b1 = (Button) findViewById(R.id.button2);
        b1.setOnClickListener(this);


    }
    @Override
    public void onClick (View v){
        if (v.getId() == R.id.button2) {
            Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, view.class);
            startActivity(intent);
            //finish();

        }
    }
}