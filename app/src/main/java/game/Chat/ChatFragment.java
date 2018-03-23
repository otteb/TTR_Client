package game.Chat;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Models.Gameplay.ActiveGame;
import Models.Gameplay.Chat;
import activities.R;

public class ChatFragment extends Fragment {
    EditText message;
    Button chat;
    ImageButton returnToStats;
    ImageButton goToGameHistory;
    RecyclerView chatRecView;
    public ChatPresenter chatPresenter;
    public ChatAdapter chatAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        chatPresenter = new ChatPresenter(getActivity());
        View view = inflater.inflate(R.layout.chat, container, false);
        chat = (Button) view.findViewById(R.id.addChat);
        message = (EditText)view.findViewById(R.id.chatMessage);
        TextView title = (TextView) view.findViewById(R.id.chat_title);
        AssetManager am = getContext().getApplicationContext().getAssets();
        Typeface custom_font = Typeface.createFromAsset(am, "game_of_thrones.ttf");
        title.setTypeface(custom_font);

        chatRecView = (RecyclerView) view.findViewById(R.id.chat_list);
        chatRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final  ArrayList<Chat> chats = ActiveGame.getInstance().getChats();
        chatAdapter = new ChatAdapter(chats);
        chatRecView.setAdapter(chatAdapter);

        returnToStats = (ImageButton)view.findViewById(R.id.chatToStats);
        returnToStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatPresenter.switchToStats(getActivity());
            }
        });
        goToGameHistory = (ImageButton) view.findViewById(R.id.chatToGameHistory);
        goToGameHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatPresenter.switchToGameHistory(getActivity());
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatPresenter.addChat(getActivity(), message.getText().toString());
                message.setText("");
            }
        });

        update();
        return view;
    }

    public void update() {
        ArrayList<Chat> updateChats = ActiveGame.getInstance().getChats();
        if(updateChats != null && (updateChats.size() > 0))
        {
            chatAdapter.clearChats();
            for(Chat c : updateChats)
            {
                chatAdapter.addChatToView(c);
            }
            chatAdapter.notifyDataSetChanged();
        }
    }

//    public void displayTurn() {
//        Toast.makeText(getActivity(), "It's " + ActiveGame.getInstance().getActivePlayer() + "'s turn", Toast.LENGTH_SHORT).show();
//    }

    public class ChatHolder extends RecyclerView.ViewHolder {

        private TextView mChatMessage;
        private Chat mChat;

        public ChatHolder(View itemView) {
            super(itemView);
            mChatMessage = itemView.findViewById(R.id.chat_item);
        }

        public void bindChat(Chat chat) {
            mChat = chat;
            mChatMessage.setText(mChat.displayChat());
        }
    }

    public class ChatAdapter extends RecyclerView.Adapter<ChatFragment.ChatHolder> {
        private ArrayList<Chat> chats;

        public ChatAdapter(ArrayList<Chat> chatList) {
            //what is this doing?
            if (chats == null)
            {
                chats = new ArrayList<>();
                Chat c = new Chat();
                chats.add(c);
            }
            else
            { chats = chatList; }
        }

        @Override
        public ChatFragment.ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.list_item_chat, parent, false);
            return new ChatFragment.ChatHolder(view);
        }

        public void addChatToView(Chat chat)
        {
            chats.add(chat);
        }

        public void clearChats()
        {
            chats.clear();
        }

        @Override
        public void onBindViewHolder(ChatFragment.ChatHolder holder, int position) {
            if(chats != null)
            {
                Chat message = chats.get(position);
                holder.bindChat(message);
            }
        }

        @Override
        public int getItemCount() {
            return chats.size();
        }
    }
}
