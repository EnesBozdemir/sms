package com.example.sms.ui.createmessage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sms.GroupModel;
import com.example.sms.MessageModel;
import com.example.sms.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    List<MessageModel> messageModelList;

    public MessageAdapter(List<MessageModel> messageModelList) {
        this.messageModelList=messageModelList;
    }

    @NonNull
    @Override
    public MessageAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_createmessage_message,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageViewHolder holder, int position) {
        MessageModel messageModel = messageModelList.get(position);
        holder.setData(messageModel);
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }


    public class MessageViewHolder extends RecyclerView.ViewHolder{
        TextView messageNameTextView, messageDescriptionTextView;
        public MessageViewHolder(View itemView){
            super(itemView);

            messageNameTextView=itemView.findViewById(R.id.item_createmessage_message_nameTextView);
            messageDescriptionTextView=itemView.findViewById(R.id.item_createmessage_message_descriptionTextView);
        }

        public void setData(MessageModel messageModel){
            messageNameTextView.setText(messageModel.getName());
            messageDescriptionTextView.setText(messageModel.getDescription());

        }
    }
}
