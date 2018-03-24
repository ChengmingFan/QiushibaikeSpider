package com.example.fan.qiushibaike;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.fan.qiushibaike.adapter.JokeAdapter;
import com.example.fan.qiushibaike.bean.Joke;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Joke> jokeList;
    private JokeAdapter adapter;
    private Handler handler;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jokeList = new ArrayList<>();
        lv = (ListView)findViewById(R.id.jokes_lv);
        getJokes();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1){
                    adapter = new JokeAdapter(MainActivity.this,jokeList);
                    lv.setAdapter(adapter);
                }
            }
        };
    }
    private void getJokes(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int i = 1;i < 5;i++) {

//                        Document doc = Jsoup.connect("http://www.qiushibaike.com/8hr/page/" + i + "/").get();
//                        Elements els = doc.select("a.contentHerf");
//                        for(int j = 0;j < els.size();j++){
//                            String pictureUrl = "";
//                            String herf = els.get(j).attr("href");
//                            Log.d("test", herf);
//                            Document detail = Jsoup.connect("http://www.qiushibaike.com" + herf).get();
//                            Elements userDetails = detail.select(".author").select(".clearfix");
//                            String nameUrl = "http:" + userDetails.select("a").select("img").attr("src");
//                            Log.d("url1", nameUrl);
//                            String nameText = userDetails.select("img").attr("alt");
//                            Elements pictures = userDetails.select("div.thumb");
//                            if(!pictures.isEmpty())
//                            {
//                                pictureUrl = "http:" + pictures.select("img").attr("src");
//                            }
//                            if(pictureUrl != null){
//                                Log.d("URLDEBUG", pictureUrl);
//                            }
//                            Elements contents = detail.select("div.content");
//                            String content = contents.text();
//                            Log.d("test1", content );
//                            Joke joke = new Joke(nameText,nameUrl,content,pictureUrl);
//                            jokeList.add(joke);


//                        Document doc = Jsoup.connect("https://www.qiushibaike.com/8hr/page/" + i + "/").get();
//                        Elements userDetails = doc.select(".author").select(".clearfix");
//                        Elements contents = doc.select("div.content");
//                        for (int j = 0; j < contents.size(); j++) {
//                            String nameUrl = "http:" + userDetails.get(j).select("img").attr("src");
//                            String nameText = userDetails.get(j).select("img").attr("alt");
//                            String content = contents.get(j).select("span").text();
//                            Joke joke = new Joke(nameText, nameUrl, content,null);
//                            jokeList.add(joke);
//                        }
                        Document doc = Jsoup.connect("http://www.qiushibaike.com/8hr/page/" + i + "/").get();
                        Elements elsHot = doc.select(".article").select(".block").select(".untagged").select(".mb15").select(".typs_hot");
                        Elements elsLong = doc.select(".article").select(".block").select(".untagged").select(".mb15").select(".typs_long");
                        Elements elsRecent = doc.select(".article").select(".block").select(".untagged").select(".mb15").select(".typs_recent");
                        Elements elsOld = doc.select(".article").select(".block").select(".untagged").select(".mb15").select(".typs_old");
                        Elements els = elsHot;
                        for (int k = 0;k < 4;k++){
                            for(int j =0;j < els.size();j++){
                                String pictureUrl = null;
                                Element el = els.get(j);
                                String userName = el.select(".author").select(".clearfix").select("img").attr("alt");
                                String userImage = "http:" + el.select(".author").select(".clearfix").select("img").attr("src");
                                if(!(el.select(".thumb").isEmpty())) {
                                    pictureUrl = "http:" + el.select(".thumb").select("img").attr("src");
                                }
                                String content = el.select(".content").text();
                                Joke joke = new Joke(userName,userImage,content,pictureUrl);
                                jokeList.add(joke);
                            }
                            switch (k){
                                case 0:els = elsLong;
                                    break;
                                case 1:els = elsRecent;
                                    break;
                                case 2:els = elsOld;
                            }
                        }
                    }
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}