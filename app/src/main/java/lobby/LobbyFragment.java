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

import Models.Request;
import ObserverPattern.TTR_Observable;
import Services.Commands.LobbyServices;
import activities.R;
import Models.Gameplay.Game;
import Models.Client;


import java.util.ArrayList;
import java.util.HashMap;

public class LobbyFragment extends Fragment {


    LobbyPresenter lobbyPresenter;
    GamesAdapter gamesAdapter;
//    Game createGame = new Game();
//    public  List<Game> gameList;
    RecyclerView mGamesRecView;
    ArrayList<TextView> players = new ArrayList<>(5);
    TextView curName;
    TextView p1;
    TextView p2;
    TextView p3;
    TextView p4;
    TextView p5;
    TextView curGame;
    Button join;
    Button start;
    Button done;
    Button create;
    LinearLayout newGame;
    EditText gameName;
    View view;
    Game currentGame = Client.getInstance().getActiveGame();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        lobbyPresenter = new LobbyPresenter(getActivity());
        view = inflater.inflate(R.layout.lobby_fragment, container, false);// setting view

        //stuff that comes from the last fragment
        final Bundle acceptedUser = getArguments();
        curName = view.findViewById(R.id.yourName);
        curName.setText(acceptedUser.getString("username"));

        //attributes for the view
        create = view.findViewById(R.id.create);
        p1 = view.findViewById(R.id.p1);
        p2 = view.findViewById(R.id.p2);
        p3 = view.findViewById(R.id.p3);
        p4 = view.findViewById(R.id.p4);
        p5 = view.findViewById(R.id.p5);
//        TextView gLabel = view.findViewById(R.id.gameLabel);
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);
        curGame = view.findViewById(R.id.curGame);
        join = view.findViewById(R.id.join);
        start = view.findViewById(R.id.start);
        done = view.findViewById(R.id.done);
        newGame = view.findViewById(R.id.newGame);
        gameName = view.findViewById(R.id.gName);
        mGamesRecView = view.findViewById(R.id.game_list);
        lobbyPresenter.setUser();
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
//                        TTR_Observable.getInstance().startGame();
                        start.setEnabled(true);
                        System.out.println("GetActiveGame == game");
//                        Request temp = new Request(); // Jordan-Approved
//                        temp.setUsername(Client.getInstance().getUserName());
//                        temp.setGameId(currentGame.getId());
//                        LobbyServices.getInstance().startGame(temp);
                        lobbyPresenter.rejoinGame(getActivity(), currentGame);
                    }
//                    else if(Client.getInstance().getActiveGame().getId().equals(currentGame.getId()))
//                    {
//                        start.setEnabled(true);
//                        TTR_Observable.getInstance().startGame();
//                        System.out.println("GetActiveGameId.equals(game.getId())");
//                    }
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
                }
            }
        });
        updateGameList();
        if(Client.getInstance().getActiveGame() != null)
        {
            currentGame = Client.getInstance().getActiveGame();
            updatePlayers();
        }
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
                } else players.get(i).setText(R.string.vacant);
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

        private GamesHolder(View itemView) {
            super(itemView);

            mGameName = itemView.findViewById(R.id.game_name);

            mGameName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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

        private void bindGame(Game game) {
            mGame = game;
            mGameName.setText(mGame.getId());
        }
    }

   public class GamesAdapter extends RecyclerView.Adapter<GamesHolder> {
        private ArrayList<Game> games;

        private GamesAdapter(ArrayList<Game> g) {
            if (games == null)
            {
                Game game = new Game();
                games = new ArrayList<>();
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

       private void addGametoView(Game game)
        {
            games.add(game);
        }

       private void clearGames()
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
