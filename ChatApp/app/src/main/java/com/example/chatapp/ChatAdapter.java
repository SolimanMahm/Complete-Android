package com.example.chatapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.databinding.BubbleChatBinding;
import com.example.chatapp.models.Message;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private ArrayList<Message> data = new ArrayList<>();
    private String email;

    public ChatAdapter(ArrayList<Message> data, String email) {
        this.data = data;
        this.email = email;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bubble_chat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        private BubbleChatBinding binding;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = BubbleChatBinding.bind(itemView);
        }

        public void bind(Message message) {
            if (message.getId().equals(email)) {
                binding.linearLayoutLeft.setVisibility(View.VISIBLE);
                binding.textViewLeft.setText(message.getMessage());
                binding.linearLayoutRight.setVisibility(View.GONE);
            } else {
                binding.linearLayoutRight.setVisibility(View.VISIBLE);
                binding.textViewRight.setText(message.getMessage());
                binding.linearLayoutLeft.setVisibility(View.GONE);
            }
        }
    }
}
