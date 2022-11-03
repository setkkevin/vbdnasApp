package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.AccountCreation;
import com.example.myapplication.BackgroundColor;
import com.example.myapplication.ChangePassword;
import com.example.myapplication.ChangeUsername;
import com.example.myapplication.Garages;
import com.example.myapplication.ProfileCreation;
import com.example.myapplication.R;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.MyUtils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Garages#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    MyUtils myUtils;
    ImageView viewim;
    TextView textView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment momos.
     */
    // TODO: Rename and change types and number of parameters
    public static Garages newInstance(String param1, String param2) {
        Garages fragment = new Garages();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_garages, container, false);
       // Button btn=view.findViewById(R.id.)
        //myUtils = new MyUtils(getApplicationContext());
           viewim = view.findViewById(R.id.imageView5);
           textView = view.findViewById(R.id.textView5);
        myUtils = new MyUtils(getContext());
        String username= myUtils.getUsername();
        String image = myUtils.getBgImage() ;

        String s = myUtils.getUsername();
        textView.setText("Welcome   "+s);

        if(!username.equals("")) {
            loadImage(username);
        }
        try {
            displayImage(myUtils.getBgImage(), viewim);
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;

//        case R.id.changeUsername:
//        Intent intent5=new Intent(getContext(), ChangeUsername.class);
//        startActivity(intent5);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    public void loadImage(String username){
        String url= AppConstants.BASE_URL+"getImageById.php";
       // RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //iyo byaciyemo
                Log.d("DATA",response);
                myUtils.saveBgImage(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fail",error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params=new HashMap<String,String>();
                params.put("username",username);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 0;
            }

            @Override
            public int getCurrentRetryCount() {
                return 0;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(stringRequest);
    }
    public void displayImage(String image, ImageView viewim){
        Picasso.get()
                .load(image)
                .into(viewim);

    }



}