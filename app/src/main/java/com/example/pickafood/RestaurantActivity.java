package com.example.pickafood;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class RestaurantActivity extends AppCompatActivity {

    private ArrayList<Restaurant> list = new ArrayList<>();
    private FirebaseFirestore db;
    private TextView restaurantName;
    private TextView address;
    private TextView description;
    private ImageView photo;
    private Random random=new Random();
    private  StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        this.restaurantName = findViewById(R.id.textView4);
        address=findViewById(R.id.address);
        description=findViewById(R.id.description);
        photo=findViewById(R.id.imageView2);
        chooseRestaurant();
    }

    private void chooseRestaurant(){

        db = FirebaseFirestore.getInstance();
        db.collection("restaurants")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Restaurant restaurant=new Restaurant(document.get("name").toString(),document.get("address").toString(),document.get("description").toString(),document.get("photo").toString());
                                list.add(restaurant);
                            }
                            setView();

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
    private void setView(){
        Restaurant restaurant=list.get(random.nextInt(list.size()));
        Picasso.get().load(restaurant.getPhotoLink()).into(photo);
        restaurantName.setText(restaurant.getName());
        address.setText(restaurant.getAddress());
        description.setText(restaurant.getDescription());
    }
}