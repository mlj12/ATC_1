package uk.aber.ac.atc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Product_Category extends AppCompatActivity {

private ImageView fruits,vegetables,seeds, wheat, meat,pesticide,equipment,dairy_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__category);

        fruits = (ImageView) findViewById(R.id.fruit);
        vegetables = (ImageView) findViewById(R.id.vegetables);
        meat = (ImageView) findViewById(R.id.meat);
        dairy_product = (ImageView) findViewById(R.id.dairy_product);

        equipment = (ImageView) findViewById(R.id.equipment);
        pesticide = (ImageView) findViewById(R.id.pesticide);
        seeds = (ImageView) findViewById(R.id.seed);
        wheat = (ImageView) findViewById(R.id.wheat);


       fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Product_Category.this, AddProduct.class);
                intent.putExtra("category", "Fruits");
                startActivity(intent);
            }
        });
        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Product_Category.this, AddProduct.class);
                intent.putExtra("category", "Vegetables");
                startActivity(intent);
            }
        });
        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Product_Category.this, AddProduct.class);
                intent.putExtra("category", "Meat");
                startActivity(intent);
            }
        });
        dairy_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Product_Category.this, AddProduct.class);
                intent.putExtra("category", "Dairy Products");
                startActivity(intent);
            }
        });
        seeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Product_Category.this, AddProduct.class);
                intent.putExtra("category", "Seeds");
                startActivity(intent);
            }
        });
        wheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Product_Category.this, AddProduct.class);
                intent.putExtra("category", "Wheat");
                startActivity(intent);
            }
        });
        equipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Product_Category.this, AddProduct.class);
                intent.putExtra("category", "Equipment");
                startActivity(intent);
            }
        });
        pesticide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Product_Category.this, AddProduct.class);
                intent.putExtra("category", "Pesticide");
                startActivity(intent);
            }
        });





    }
}
