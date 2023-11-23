package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ListView item;
    private ImageButton calandar;
    private TextView txtcalandar;
    private Toolbar toolbar1;
    public static TextView price_t;
    public static TextView counter;
    private DatePicker datePicker;
    private ImageButton calander;
    private boolean date;
    private TextView date_t;
    private LinearLayout date_pl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);

        price_t = findViewById(R.id.price_t);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Commande de :");
            getSupportActionBar().setSubtitle("Grossiste kaf naadja");
            toolbar1.setTitleTextColor(getResources().getColor(R.color.white));
            toolbar1.setSubtitleTextColor(getResources().getColor(R.color.white));
        }

        item = findViewById(R.id.itemss);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ArrayList<Product> items = new ArrayList<>();
        items.add(new Product(R.drawable.img1, "Amandes", "F", 150.0));
        items.add(new Product(R.drawable.img1, "Amandes", "F", 150.0));
        items.add(new Product(R.drawable.img1, "Amandes", "F", 150.0));
        items.add(new Product(R.drawable.img3, "Noix de Cajou", "F", 150.0));
        items.add(new Product(R.drawable.img4, "Noix de Cajou", "F", 150.0));
        items.add(new Product(R.drawable.img1, "Amandes", "F", 150.0));
        items.add(new Product(R.drawable.img2, "Pistache", "F", 150.0));
        items.add(new Product(R.drawable.img2, "Pistache", "F", 150.0));
        items.add(new Product(R.drawable.img4, "Noi de Cajou", "F", 150.0));
        items.add(new Product(R.drawable.img4, "Noix d Cajou", "F", 150.0));
        items.add(new Product(R.drawable.img4, "Noix de Caou", "F", 150.0));
        items.add(new Product(R.drawable.img4, "Noix de Cajou", "F", 150.0));
        items.add(new Product(R.drawable.img2, "Pistache", "F", 150.0));
        items.add(new Product(R.drawable.img2, "Pistache", "F", 150.0));

        productAdapter adapter = new productAdapter(this,MainActivity.this, R.layout.item_product, items);
        item.setAdapter(adapter);

        counter = findViewById(R.id.counter);
        setCounterVisibility();

        datePicker = findViewById(R.id.datePicker);
        calander = findViewById(R.id.calander);
        date_t = findViewById(R.id.date_t);
        date_pl = findViewById(R.id.date_pl);

        date_pl.setOnClickListener(v -> {
            date_pl.setVisibility(View.GONE);
                }
        );

        date = true;
        calander.setOnClickListener(v -> {
            date = !date;
            if (date) {
                date_pl.setVisibility(View.GONE);
            } else {
                date_pl.setVisibility(View.VISIBLE);
            }
        });
        datePicker.init(
                datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String selectedDate = String.format(Locale.getDefault(), "%02d %s %d", dayOfMonth, getMonthName(monthOfYear), year);
                        date_t.setText(selectedDate);
                    }
                }
        );
    }

    private String getMonthName(int month) {
        String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return monthNames[month];
    }


    public static void setCounterVisibility(){
        if (Integer.parseInt(counter.getText().toString()) == 0) {
            counter.setVisibility(View.GONE);
        } else {
            counter.setVisibility(View.VISIBLE);
        }
    }   public static void incrementCounter(){
        int currentCount = Integer.parseInt(String.valueOf(MainActivity.counter.getText()));
        counter.setText(String.valueOf(currentCount + 1));
        setCounterVisibility();
    }   public static void decrementCounter(){
        int currentCount = Integer.parseInt(String.valueOf(MainActivity.counter.getText()));
        counter.setText(String.valueOf(currentCount - 1));
        setCounterVisibility();
    }
}
