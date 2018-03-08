//package game.GameHistory;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//import Models.Client;
//import Models.Gameplay.Game;
//import Models.Gameplay.GameHistory;
//import activities.R;
//
///**
// * Created by brianotte on 3/7/18.
// */
//
//public class GameHistoryFragment {
//
//
//
//    public class GameHistoryHolder extends RecyclerView.ViewHolder {
//
//        private TextView mHistoryItem;
//        private String mAction;
//
//        public GameHistoryHolder(View itemView) {
//            super(itemView);
//
//            mHistoryItem = itemView.findViewById(R.id.history_item);
//
//            mHistoryItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //This needs to be adjusted in the client model;
////                    ArrayList<Game> tempGlist = Client.getInstance().getGameList();
//                    GameHistory history =
//                    int gListSize = tempGlist.size();
//                    String changeString = mHistoryItem.getText().toString();
//
//                    for (int i = 0; i <gListSize; i++)
//                    {
//                        if (tempGlist.get(i).getId() == null)
//                        {
//                            continue;
//                        }
//                        if (tempGlist.get(i).getId().equals(changeString)) {
//                            currentGame = tempGlist.get(i);
//                            updatePlayers();
//                            curGame.setText(currentGame.getId());
//                            break;
//                        }
//                    }
//                }
//            });
//        }
//
//        public void bindGame(String action) {
//            mAction = action;
//            mHistoryItem.setText(mAction);
//        }
//    }
//
//    public class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryFragment.GameHistoryHolder> {
//        private ArrayList<Game> games;
//
//        public GameHistoryAdapter(ArrayList<Game> g) {
//            if (games == null)
//            {
//                Game game = new Game();
//                games = new ArrayList<Game>();
//                games.add(game);
//            }
//            else
//            {games = g; }
//        }
//
//        @Override
//        public GameHistoryFragment.GameHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater layoutInflater = getLayoutInflater();
//            View view = layoutInflater.inflate(R.layout.list_item_games, parent, false);
//            return new GameHistoryFragment.GameHistoryHolder(view);
//        }
//
//        public void addActiontoView(Game game)
//        {
//            games.add(game);
//        }
//
//        public void clearGameHistory()
//        {
//            games.clear();
//        }
//
//        @Override
//        public void onBindViewHolder(GameHistoryFragment.GameHistoryHolder holder, int position) {
//
//            Game game = games.get(position);
//            holder.bindGame(game);
//        }
//
//        @Override
//        public int getItemCount() {
//            return games.size();
//        }
//    }
//
//}
