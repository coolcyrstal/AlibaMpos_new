package com.example.chayenjr.alibampos;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class StartPayment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private View view;
    // private OnFragmentInteractionListener mListener;

    public StartPayment() {
        // Required empty public constructor
    }

    public static StartPayment newInstance(String param1, String param2) {
        StartPayment fragment = new StartPayment();
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
        View view = inflater.inflate(R.layout.fragment_start_payment, container, false);
        View gopaymentbutton = view.findViewById(R.id.startpaymentbutton);
        final Button button = (Button)view.findViewById(R.id.startpaymentbutton);
        gopaymentbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                button.setText("go go go ---------");
                buttonOnClickGoPayment();
//                removeView();
            }
        });

        return view;
    }

    public void buttonOnClickGoPayment(){
        getActivity().findViewById(R.id.startpaymentbutton).setVisibility(View.INVISIBLE);
        MoneyValue fragment = new MoneyValue();
        FragmentTransaction i = getActivity().getSupportFragmentManager().beginTransaction();
        i.replace(R.id.moneypay, fragment).addToBackStack(null);
        i.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        i.commit();
    }

    public void removeView(){
//        getActivity().findViewById(R.id.startpaymentbutton).setVisibility(View.INVISIBLE);
//        getActivity().findViewById(R.id.textpaymentPage).setVisibility(View.INVISIBLE);
//        getFragmentManager().popBackStack();
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
