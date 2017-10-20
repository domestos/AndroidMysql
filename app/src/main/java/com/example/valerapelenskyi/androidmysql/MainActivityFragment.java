package com.example.valerapelenskyi.androidmysql;

import android.os.AsyncTask;
import android.util.Log;

import com.example.valerapelenskyi.androidmysql.JsonRequest.BoxValues;
import com.example.valerapelenskyi.androidmysql.JsonRequest.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valera.pelenskyi on 20.10.17.
 */

public class MainActivityFragment extends android.app.Fragment {

    private JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCT = "product";
    String TAG_LOG ="TAG_LOG";


    public void runAsincTask(String searcheNumber) {
       new MainActivityFragmentATask().execute(searcheNumber);
    }

    class MainActivityFragmentATask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String number =  "'"+strings[0].toString()+"'";
            Log.d(TAG_LOG, "number= "+ number);
            int success ;
            JSONArray productObj = null;

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("number", number));
            // получаем продукт по HTTP запросу
            JSONObject json = jsonParser.makeHttpRequest(BoxValues.URL_SELECT_NUMEBER, "GET", params);
            Log.d(TAG_LOG, json.toString());

            try {
                success = json.getInt(TAG_SUCCESS);
                if (success ==1){
                    // Успешно получинна детальная информация о продукте
                    productObj = json.getJSONArray(TAG_PRODUCT);
                    JSONObject product = productObj.getJSONObject(0);
                    Log.d(TAG_LOG, "product= "+ product);
                   return   product.toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
         }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            MainActivity.tvShowResult.setText(s);

        }
    } //end Async Task
}
