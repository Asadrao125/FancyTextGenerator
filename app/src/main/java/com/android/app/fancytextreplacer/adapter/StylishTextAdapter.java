package com.android.app.fancytextreplacer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app.fancytextreplacer.R;
import com.android.app.fancytextreplacer.utils.CopyHandler;

import java.util.ArrayList;

public class StylishTextAdapter extends RecyclerView.Adapter<StylishTextAdapter.MyViewHolder> {
    ArrayList<String> dataList = new ArrayList<>();
    Context activity;

    public StylishTextAdapter(Context activity) {
        this.activity = activity;

    }

    public void setData(ArrayList<String> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_stylish_adapter, parent, false);
        return new StylishTextAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.description.setText(dataList.get(position));
        holder.description.setSelected(true);

        // Count List Number
        holder.number.setText(String.valueOf(position + 1));
        CopyHandler copyHandler = new CopyHandler(activity);
        String text = holder.description.getText().toString();

        // Copy Button
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHandler.copyText(text);
            }
        });

        // Share Button
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHandler.shareText(text);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView description, number;
        ImageView copy, share;
        LinearLayout layout;

        private MyViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.descriptionTV);
            number = itemView.findViewById(R.id.txt_number);
            copy = itemView.findViewById(R.id.btn_copy);
            share = itemView.findViewById(R.id.btn_share);
            layout = itemView.findViewById(R.id.linearclick);
        }
    }
}
