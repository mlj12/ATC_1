package uk.aber.ac.agrotradecentre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buyerBtn, sellerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buyerBtn = (Button)findViewById(R.id.btn_buyers);
        sellerBtn = (Button)findViewById(R.id.btn_sellers);


    }
}

