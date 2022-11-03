package com.example.myapplication.fragments;

import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.utils.AppConstants;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.AccountCreation;
//import com.example.myapplication.GarageList;
import com.example.myapplication.Garages;
import com.example.myapplication.MainActivity;
import com.example.myapplication.ProfileCreation;
import com.example.myapplication.R;
import com.example.myapplication.adapters.GarageAdapter;
import com.example.myapplication.models.Garage;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.MyUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GarageListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String TAG = "GarageList";
    RecyclerView recyclerView;
    GarageAdapter recyclerAdapter;
    List<Garage> garageList;
    double latitude = 0, longitude = 0;
    private LocationManager locationManager;
    public String garagePhone;
    MyUtils myUtils;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GarageListFragment() {
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
        View view=inflater.inflate(R.layout.garage_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        garageList = new ArrayList<Garage>();
        locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                try {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    Log.d("dd", latitude + " " + longitude);
                    // Toast.makeText(getApplicationContext(),"Lat "+latitude+" Long "+longitude,Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        getGaragesFromServer(latitude,longitude);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
//my googleMap
/*
Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:a,b?z=9"));
 startActivity(intent);

*/
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu,inflater);
            inflater.inflate(R.menu.nav_menu,menu);
    MenuItem menuItem = menu.findItem(R.id.search);
    android.widget.SearchView searchView = (android.widget.SearchView)  menuItem.getActionView();
    searchView.setQueryHint("Type here");
    searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {

            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            List<Garage> filteredList = new ArrayList<>();
            for (Garage item : garageList) {
                if (item.getName().toLowerCase().contains(s.toLowerCase())) {
                    filteredList.add(item);
                }
            }
            recyclerAdapter.filterList(filteredList);
            return false;
        }
    });
}



    public void getGaragesFromServer(double latitude, double longitude) {
        String url = AppConstants.BASE_URL+"getGarage.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //iyo byaciyemo
                Log.d("DATA", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("garages");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject garageObject = jsonArray.getJSONObject(i);
                        String id = garageObject.getString("id");
                        String garageName = garageObject.getString("garagename");
                        garagePhone = garageObject.getString("telephone");
                        String photo = garageObject.getString("photo");
                        long distance = garageObject.getLong("distance");
                        long lat = garageObject.getLong("latitude");
                        long log = garageObject.getLong("longitude");
                        //Toast.makeText(horder.getApplicationContext(), "==" + garageName, Toast.LENGTH_LONG).show();
                        if (photo.equals("")) {
                            garageList.add(new Garage(garageName, garagePhone, distance, "Empty.png",lat,log));
                        }else{
                            garageList.add(new Garage(garageName, garagePhone, distance, photo,lat,log));
                        }
                    }
                    recyclerAdapter = new GarageAdapter(garageList);
                    recyclerView.setAdapter(recyclerAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fail", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("longitude", Double.toString(longitude));
                params.put("latitude", Double.toString(latitude));
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

}