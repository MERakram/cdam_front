package com.example.tp2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    Activity activity;
    int itemResourceId;
    List<Product> items;
    private LinearLayout card;
    private ImageView card_img;
    private TextView card_name;
    private TextView card_category;
    private Context mContext;

    public CustomExpandableListAdapter(Context mContext, Activity activity, int itemResourceId, List<Product> items) {
        this.mContext = mContext;
        this.activity = activity;
        this.itemResourceId = itemResourceId;
        this.items = items;
    }

    @NonNull
    @SuppressLint("SetTextI18n")
//    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View layout;
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            layout = inflater.inflate(itemResourceId, parent, false);
        } else {
            layout = convertView;
        }

        Product currentProduct = items.get(position);
        ImageButton productImage = layout.findViewById(R.id.img);
        TextView productName = layout.findViewById(R.id.name);
        TextView productCategory = layout.findViewById(R.id.category);
        TextView productPrice = layout.findViewById(R.id.price);
        TextView productCount = layout.findViewById(R.id.quantity);
        TextView productTotal = layout.findViewById(R.id.productTotal);
        ImageButton productCart = layout.findViewById(R.id.cart);

        productName.setText(currentProduct.getName());
        productCategory.setText(currentProduct.getCategory());
        productPrice.setText(String.valueOf(currentProduct.getCount()) + " x " + currentProduct.getPrice() + " DZD");
        productCount.setText(String.valueOf(currentProduct.getCount()));
        productTotal.setText(String.valueOf(currentProduct.getTotal()) + " DZD");
        productImage.setImageResource(currentProduct.getImage());

        ImageButton add = layout.findViewById(R.id.add);
        ImageButton minus = layout.findViewById(R.id.minus);

        if (currentProduct.selected()) {
            productCart.setImageResource(R.drawable.cart3);

        } else {
            productCart.setImageResource(R.drawable.cart2);
        }

        productCart.setOnClickListener(v -> {
            currentProduct.toggleSelect();

            if (currentProduct.selected()) {
                productCart.setImageResource(R.drawable.cart3);
                MainActivity.incrementCounter();
            } else {
                productCart.setImageResource(R.drawable.cart2);
                MainActivity.decrementCounter();
            }
            getAllTotal();
            notifyDataSetChanged();
        });

        add.setOnClickListener(view -> {
            currentProduct.incrementCount();
            currentProduct.calculateTotal();
            getAllTotal();
            notifyDataSetChanged();
        });

        minus.setOnClickListener(view -> {
            currentProduct.decrementCount();
            currentProduct.calculateTotal();
            getAllTotal();
            notifyDataSetChanged();
        });

        productImage.setOnClickListener(v -> {
            card = layout.findViewById(R.id.card);
            card_img = layout.findViewById(R.id.card_img);
            card_name = layout.findViewById(R.id.card_name);
            card_category = layout.findViewById(R.id.card_category);

            currentProduct.toggleOpened();

            if (currentProduct.opened()) {
                card.setVisibility(View.GONE);
            } else {
                card_img.setImageResource(currentProduct.getImage());
                card_name.setText(currentProduct.getName());
                card_category.setText(currentProduct.getCategory());
                card.setVisibility(View.VISIBLE);
            }
            notifyDataSetChanged();
        });

        return layout;
    }

    @Override
    public int getGroupCount() {
        return this.items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.items.size();
//        return this.items.get(this.expandableListTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.items.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.items.get(groupPosition);
//        return this.items.get(this.items.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        String listTitle = (String) getGroup(groupPosition);
        View layout;
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(itemResourceId, parent, false);
//            LayoutInflater inflater = (LayoutInflater)
//            convertView = inflater.inflate(R.layout.item_product);
//            convertView = inflater.inflate(R.layout.list_group,null);
        }

//        TextView listTitleTextView = convertView.findViewById(R.id.listTitle);
//        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public View getChildView(int parentPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Product expandedListTile = (Product) getChild(parentPosition, childPosition);
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater)
                    this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_tile, null);

        }
//        card = convertView.findViewById(R.id.card);
        card_img = convertView.findViewById(R.id.card_img);
        card_name = convertView.findViewById(R.id.card_name);
        card_category = convertView.findViewById(R.id.card_category);
//        TextView expandedListTextView = convertView.findViewById(R.id.expandedListItem);
//        expandedListTextView.setText(expandedListTile);
        card_img.setImageResource(expandedListTile.getImage());
        card_name.setText(expandedListTile.getName());
        card_category.setText(expandedListTile.getCategory());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void getAllTotal() {
        double total = 0;

        for (Product item : this.items) {
            if (item.selected())
                total += item.getCount() * item.getPrice();
        }

        MainActivity.price_t.setText(String.valueOf(total) + " DZD");

    }
}
