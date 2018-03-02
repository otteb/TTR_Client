package lobby;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import activities.R;
import Models.Game;
import Models.Client;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by fjameson on 2/2/18.
 */

public class LobbyFragment extends Fragment {

    public GamesAdapter gamesAdapter;
    private RecyclerView mGamesRecView;
    public Game createGame = new Game();
    TextView curName;
    TextView p1;
    TextView p2;
    TextView p3;
    TextView p4;
    TextView p5;

    public  List<Game> gameList;
    TextView curGame;
    Button join;
    Button start;
    Button done;
    Button create;
    LinearLayout newGame;
    EditText gameName;
    View view;
    public boolean createUpdate = false;
    Game currentGame = null;



    ArrayList<TextView> players = new ArrayList<>(5);
    ArrayList<Game> games = new ArrayList<>();
    ArrayList<String> play;
    public LobbyFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lobby_fragment, container, false);
        final ArrayList<String>playernames = new ArrayList<String>();

        final Bundle acceptedUser = getArguments();
        playernames.add(acceptedUser.getString("username"));
        curName = (TextView) view.findViewById(R.id.yourName);
        curName.setText(acceptedUser.getString("username"));


        create= (Button)view.findViewById(R.id.create);
        p1 = (TextView) view.findViewById(R.id.p1);
        p2 = (TextView) view.findViewById(R.id.p2);
        p3 = (TextView) view.findViewById(R.id.p3);
        p4 = (TextView) view.findViewById(R.id.p4);
        p5 = (TextView) view.findViewById(R.id.p5);
        TextView gLabel = (TextView) view.findViewById(R.id.gameLabel);
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);
        curGame = (TextView) view.findViewById(R.id.curGame);
        final LobbyPresenter lobbyPresenter = new LobbyPresenter(getActivity());
        final  TextView numPlayers = (TextView) view.findViewById(R.id.numPlayers);
        numPlayers.setVisibility(View.GONE);
        join= (Button) view.findViewById(R.id.join);
        start= (Button) view.findViewById(R.id.start);
        done= (Button) view.findViewById(R.id.done);
        newGame = (LinearLayout) view.findViewById(R.id.newGame);
        gameName = (EditText) view.findViewById(R.id.gName);
        mGamesRecView = (RecyclerView) view.findViewById(R.id.game_list);
        mGamesRecView.setLayoutManager(new LinearLayoutManager(getActivity()));


        final ArrayList<Game> games = Client.getInstance().getGameList();

        gamesAdapter = new GamesAdapter(games);
        mGamesRecView.setAdapter(gamesAdapter);
        gameList = Client.getInstance().getGameList();
        for(int i=0; i<gameList.size(); i++)
            gamesAdapter.addGametoView(Client.getInstance().getGameList().get(i));
        gamesAdapter.notifyDataSetChanged();
        //starting to add people


        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentGame != null) {
                    currentGame = lobbyPresenter.joinGame(getActivity(), currentGame, acceptedUser.getString("username"));
                    if (currentGame.isJoinable()) {
                        start.setEnabled(true);
                    }
                } else {
                    Toast.makeText(getActivity(), "you can't join a game that doesn't exist", Toast.LENGTH_LONG).show();
                }

                if (currentGame != null) {
                    for (int i = 0; i < currentGame.getPlayers().size(); i++) {
                        players.get(i).setText(currentGame.getPlayers().get(i));
                    }
                } else Toast.makeText(getActivity(), "no game selected", Toast.LENGTH_SHORT).show();

            }

        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentGame != null) {
                  lobbyPresenter.startGame(getActivity(), currentGame);
                }
                else
                {
                    Toast.makeText(getActivity(), "you can't join a game that doesn't exist", Toast.LENGTH_LONG).show();
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(),"You're creating a game", Toast.LENGTH_SHORT).show();
                newGame.setVisibility(View.VISIBLE);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame.setVisibility(View.VISIBLE);
                if (gameName.getText() == null)
                {
                    Toast.makeText(getActivity(), "The game needs a name", Toast.LENGTH_SHORT).show();
                }
                else {
                    //question for Finn -
                    currentGame = lobbyPresenter.createGame(getActivity(), play, gameName.getText().toString(),  acceptedUser.getString("username"));

                    newGame.setVisibility(View.GONE);
                    gameName.setText(null);
                    gamesAdapter.addGametoView(currentGame);
                    gamesAdapter.notifyDataSetChanged();
                    curGame.setText(currentGame.getId());
                    for(int i=0; i<Client.getInstance().getGameList().size(); i++)
                        gamesAdapter.addGametoView(Client.getInstance().getGameList().get(i));
                    gamesAdapter.notifyDataSetChanged();

                    for (int i = 0; i < currentGame.getPlayers().size(); i++) {

                        players.get(i).setText(currentGame.getPlayers().get(i));

                        if (!players.get(i).getText().equals("")) {
                            players.get(i).setVisibility(View.VISIBLE);
                            players.get(i).setText(currentGame.getPlayers().get(i));
                        }
                        else players.get(i).setVisibility(View.GONE);
                    }

                    ArrayList<Game> temp = Client.getInstance().getGameList();



                }
            }
        });

        if( createUpdate == true){
            newGame.setVisibility(View.GONE);
            gameName.setText(null);
            gamesAdapter.addGametoView(currentGame);
            gamesAdapter.notifyDataSetChanged();
            for (int i=0; i< Client.getInstance().getGameList().size(); i++){

           curGame.setText( Client.getInstance().getGameList().get(i).getId());
            }

            for (int i = 0; i < currentGame.getPlayers().size(); i++) {

                players.get(i).setText(currentGame.getPlayers().get(i));

                if (!players.get(i).getText().equals("")) {
                    players.get(i).setVisibility(View.VISIBLE);
                    players.get(i).setText(currentGame.getPlayers().get(i));
                }
                else players.get(i).setVisibility(View.GONE);
            }

        }
        if (Client.getInstance().getGameList().size()>0)
        {
            for(int i=0; i<Client.getInstance().getGameList().size(); i++)
            gamesAdapter.addGametoView(Client.getInstance().getGameList().get(i));
            gamesAdapter.notifyDataSetChanged();
        }

        return view;
    }

    private class GamesHolder extends RecyclerView.ViewHolder {

        private TextView mGameName;
        private Game mGame;

        public GamesHolder(View itemView) {
            super(itemView);

            mGameName = (TextView) itemView.findViewById(R.id.game_name);

            mGameName.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    //This needs to be adjusted in the client model;
                    ArrayList<Game> tempGlist = Client.getInstance().getGameList();
                    int gListSize = tempGlist.size();
                    String changeString = mGameName.getText().toString();

                    for (int i = 0; i <gListSize; i++) {

                        if (tempGlist.get(i).getId() == null) {
                            continue;
                        }
                        if (tempGlist.get(i).getId().equals(changeString)) {
                            currentGame = tempGlist.get(i);
                            curGame.setText(currentGame.getId());
                            break;
                        }

                    }

                    for (int i = 0; i < currentGame.getPlayers().size(); i++) {

                        players.get(i).setText(currentGame.getPlayers().get(i));

                        if (!players.get(i).getText().equals("")) {
                            players.get(i).setVisibility(View.VISIBLE);
                            players.get(i).setText(currentGame.getPlayers().get(i));
                        }
                        else players.get(i).setVisibility(View.GONE);
                    }




                }
            });
        }

        public void bindGame(Game game) {
            mGame = game;
            mGameName.setText(mGame.getId());

        }
    }


   private class GamesAdapter extends RecyclerView.Adapter<GamesHolder> {

        private ArrayList<Game> games;

        public GamesAdapter(ArrayList<Game> g) {
            if (games == null)
            {
                Game game = new Game();
                games = new ArrayList<Game>();
                games.add(game);
            }
            else
            {games = g; }}

        @Override
        public GamesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.list_item_games, parent, false);
            return new GamesHolder(view);
        }

        public void addGametoView(Game game)
        {
            games.add(game);
        }

        @Override
        public void onBindViewHolder(GamesHolder holder, int position) {

            Game game = games.get(position);
            holder.bindGame(game);
        }

        @Override
        public int getItemCount() {
            return games.size();
        }
    }




}
