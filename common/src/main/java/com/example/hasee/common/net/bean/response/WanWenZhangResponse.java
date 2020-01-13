package com.example.hasee.common.net.bean.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author apppp
 */
public class WanWenZhangResponse implements Serializable {

    /**
     * curPage : 1
     * datas : [{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":11479,"link":"https://www.jianshu.com/p/6c0e17f71736","niceDate":"9小时前","niceShareDate":"9小时前","origin":"","prefix":"","projectLink":"","publishTime":1578844187000,"selfVisible":0,"shareDate":1578844187000,"shareUser":"逐梦少年","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"每周一文第十二周--分布式协调工具zookeeper开山篇-编译安装与基础命令使用","type":0,"userId":29062,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":11477,"link":"https://blog.csdn.net/hfy8971613/article/details/103939085","niceDate":"14小时前","niceShareDate":"14小时前","origin":"","prefix":"","projectLink":"","publishTime":1578826738000,"selfVisible":0,"shareDate":1578826738000,"shareUser":"feiyang","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"面试官：子线程 真的不能更新UI ？","type":0,"userId":31360,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":252,"chapterName":"奇怪的Bug","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":11431,"link":"https://blog.csdn.net/llew2011/article/details/79054457","niceDate":"18小时前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1578812106000,"selfVisible":0,"shareDate":1578637681000,"shareUser":"残页","superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"Android 源码系列之通过反射解决在HuaWei手机出现Register too many Broadcast Receivers的crash","type":0,"userId":12467,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":459,"chapterName":"Activity","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":11460,"link":"https://www.jianshu.com/p/ea464eb15436","niceDate":"18小时前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1578812072000,"selfVisible":0,"shareDate":1578675806000,"shareUser":"深红骑士","superChapterId":153,"superChapterName":"framework","tags":[],"title":"Android之你真的了解View.post()原理吗？","type":0,"userId":29303,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":259,"chapterName":"ScrollView","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":11473,"link":"https://www.jianshu.com/p/c3ed4253f87e","niceDate":"18小时前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1578812049000,"selfVisible":0,"shareDate":1578753415000,"shareUser":"wangzhengyi","superChapterId":26,"superChapterName":"常用控件","tags":[],"title":"ScrollView源码分析","type":0,"userId":38889,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":424,"chapterName":"协程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":11470,"link":"https://www.jianshu.com/p/3d5acd4e08ff","niceDate":"18小时前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1578812030000,"selfVisible":0,"shareDate":1578740359000,"shareUser":"鸿洋","superChapterId":232,"superChapterName":"Kotlin","tags":[],"title":"利用Kotlin和协程实现DSL样式的网络请求","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":97,"chapterName":"音视频","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":11474,"link":"https://juejin.im/entry/5e1963eff265da3e505f0d7b","niceDate":"18小时前","niceShareDate":"23小时前","origin":"","prefix":"","projectLink":"","publishTime":1578812013000,"selfVisible":0,"shareDate":1578795566000,"shareUser":"goweii","superChapterId":95,"superChapterName":"多媒体技术","tags":[],"title":"Android音视频开发:踩一踩&ldquo;门槛&rdquo;","type":0,"userId":20382,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>这个问题其实不算一个太好的问题，但是也能考察事件的分发流程，搞清楚 Window,Activity,DecorView 在事件分发环节的调用流程。<\/p>","envelopePic":"","fresh":true,"id":11363,"link":"https://www.wanandroid.com/wenda/show/11363","niceDate":"18小时前","niceShareDate":"2020-01-04 20:53","origin":"","prefix":"","projectLink":"","publishTime":1578811948000,"selfVisible":0,"shareDate":1578142403000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问  为什么 Dialog 弹出后 Activity 就无法响应用户事件了？","type":0,"userId":2,"visible":1,"zan":14},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":11468,"link":"https://blog.csdn.net/hfy8971613/article/details/103881609","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1578737373000,"selfVisible":0,"shareDate":1578737373000,"shareUser":"feiyang","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Handler+ThreadLocal+Looper","type":0,"userId":31360,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":11464,"link":"https://juejin.im/post/5e0a0634e51d4575ef17225d","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1578726069000,"selfVisible":0,"shareDate":1578726069000,"shareUser":"黎明鸟飞去","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android Helper-软件开发工具集-&quot;神兵利器&quot;驾到","type":0,"userId":355,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":11459,"link":"https://juejin.im/post/5e17e4726fb9a030176e6352","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1578668300000,"selfVisible":0,"shareDate":1578668300000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"一文彻底搞懂Android View的绘制流程","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":11433,"link":"https://juejin.im/post/5e040b95518825126e63aee1","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1578642764000,"selfVisible":0,"shareDate":1578642764000,"shareUser":"guofudong","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Jetpack-Lifecycle","type":0,"userId":14556,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":424,"chapterName":"协程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":11423,"link":"https://juejin.im/post/5e1709cd6fb9a02fd127c3b2","niceDate":"2020-01-10 00:32","niceShareDate":"2020-01-09 19:53","origin":"","prefix":"","projectLink":"","publishTime":1578587568000,"selfVisible":0,"shareDate":1578570793000,"shareUser":"chs2018","superChapterId":232,"superChapterName":"Kotlin","tags":[],"title":"Retrofit加kotlin协程为何如此优雅","type":0,"userId":9180,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":229,"chapterName":"AOP","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":11425,"link":"https://juejin.im/post/5e153559e51d45413846f8e0","niceDate":"2020-01-10 00:32","niceShareDate":"2020-01-10 00:30","origin":"","prefix":"","projectLink":"","publishTime":1578587537000,"selfVisible":0,"shareDate":1578587408000,"shareUser":"鸿洋","superChapterId":227,"superChapterName":"注解 & 反射 & AOP","tags":[],"title":"和我一起用 ASM 实现编译期字节码织入","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":438,"chapterName":"权限","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":11426,"link":"https://www.jianshu.com/p/8d1d0b36bf8a","niceDate":"2020-01-10 00:32","niceShareDate":"2020-01-10 00:31","origin":"","prefix":"","projectLink":"","publishTime":1578587530000,"selfVisible":0,"shareDate":1578587463000,"shareUser":"鸿洋","superChapterId":153,"superChapterName":"framework","tags":[],"title":"Android 权限管理 &mdash; 只防君子不防小人","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":11439,"link":"https://mp.weixin.qq.com/s/kZ1qhrk3SOCSVk2pszyPCw","niceDate":"2020-01-10 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1578585600000,"selfVisible":0,"shareDate":1578660548000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"移动开发者的音视频基础知识","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":11443,"link":"https://mp.weixin.qq.com/s/Wgbvb7JXtfYI55jvq5LR5A","niceDate":"2020-01-10 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1578585600000,"selfVisible":0,"shareDate":1578660641000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"Google官方提供的分页加载解决方案","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"互联网侦察","chapterId":421,"chapterName":"互联网侦察","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":11451,"link":"https://mp.weixin.qq.com/s/fhvZ35Ocq4s3j2LmkXzP9A","niceDate":"2020-01-10 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1578585600000,"selfVisible":0,"shareDate":1578660860000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/421/1"}],"title":"牛逼哄哄的 Lambda 表达式，简洁优雅就是生产力！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"谷歌开发者","chapterId":415,"chapterName":"谷歌开发者","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":11452,"link":"https://mp.weixin.qq.com/s/whOyY9-rwiobbQh4yiwxXA","niceDate":"2020-01-10 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1578585600000,"selfVisible":0,"shareDate":1578660904000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"开源两段 Flutter 代码 | 开发者说&middot;DTalk","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":11424,"link":"https://juejin.im/post/5e16b001f265da5d16023d67","niceDate":"2020-01-09 22:45","niceShareDate":"2020-01-09 22:45","origin":"","prefix":"","projectLink":"","publishTime":1578581143000,"selfVisible":0,"shareDate":1578581143000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android触摸事件传递机制，你还不会吗？","type":0,"userId":611,"visible":1,"zan":0}]
     * offset : 0
     * over : false
     * pageCount : 393
     * size : 20
     * total : 7860
     */

    @SerializedName("curPage")
    private int curPage;
    @SerializedName("offset")
    private int offset;
    @SerializedName("over")
    private boolean over;
    @SerializedName("pageCount")
    private int pageCount;
    @SerializedName("size")
    private int size;
    @SerializedName("total")
    private int total;
    @SerializedName("datas")
    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean implements Serializable{
        /**
         * apkLink :
         * audit : 1
         * author :
         * chapterId : 502
         * chapterName : 自助
         * collect : false
         * courseId : 13
         * desc :
         * envelopePic :
         * fresh : true
         * id : 11479
         * link : https://www.jianshu.com/p/6c0e17f71736
         * niceDate : 9小时前
         * niceShareDate : 9小时前
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1578844187000
         * selfVisible : 0
         * shareDate : 1578844187000
         * shareUser : 逐梦少年
         * superChapterId : 494
         * superChapterName : 广场Tab
         * tags : []
         * title : 每周一文第十二周--分布式协调工具zookeeper开山篇-编译安装与基础命令使用
         * type : 0
         * userId : 29062
         * visible : 1
         * zan : 0
         */

        @SerializedName("apkLink")
        private String apkLink;
        @SerializedName("audit")
        private int audit;
        @SerializedName("author")
        private String author;
        @SerializedName("chapterId")
        private int chapterId;
        @SerializedName("chapterName")
        private String chapterName;
        @SerializedName("collect")
        private boolean collect;
        @SerializedName("courseId")
        private int courseId;
        @SerializedName("desc")
        private String desc;
        @SerializedName("envelopePic")
        private String envelopePic;
        @SerializedName("fresh")
        private boolean fresh;
        @SerializedName("id")
        private int id;
        @SerializedName("link")
        private String link;
        @SerializedName("niceDate")
        private String niceDate;
        @SerializedName("niceShareDate")
        private String niceShareDate;
        @SerializedName("origin")
        private String origin;
        @SerializedName("prefix")
        private String prefix;
        @SerializedName("projectLink")
        private String projectLink;
        @SerializedName("publishTime")
        private long publishTime;
        @SerializedName("selfVisible")
        private int selfVisible;
        @SerializedName("shareDate")
        private long shareDate;
        @SerializedName("shareUser")
        private String shareUser;
        @SerializedName("superChapterId")
        private int superChapterId;
        @SerializedName("superChapterName")
        private String superChapterName;
        @SerializedName("title")
        private String title;
        @SerializedName("type")
        private int type;
        @SerializedName("userId")
        private int userId;
        @SerializedName("visible")
        private int visible;
        @SerializedName("zan")
        private int zan;
        @SerializedName("tags")
        private List<?> tags;

        public String getApkLink() {
            return apkLink;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public int getAudit() {
            return audit;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public boolean isFresh() {
            return fresh;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getNiceShareDate() {
            return niceShareDate;
        }

        public void setNiceShareDate(String niceShareDate) {
            this.niceShareDate = niceShareDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getSelfVisible() {
            return selfVisible;
        }

        public void setSelfVisible(int selfVisible) {
            this.selfVisible = selfVisible;
        }

        public long getShareDate() {
            return shareDate;
        }

        public void setShareDate(long shareDate) {
            this.shareDate = shareDate;
        }

        public String getShareUser() {
            return shareUser;
        }

        public void setShareUser(String shareUser) {
            this.shareUser = shareUser;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }
    }
}
