package game.GameHistory;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import Models.Gameplay.ActiveGame;
import Models.Gameplay.GameHistory;
import activities.R;

public class GameHistoryFragment extends Fragment {
    public GameHistoryPresenter historyPresenter;
    public GameHistoryAdapter historyAdapter;
    RecyclerView historyRecView;
    ImageButton leftToChat;
    ImageButton rightToGame;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        historyPresenter = new GameHistoryPresenter(getActivity());
        view = inflater.inflate(R.layout.game_history, container, false);// setting view
        leftToChat = (ImageButton) view.findViewById(R.id.historyToChat);
        rightToGame = (ImageButton) view.findViewById(R.id.historyToGame);
        TextView title = (TextView) view.findViewById(R.id.history_title);
        AssetManager am = getContext().getApplicationContext().getAssets();
        Typeface custom_font = Typeface.createFromAsset(am, "game_of_thrones.ttf");
        title.setTypeface(custom_font);

        final GameHistory history = ActiveGame.getInstance().getHistory();
        historyRecView = (RecyclerView) view.findViewById(R.id.history_list);
        historyRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        historyAdapter = new GameHistoryAdapter(history);
        historyRecView.setAdapter(historyAdapter);

        leftToChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyPresenter.switchToChat(getActivity());
            }
        });

        rightToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyPresenter.switchToGame(getActivity());
            }
        });
        update();
        return view;
    }

    public void update() {
        GameHistory updateHistory = ActiveGame.getInstance().getHistory();
        if(updateHistory != null && (updateHistory.size() > 0)) {
            historyAdapter.clearGameHistory();
            for(int i = 0; i < updateHistory.size(); i++)
            {
                historyAdapter.addActionToView(updateHistory.get(i));
            }
            historyAdapter.notifyDataSetChanged();
        }
    }

    public class GameHistoryHolder extends RecyclerView.ViewHolder {

        private TextView mHistoryItem;
        private String mAction;

        public GameHistoryHolder(View itemView) {
            super(itemView);
            mHistoryItem = itemView.findViewById(R.id.history_item);
        }

        public void bindAction(String action) {
            mAction = action;
            mHistoryItem.setText(mAction);
        }
    }

    public class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryFragment.GameHistoryHolder> {
        private GameHistory history;

        public GameHistoryAdapter(GameHistory gh) {
            //what is this doing?
            if (history == null)
            {
                history = new GameHistory();
                history.add("");
            }
            else
            { history = gh; }
        }

        @Override
        public GameHistoryFragment.GameHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.list_item_history, parent, false);
            return new GameHistoryFragment.GameHistoryHolder(view);
        }

        public void addActionToView(String action)
        {
            history.add(action);
        }

        public void clearGameHistory()
        {
            history.clearHistory();
        }

        @Override
        public void onBindViewHolder(GameHistoryFragment.GameHistoryHolder holder, int position) {

            String action = history.get(position);
            holder.bindAction(action);
        }

        @Override
        public int getItemCount() {
            return history.size();
        }
    }

}
