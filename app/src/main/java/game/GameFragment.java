package game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Set;

import Models.Result;
import activities.R;
import lobby.LobbyFragment;
import RegisterLogin.LoginRegisterPresenter;

/**
 * Created by fjameson on 2/2/18.
 */

public class GameFragment extends Fragment {
    ImageButton city_one;
    ImageButton city_two;
    Button claimRoute;
    Button viewDestCards;
    Button viewTrainCards;
    ImageButton goToStats;
    GamePresenter gamePresenter;
    Set<ImageButton> cities;
    ImageButton sunSpeare;
    ImageButton saltShore;

    public GameFragment()
    {
        LoginRegisterPresenter loginRegisterPresenter = new LoginRegisterPresenter(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gamePresenter = new GamePresenter(getContext());
        View view = inflater.inflate(R.layout.game, container, false);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.gameView);

        sunSpeare = (ImageButton)view.findViewById(R.id.sunSpeare);
        saltShore = (ImageButton) view.findViewById(R.id.saltShore);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(10);
        paint.setColor(Color.WHITE);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)sunSpeare.getLayoutParams();
        RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams)saltShore.getLayoutParams();
        relativeLayout.addView(new Line(getActivity(), lp.leftMargin, lp.topMargin, lp2.leftMargin, lp2.topMargin, paint));

        Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setStrokeWidth(10);
        paint2.setColor(Color.BLACK);
        relativeLayout.addView(new Line(getActivity(), lp.leftMargin+10,lp.topMargin+10, lp2.leftMargin+10, lp2.topMargin+10, paint2));









        claimRoute= (Button)view.findViewById(R.id.claim);
        claimRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result r = gamePresenter.claimRoute(getActivity());
                if(r != null)
                {

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
        });


        //switch to the cards fragment with the bundle passed-in:
        viewDestCards= (Button)view.findViewById(R.id.gameToDestCards);
        viewDestCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePresenter.switchToCards(getActivity());
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