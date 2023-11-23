package com.example.cdam_front;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cdam_front.models.Product;
import com.example.cdam_front.adapters.CartItemsAdapter;
import com.google.android.material.badge.ExperimentalBadgeUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private ListView listView;
    private TextView totalPriceView;
    private double total = 0;

    @ExperimentalBadgeUtils
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView = findViewById(R.id.cartListView);
        List<Product> cartItems = new ArrayList<>();
        cartItems.add(new Product("almond", "desc", 100,R.drawable.almond));
        cartItems.add(new Product("grape", "desc", 115,R.drawable.grape));
        cartItems.add(new Product("grape", "desc", 115,R.drawable.grape));
        cartItems.add(new Product("grape", "desc", 115,R.drawable.grape));
        cartItems.add(new Product("grape", "desc", 115,R.drawable.grape));
        cartItems.add(new Product("orange", "desc", 230,R.drawable.orange));
        cartItems.add(new Product("pineapple", "desc", 20,R.drawable.pineapple));
        cartItems.add(new Product("pineapple", "desc", 20,R.drawable.pineapple));
        cartItems.add(new Product("pineapple", "desc", 20,R.drawable.pineapple));
        cartItems.add(new Product("banana", "desc", 70,R.drawable.banana));
        cartItems.add(new Product("banana", "desc", 70,R.drawable.banana));
        cartItems.add(new Product("banana", "desc", 70,R.drawable.banana));
        cartItems.add(new Product("banana", "desc", 70,R.drawable.banana));
        cartItems.add(new Product("banana", "desc", 70,R.drawable.banana));
        cartItems.add(new Product("almond", "desc", 180,R.drawable.almond));
        cartItems.add(new Product("almond", "desc", 180,R.drawable.almond));
        cartItems.add(new Product("almond", "desc", 180,R.drawable.almond));
        cartItems.add(new Product("almond", "desc", 180,R.drawable.almond));
        cartItems.add(new Product("almond", "desc", 180,R.drawable.almond));
        cartItems.add(new Product("orange", "desc", 180,R.drawable.orange));
        cartItems.add(new Product("orange", "desc", 180,R.drawable.orange));
        cartItems.add(new Product("orange", "desc", 180,R.drawable.orange));
        cartItems.add(new Product("orange", "desc", 180,R.drawable.orange));
        cartItems.add(new Product("banana", "desc", 40,R.drawable.banana));

        CartItemsAdapter cartItemsAdapter = new CartItemsAdapter(this, cartItems);
        listView.setAdapter(cartItemsAdapter);

        FloatingActionButton fabButton = findViewById(R.id.fabButton);

        totalPriceView = findViewById(R.id.totalPrice);
        totalPriceView.setText(getString(R.string.total, String.format(Locale.getDefault(), "%.2f", total)));
        
        fabButton.setOnClickListener(v -> {
            total=0;

            for (Product product : cartItems) {
                total += product.getCount() * product.getPrice();
            }

            String formattedTotal = getString(R.string.total, String.format(Locale.getDefault(), "%.2f", total));
            totalPriceView.setText(formattedTotal);
        });
    }

}

