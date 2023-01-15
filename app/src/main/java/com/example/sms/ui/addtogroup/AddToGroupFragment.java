package com.example.sms.ui.addtogroup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sms.GroupModel;
import com.example.sms.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;


public class AddToGroupFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseFirestore mStore;

    RecyclerView groupRecyclerView, contactRecyclerView;
    TextView selectedGroupTextView;

    GroupModel selectedGroup;
    ArrayList<GroupModel> groupModelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_to_group, container, false);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        groupRecyclerView = view.findViewById(R.id.addtogroup_groupRecyclerView);
        contactRecyclerView = view.findViewById(R.id.addtogroup_contactRecyclerView);
        selectedGroupTextView = view.findViewById(R.id.addtogroup_selectedGroupTextView);

        groupModelList = new ArrayList<>();

        FetchGroups();
        return view;
    }

    private void FetchGroups(){
        String uid = mAuth.getCurrentUser().getUid();

        mStore.collection("/userdata/" + uid + "/groups").get().addOnSuccessListener(queryDocumentSnapshots -> {
            groupModelList.clear();
           for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
               GroupModel groupModel = new GroupModel(documentSnapshot.getString("name"), documentSnapshot.getString("description"), documentSnapshot.getString("image"), (List<String>)documentSnapshot.get("numbers"),documentSnapshot.getId());
               groupModelList.add(groupModel);
           }


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
            groupRecyclerView.setLayoutManager(linearLayoutManager);
        });
    }
}