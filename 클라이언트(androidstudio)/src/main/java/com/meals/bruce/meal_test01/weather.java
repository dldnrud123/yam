package com.meals.bruce.meal_test01;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Bruce on 2018-01-16.
 */

public class weather{
    String word[] = new String[3];
    int id;

    public void parsing() {
        try {
            String path = "http://weather.naver.com/rgn/townWetr.nhn?naverRgnCd=03220113";
            Document document = Jsoup.connect(path).get();
            Elements elements = document.select("div.fl em");
            Log.d("elements:", elements.toString());
            Element targetElement = elements.get(0);
            String tag = targetElement.toString().substring(5);
            String line[] = StringUtils.split(tag, " <", 2);
            word[0] = line[0];
            word[1] = targetElement.select("span").text();
            word[2] = targetElement.select("strong").text();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getWeather() {
        //맑음,구름조금,구름많음,흐림,비,눈
        String[] list = {"맑음", "흐림", "구름조금", "구름많음", "비", "눈","눈비"};
        int[] idList = {R.drawable.sunny, R.drawable.cloudy, R.drawable.some_clody, R.drawable.some_clody, R.drawable.rainy, R.drawable.snow, R.drawable.rain_snow};
        int index;

        for (int i = 0 ; i<list.length; i++) {
            if (list[i].equals(word[2])) {
                id = idList[i];
                break;
            }
        }
        return id;
    }

    public String getTmp(){
        Log.d("getTmp:",word[0]+word[1]);
        if(word[0]==null){
            return "offline";
        }else {
            return word[0] + word[1];
        }
    }
}
