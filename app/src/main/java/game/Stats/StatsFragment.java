package game.Stats;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import Models.Gameplay.ActiveGame;
import Models.Gameplay.Player;
import activities.R;

//TODO: check/implement observable

public class StatsFragment extends Fragment {
    Button returnToGame;
    Button goToChat;
    RecyclerView statsRecView;
    public StatsPresenter statsPresenter;
    public StatsAdapter statsAdapter;

    public StatsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: set the color number cards
        statsPresenter = new StatsPresenter(getContext());
        View view = inflater.inflate(R.layout.stats_rv, container, false);
        returnToGame= (Button)view.findViewById(R.id.statsToGame);
        returnToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsPresenter.returnToGame(getActivity());
            }
        });

        goToChat= (Button)view.findViewById(R.id.statsToChat);
        goToChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsPresenter.viewChat(getActivity());
            }
        });

        ArrayList<Player> players = new ArrayList<>();
        players.addAll(ActiveGame.getInstance().getPlayers());
        statsRecView = (RecyclerView) view.findViewById(R.id.stats_list);
        statsRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        statsAdapter = new StatsAdapter(players);
        statsRecView.setAdapter(statsAdapter);

        update();
        return view;
    }

    public void update() {
        ArrayList<Player> updatePlayers = new ArrayList<>();
        updatePlayers.addAll(ActiveGame.getInstance().getPlayers());
        if(updatePlayers.size() > 0)
        {
            statsAdapter.clearPlayerList();
            for(Player p : updatePlayers)
            {
                statsAdapter.addPlayerToView(p);
            }
            statsAdapter.notifyDataSetChanged();
        }
    }

    public class StatsHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mPoints;
        private TextView mTrains;
        private TextView mCards;
        private TextView mRoutes;
        private Player mPlayer;

//        private StatsHolder(LayoutInflater inflater, ViewGroup parent) {
//            super(inflater.inflate(R.layout.list_item_stats, parent, false));
//        }

        private StatsHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.pName);
            mPoints = itemView.findViewById(R.id.pPoints);
            mTrains = itemView.findViewById(R.id.pTrains);
            mCards = itemView.findViewById(R.id.pCards);
            mRoutes = itemView.findViewById(R.id.pRoutes);
        }

        private void bindPlayer(Player player) {
            mPlayer = player;
            int handSize = player.getHand().size();
            int destSize = player.getDestination_cards().size();
            int numRoutes = player.getClaimedRoutes().size();
            mName.setText(mPlayer.getName());
            mPoints.setText(String.valueOf(mPlayer.getPoints()));
            mTrains.setText(String.valueOf(mPlayer.getNumTrains()));
            mCards.setText(String.valueOf(handSize) + "/" + String.valueOf(destSize));
            mRoutes.setText(String.valueOf(numRoutes));
        }
    }

    public class StatsAdapter extends RecyclerView.Adapter<StatsFragment.StatsHolder> {
        private ArrayList<Player> players;

        public StatsAdapter(ArrayList<Player> player_list) {
            if (players == null)
            {
                players = new ArrayList<>();
                Player p = new Player();
                players.add(p);
            }
            else
            { players.addAll(player_list); }
        }

        @Override
        public StatsFragment.StatsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.list_item_stats, parent, false);
            return new StatsFragment.StatsHolder(view);
        }

        public void addPlayerToView(Player plyr)
        {
            players.add(plyr);
        }

        public void clearPlayerList()
        {
            players.clear();
        }

        @Override
        public void onBindViewHolder(StatsFragment.StatsHolder holder, int position) {

            Player plyr = players.get(position);
            holder.bindPlayer(plyr);
        }

        @Override
        public int getItemCount() {
            return players.size();
        }
    }
}