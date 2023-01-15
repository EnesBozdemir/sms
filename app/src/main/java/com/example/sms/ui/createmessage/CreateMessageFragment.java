package com.example.sms.ui.createmessage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sms.LoginActivity;
import com.example.sms.MessageModel;
import com.example.sms.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PlayGamesAuthCredential;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;


public class CreateMessageFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseFirestore mStore;

    EditText messageNameEditText, messageDescriptionEditText;
    Button createMessageButton;
    RecyclerView messageRecyclerView;

    ArrayList<MessageModel> messageModelArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_message, container, false);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        messageModelArrayList=new ArrayList<>();

        messageNameEditText=view.findViewById(R.id.cratemessage_messageNameEditText);
        messageDescriptionEditText=view.findViewById(R.id.createmessage_messageDescriptionEditText);
        createMessageButton=view.findViewById(R.id.createmessage_createMessageButton);
        messageRecyclerView=view.findViewById(R.id.createmessage_messagesRecyclerView);

        createMessageButton.setOnClickListener(v -> {
            String messageName = messageNameEditText.getText().toString();
            String messageDescription = messageDescriptionEditText.getText().toString();

            if(messageName.isEmpty() || messageDescription.isEmpty()){
                Toast.makeText(getContext(), "Lütfen tüm alanlaro doldurunuz", Toast.LENGTH_SHORT).show();
                return;
            }
            CreateMessage(messageName, messageDescription);
        });

        FetchMessages();
        return view;
    }

    private void CreateMessage(String messageName, String messageDescription){
        String userId = mAuth.getCurrentUser().getUid();

        mStore.collection("/userdata/" + userId + "/messages").add(new HashMap<String,String>(){{
            put("messageName", messageName);
            put("messageDescription", messageDescription);
        }}).addOnSuccessListener(documentReference -> {
            Toast.makeText(getContext(), "mesaj başarıyla oluşturuldu", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(documentReference -> {
            Toast.makeText(getContext(), "Mesaj oluşturulken bir hata oluştu", Toast.LENGTH_SHORT).show();
        });
    }

    private void FetchMessages() {
        String userId =mAuth.getCurrentUser().getUid();
        mStore.collection("/userdata/" + userId + "/messages").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    messageModelArrayList.clear();
                    for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){
                        MessageModel messageModel = new MessageModel(documentSnapshot.getString("name"), documentSnapshot.getString("description"), documentSnapshot.getString("uid"));
                        messageModelArrayList.add(messageModel);
                    }

                    messageRecyclerView.setAdapter(new MessageAdapter(messageModelArrayList));
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
                    messageRecyclerView.setLayoutManager(linearLayoutManager);

                }).addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "mesajlar alınırken bir hata oluştu", Toast.LENGTH_SHORT).show();
                });
    }
}