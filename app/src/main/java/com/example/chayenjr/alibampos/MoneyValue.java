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
    private String paymentmoney = "";
    private PlusOneButton mPlusOneButton;

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
        Button dot= (Button)view.findViewById(R.id.dot);
        dot.setOnClickListener(getButtonOnClickListener());

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
        switch (v.getId()){
            case R.id.del:
                paymentmoney = moneypay.getText().toString().substring(0, moneypay.getText().length() - 1);
                break;
            case R.id.ac:
                paymentmoney = "";
                break;
            case R.id.pay:
                ScanCodePage fragment = new ScanCodePage();
                FragmentTransaction i = getActivity().getSupportFragmentManager().beginTransaction();
                i.replace(R.id.scancodepage, fragment).addToBackStack(null);
                i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                i.commit();
                break;
            case R.id.one:
                paymentmoney += "1";
                break;
            case R.id.two:
                paymentmoney += "2";
                break;
            case R.id.three:
                paymentmoney += "3";
                break;
            case R.id.four:
                paymentmoney += "4";
                break;
            case R.id.five:
                paymentmoney += "5";
                break;
            case R.id.six:
                paymentmoney += "6";
                break;
            case R.id.seven:
                paymentmoney += "7";
                break;
            case R.id.eight:
                paymentmoney += "8";
                break;
            case R.id.nine:
                paymentmoney += "9";
                break;
            case R.id.zero:
                paymentmoney += "0";
                break;
            case R.id.dot:
                paymentmoney += ".";
                break;
            default:break;
        }
        moneypay.setText(paymentmoney);
    }
//   
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
