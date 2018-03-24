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
                    for(int i = 1;i < 5;i++){
                        Document doc = Jsoup.connect("https://www.qiushibaike.com/8hr/page/" + i + "/").get();
                        Elements userDetails = doc.select(".author").select(".clearfix");
                        Elements contents = doc.select("div.content");
                        for(int j = 0;j < contents.size();j++){
                            String nameUrl = "http:" + userDetails.get(j).select("img").attr("src");
                            String nameText = userDetails.get(j).select("img").attr("alt");
                            Log.d("test1", nameUrl);
                            String content = contents.get(j).select("span").text();
//                            Log.d("test", content );
                            Joke joke = new Joke(nameText,nameUrl,content);
                            jokeList.add(joke);
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