package com.example.hasee.bean;

import java.util.List;

/**
 * Created by HASEE on 2019/3/1.
 */

public class GanRandomBean {

    /**
     * error : false
     * results : [{"_id":"57fc40a1421aa95dd78e8ddb","createdAt":"2016-10-11T09:30:09.136Z","desc":"10-11","publishedAt":"2016-10-11T11:42:22.814Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f8o2ov8xi0j20u00u0q61.jpg","used":true,"who":"daimajia"},{"_id":"583d96f6421aa939befafacf","createdAt":"2016-11-29T22:55:50.767Z","desc":"11-30","publishedAt":"2016-11-30T11:35:55.916Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034gw1fa9dca082pj20u00u0wjc.jpg","used":true,"who":"daimajia"},{"_id":"5b6151509d21225206860f08","createdAt":"2018-08-01T14:21:04.556Z","desc":"2018-08-01","publishedAt":"2018-08-01T00:00:00.0Z","source":"api","type":"福利","url":"https://ww1.sinaimg.cn/large/0065oQSqly1ftu6gl83ewj30k80tites.jpg","used":true,"who":"lijinshan"},{"_id":"56cc6d29421aa95caa7082b9","createdAt":"2016-02-15T09:16:22.91Z","desc":"2.16","publishedAt":"2016-02-16T04:46:45.140Z","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1f1052bhjauj20f00l6q4o.jpg","used":true,"who":"张涵宇"},{"_id":"5b0d6ac0421aa97efda86560","createdAt":"2018-05-29T22:59:12.622Z","desc":"2018-06-02","publishedAt":"2018-06-22T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1frslruxdr1j30j60ok79c.jpg","used":true,"who":"lijinshanmx"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 57fc40a1421aa95dd78e8ddb
         * createdAt : 2016-10-11T09:30:09.136Z
         * desc : 10-11
         * publishedAt : 2016-10-11T11:42:22.814Z
         * source : chrome
         * type : 福利
         * url : http://ww2.sinaimg.cn/large/610dc034jw1f8o2ov8xi0j20u00u0q61.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
