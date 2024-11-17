package com.example.chatapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.databinding.ActivityChatBinding;
import com.example.chatapp.models.Message;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private ArrayList<Message> data = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Map<String, Object> message = new HashMap<>();
    private final String collectionName = "messages";
    private final String fieldMessage = "message";
    private final String fieldCreatedAt = "createdAt";
    private final String fieldId = "id";
    private ChatAdapter adapter;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("email");

        binding.RecyclerView.setHasFixedSize(true);
        binding.RecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, true));

        readMessages();

        adapter = new ChatAdapter(data, email);
        binding.RecyclerView.setAdapter(adapter);

        binding.TextInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.edtMessage.getText().toString().isEmpty()) saveMessage();
                binding.edtMessage.setText("");
            }
        });
    }

    private void saveMessage() {
        message.put(fieldId, email);
        message.put(fieldMessage, binding.edtMessage.getText().toString());
        message.put(fieldCreatedAt, new Date());
        db.collection(collectionName)
                .add(message)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });
    }

    private void readMessages() {
        db.collection(collectionName)
                .orderBy(fieldCreatedAt)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(ChatActivity.this, "Failed to load messages", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        data.clear();
                        for (QueryDocumentSnapshot document : value) {
                            data.add(0, new Message(document.getString(fieldId), document.getString(fieldMessage)));
                        }
                        adapter.notifyDataSetChanged();
                        binding.RecyclerView.smoothScrollToPosition(0);
                    }
                });
    }
}