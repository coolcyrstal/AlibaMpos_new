package com.example.chayenjr.alibampos;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipePage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecipePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipePage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RecipePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipePage.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipePage newInstance(String param1, String param2) {
        RecipePage fragment = new RecipePage();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_page, container, false);
        TextView moneypay = (TextView)view.findViewById(R.id.paymoney);
        String showvalue = MoneyValue.showreceipt;
        moneypay.setText("e-Receipt for amount THB" + showvalue);
        Button homebutton= (Button)view.findViewById(R.id.homebutton);
        homebutton.setOnClickListener(getButtonOnClickListener());
        return view;
    }

    private View.OnClickListener getButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClickGoHome(v);
            }
        };
    }

    public void buttonOnClickGoHome(View v){
        StartPayment fragment = new StartPayment();
        FragmentTransaction i = getActivity().getSupportFragmentManager().beginTransaction();
        i.replace(R.id.startpaymentPage, fragment).addToBackStack(null);
        i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        i.commit();
        getActivity().findViewById(R.id.signin_button).setVisibility(View.VISIBLE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
