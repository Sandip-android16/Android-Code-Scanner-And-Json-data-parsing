# QRCode
Android All Type Of Code Scanner in APP.
Android  Json Data Parsing and  set mediatype value  is Json using volley api.
Android Volley APis Mediatype Set.

This Code Of mediaType Of RAW in  JSONFile

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


