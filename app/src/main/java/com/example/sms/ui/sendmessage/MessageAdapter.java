package com.example.sms.ui.sendmessage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.R;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nameTextView, descriptionTextView;
        public MessageViewHolder(View itemView) {
            super(itemView);

            nameTextView=itemView.findViewById(R.id.item_semdmeesage_message_nameTextView);
            descriptionTextView=itemView.findViewById(R.id.item_semdmeesage_message_descriptionTextView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
