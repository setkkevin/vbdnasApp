package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.adapters.GarageAdapter;
import com.example.myapplication.models.Garage;
import com.example.myapplication.utils.AppConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GarageList extends AppCompatActivity  {
    String TAG = "GarageList";
    RecyclerView recyclerView;
    GarageAdapter recyclerAdapter;
    List<Garage> garageList;
    SearchView searchView;
    double latitude = 0, longitude = 0;
    private LocationManager locationManager;
    public String garagePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garage_list);
        //  Log.i(TAG,"--onCreate--");
        //searchView= findViewById(R.id.garageSearch);
        garageList = new ArrayList<Garage>();
        recyclerView = findViewById(R.id.recyclerView);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(GarageList.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(GarageList.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(GarageList.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
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

    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.nav_menu,menu);
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

       return super.onCreateOptionsMenu(menu);
   }

//        public void setGarages(){
//            garageList.add(new Garage("Magerwa","0785685585",45));
//            garageList.add(new Garage("Magerwa","0785685585",45));
//   }
    public void getGaragesFromServer(double latitude, double longitude) {
        String url = AppConstants.BASE_URL +"getGarage.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
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
                        //Toast.makeText(getApplicationContext(), "=jjjj=" + garageName, Toast.LENGTH_LONG).show();
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