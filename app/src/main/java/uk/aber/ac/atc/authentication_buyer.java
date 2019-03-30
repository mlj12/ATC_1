package uk.aber.ac.atc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class authentication_buyer extends AppCompatActivity {

    private Button logInBtn,signUpbtn;
    private ProgressDialog loadingbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_authentication_buyer);

        signUpbtn = (Button) findViewById(R.id.main_signup_btn);
        logInBtn = (Button) findViewById(R.id.main_login_btn);




        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(authentication_buyer.this, register_buyer.class);
                startActivity(intent);
            }
        });


        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(authentication_buyer.this, Log_In_buyer.class);
                startActivity(intent);
            }
        });
    }


}
