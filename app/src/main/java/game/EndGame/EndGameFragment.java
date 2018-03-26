package game.EndGame;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Models.Gameplay.ActiveGame;
import Models.Gameplay.Player;
import activities.R;

/**
 * Created by brianotte on 3/24/18.
 */


//This class looks like it is built correctly, but I have no idea how to test it...
public class EndGameFragment extends android.support.v4.app.Fragment {
    //holds the view elements:
    public EndGamePresenter endGamePresenter;
    public EndGameAdapter endGameAdapter;
    RecyclerView endGameRecyclerView;
    View view;

    //onCreate method:
    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){
        endGamePresenter = new EndGamePresenter(getActivity());
        view = inflater.inflate(R.layout.end_game_fragment, container, false);
        //initiate any further buttons here:
        TextView title = (TextView) view.findViewById(R.id.end_game_title);
        AssetManager am = getContext().getApplicationContext().getAssets();
        //change the font of the title to game of thrones...
        Typeface custom_font = Typeface.createFromAsset(am, "game_of_thrones.ttf");
        title.setTypeface(custom_font);

        //instantiate the recycler view here:
        endGameRecyclerView = (RecyclerView) view.findViewById(R.id.endGameRecycler);
        endGameRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //passes in the players from the ActiveGame - the player models should all have the information needed to display:
        endGameAdapter = new EndGameAdapter(ActiveGame.getInstance().getPlayers());
        endGameRecyclerView.setAdapter(endGameAdapter);

        fillPlayers();
        //This is where the onClick listeners should be for button navigation:

        //shouldn't be any information updated at this point - this fragment only launches when the server tells the clients to.
        return view;
    }


    public void fillPlayers() {
        ArrayList<Player> updatePlayers = new ArrayList<>();
        updatePlayers.addAll(ActiveGame.getInstance().getPlayers());
        if(updatePlayers.size() > 0)
        {
            endGameAdapter.clearPlayerList();
            for(Player p : updatePlayers)
            {
                endGameAdapter.addPlayerToView(p);
            }
            endGameAdapter.notifyDataSetChanged();
        }
    }


    //create the RecyclerView classes here:

    //create the holder for the endGame list items:
    public class EndGameHolder extends RecyclerView.ViewHolder{
        //Properties of the list item
        private TextView rankItem;
//        private String rankItemValue;
        private TextView nameItem;
//        private String nameItemValue;
        private TextView routePtsItem;
//        private String routePtsValue;
        private TextView longestRoadPtsItem;
//        private String longestRoadValue;
        private TextView destCardsPtsItem;
//        private String destCardsPtsValue;
        private TextView totalPtsItem;
//        private String totalPtsValue;
        private Player endGamePlayer;

        public EndGameHolder(View itemView) {
            super(itemView);
            //set the TextViews to their XML elements:
            rankItem = itemView.findViewById(R.id.playerRank);
            nameItem = itemView.findViewById(R.id.playerName);
            routePtsItem = itemView.findViewById(R.id.playerRoutePts);
            longestRoadPtsItem = itemView.findViewById(R.id.playerLongestRoad);
            destCardsPtsItem = itemView.findViewById(R.id.playerDestCardsPts);
            totalPtsItem = itemView.findViewById(R.id.playerTotalPts);
        }
        //create the bindAction method - binds the values into the TextViews:
        public void bindPlayer(Player player){
            //bind the xml views to the data from the player
            rankItem.setText(String.valueOf(player.getPlayerRank()));
            nameItem.setText(player.getName());
            //check for user's player and adjust font accordingly:
            if(ActiveGame.getInstance().getMyPlayer().getName().equals(player.getName())){
                nameItem.setTypeface(null, Typeface.BOLD_ITALIC);
            }else{
                nameItem.setTypeface(null, Typeface.NORMAL);
            }
            //assign all of the points from the player argument:
            routePtsItem.setText(String.valueOf(player.getScore().getRoutePoints()));
            longestRoadPtsItem.setText(String.valueOf(player.getScore().getLongestRoad()));
            destCardsPtsItem.setText(String.valueOf(player.getScore().getPosDestPoints() +"/"+ player.getScore().getNegDestPoints()));
            totalPtsItem.setText(String.valueOf(player.getScore().getTotal()));
        }

    }

    //create the adapter for the endGame:
    public class EndGameAdapter extends RecyclerView.Adapter<EndGameFragment.EndGameHolder>{
        private ArrayList<Player> players;

        private EndGameAdapter(ArrayList<Player> player_list){
            if(players == null){
                players = new ArrayList<>();
                Player p = new Player();
                players.add(p);
            }else{
                players.addAll(player_list);
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public EndGameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.list_item_endgame, parent, false);
            return new EndGameHolder(view);
        }

        private void addPlayerToView(Player plyr){
            players.add(plyr);
        }

        private void clearPlayerList(){
            players.clear();
        }

        @Override
        public void onBindViewHolder(EndGameHolder holder, int position) {
            //bind the player in the array to the adapter;
            holder.bindPlayer(players.get(position));
        }

        @Override
        public int getItemCount() {
            return players.size();
        }
    }
}
