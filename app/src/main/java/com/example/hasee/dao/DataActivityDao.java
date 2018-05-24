package com.example.hasee.dao;

import com.example.hasee.bean.DataActivityBean;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/22 11:09
 */

public class DataActivityDao {

    /**
     * 获取活动
     *
     * @return
     */
    public static List<DataActivityBean> getDataActivity() {
        return DataSupport.findAll(DataActivityBean.class);
    }


    /**
     * 清除，重新存
     * @param daoList
     */
    public static void savaDataActivty(List<DataActivityBean> daoList){
        if(daoList == null){
            return;
        }
        if(daoList.size() > 0) {
            final List<DataActivityBean> dataActivityBeanList = new ArrayList<>();
            dataActivityBeanList.addAll(daoList);
            DataSupport.deleteAllAsync(DataActivityBean.class).listen(new UpdateOrDeleteCallback() {
                @Override
                public void onFinish(int rowsAffected) {
                    /**
                     * 因为model之前被存储过了，再次存储就存不进去了。
                     * 单个model调用一下clearSavedState方法就可以了，
                     * 集合的话调用markAsDeleted方法。
                     */
                    DataSupport.markAsDeleted(dataActivityBeanList);
                    DataSupport.saveAllAsync(dataActivityBeanList).listen(new SaveCallback() {
                        @Override
                        public void onFinish(boolean success) {

                        }
                    });

                }
            });

        }
    }

    public static void updateSingleDataActivity(DataActivityBean dataActivityBean, int position){
        dataActivityBean.save();
    }

    /**
     * 清空所有活动
     */
    public static void cleanAllActivity() {
        DataSupport.deleteAll(DataActivityBean.class);
    }

    /**
     * 清空所有活动
     */
    public static void cleanSingleActivity(int position) {
        DataSupport.deleteAsync(DataActivityBean.class, position);
    }




}
