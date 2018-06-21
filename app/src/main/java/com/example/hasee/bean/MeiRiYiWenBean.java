package com.example.hasee.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/6/20 14:21
 */

public class MeiRiYiWenBean implements Serializable {

    /**
     * data : [{"code":200,"search":"http://www.bing.com/search?q=%E5%A4%8F%E8%87%B3&form=hpcapt&mkt=zh-cn","url":"https://www.bing.com/az/hprichbg/rb/lotus_ZH-CN12081917194_1920x1080.jpg","url2":"//www.bing.com/az/hprichbg/rb/lotus_ZH-CN12081917194_1366x768.jpg","image":"https://www.bing.com/az/hprichbg/rb/lotus_ZH-CN12081917194_1920x1080.jpg","title":"啊，无与伦比的夏日","attribute":"今日夏至","info":"今日夏至","story":"强烈的日光和放肆的雷声无不向我们宣告着夏天的来临，而莲花就盛开在这炎热的季节。你看那粉里透着白的莲花，纹路清晰，大而碧绿的荷叶为莲花遮挡着太阳，它们舒适的躺在荷塘里，成为这个夏至最美的风景。喜欢夏天的你，要不要来赏一场荷花盛宴？","date":"20180621","provider":"© Adam Jones/DanitaDelimont.com -- All rights reserved","Continent":"非洲","Country":"","City":"","Longitude":"","Latitude":""},{"title":"热吗","info":"夏至面","image":"http://s4.cn.bing.net/th?id=OJ.0uYgdQO400UHiA&pid=MSNJVFeeds","story":"夏至面是汉族风俗，流行于全国大部分地区，指夏至日吃凉面的习俗。\u201c冬至饺子夏至面\u201d，好吃的北京人在夏至这天讲究吃面。民俗回归的当下，凡是跟夏至节令沾边儿的吃食都卖得不错。夏至这天，北京各家面馆人气很旺，各种面条都很\u201c畅销\u201d。热吗？来北京吃碗炸酱面过夏天吧！","search":"/Search?q=%e5%a4%8f%e8%87%b3%e9%9d%a2&amp;mkt=zh-cn&amp;FORM=BNLH\" h=\"ID=SERP,5020.2"},{"title":"不夜城的夏日狂欢","info":"漠河夏至节","image":"http://s.cn.bing.net/th?id=OJ.VCiP00RArydOqw&pid=MSNJVFeeds","story":"漠河夏至节是漠河北极村独有的一个节日。按照北极村人的习俗，每年夏至这一天，北极村的人们都会自发来到黑龙江边，点起篝火，边跳舞边等待北极光的出现。漠河县是中国纬度最高的县份，由于纬度高，使漠河地区在夏季产生极昼现象，时常有北极光出现，因此人们称漠河县为\u201c中国的不夜城\u201d、\u201c极光城\u201d。","search":"/Search?q=%e6%bc%a0%e6%b2%b3%e5%a4%8f%e8%87%b3%e8%8a%82&amp;mkt=zh-cn&amp;FORM=BNLH\" h=\"ID=SERP,5021.2"}]
     * time : 20180621
     */

    private String time;
    private List<DataBean> data;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * code : 200
         * search : http://www.bing.com/search?q=%E5%A4%8F%E8%87%B3&form=hpcapt&mkt=zh-cn
         * url : https://www.bing.com/az/hprichbg/rb/lotus_ZH-CN12081917194_1920x1080.jpg
         * url2 : //www.bing.com/az/hprichbg/rb/lotus_ZH-CN12081917194_1366x768.jpg
         * image : https://www.bing.com/az/hprichbg/rb/lotus_ZH-CN12081917194_1920x1080.jpg
         * title : 啊，无与伦比的夏日
         * attribute : 今日夏至
         * info : 今日夏至
         * story : 强烈的日光和放肆的雷声无不向我们宣告着夏天的来临，而莲花就盛开在这炎热的季节。你看那粉里透着白的莲花，纹路清晰，大而碧绿的荷叶为莲花遮挡着太阳，它们舒适的躺在荷塘里，成为这个夏至最美的风景。喜欢夏天的你，要不要来赏一场荷花盛宴？
         * date : 20180621
         * provider : © Adam Jones/DanitaDelimont.com -- All rights reserved
         * Continent : 非洲
         * Country :
         * City :
         * Longitude :
         * Latitude :
         */

        private int code;
        private String search;
        private String url;
        private String url2;
        private String image;
        private String title;
        private String attribute;
        private String info;
        private String story;
        private String date;
        private String provider;
        private String Continent;
        private String Country;
        private String City;
        private String Longitude;
        private String Latitude;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getSearch() {
            return search;
        }

        public void setSearch(String search) {
            this.search = search;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl2() {
            return url2;
        }

        public void setUrl2(String url2) {
            this.url2 = url2;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getStory() {
            return story;
        }

        public void setStory(String story) {
            this.story = story;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }

        public String getContinent() {
            return Continent;
        }

        public void setContinent(String Continent) {
            this.Continent = Continent;
        }

        public String getCountry() {
            return Country;
        }

        public void setCountry(String Country) {
            this.Country = Country;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String Longitude) {
            this.Longitude = Longitude;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String Latitude) {
            this.Latitude = Latitude;
        }
    }
}
