package game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.sip.SipSession;
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

import java.util.Set;

import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Location;
import Models.Result;
import StatePattern.GameSetup;
import activities.R;
import lobby.LobbyFragment;
import RegisterLogin.LoginRegisterPresenter;

/**
 * Created by fjameson on 2/2/18.
 */

public class GameFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    ImageView map;
    ImageButton city_one;
    ImageButton city_two;
    Button claimRoute;
    Button viewDestCards;
    Button viewTrainCards;
    Button simulateTurn;
    ImageButton goToStats;
    GamePresenter gamePresenter;
    Set<ImageButton> cities;
    ImageButton sunSpeare;
    ImageButton saltShore;
    public GameFragment()
    {
//        LoginRegisterPresenter loginRegisterPresenter = new LoginRegisterPresenter(getActivity());
        gamePresenter = new GamePresenter(getContext());
//        gamePresenter = new GamePresenter(getContext(), new GameSetup());

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        gamePresenter = new GamePresenter(getContext());
        View view = inflater.inflate(R.layout.game, container, false);

        final RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.gameView);
        map = (ImageView) view.findViewById(R.id.gameMap);
//        sunSpeare = (ImageButton)view.findViewById(R.id.sunSpeare);
//        saltShore = (ImageButton) view.findViewById(R.id.saltShore);

        final Location start1 = new Location(250, 1330);
        final Location end1 = new Location(480, 1265);

        final Location start2 = new Location(470, 1500);
        final Location end2 = new Location(847, 1665);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(10);
        paint.setColor(Color.WHITE);
        relativeLayout.addView(new Line(getActivity(), start1.getX(), start1.getY(), end1.getX(), end1.getY(), paint));

//        Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint2.setStrokeWidth(10);
//        paint2.setColor(Color.WHITE);
        relativeLayout.addView(new Line(getActivity(), start2.getX(), start2.getY(), end2.getX(), end2.getY(), paint));

        if(ActiveGame.getInstance().getMyPlayer().getClaimedRoutes().size() == 1) //current user
        {
            Paint paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint3.setStrokeWidth(10);
            if(ActiveGame.getInstance().getMyPlayer().getColor().equals("red"))
            {
                paint3.setColor(Color.RED);
            }
            else
            {
                paint3.setColor(Color.GREEN);
            }
            relativeLayout.addView(new Line(getActivity(), start1.getX(), start1.getY(), end1.getX(), end1.getY(), paint3));
        }
        if(ActiveGame.getInstance().getOtherPlayer().getClaimedRoutes().size() == 1) //other player
        {
            Paint paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint4.setStrokeWidth(10);
            if(ActiveGame.getInstance().getOtherPlayer().getColor().equals("red"))
            {
                paint4.setColor(Color.RED);
            }
            else
            {
                paint4.setColor(Color.GREEN);
            }
            relativeLayout.addView(new Line(getActivity(), start2.getX(), start2.getY(), end2.getX(), end2.getY(), paint4));
        }


//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        final RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)sunSpeare.getLayoutParams();
//        final RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams)saltShore.getLayoutParams();
//        relativeLayout.addView(new Line(getActivity(), lp.leftMargin, lp.topMargin, lp2.leftMargin, lp2.topMargin, paint));
//
//        Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint2.setStrokeWidth(10);
//        paint2.setColor(Color.BLACK);
//        relativeLayout.addView(new Line(getActivity(), lp.leftMargin+10,lp.topMargin+10, lp2.leftMargin+10, lp2.topMargin+10, paint2));

        simulateTurn = (Button) view.findViewById(R.id.test_button);
        simulateTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ActiveGame.getInstance().getActivePlayerObj().getName();
                if(Client.getInstance().getUserName().equals(username))
                {
                    Toast.makeText(getActivity(), "It's not their turn!", Toast.LENGTH_SHORT).show();
                }
                else{
                    gamePresenter.claimOtherRoute(getActivity());
                    Paint paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint4.setStrokeWidth(10);
                    if(ActiveGame.getInstance().getOtherPlayer().getColor().equals("red"))
                    {
                        paint4.setColor(Color.RED);
                    }
                    else
                    {
                        paint4.setColor(Color.GREEN);
                    }
                    relativeLayout.addView(new Line(getActivity(), start2.getX(), start2.getY(), end2.getX(), end2.getY(), paint4));
                }
            }
        });

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
                    Result r = gamePresenter.claimRoute(getActivity());
                    Paint paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint3.setStrokeWidth(10);
                    if(ActiveGame.getInstance().getMyPlayer().getColor().equals("red"))
                    {
                        paint3.setColor(Color.RED);
                    }
                    else
                    {
                        paint3.setColor(Color.GREEN);
                    }
                    relativeLayout.addView(new Line(getActivity(), start1.getX(), start1.getY(), end1.getX(), end1.getY(), paint3));
//                    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
//                    paint2.setStrokeWidth(10);
//                    paint2.setColor(Color.RED);
//                    relativeLayout.addView(new Line(getActivity(), lp.leftMargin + 10, lp.topMargin + 10, lp2.leftMargin + 10, lp2.topMargin + 10, paint2));
                    if (r != null) {

                        FragmentManager headfrag = getActivity().getSupportFragmentManager();
                        Fragment fragment = new LobbyFragment();

                        //need something along these lines for the game and users in it?
                    /*bundle.putString("username", username.getText().toString());
                    bundle.putString("password", password.getText().toString());
                    bundle.putString("authToken", r.getAuthToken());
                    fragment.setArguments(bundle);*/

                        headfrag.beginTransaction().replace(R.id.activity_main, fragment).commit();
                    }
                }

            }
        });


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


        goToStats= (ImageButton)view.findViewById(R.id.stats);
        goToStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePresenter.switchToStats(getActivity());

            }
        });
        map.setOnTouchListener(this);

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

    public void claimingRoute(final ImageButton city){
        city.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //add highlight
                    if (city == null)
                        city_one =city;
                    else if (city_two == null)
                    {
                        city_two=city;
                    }
                    else {
                        Toast.makeText(getActivity(), "You need to deselect one of your cities", Toast.LENGTH_SHORT).show();
                    }
            }
            else if(event.getAction() == MotionEvent.ACTION_UP){
                    //remove highlight
                    if(city_one.equals(city))
                    {
                        city_one=null;
                    }
                    else {
                        city_two=null;
                    }
                }
                return true;
        };
        });

    }



    @Override
    public void onClick(View v) {
        v.getY();
        v.getX();
        String s = String.valueOf(v.getX())+" "+ String.valueOf(v.getY());
        Toast.makeText(getContext(), String.valueOf(v.getTag()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_DOWN) {
            String s = String.valueOf(event.getX()) + " " + String.valueOf(event.getY());
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            boolean getRoute = gamePresenter.selectingRoute(event.getX(), event.getY());
            if (getRoute)
            {
                Toast.makeText(getContext(), "YOu claimed this route!!", Toast.LENGTH_SHORT).show();
            }
            return true;
        }return false;
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

        public void getPoints (int sX,int  sY,int eX, int eY)
        {
            startX=sX;
            startY=sY;
            endX=eX;
            endY=eY;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawLine(startX, startY, endX, endY, paint);

        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
           // super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
        }
    }
}