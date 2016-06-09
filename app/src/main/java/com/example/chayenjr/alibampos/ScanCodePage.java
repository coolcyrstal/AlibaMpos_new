package com.example.chayenjr.alibampos;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScanCodePage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScanCodePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScanCodePage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";

    private OnFragmentInteractionListener mListener;

    public ScanCodePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScanCodePage.
     */
    // TODO: Rename and change types and number of parameters
    public static ScanCodePage newInstance(String param1, String param2) {
        ScanCodePage fragment = new ScanCodePage();
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
        getActivity().setTitle("Scan QR|Bar Code");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan_code_page, container, false);
        // Inflate the layout for this fragment
        Button scanbar= (Button)view.findViewById(R.id.goscanbarcode);
        scanbar.setOnClickListener(getButtonOnClickListener());
        Button scanqr= (Button)view.findViewById(R.id.goscanqrcode);
        scanqr.setOnClickListener(getButtonOnClickListener());
        return view;
    }

    private View.OnClickListener getButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode(v);
            }
        };
    }

    public void scanCode(View v){
        SendRecipePage fragment = new SendRecipePage();
        FragmentTransaction i = getActivity().getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.goscanbarcode:
                try {
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
                    startActivityForResult(intent, 0);
                }catch (ActivityNotFoundException e){
                    showDialog((AppCompatActivity) getActivity(), "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
                }
                getActivity().findViewById(R.id.goscanbarcode).setVisibility(View.INVISIBLE);
                getActivity().findViewById(R.id.goscanqrcode).setVisibility(View.INVISIBLE);
                i.replace(R.id.sendrecipepage, fragment).addToBackStack(null);
                i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                i.commit();
                LoginPage.countPage = 4;
                break;
            case R.id.goscanqrcode:
                try {
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                    startActivityForResult(intent, 0);
                }catch (ActivityNotFoundException e){
                    showDialog((AppCompatActivity) getActivity(), "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
                }
                getActivity().findViewById(R.id.goscanbarcode).setVisibility(View.INVISIBLE);
                getActivity().findViewById(R.id.goscanqrcode).setVisibility(View.INVISIBLE);
                i.replace(R.id.sendrecipepage, fragment).addToBackStack(null);
                i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                i.commit();
                LoginPage.countPage = 4;
                break;
        }
    }

    private static AlertDialog showDialog(final AppCompatActivity act, CharSequence title,
                                          CharSequence message, CharSequence buttonYes, CharSequence buttonNo){
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title).setMessage(message).setPositiveButton(buttonYes, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                }
            }
        }).setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        return downloadDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == 0) {
            if(resultCode == getActivity().RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Toast.makeText(getActivity(), "Content:" + contents + " Format:" + format, Toast.LENGTH_LONG).show();
            }
        }
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
