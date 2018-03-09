package lobby;

import android.content.res.AssetManager;
import android.graphics.Typeface;
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

import Models.Gameplay.Player;
import ObserverPattern.TTR_Observable;
import activities.R;
import Models.Gameplay.Game;
import Models.Client;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LobbyFragment extends Fragment {


    public LobbyPresenter lobbyPresenter;
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
    Game currentGame = Client.getInstance().getActiveGame();
    ArrayList<TextView> players = new ArrayList<>(5);
//    ArrayList<Game> games = new ArrayList<>();
//    ArrayList<String> play;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        lobbyPresenter = new LobbyPresenter(getActivity());
        view = inflater.inflate(R.layout.lobby_fragment, container, false);// setting view
//        final ArrayList<String>playernames = new ArrayList<String>();

        //stuff that comes from the last fragment
        final Bundle acceptedUser = getArguments();
//        playernames.add(acceptedUser.getString("username"));
        curName = (TextView) view.findViewById(R.id.yourName);
        curName.setText(acceptedUser.getString("username"));

        //attributes for the view
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
        join= (Button) view.findViewById(R.id.join);
        start= (Button) view.findViewById(R.id.start);
        done= (Button) view.findViewById(R.id.done);
        newGame = (LinearLayout) view.findViewById(R.id.newGame);
        gameName = (EditText) view.findViewById(R.id.gName);
        mGamesRecView = (RecyclerView) view.findViewById(R.id.game_list);
//        final  TextView numPlayers = (TextView) view.findViewById(R.id.numPlayers);

        lobbyPresenter.setUser(acceptedUser.getString("username"), acceptedUser.getString("password"), acceptedUser.getString("authToken"));

        mGamesRecView.setLayoutManager(new LinearLayoutManager(getActivity()));


        final ArrayList<Game> games = Client.getInstance().getGameList();
        gamesAdapter = new GamesAdapter(games);
        mGamesRecView.setAdapter(gamesAdapter);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentGame != null) {
                    currentGame = Client.getInstance().getGameById(currentGame.getId());
                    lobbyPresenter.joinGame(getActivity(), currentGame, acceptedUser.getString("username"));
                    currentGame = Client.getInstance().getGameById(currentGame.getId());
                }
                else
                {
                    Toast.makeText(getActivity(), "No game selected", Toast.LENGTH_LONG).show();
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentGame != null)
                {
                    currentGame = Client.getInstance().getGameById(currentGame.getId());
                    if (!currentGame.isActive()) {
                        start.setEnabled(true);
                        lobbyPresenter.startGame(getActivity(), currentGame);
                    }
                    else if(Client.getInstance().getActiveGame() == currentGame)
                    {
                        TTR_Observable.getInstance().startGame();
                        start.setEnabled(true);
//                        lobbyPresenter.startGame(getActivity(), currentGame);
                        System.out.println("GetActiveGame == game");
                    }
                    else if(Client.getInstance().getActiveGame().getId().equals(currentGame.getId()))
                    {
                        TTR_Observable.getInstance().startGame();
                        start.setEnabled(true);
//                        lobbyPresenter.startGame(getActivity(), currentGame);
                        System.out.println("GetActiveGameId.equals(game.getId())");
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "You cannot start that game", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    currentGame = lobbyPresenter.createGame(getActivity(), gameName.getText().toString());
                    newGame.setVisibility(View.GONE);
                    gameName.setText(null);
                    curGame.setText(currentGame.getId());
//                    ArrayList<Game> temp = Client.getInstance().getGameList();
                }
            }
        });
        updateGameList();
        return view;
    }

    public void updateGameList()
    {
        HashMap<String, Game> games = Client.getInstance().getGameMap();
        if(games != null) {
            gamesAdapter.clearGames();
            for(String i : games.keySet()) {
                Game game = Client.getInstance().getGameById(i);
                gamesAdapter.addGametoView(game);
            }
            gamesAdapter.notifyDataSetChanged();
        }

    }

    public void updatePlayers()
    {
        currentGame = Client.getInstance().getGameById(currentGame.getId());
        if(currentGame != null)
        {
            curGame.setText(currentGame.getId());
            for (int i = 0; i < players.size(); i++)
            {
                if (i < Client.getInstance().getGameSize(currentGame.getId()))
                {
                    players.get(i).setText(currentGame.getPlayers().get(i).getName());
                } else players.get(i).setText("vacant");
            }
        }
        else
        {
            System.out.println("The current game is null");
        }
    }


    public class GamesHolder extends RecyclerView.ViewHolder {

        private TextView mGameName;
        private Game mGame;

        public GamesHolder(View itemView) {
            super(itemView);

            mGameName = itemView.findViewById(R.id.game_name);

            mGameName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //This needs to be adjusted in the client model;
                    ArrayList<Game> tempGlist = Client.getInstance().getGameList();
                    int gListSize = tempGlist.size();
                    String changeString = mGameName.getText().toString();

                    for (int i = 0; i <gListSize; i++)
                    {
                        if (tempGlist.get(i).getId() == null)
                        {
                            continue;
                        }
                        if (tempGlist.get(i).getId().equals(changeString)) {
                            currentGame = tempGlist.get(i);
                            updatePlayers();
                            curGame.setText(currentGame.getId());
                            break;
                        }
                    }
                }
            });
        }

        public void bindGame(Game game) {
            mGame = game;
            mGameName.setText(mGame.getId());
        }
    }

   public class GamesAdapter extends RecyclerView.Adapter<GamesHolder> {
        private ArrayList<Game> games;

        public GamesAdapter(ArrayList<Game> g) {
            if (games == null)
            {
                Game game = new Game();
                games = new ArrayList<Game>();
                games.add(game);
            }
            else
            {games = g; }
        }

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

        public void clearGames()
        {
            games.clear();
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
