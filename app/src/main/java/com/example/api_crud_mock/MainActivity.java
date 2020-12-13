package com.example.api_crud_mock;

import android.service.autofill.Dataset;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List listItem;
    ArrayAdapter arrayAdapter;
    List<Object> listobject;
    Button btnClick;
    Button btnClickArray;
    Button btnDel;
    Button btnUpdate;
    EditText textFIRSTNAME;
    EditText textLASTNAME;
    EditText textGENDER;
    EditText textSALARY;
    EditText textUpdateDelete;
    TextView tvDisplay;
    String url = "https://5fcecccf3e19cc00167c62d9.mockapi.io/users/minhtri/users";
    ListView listView;
    long index;

   // String url = "http://5b7e85ceadf2070014bfa383.mockapi.io/users/21";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        btnClick = (Button) findViewById(R.id.btnClick);
        listView=findViewById(R.id.listview1);
        btnClickArray=(Button) findViewById(R.id.btnClickArray);
        btnDel=(Button) findViewById(R.id.btnDel);
        btnUpdate=(Button) findViewById(R.id.btnUpdate);
        textFIRSTNAME=findViewById(R.id.editFIRSTNAME);
        textLASTNAME=findViewById(R.id.editLASTNAME);
        textGENDER=findViewById(R.id.editGENDER);
        textSALARY=findViewById(R.id.editSALARY);
        textUpdateDelete=findViewById(R.id.editTextIDEdit);
        btnClickArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GetData(url);
                //GetJson(url);
                GetArrayJson(url);
                //PostApi(url);
                //PutApi(url);
               // DeleteApi(url);
            }
        });
        //Create New
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostApi(url);

            }
        });
        //Delete
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteApi(url);

            }
        });
        //Update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PutApi(url);


            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



            }
        });


    }
    private void GetData(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response. toString(), Toast.LENGTH_SHORT).show();
              //  tvDisplay.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error make by API server!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void GetJson(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            tvDisplay.setText(response.getString("LASTNAME").toString());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by get JsonObject...", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
    private void GetArrayJson(String url){
        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject object = (JSONObject) response.get(i);
                       // Toast.makeText(MainActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
                        arrayAdapter.add(object.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
            }
        });

       listView.setAdapter(arrayAdapter);

      RequestQueue requestQueue = Volley.newRequestQueue(this);
       requestQueue.add(jsonArrayRequest);

    }
    private void PostApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                //Dữ liệu mẫu
             /*   params.put("FIRSTNAME", "Lâm");
                params.put("LASTNAME", "Tấn Hào");
                params.put("GENDER", "Male");
                params.put("SALARY", "10000");*/
                //Dữ liệu lấy vào từ edit text
                params.put("FIRSTNAME",textFIRSTNAME.getText().toString());
                params.put("LASTNAME", textLASTNAME.getText().toString());
                params.put("GENDER", textGENDER.getText().toString());
                params.put("SALARY", textSALARY.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void PutApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT,
                url + '/' + textUpdateDelete.getText(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
               /* params.put("FIRSTNAME", "Lâm");
                params.put("LASTNAME", "Tấn Xu");
                params.put("GENDER", "Male");
                params.put("SALARY", "100000");*/
                params.put("FIRSTNAME",textFIRSTNAME.getText().toString());
                params.put("LASTNAME", textLASTNAME.getText().toString());
                params.put("GENDER", textGENDER.getText().toString());
                params.put("SALARY", textSALARY.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        arrayAdapter.notifyDataSetChanged();
    }
    private void DeleteApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + textUpdateDelete.getText(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        arrayAdapter.notifyDataSetChanged();
    }

}

