package jp.techinstitute.oshimaryo.sample4;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    CustomView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = new CustomView(this);



        Button button1 = (Button) findViewById(R.id.Redbutton);
        Button button2 = (Button) findViewById(R.id.Bluebutton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.mPaint.setColor(Color.RED);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.mPaint.setColor(Color.BLUE);

            }
        });

    }

}
