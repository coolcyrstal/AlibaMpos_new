package com.example.chayenjr.alibampos;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginPage extends AppCompatActivity{

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final int UI_ANIMATION_DELAY = 300;
    public static int check_login = 0;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private EditText username, password;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        findViewById(R.id.logo).setVisibility(View.INVISIBLE);
        findViewById(R.id.username).setVisibility(View.INVISIBLE);
        findViewById(R.id.password).setVisibility(View.INVISIBLE);
        findViewById(R.id.signin_button).setVisibility(View.INVISIBLE);

        SplashScreen fragment = new SplashScreen();
        FragmentTransaction i = getSupportFragmentManager().beginTransaction();
        i.replace(R.id.splashscreenpage, fragment).addToBackStack(null);
        i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        i.commit();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 3s = 3000ms
                getSupportFragmentManager().popBackStack();
                findViewById(R.id.logo).setVisibility(View.VISIBLE);
                findViewById(R.id.username).setVisibility(View.VISIBLE);
                findViewById(R.id.password).setVisibility(View.VISIBLE);
                findViewById(R.id.signin_button).setVisibility(View.VISIBLE);
            }
        }, 3000);
    }

    public void buttonOnClick(View v){
        username = (EditText)findViewById(R.id.textUsername);
        password = (EditText)findViewById(R.id.textPassword);

        FrameLayout lView = (FrameLayout)findViewById(R.id.loginpage);
        TextView myText = new TextView(this);

        StartPayment fragment = new StartPayment();
        FragmentTransaction i = getSupportFragmentManager().beginTransaction();

        Button button = (Button)findViewById(R.id.signin_button);

        if(check_login == 0 && username.getText().toString().equals("") && password.getText().toString().equals("")){
            //wrong password
            showDialog(LoginPage.this, "Username or password incorrect", "Please type again", "OK");
        } else {
            //correct password
            if(check_login == 0){
                check_login = 1;
                username.getText().clear();
                password.getText().clear();
//                myText.setText("You're login");
                findViewById(R.id.logo).setVisibility(View.INVISIBLE);
//                i.add(R.id.startpaymentPage, fragment);
                i.replace(R.id.startpaymentPage, fragment).addToBackStack(null);
                i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                findViewById(R.id.username).setVisibility(View.INVISIBLE);
                findViewById(R.id.password).setVisibility(View.INVISIBLE);
//                i.commit();
                button.setText("Sign out");
            } else{
                check_login = 0;
//                i.remove(fragment);
                findViewById(R.id.logo).setVisibility(View.VISIBLE);
                findViewById(R.id.username).setVisibility(View.VISIBLE);
                findViewById(R.id.password).setVisibility(View.VISIBLE);
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//                i.commit();
//                myText.setText("You're logout");
                button.setText("Sign in");
            }
            lView.addView(myText);
            i.commit();
        }
    }

    private static AlertDialog showDialog(final AppCompatActivity act, CharSequence title,
                                          CharSequence message, CharSequence buttonYes){
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title).setMessage(message).setPositiveButton(buttonYes, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return downloadDialog.show();
    }
}
