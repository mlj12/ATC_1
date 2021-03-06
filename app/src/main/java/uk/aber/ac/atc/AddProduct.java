package uk.aber.ac.atc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddProduct extends AppCompatActivity
{
    private String CategoryName, description, price, pName,quantity,shipping,currency,saveCurrentDate, saveCurrentTime;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private EditText product_name,product_description,product_price,product_shipping,
            product_quantity,product_currency;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference productsRef;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


        CategoryName = getIntent().getExtras().get("category").toString();
        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        productsRef = FirebaseDatabase.getInstance().getReference().child("Products");


        AddNewProductButton = (Button) findViewById(R.id.add_product_btn);
        InputProductImage = (ImageView) findViewById(R.id.import_picture);
        product_name = (EditText) findViewById(R.id.product_name);
        product_description = (EditText) findViewById(R.id.product_description);
        product_quantity = (EditText) findViewById(R.id.available_inventory);
        product_price = (EditText) findViewById(R.id.product_price);
        product_currency = (EditText) findViewById(R.id.currency);
        product_shipping = (EditText) findViewById(R.id.location);

        loadingBar = new ProgressDialog(this);


        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                OpenGallery();
            }
        });


        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ValidateProductData();
            }
        });
    }



    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }


    private void ValidateProductData()
    {
        description = product_description.getText().toString();
        price = product_price.getText().toString();
        pName = product_name.getText().toString();
        shipping =product_shipping.getText().toString();
        currency = product_currency.getText().toString();
        quantity = product_quantity.getText().toString();



        if (ImageUri == null)
        {
            Toast.makeText(this, "Product image is mandatory...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(description))
        {
            Toast.makeText(this, "Please write product description...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(price))
        {
            Toast.makeText(this, "Please write product Price...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pName))
        {
            Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(shipping))
        {
            Toast.makeText(this, "Please write product shipping...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(currency))
        {
            Toast.makeText(this, "Please write product currency...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(quantity))
        {
            Toast.makeText(this, "Please write product quantity...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(description))
        {
            Toast.makeText(this, "Please write product description...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(price))
        {
            Toast.makeText(this, "Please write product Price...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pName))
        {
            Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(shipping))
        {
            Toast.makeText(this, "Please write product shipping...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(currency))
        {
            Toast.makeText(this, "Please write product currency...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(quantity))
        {
            Toast.makeText(this, "Please write product quantity...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreProductInformation();
        }
    }



    private void StoreProductInformation()
    {
        loadingBar.setTitle("Add New Product");
        loadingBar.setMessage("Please wait while we are adding your product.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;


        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(AddProduct.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(AddProduct.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(AddProduct.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                        }
                    }
                });
            }
        });
    }



    private void SaveProductInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("pid", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("description",description);
        productMap.put("image", downloadImageUrl);
        productMap.put("category", CategoryName);
        productMap.put("Price", price);
        productMap.put("Quantity", quantity);
        productMap.put("Currency", currency);
        productMap.put("Shipping", shipping);
        productMap.put("Name", pName);


        productsRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(AddProduct.this, Home_buyer.class);
                            startActivity(intent);

                            System.out.print(intent);

                            loadingBar.dismiss();
                            Toast.makeText(AddProduct.this, "Product is added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AddProduct.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}