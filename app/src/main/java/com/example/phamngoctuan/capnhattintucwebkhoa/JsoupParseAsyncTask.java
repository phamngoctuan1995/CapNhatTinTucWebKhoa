package com.example.phamngoctuan.capnhattintucwebkhoa;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by phamngoctuan on 14/04/2016.
 */
public class JsoupParseAsyncTask extends AsyncTask<String, Void, Document>{

    JsoupParseCallback callback;
    public JsoupParseAsyncTask(JsoupParseCallback cb)
    {
        callback = cb;
        Log.d("debug", "Init task");
    }

    @Override
    protected Document doInBackground(String... params) {
        Document doc = null;
        try {
            Log.d("debug", params[0]);
            doc = Jsoup.connect(params[0]).get();
            Log.d("debug", "Parse title: " + doc.title());
        } catch (Exception e) {
            e.printStackTrace();
            doc = null;
            Log.d("debug", "Exception doInBackground Parse: " + e.getMessage());
        }
        return doc;
    }

    @Override
    protected void onPostExecute(Document document) {
        super.onPostExecute(document);
        if (document == null)
            return;
        try {
            ArrayList<HashMap<String, String>> listNews = new ArrayList<>();
            Element div = document.body().getElementsByClass("blog_more").get(0);
            Elements list = div.child(0).children();
            Elements child;

            for (Element li:list)
            {
                HashMap<String, String> news = new HashMap<>();
                child = li.children();
                news.put("title", child.get(0).text());
                news.put("link", child.get(0).attr("href"));
                news.put("date", child.get(1).text());
                listNews.add(news);
            }

            if (listNews.size() > 0)
                callback.ParseSuccess(listNews);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.d("debug", "Exception parse onPost");
        }

    }
}
