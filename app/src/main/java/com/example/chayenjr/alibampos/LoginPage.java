package com.example.chayenjr.alibampos;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Point;
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
    public static int check_login = 0, confirm_logout = 0;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private EditText username, password;
    public static int countPage = -1;
    public static String m_id = "", tele_num = "";
    private int newpoint_x = 680, newpoint_y = 20, point_x = 0,point_y = 0;
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
                setTitle("Log In");
                findViewById(R.id.logo).setVisibility(View.VISIBLE);
                findViewById(R.id.username).setVisibility(View.VISIBLE);
                findViewById(R.id.password).setVisibility(View.VISIBLE);
                findViewById(R.id.signin_button).setVisibility(View.VISIBLE);
                Button butt = (Button)findViewById(R.id.signin_button);
                Point point = getPointOfView(butt);
                point_x = point.x;
                point_y = 12*point.y/13;
                newpoint_x = 2 * point_x;
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
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) button.getLayoutParams();

        if((check_login == 0 || check_login == 1) && username.getText().toString().equals("") && password.getText().toString().equals("")){
            //wrong password
            showDialog(LoginPage.this, "Merchant_ID incorrect", "Please type again", "OK");
        } else {
            //correct password
            if(check_login == 0){
                check_login = 1;
                countPage = 0;
                findViewById(R.id.logo).setVisibility(View.INVISIBLE);
                findViewById(R.id.username).setVisibility(View.INVISIBLE);
                findViewById(R.id.password).setVisibility(View.INVISIBLE);
//                myText.setText("You're login");
                OtpCheckPage fragment_otp = new OtpCheckPage();
                FragmentTransaction a = getSupportFragmentManager().beginTransaction();
                a.replace(R.id.otpcheckPage, fragment_otp).addToBackStack(null);
                a.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                m_id = ((EditText) findViewById(R.id.textUsername)).getText().toString();
                tele_num = ((EditText) findViewById(R.id.textPassword)).getText().toString();
                setTitle("OTP Verification");
                a.commit();
            } else if(check_login == 1){
                check_login = 2;
                countPage = 1;
                username.getText().clear();
                password.getText().clear();
                findViewById(R.id.logo).setVisibility(View.INVISIBLE);
//                i.add(R.id.startpaymentPage, fragment);
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                i.replace(R.id.startpaymentPage, fragment).addToBackStack(null);
                i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                setTitle("AlipayMpos's shop");
                findViewById(R.id.username).setVisibility(View.INVISIBLE);
                findViewById(R.id.password).setVisibility(View.INVISIBLE);
//                i.commit();
                lView.addView(myText);
                i.commit();
                button.setX(newpoint_x);
                button.setY(newpoint_y);
                button.setText("Logout");
            } else{
                logoutDialog(LoginPage.this, "Logout", "Are you sure to logout?", "Confirm", "No");
            }
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

    private static Point getPointOfView(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return new Point(location[0], location[1]);
    }

    @Override
    public void onBackPressed(){
        if(countPage == -1){
            quitProgramDialog(LoginPage.this, "Quit Program", "Are you sure to exit application?", "Yes", "No");
        } else if(countPage == 0){
            findViewById(R.id.logo).setVisibility(View.VISIBLE);
            findViewById(R.id.username).setVisibility(View.VISIBLE);
            findViewById(R.id.password).setVisibility(View.VISIBLE);
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            countPage = -1;
            check_login = 0;
        } else if(countPage == 1){
        } else if(countPage == 2 || countPage == 201 || countPage == 202 || countPage == 203 || countPage == 204){
            StartPayment fragment = new StartPayment();
            FragmentTransaction i = getSupportFragmentManager().beginTransaction();
            findViewById(R.id.signin_button).setVisibility(View.VISIBLE);
            i.replace(R.id.startpaymentPage, fragment).addToBackStack(null);
            i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            i.commit();
            setTitle("AlipayMpos's shop");
            countPage = 1;
        } else if(countPage == 3){
            MoneyValue fragment = new MoneyValue();
            FragmentTransaction i = getSupportFragmentManager().beginTransaction();
            i.replace(R.id.moneypay, fragment).addToBackStack(null);
            i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            i.commit();
            setTitle("AlipayMpos");
            countPage = 2;
        } else if(countPage == 4){
            ScanCodePage fragment = new ScanCodePage();
            FragmentTransaction i = getSupportFragmentManager().beginTransaction();
            i.replace(R.id.scancodepage, fragment).addToBackStack(null);
            i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            i.commit();
            countPage = 3;
        } else if(countPage == 5){
            SendRecipePage fragment = new SendRecipePage();
            FragmentTransaction i = getSupportFragmentManager().beginTransaction();
            findViewById(R.id.goscanbarcode).setVisibility(View.INVISIBLE);
            findViewById(R.id.goscanqrcode).setVisibility(View.INVISIBLE);
            i.replace(R.id.sendrecipepage, fragment).addToBackStack(null);
            i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            i.commit();
            countPage = 4;
        }
    }

    private static AlertDialog quitProgramDialog(final AppCompatActivity act, CharSequence title,
                                          CharSequence message, CharSequence buttonYes, CharSequence buttonNo){
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title).setMessage(message).setPositiveButton(buttonYes, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        }).setNegativeButton(buttonNo, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return downloadDialog.show();
    }

    private AlertDialog logoutDialog(final AppCompatActivity act, CharSequence title,
                                     CharSequence message, CharSequence buttonYes, CharSequence buttonNo){
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title).setMessage(message).setPositiveButton(buttonYes, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmLogout();
            }
        }).setNegativeButton(buttonNo, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return downloadDialog.show();
    }

    private void confirmLogout(){
        check_login = 0;
        countPage = -1;
//                i.remove(fragment);

        StartPayment fragment = new StartPayment();
        FragmentTransaction i = getSupportFragmentManager().beginTransaction();
        findViewById(R.id.logo).setVisibility(View.VISIBLE);
        findViewById(R.id.username).setVisibility(View.VISIBLE);
        findViewById(R.id.password).setVisibility(View.VISIBLE);
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        setTitle("Log In");
//                i.commit();
//                myText.setText("You're logout");
        Button button = (Button)findViewById(R.id.signin_button);
        button.setX(point_x);
        button.setY(point_y - 200);
        button.setText("Submit");
        FrameLayout lView = (FrameLayout)findViewById(R.id.loginpage);
        TextView myText = new TextView(this);
        lView.addView(myText);
        i.commit();
        confirm_logout = 0;
    }
}
