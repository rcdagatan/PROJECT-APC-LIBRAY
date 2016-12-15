package goevents.online.samplevolley;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Aaron on 12/12/2016.
 */

public class First extends AppCompatActivity implements View.OnClickListener {

    private Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        b1 = (Button) findViewById(R.id.button5);
        b1.setOnClickListener(this);
        b2 = (Button) findViewById(R.id.button6);
        b2.setOnClickListener(this);

        }
        @Override
        public void onClick (View v){
            if (v.getId() == R.id.button5) {
                Toast.makeText(this, "Loading Admin Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                //finish();
            } else if (v.getId() == R.id.button6) {
                Toast.makeText(this, "Loading View", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, usermain.class);
                startActivity(intent);
            }
        }
    }