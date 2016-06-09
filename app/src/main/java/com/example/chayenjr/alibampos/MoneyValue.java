package com.example.chayenjr.alibampos;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.plus.PlusOneButton;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link MoneyValue.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoneyValue#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoneyValue extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static String paymentmoney = "";
    public static String paymentmoneydot = "";
    public static String showreceipt = "";
    public static String money = "";
    private PlusOneButton mPlusOneButton;
    private int checkdot = 0;
    private OnFragmentInteractionListener mListener;

    public MoneyValue() {
        // Required empty public constructor
    }

    public static MoneyValue newInstance(String param1, String param2) {
        MoneyValue fragment = new MoneyValue();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_money_value, container, false);

        Button del = (Button)view.findViewById(R.id.del);
        del.setOnClickListener(getButtonOnClickListener());
        Button ac = (Button)view.findViewById(R.id.ac);
        ac.setOnClickListener(getButtonOnClickListener());
        Button pay = (Button)view.findViewById(R.id.pay);
        pay.setOnClickListener(getButtonOnClickListener());

        Button one = (Button)view.findViewById(R.id.one);
        one.setOnClickListener(getButtonOnClickListener());
        Button two = (Button)view.findViewById(R.id.two);
        two.setOnClickListener(getButtonOnClickListener());
        Button three = (Button)view.findViewById(R.id.three);
        three.setOnClickListener(getButtonOnClickListener());
        Button four = (Button)view.findViewById(R.id.four);
        four.setOnClickListener(getButtonOnClickListener());
        Button five = (Button)view.findViewById(R.id.five);
        five.setOnClickListener(getButtonOnClickListener());
        Button six = (Button)view.findViewById(R.id.six);
        six.setOnClickListener(getButtonOnClickListener());
        Button seven = (Button)view.findViewById(R.id.seven);
        seven.setOnClickListener(getButtonOnClickListener());
        Button eight = (Button)view.findViewById(R.id.eight);
        eight.setOnClickListener(getButtonOnClickListener());
        Button nine = (Button)view.findViewById(R.id.nine);
        nine.setOnClickListener(getButtonOnClickListener());
        Button zero = (Button)view.findViewById(R.id.zero);
        zero.setOnClickListener(getButtonOnClickListener());
        Button zerozero= (Button)view.findViewById(R.id.zerozero);
        zerozero.setOnClickListener(getButtonOnClickListener());

        return view;
    }

    private View.OnClickListener getButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickGoScanCode(v);
            }
        };
    }

    public void buttonOnClickGoScanCode(View v){
        EditText moneypay = (EditText) getActivity().findViewById(R.id.textmoney);
        paymentmoney = moneypay.getText().toString();
        EditText moneydot = (EditText) getActivity().findViewById(R.id.textmoneydot);
        paymentmoneydot = moneydot.getText().toString();
        if(checkdot == 1){
            if(money.length() >= 10){
                switch (v.getId()){
                    case R.id.del:
                        money = money.toString().substring(0, money.length()-1);
                        break;
                    case R.id.ac:money = "";checkdot = 0;break;
                    case R.id.pay:
                        getActivity().findViewById(R.id.pay).setVisibility(View.INVISIBLE);
                        getActivity().findViewById(R.id.del).setVisibility(View.INVISIBLE);
                        ScanCodePage fragment = new ScanCodePage();
                        FragmentTransaction i = getActivity().getSupportFragmentManager().beginTransaction();
                        i.replace(R.id.scancodepage, fragment).addToBackStack(null);
                        i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        i.commit();
                        showreceipt = paymentmoney + "." + paymentmoneydot;
                        break;
                    case R.id.one:break;
                    case R.id.two:break;
                    case R.id.three:;break;
                    case R.id.four:break;
                    case R.id.five:break;
                    case R.id.six:break;
                    case R.id.seven:break;
                    case R.id.eight:break;
                    case R.id.nine:break;
                    case R.id.zero:break;
                    case R.id.zerozero:break;
                    default:break;
                }
            } else {
                switch (v.getId()){
                    case R.id.del:
                        if(money.length() == 3){
                            checkdot = 0;
                            money = money.toString().substring(0, money.length()-1);
                        } else{
                            money = money.toString().substring(0, money.length()-1);
                        }
                        break;
                    case R.id.ac:money = "";checkdot = 0;break;
                    case R.id.pay:
                        getActivity().findViewById(R.id.pay).setVisibility(View.INVISIBLE);
                        getActivity().findViewById(R.id.del).setVisibility(View.INVISIBLE);
                        ScanCodePage fragment = new ScanCodePage();
                        FragmentTransaction i = getActivity().getSupportFragmentManager().beginTransaction();
                        i.replace(R.id.scancodepage, fragment).addToBackStack(null);
                        i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        i.commit();
                        showreceipt = paymentmoney + "." + paymentmoneydot;
                        break;
                    case R.id.one:money += "1";break;
                    case R.id.two:money += "2";break;
                    case R.id.three:money += "3";break;
                    case R.id.four:money += "4";break;
                    case R.id.five:money += "5";break;
                    case R.id.six:money += "6";break;
                    case R.id.seven:money += "7";break;
                    case R.id.eight:money += "8";break;
                    case R.id.nine:money += "9";break;
                    case R.id.zero:money += "0";break;
                    case R.id.zerozero:money += "00";break;
                    default:break;
                }
            }
        } else{
            if(money.length() == 2){
                switch (v.getId()){
                    case R.id.del:
                        money = money.toString().substring(0, money.length()-1);
                        break;
                    case R.id.ac:money = "";break;
                    case R.id.pay:
                        getActivity().findViewById(R.id.pay).setVisibility(View.INVISIBLE);
                        getActivity().findViewById(R.id.del).setVisibility(View.INVISIBLE);
                        ScanCodePage fragment = new ScanCodePage();
                        FragmentTransaction i = getActivity().getSupportFragmentManager().beginTransaction();
                        i.replace(R.id.scancodepage, fragment).addToBackStack(null);
                        i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        i.commit();
                        showreceipt = paymentmoney + "." + paymentmoneydot;
                        break;
                    case R.id.one:money += "1";checkdot = 1;break;
                    case R.id.two:money += "2";checkdot = 1;break;
                    case R.id.three:money += "3";checkdot = 1;break;
                    case R.id.four:money += "4";checkdot = 1;break;
                    case R.id.five:money += "5";checkdot = 1;break;
                    case R.id.six:money += "6";checkdot = 1;break;
                    case R.id.seven:money += "7";checkdot = 1;break;
                    case R.id.eight:money += "8";checkdot = 1;break;
                    case R.id.nine:money += "9";checkdot = 1;break;
                    case R.id.zero:money += "0";checkdot = 1;break;
                    case R.id.zerozero:money += "00";checkdot = 1;break;
                    default:break;
                }
            } else{
                switch (v.getId()){
                    case R.id.del:
                        if(money.length() <= 0){}
                        else{
                            money = "";
                        }
                        break;
                    case R.id.ac:money = "";break;
                    case R.id.pay:
                        getActivity().findViewById(R.id.pay).setVisibility(View.INVISIBLE);
                        getActivity().findViewById(R.id.del).setVisibility(View.INVISIBLE);
                        ScanCodePage fragment = new ScanCodePage();
                        FragmentTransaction i = getActivity().getSupportFragmentManager().beginTransaction();
                        i.replace(R.id.scancodepage, fragment).addToBackStack(null);
                        i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        i.commit();
                        showreceipt = paymentmoney + "." + paymentmoneydot;
                        break;
                    case R.id.one:money += "1";break;
                    case R.id.two:money += "2";break;
                    case R.id.three:money += "3";break;
                    case R.id.four:money += "4";break;
                    case R.id.five:money += "5";break;
                    case R.id.six:money += "6";break;
                    case R.id.seven:money += "7";break;
                    case R.id.eight:money += "8";break;
                    case R.id.nine:money += "9";break;
                    case R.id.zero:
                        if(money == ""){}
                        else{money += "0";}break;
                    case R.id.zerozero:
                        if(money == ""){}
                        else{
                            money += "00";
                            if(moneydot.length() == 1){
                                checkdot = 1;
                            }
                        }
                        break;
                    default:break;
                }
            }
        }
        if(money.length() > 8){
            moneydot.setTextSize(15);
            moneypay.setTextSize(15);
        }else if(money.length() > 6) {
            moneydot.setTextSize(20);
            moneypay.setTextSize(20);
        }else{
            moneydot.setTextSize(30);
            moneypay.setTextSize(30);
        }
        if(money.length() <= 2){
            moneypay.setText("");
            moneydot.setText(money);
        } else{
            moneypay.setText(money.substring(0,money.length() - 2));
            moneydot.setText(money.substring(money.length() - 2,money.length()));
        }
//        double moneypayint = Double.parseDouble(paymentmoney);
    }
//   
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
