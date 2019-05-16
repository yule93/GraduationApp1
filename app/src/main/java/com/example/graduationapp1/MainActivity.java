package com.example.graduationapp1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "UsingThingspeakAPI";
    private static final String THINGSPEAK_CHANNEL_ID = "746581";
    private static final String THINGSPEAK_API_KEY = "BY2LPBO0F3OAEBZT"; //GARBAGE KEY
    private static final String THINGSPEAK_API_KEY_STRING = "BY2LPBO0F3OAEBZT";     // 띵스피크 채널 Read API 키
    /* Be sure to use the correct fields for your own app*/
    private static final String THINGSPEAK_FIELD1 = "field1";
    private static final String THINGSPEAK_FIELD2 = "field2";
    private static final String THINGSPEAK_FIELD3 = "field3";
    private static final String THINGSPEAK_UPDATE_URL = "https://api.thingspeak.com/update?";
    private static final String THINGSPEAK_CHANNEL_URL = "https://api.thingspeak.com/channels/";
    private static final String THINGSPEAK_FEEDS_LAST = "/feeds/last?";
    TextView t1,t2, t3, t4, t5;
    ImageView im1, im2, im3;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.textView);       // textView라는 id를 가진 텍스트뷰를 지정
        t2=(TextView)findViewById(R.id.textView1);      // textView1라는 id를 가진 텍스트뷰를 지정
        t3=(TextView)findViewById(R.id.textView2);      // textView2라는 id를 가진 텍스트뷰를 지정
        t4=(TextView)findViewById(R.id.textView3);      // textView3라는 id를 가진 텍스트뷰를 지정
        t5 = (TextView)findViewById(R.id.textView4);

        b1=(Button) findViewById(R.id.button);      // Synchronizing(동기화)라는 텍스트를 가진 버튼 지정
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       // b1을 클릭했을 때
                try {
                    new FetchThingspeakTask().execute();        // 정상 작동시 FetchThingspeakTask() 함수 작동
                }
                catch(Exception e){
                    Log.e("ERROR", e.getMessage(), e);      // 비정상일시 에러 메세지 출력
                }
            }
        });

        im1 = (ImageView)findViewById(R.id.plus1);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    class FetchThingspeakTask extends AsyncTask<Void, Void, String> {       // 띵스피크 값 받아오는 함수
        protected void onPreExecute() {
            t1.setText("Fetching Data from Server.Please Wait...");
            Log.i("one", "error1");
        }
        protected String doInBackground(Void... urls) {
            try {
                Log.i("one", "error2");
                URL url = //new URL("https://api.thingspeak.com/channels/746581/fields/1.json?results=1");
                        new URL(THINGSPEAK_CHANNEL_URL + THINGSPEAK_CHANNEL_ID +
                                THINGSPEAK_FEEDS_LAST + THINGSPEAK_API_KEY_STRING + "=" +
                                THINGSPEAK_API_KEY + "");       // 값을 받아올 채널의 url
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                Log.i("one", "error3");
                try {
                    Log.i("one", "error4");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    Log.i("one", "error5");
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    Log.i("one", "error6");
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
        protected void onPostExecute(String response) {
            if(response == null) {
                Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                JSONObject channel = (JSONObject) new JSONTokener(response).nextValue();
                String v1 = channel.getString(THINGSPEAK_FIELD1);       // field1 값을 받아와 저장
                String v2 = channel.getString(THINGSPEAK_FIELD2);       // field2 값을 받아와 저장
                String v3 = channel.getString(THINGSPEAK_FIELD3);       // field3 값을 받아와 저장

                //t2.setText(v1+" g");        // t2에 field1 값 표기
                t3.setText(v1+" g");        // t3에 field2 값 표기
                t4.setText(v2 + " g");       // t4에 field3 값 표기
                t5.setText(v3 +" g");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
