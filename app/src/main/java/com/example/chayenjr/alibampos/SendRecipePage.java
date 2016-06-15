package com.example.chayenjr.alibampos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SendRecipePage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SendRecipePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendRecipePage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SendRecipePage() {
        // Required empty public constructor
    }

    public static SendRecipePage newInstance(String param1, String param2) {
        SendRecipePage fragment = new SendRecipePage();
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
        getActivity().setTitle("AlipayMpos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_recipe_page, container, false);
        // Inflate the layout for this fragment
        Button scan= (Button)view.findViewById(R.id.sendrecipebutton);
        scan.setOnClickListener(getButtonOnClickListener());
        return view;
    }

    private View.OnClickListener getButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendrecipe(v);
            }
        };
    }

    public void sendrecipe(View v){
        EditText textemail = (EditText) getActivity().findViewById(R.id.textemail);
        String str = textemail.getText().toString();
        if(!(str.contains("@") && str.contains(".")) && !(str.equals(""))){
            textemail.setError("Invalid email address");
        } else if(str.equals("")){
            getActivity().findViewById(R.id.sendrecipebutton).setVisibility(View.INVISIBLE);
            RecipePage fragment = new RecipePage();
            FragmentTransaction i = getActivity().getSupportFragmentManager().beginTransaction();
            i.replace(R.id.recipepage, fragment).addToBackStack(null);
            i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            i.commit();
            LoginPage.countPage = 5;
        }
        else{
            sendEmail();
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    protected void sendEmail() {
        Log.i("Send email", "");

        String[] TO = {((EditText) getActivity().findViewById(R.id.textemail)).getText().toString()};
//        String[] CC = {"xyz@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Test Sending Email");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "e-Receipt for amount THB" + MoneyValue.showreceipt);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            getActivity().finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
