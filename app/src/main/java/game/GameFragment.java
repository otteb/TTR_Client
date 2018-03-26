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
import StatePattern.NotMyTurn;
import activities.MainActivity;
import activities.R;
import lobby.LobbyFragment;


public class GameFragment extends Fragment implements  View.OnTouchListener {

    MainActivity mainActivity;
 RelativeLayout relativeLayout;
    Paint blackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint yellowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint bluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
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
        gamePresenter = new GamePresenter(getActivity());

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        blackPaint.setStrokeWidth(30);
        blackPaint.setColor(Color.BLACK);
        yellowPaint.setStrokeWidth(30);
        yellowPaint.setColor(Color.YELLOW);
        bluePaint.setStrokeWidth(30);
        bluePaint.setColor(Color.BLUE);
        redPaint.setStrokeWidth(30);
        redPaint.setColor(Color.RED);
        greenPaint.setStrokeWidth(30);
        greenPaint.setColor(Color.GREEN);
        View view = inflater.inflate(R.layout.game, container, false);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.gameView);


        claimRoute= (Button)view.findViewById(R.id.claim);
        claimRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = ActiveGame.getInstance().getActivePlayerObj().getName();
                if(Client.getInstance().getCurState() instanceof NotMyTurn){
                    Toast.makeText(getActivity(), "It's not your turn!", Toast.LENGTH_SHORT).show();
                }
                else {
                    gamePresenter.claimRoute(getActivity(), routeNumber);
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

        String username = ActiveGame.getInstance().getActivePlayer();
        if(Client.getInstance().getUserName().equals(username))
        {
            Toast.makeText(getActivity(), "It's your turn!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getActivity(), "It\'s " + username + "\'s turn!", Toast.LENGTH_SHORT).show();
        }
        updateRoutes();
        return view;
    }

    public void makeLines(Route route, Player player)
    {
        if(player.getColor().equals("red"))
        {
            relativeLayout.addView(new Line(getActivity(), (float)route.getStartX(), (float)route.getStartY(), (float)route.getEndX(),
                (float)route.getEndY(), redPaint));
        }
        else if(player.getColor().equals("green"))
        {
            relativeLayout.addView(new Line(getActivity(), (float)route.getStartX(), (float)route.getStartY(), (float)route.getEndX(),
                    (float)route.getEndY(), greenPaint));
        }
        else if(player.getColor().equals("blue"))
        {
            relativeLayout.addView(new Line(getActivity(), (float)route.getStartX(), (float)route.getStartY(), (float)route.getEndX(),
                    (float)route.getEndY(), bluePaint));
        }
        else if(player.getColor().equals("black"))
        {
            relativeLayout.addView(new Line(getActivity(), (float)route.getStartX(), (float)route.getStartY(), (float)route.getEndX(),
                    (float)route.getEndY(), blackPaint));
        }
        else
        {
            relativeLayout.addView(new Line(getActivity(), (float)route.getStartX(), (float)route.getStartY(), (float)route.getEndX(),
                    (float)route.getEndY(), yellowPaint));
        }
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_DOWN) {
            routeNumber = gamePresenter.selectingRoute(event.getX(), event.getY());
            if (routeNumber != null)
            {
                claimRoute.setVisibility(v.VISIBLE);
            }
            else {
                //debatebly we should remove this
                Toast.makeText(getContext(), "You weren't close enough to claim this route", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return false;
    }

    public void updateRoutes(){
        Map<Integer, Route> updateClaimedRoutes = ActiveGame.getInstance().getClaimedRoutes();
        if(updateClaimedRoutes != null && (updateClaimedRoutes.size() > 0)) {
            for (Integer i : updateClaimedRoutes.keySet()) {
                Player p = ActiveGame.getInstance().getPlayer(updateClaimedRoutes.get(i).getOwner());
                makeLines(updateClaimedRoutes.get(i), p);
            }
        }
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