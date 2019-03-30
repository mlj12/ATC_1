package uk.aber.ac.atc;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Home_buyer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Button btn_test;
    private TextView displayName,displayDescription,displayPrice;
    private ImageView displayImage;
    private DatabaseReference productRef;
    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {




        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        displayName = (TextView) findViewById(R.id.display_name);
        displayDescription =(TextView)findViewById(R.id.display_description);
        displayPrice =(TextView)findViewById(R.id.display_price);
        displayImage=(ImageView) findViewById(R.id.display_image);




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_buyer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        productRef = FirebaseDatabase.getInstance().getReference()
        .child("Products");



    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_buyer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.buyer_role) {

            Intent intent = new Intent(Home_buyer.this, Home_buyer.class);
            startActivity(intent);
            return true;
        }

      /*  else if
        (id == R.id.seller_role) {

            Intent intent = new Intent(Home_buyer.this, Home_seller.class);
            startActivity(intent);
            return true;
        } */

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.cart) {
            // Handle the camera action

            Intent intent = new Intent(Home_buyer.this, Cart.class);
            startActivity(intent);
        } else if (id == R.id.Purchases) {

          //  Intent intent = new Intent(Home_buyer.this, Cart.class);
            //startActivity(intent);

        } else if (id == R.id.inbox) {

        } else if (id == R.id.settings) {

        }

        else if (id == R.id.sell) {

            Intent intent = new Intent(Home_buyer.this, Product_Category.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
