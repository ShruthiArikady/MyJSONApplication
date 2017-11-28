package com.example.shruthi.myjsonapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

public class MainActivity extends AppCompatActivity {
private String content;
private   int gCount=0;
private String cString="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       content ="{\n"+
                "     \"colors\": [\n"+
                "       {\n"+
                "         \"color\": \"black\",\n"+
                "         \"category\": \"hue\",\n"+
                "         \"type\": \"primary\",\n"+
                "         \"code\": {\n"+
                "           \"rgba\": [255,255,255,1],\n"+
                "           \"hex\": \"#000\"\n"+
                "         }\n"+
                "       },\n"+
                "       {\n"+
                "         \"color\": \"white\",\n"+
                "         \"category\": \"value\",\n"+
                "         \"code\": {\n"+
                "           \"rgba\": [0,0,0,1],\n"+
                "           \"hex\": \"#FFF\"\n"+
                "         }\n"+
                "       },\n"+
                "       {\n"+
                "         \"color\": \"red\",\n"+
                "         \"category\": \"hue\",\n"+
                "         \"type\": \"primary\",\n"+
                "         \"code\": {\n"+
                "           \"rgba\": [255,0,0,1],\n"+
                "           \"hex\": \"#FF0\"\n"+
                "         }\n"+
                "       },\n"+
                "       {\n"+
                "         \"color\": \"blue\",\n"+
                "         \"category\": \"hue\",\n"+
                "         \"type\": \"primary\",\n"+
                "         \"code\": {\n"+
                "           \"rgba\": [0,0,255,1],\n"+
                "           \"hex\": \"#00F\"\n"+
                "         }\n"+
                "       },\n"+
                "       {\n"+
                "         \"color\": \"yellow\",\n"+
                "         \"category\": \"hue\",\n"+
                "         \"type\": \"primary\",\n"+
                "         \"code\": {\n"+
                "           \"rgba\": [255,255,0,1],\n"+
                "           \"hex\": \"#FF0\"\n"+
                "         }\n"+
                "       },\n"+
                "       {\n"+
                "         \"color\": \"green\",\n"+
                "         \"category\": \"hue\",\n"+
                "         \"type\": \"secondary\",\n"+
                "         \"code\": {\n"+
                "           \"rgba\": [0,255,0,1],\n"+
                "           \"hex\": \"#0F0\"\n"+
                "         }\n"+
                "       }\n"+
                "     ]\n"+
                "   }";
          readJson();
    }

    private void readJson() {

        try {
            JSONObject jobj = (JSONObject) new JSONTokener(content).nextValue();
            JSONArray jsonArray = (JSONArray) jobj.getJSONArray("colors");
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonelem = (JSONObject) jsonArray.get(i);
                JSONObject jcode =(JSONObject) jsonelem.getJSONObject("code");
                JSONArray jrgba= (JSONArray) jcode.getJSONArray("rgba");
                if(jrgba.getInt(1)==255)
                {
                    gCount +=1;
                    cString += jsonelem.getString("color") + "-";
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void count(View view) {

        TextView txtView = (TextView) findViewById(R.id.txt_text);
        txtView.setText("Number of Greens : "+ gCount);

    }

    public void list(View view)
    {
        TextView txtview = (TextView) findViewById(R.id.txt_text);
        txtview.setText("color field" + cString);
    }

    public void modify(View view) {

        try {
            JSONObject jsonA = (JSONObject) new JSONTokener(content).nextValue();

            JSONArray jsonArray = (JSONArray) jsonA.getJSONArray("colors");

            JSONObject newJson = new JSONObject();

            newJson.put("color","orange");
            newJson.put("category","hue");

            JSONObject newRgba = new JSONObject();
            JSONArray newarr = new JSONArray();
            newarr.put(255);
            newarr.put(165);
            newarr.put(0);
            newarr.put(1);
            newRgba.put("rgba",newarr);
            newRgba.put("hex","#FA0");

            newJson.put("code",newRgba);

            jsonArray.put(newJson);

            JSONObject jsonnewMain = new JSONObject();
            jsonnewMain.put("colors",jsonArray);

            String result = jsonnewMain.toString(2);

            TextView txtView = (TextView) findViewById(R.id.txt_text);
            txtView.setText(result);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
