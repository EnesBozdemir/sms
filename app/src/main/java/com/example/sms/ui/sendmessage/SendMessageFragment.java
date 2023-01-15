package com.example.sms.ui.sendmessage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sms.GroupModel;
import com.example.sms.MessageModel;
import com.example.sms.R;
import com.example.sms.ui.creategroup.GroupAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class SendMessageFragment extends Fragment {

    RecyclerView groupRecyclerView, messageRecyclerView;
    TextView selectedGroupTextView, selectedMessageTextView;
    Button sendButton;

    FirebaseAuth mAuth;
    FirebaseFirestore mStore;

    ArrayList<GroupModel> groupModelArrayList;
    ArrayList<MessageModel> messageModelArrayList;

    GroupModel selectedGroupModel;
    MessageModel selectedMessageModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_message, container, false);

        groupRecyclerView = view.findViewById(R.id.sendmessage_groupRecyclerView);
        messageRecyclerView = view.findViewById(R.id.sendmessage_messagesRecyclerView);

        selectedGroupTextView = view.findViewById(R.id.sendmessage_selectedGroupTextView);
        selectedMessageTextView= view.findViewById(R.id.sendmessage_selectedMessageTextView);

        sendButton = view.findViewById(R.id.sendmessage_sendButton);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        groupModelArrayList=new ArrayList<>();
        messageModelArrayList= new ArrayList<>();

        return view;
    }

    private void FetchGroups(){
        String uid=mAuth.getCurrentUser().getUid();

        mStore.collection("/userdata/"+uid+"/groups").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                groupModelArrayList.clear();
                for(DocumentSnapshot document : task.getResult()){
                    GroupModel groupModel=new GroupModel(document.getString("name"), document.getString("description"), document.getString("image"), (List<String>)document.get("numbers"),document.getId());
                    groupModelArrayList.add(groupModel);
                }
            }
        });
    }

    private  void FetchMessages(){
        String uid=mAuth.getCurrentUser().getUid();

        mStore.collection("/userdata/"+uid+"/groups").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                groupModelArrayList.clear();
                for(DocumentSnapshot document : task.getResult()){
                    GroupModel groupModel=new GroupModel(document.getString("name"), document.getString("description"), document.getString("image"), (List<String>)document.get("numbers"),document.getId());
                    groupModelArrayList.add(groupModel);
                }
            }
        });
    }
}