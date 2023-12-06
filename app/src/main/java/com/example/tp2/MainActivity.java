package com.example.tp2;
import androidx.appcompat.app.AlertDialog;
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


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;
import android.content.DialogInterface;
public class MainActivity extends AppCompatActivity {
    public static TextView price_t;
    public static TextView counter;
    private boolean date;
    private TextView date_t;
    private LinearLayout date_pl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        price_t = findViewById(R.id.price_t);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Commande de :");
            getSupportActionBar().setSubtitle("Grossiste kaf naadja");
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            toolbar.setSubtitleTextColor(getResources().getColor(R.color.white));
        }


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

        ListView listView = findViewById(R.id.listView);
        productAdapter adapter = new productAdapter(this, MainActivity.this, R.layout.item_product, items);
        listView.setAdapter(adapter);

//        ExpandableListView expandableListView = findViewById(R.id.expandableListView);
//        CustomExpandableListAdapter expandableListAdapter = new CustomExpandableListAdapter(this, MainActivity.this, R.layout.item_product, items);
//        expandableListView.setAdapter(expandableListAdapter);

        FloatingActionButton floatingActionBtn = findViewById(R.id.floating);
        floatingActionBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Do you want to proceed?");
            builder.setTitle("Confirmation !");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                for(Product item : items){
                    item.setCount(0);
                    item.setSelected(false);
                    item.calculateTotal();
                    adapter.notifyDataSetChanged();
                }
                    adapter.getAllTotal();
                    setCounter(0);
                dialog.cancel();
            });
            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        counter = findViewById(R.id.counter);
        setCounterVisibility();

        DatePicker datePicker = findViewById(R.id.datePicker);
        ImageButton calander = findViewById(R.id.calender);
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
                (view, year, monthOfYear, dayOfMonth) -> {
                    String selectedDate = String.format(Locale.getDefault(), "%02d %s %d", dayOfMonth, getMonthName(monthOfYear), year);
                    date_t.setText(selectedDate);
                }
        );

    }

    private String getMonthName(int month) {
        String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return monthNames[month];
    }


    public static void setCounterVisibility() {
        if (Integer.parseInt(counter.getText().toString()) == 0) {
            counter.setVisibility(View.GONE);
        } else {
            counter.setVisibility(View.VISIBLE);
        }
    }

    public static void incrementCounter() {
        int currentCount = Integer.parseInt(String.valueOf(MainActivity.counter.getText()));
        counter.setText(String.valueOf(currentCount + 1));
        setCounterVisibility();
    }

    public static void decrementCounter() {
        int currentCount = Integer.parseInt(String.valueOf(MainActivity.counter.getText()));
        counter.setText(String.valueOf(currentCount - 1));
        setCounterVisibility();
    }
    public void setCounter(int count){
        counter.setText(String.valueOf(count));
        setCounterVisibility();
    }
}