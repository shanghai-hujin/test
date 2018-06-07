package com.example.hasee.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author TT
 * @date 2017/9/21
 */

public class MovieDataBean implements Serializable {

    /**
     * count : 20
     * start : 20
     * total : 24
     * subjects : [{"rating":{"max":10,"average":6.4,"stars":"35","min":0},"genres":["剧情","传记","历史"],"title":"青年马克思","casts":[{"alt":"https://movie.douban.com/celebrity/1014063/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22970.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22970.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22970.webp"},"name":"奥古斯特·迪尔","id":"1014063"},{"alt":"https://movie.douban.com/celebrity/1387917/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1517306102.13.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1517306102.13.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1517306102.13.webp"},"name":"史特凡·柯纳斯克","id":"1387917"},{"alt":"https://movie.douban.com/celebrity/1341743/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406537335.62.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406537335.62.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406537335.62.webp"},"name":"薇姬·克里普斯","id":"1341743"}],"collect_count":3696,"original_title":"Le jeune Karl Marx","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1294186/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484919465.79.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484919465.79.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484919465.79.webp"},"name":"哈乌·佩克","id":"1294186"}],"year":"2017","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521271645.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521271645.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521271645.webp"},"alt":"https://movie.douban.com/subject/5330387/","id":"5330387"},{"rating":{"max":10,"average":6.9,"stars":"35","min":0},"genres":["剧情","喜剧"],"title":"荒城纪","casts":[{"alt":"https://movie.douban.com/celebrity/1391304/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1523245970.11.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1523245970.11.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1523245970.11.webp"},"name":"李畅","id":"1391304"},{"alt":"https://movie.douban.com/celebrity/1391305/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1523245975.99.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1523245975.99.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1523245975.99.webp"},"name":"郝星棋","id":"1391305"},{"alt":"https://movie.douban.com/celebrity/1274429/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1524024240.83.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1524024240.83.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1524024240.83.webp"},"name":"斯琴高娃","id":"1274429"}],"collect_count":3728,"original_title":"荒城纪","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1316281/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p35814.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p35814.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p35814.webp"},"name":"徐啸力","id":"1316281"}],"year":"2017","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521436620.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521436620.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521436620.webp"},"alt":"https://movie.douban.com/subject/30167129/","id":"30167129"},{"rating":{"max":10,"average":0,"stars":"00","min":0},"genres":["爱情","冒险"],"title":"蓝色金鱼","casts":[{"alt":"https://movie.douban.com/celebrity/1015278/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9299.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9299.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9299.webp"},"name":"朱孝天","id":"1015278"},{"alt":"https://movie.douban.com/celebrity/1314763/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p28162.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p28162.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p28162.webp"},"name":"蓝燕","id":"1314763"},{"alt":"https://movie.douban.com/celebrity/1364130/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1525685062.32.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1525685062.32.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1525685062.32.webp"},"name":"曲木古火·秋风","id":"1364130"}],"collect_count":68,"original_title":"蓝色金鱼","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1339304/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526367197.0.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526367197.0.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526367197.0.webp"},"name":"唐明智","id":"1339304"}],"year":"2018","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2523441579.webp","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2523441579.webp","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2523441579.webp"},"alt":"https://movie.douban.com/subject/30215189/","id":"30215189"},{"rating":{"max":10,"average":4.5,"stars":"25","min":0},"genres":["动作","冒险"],"title":"深海越狱","casts":[{"alt":"https://movie.douban.com/celebrity/1027149/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1003.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1003.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1003.webp"},"name":"尚格·云顿","id":"1027149"},{"alt":"https://movie.douban.com/celebrity/1040508/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p28363.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p28363.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p28363.webp"},"name":"杜夫·龙格尔","id":"1040508"},{"alt":"https://movie.douban.com/celebrity/1244826/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49916.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49916.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49916.webp"},"name":"杰思敏·沃兹","id":"1244826"}],"collect_count":1417,"original_title":"Black Water","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1379281/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1527565556.83.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1527565556.83.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1527565556.83.webp"},"name":"帕夏·帕特里基","id":"1379281"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521514516.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521514516.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521514516.webp"},"alt":"https://movie.douban.com/subject/26949264/","id":"26949264"}]
     * title : 正在上映的电影-上海
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsBean> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectsBean implements Serializable{
        /**
         * rating : {"max":10,"average":6.4,"stars":"35","min":0}
         * genres : ["剧情","传记","历史"]
         * title : 青年马克思
         * casts : [{"alt":"https://movie.douban.com/celebrity/1014063/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22970.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22970.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22970.webp"},"name":"奥古斯特·迪尔","id":"1014063"},{"alt":"https://movie.douban.com/celebrity/1387917/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1517306102.13.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1517306102.13.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1517306102.13.webp"},"name":"史特凡·柯纳斯克","id":"1387917"},{"alt":"https://movie.douban.com/celebrity/1341743/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406537335.62.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406537335.62.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406537335.62.webp"},"name":"薇姬·克里普斯","id":"1341743"}]
         * collect_count : 3696
         * original_title : Le jeune Karl Marx
         * subtype : movie
         * directors : [{"alt":"https://movie.douban.com/celebrity/1294186/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484919465.79.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484919465.79.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484919465.79.webp"},"name":"哈乌·佩克","id":"1294186"}]
         * year : 2017
         * images : {"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521271645.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521271645.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521271645.webp"}
         * alt : https://movie.douban.com/subject/5330387/
         * id : 5330387
         */

        private RatingBean rating;
        private String title;
        private int collect_count;
        private String original_title;
        private String subtype;
        private String year;
        private ImagesBean images;
        private String alt;
        private String id;
        private List<String> genres;
        private List<CastsBean> casts;
        private List<DirectorsBean> directors;

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }

        public List<DirectorsBean> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsBean> directors) {
            this.directors = directors;
        }

        public static class RatingBean {
            /**
             * max : 10
             * average : 6.4
             * stars : 35
             * min : 0
             */

            private int max;
            private double average;
            private String stars;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class ImagesBean {
            /**
             * small : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521271645.webp
             * large : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521271645.webp
             * medium : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521271645.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class CastsBean implements Serializable{
            /**
             * alt : https://movie.douban.com/celebrity/1014063/
             * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22970.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22970.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22970.webp"}
             * name : 奥古斯特·迪尔
             * id : 1014063
             */

            private String alt;
            private AvatarsBean avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsBean getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBean avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBean {
                /**
                 * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22970.webp
                 * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22970.webp
                 * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p22970.webp
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }

        public static class DirectorsBean implements Serializable{
            /**
             * alt : https://movie.douban.com/celebrity/1294186/
             * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484919465.79.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484919465.79.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484919465.79.webp"}
             * name : 哈乌·佩克
             * id : 1294186
             */

            private String alt;
            private AvatarsBeanX avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsBeanX getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBeanX avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBeanX {
                /**
                 * small : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484919465.79.webp
                 * large : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484919465.79.webp
                 * medium : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484919465.79.webp
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }
    }
}
