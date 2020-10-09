package com.example.enamul.qrcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;




public class LoginApi extends AppCompatActivity {

    Button submit;
    EditText name1,name2;
    ProgressBar progress_circular;
    TextView aaa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_api);

        name1=findViewById(R.id.name1);
        name2=findViewById(R.id.name2);
        submit=findViewById(R.id.submit);
        progress_circular=findViewById(R.id.progress_circular);
        aaa=findViewById(R.id.aaa);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=name1.getText().toString().trim();
                String password=name2.getText().toString().trim();
                viewProfile();
            }
        });

    }

    private void viewProfile() {


        JSONObject js = new JSONObject();
        try {
            js.put("LoginID","Admin");
            js.put("Password","admin@123");
        } catch (JSONException e) {
            e.printStackTrace();
        }



        final String url = "http://linkerpwebapi.ascdevs2.com/api/Account/Authenticate";
        // Make request for JSONObject
        progress_circular.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("aaa", response.toString());

                        try {
                            String name=response.getString("data");
                            Log.e("aaa",name);
                            aaa.setText(name);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(getApplicationContext(),"aaa",Toast.LENGTH_SHORT).show();

                        progress_circular.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              Log.d("aaa", "Error: " + error.getMessage());
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        // Adding request to request queue
        Volley.newRequestQueue(this).add(jsonObjReq);

    }




}