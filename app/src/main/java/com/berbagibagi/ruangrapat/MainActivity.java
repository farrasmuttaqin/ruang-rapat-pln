package com.berbagibagi.ruangrapat;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    private static String URL_ALL_HASIL_RAPAT = "http://ruangrapat.000webhostapp.com/JSON/api_all_hasil_rapat.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        loadRecyclerViewData();
    }

    private void loadRecyclerViewData(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Hasil rapat sedang di proses");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ALL_HASIL_RAPAT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("records");

                            JSONObject object;
                            String ruangA,namaA,tanggalA,waktuA,hasilA,perihalA,notulisA,tampungHasil;

                            int jumlah,i;
                            for (jumlah = 0; jumlah < jsonArray.length(); jumlah++) {
                                for (i = 0; i < jsonObject.length(); i++) {

                                    object = jsonArray.getJSONObject(jumlah);
                                    ruangA = "Ruangan : "+object.getString("nama_ruang").trim();
                                    namaA = "Nama : "+object.getString("nama").trim();
                                    tanggalA = "Tanggal : "+object.getString("tanggal").trim();
                                    tampungHasil = object.getString("hasil").trim();
                                    waktuA = "Waktu : "+object.getString("mulai").trim()+" - "+object.getString("selesai").trim();;
                                    hasilA = "Hasil : "+object.getString("hasil").trim();
                                    perihalA = "Perihal : "+object.getString("prihal").trim();
                                    notulisA = "Notulis : "+object.getString("notulis").trim();

                                    if (!object.getString("prihal").trim().equals("null")){
                                        ListItem item = new ListItem(ruangA,namaA,tanggalA,waktuA,hasilA,perihalA,notulisA,tampungHasil);
                                        listItems.add(item);
                                    }
                                }
                            }

                            adapter = new MyAdapter(listItems,getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "a", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "c = "+error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
