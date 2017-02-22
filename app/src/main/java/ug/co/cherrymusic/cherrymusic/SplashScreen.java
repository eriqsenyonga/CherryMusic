package ug.co.cherrymusic.cherrymusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView tvCherry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tvCherry = (TextView) findViewById(R.id.tv_cherry);

        tvCherry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                i.putExtra("beginning", 1);
                startActivity(i);
                finish();

            }
        });
    }
}
