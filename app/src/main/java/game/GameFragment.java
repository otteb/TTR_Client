package game;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Map;
import java.util.Set;

import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Location;
import Models.Gameplay.Player;
import Models.Gameplay.Route;
import Models.Result;
import activities.R;
import lobby.LobbyFragment;


public class GameFragment extends Fragment implements  View.OnTouchListener {

 RelativeLayout relativeLayout;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint yellowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Button claimRoute;
    Button viewDestCards;
    Route routeNumber= null;
    Button viewTrainCards;
//    Button simulateTurn;
    ImageButton goToStats;
    ImageButton goToHistory;
    GamePresenter gamePresenter;
    public GameFragment()
    {
        gamePresenter = new GamePresenter(getContext());

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        paint.setStrokeWidth(30);
        paint.setColor(Color.WHITE);
        View view = inflater.inflate(R.layout.game, container, false);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.gameView);


        claimRoute= (Button)view.findViewById(R.id.claim);
        claimRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = ActiveGame.getInstance().getActivePlayerObj().getName();
                if(!Client.getInstance().getUserName().equals(username))
                {
                    Toast.makeText(getActivity(), "It's not your turn!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Player player = ActiveGame.getInstance().getMyPlayer();
                    //makeLines(routeNumber, player);
                    gamePresenter.claimRoute(getActivity(), routeNumber);
//                    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
//                    paint2.setStrokeWidth(10);
//                    paint2.setColor(Color.RED);
//                    relativeLayout.addView(new Line(getActivity(), lp.leftMargin + 10, lp.topMargin + 10, lp2.leftMargin + 10, lp2.topMargin + 10, paint2));
                    //if (r != null) {

                      //  FragmentManager headfrag = getActivity().getSupportFragmentManager();
                        //Fragment fragment = new LobbyFragment();

                        //need something along these lines for the game and users in it?
                    /*bundle.putString("username", username.getText().toString());
                    bundle.putString("password", password.getText().toString());
                    bundle.putString("authToken", r.getAuthToken());
                    fragment.setArguments(bundle);*/

                       // headfrag.beginTransaction().replace(R.id.activity_main, fragment).commit();
                    //}
                }
                routeNumber = null;
                claimRoute.setVisibility(View.GONE);
            }
        });
        claimRoute.setVisibility(View.GONE);


        //switch to the cards fragment with the bundle passed-in:
        viewDestCards= (Button)view.findViewById(R.id.gameToDestCards);
        viewDestCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePresenter.switchToDestCards(getActivity());
            }
        });

        //switch to the cards fragment with the bundle passed-in:
        viewTrainCards = (Button)view.findViewById(R.id.gameToTrainCards);
        viewTrainCards.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                gamePresenter.switchToCards(getActivity());
            }
        });


        goToStats= (ImageButton)view.findViewById(R.id.gameToStats);
        goToStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePresenter.switchToStats(getActivity());

            }
        });
        goToHistory= (ImageButton)view.findViewById(R.id.gameToGameHistory);
        goToHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePresenter.switchToGameHistory(getActivity());

            }
        });

        relativeLayout.setOnTouchListener(this);

        String username = ActiveGame.getInstance().getActivePlayerObj().getName();
        if(Client.getInstance().getUserName().equals(username))
        {
            Toast.makeText(getActivity(), "It's your turn!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getActivity(), "It\'s " + username + "\'s turn!", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    public void makeLines(Route route, Player player)
    {
        if(player.getColor().equals("red"))
        {
            paint.setColor(Color.RED);
        }
        else if(player.getColor().equals("green"))
        {
            paint.setColor(Color.GREEN);
        }
        else if(player.getColor().equals("blue"))
        {
            paint.setColor(Color.BLUE);
        }
        else if(player.getColor().equals("black"))
        {
            paint.setColor(Color.BLACK);
        }
        else
        {
            paint.setColor(Color.YELLOW);
        }
        relativeLayout.addView(new Line(getActivity(), (float)routeNumber.getStartX(), (float)routeNumber.getStartY(), (float)routeNumber.getEndX(),
                (float)routeNumber.getEndY(), paint));
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_DOWN) {
            String s = String.valueOf(event.getX()) + " " + String.valueOf(event.getY());
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            routeNumber = gamePresenter.selectingRoute(event.getX(), event.getY());
            if (routeNumber != null)
            {
                claimRoute.setVisibility(v.VISIBLE);
            }
            else {
                Toast.makeText(getContext(), "You weren't close enough to claim this route", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return false;
    }

    public void updateRoutes(){
        Map<Integer, Route> updateClaimedRoutes = ActiveGame.getInstance().getClaimedRoutes();
        if(updateClaimedRoutes != null && (updateClaimedRoutes.size() > 0))
        {
            for(Integer i : updateClaimedRoutes.keySet())
            {
                Player p = ActiveGame.getInstance().getPlayer(updateClaimedRoutes.get(i).getOwner());
                makeLines(updateClaimedRoutes.get(i), p);
            }
        }
        Toast.makeText(getContext(), "we are in updateClaimedRoutes", Toast.LENGTH_SHORT).show();
    }


    //////LINE CLASS
    private class Line extends View{

        Paint paint;
        float startX;
        float startY;
        float endX;
        float endY;

        public Line(Context context, float sX, float sY, float eX, float eY, Paint p ) {
            super(context);
            startX=sX;
            startY=sY;
            endX=eX;
            endY=eY;
            paint=p;
        }

        @Override
        protected void onDraw(Canvas canvas){
            canvas.drawLine(startX, startY, endX, endY, paint);
        }

    }
}