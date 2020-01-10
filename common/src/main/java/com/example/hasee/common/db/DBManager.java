package com.example.hasee.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.hasee.common.net.bean.response.LoginResponce;
import com.example.hasee.db.DaoMaster;
import com.example.hasee.db.DaoSession;
import com.example.hasee.db.LoginResponceDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @author apppp
 */
public class DBManager {
    private volatile static DBManager sInstance;
    private Context context;
    private final String DB_NAME ="xiaoxiaotaozai.db";
    protected MySQLiteOpenHelper mOpenHelper;

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DBManager.class) {
                if (sInstance == null) {
                    sInstance = new DBManager(context.getApplicationContext());


                }
            }
        }
        return sInstance;
    }

    public DBManager(Context context) {
        this.context = context;
        initDataBase();
    }


    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (mOpenHelper == null) {
            mOpenHelper = new MySQLiteOpenHelper(context, DB_NAME, null);
        }
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        return db;
    }


    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (mOpenHelper == null) {
            mOpenHelper = new MySQLiteOpenHelper(context, DB_NAME, null);
        }
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        return db;
    }


    /**
     * 初始化数据库及相关类
     */
    private void initDataBase(){
        //默认开启Log打印
        setDebugMode(true);
        //建库
        mOpenHelper = new MySQLiteOpenHelper(context, DB_NAME, null);
//        mDaoMaster = new DaoMaster(getWritableDatabase());
//        mDaoSession = mDaoMaster.newSession();
//        mDaoSession.clear();//清空所有数据表的缓存
    }


    /**
     * 是否开启Log
     * @param flag
     */
    public void setDebugMode(boolean flag) {
        //如果查看数据库更新的Log，请设置为true
        MigrationHelper.DEBUG = true;
        QueryBuilder.LOG_SQL = flag;
        QueryBuilder.LOG_VALUES = flag;
    }

    private DaoSession getDaoSessionWriteQuckley() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        return daoMaster.newSession();
    }

    private DaoSession getDaoSessionReadQuckley() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        return daoMaster.newSession();
    }


    /**
     * 插入一条记录
     *  登录状态 更新
     * @param loginResponce
     */
    public void updateLoginResponce(LoginResponce loginResponce) {
        getDaoSessionWriteQuckley().clear();
        LoginResponceDao loginResponceDao = getDaoSessionWriteQuckley().getLoginResponceDao();
        if(loginResponce != null){
            loginResponceDao.insertOrReplace(loginResponce);
        }else {
            loginResponceDao.deleteAll();
        }
    }

    /**
     * 查询登录状态
     * @return
     */
    public LoginResponce queryLoginResponce(){
        LoginResponceDao loginResponceDao = getDaoSessionReadQuckley().getLoginResponceDao();
        List<LoginResponce> loginResponces = loginResponceDao.loadAll();
        if(loginResponces.size() == 1){
            return loginResponces.get(0);
        }else {
            return null;
        }
    }

}


