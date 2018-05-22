package com.example.hasee.ui.mycenter;

import com.example.hasee.bean.DataActivityBean;
import com.example.hasee.dao.DataActivityDao;
import com.example.hasee.ui.base.BasePresenter;

import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/18 17:24
 */

public class MyCenterPresenter extends BasePresenter<MyCenterContract.MyCenterView> implements MyCenterContract.MyCenterPresenter {


    @Override
    public void getDateActivity() {
        //获取本地数据库的数据
        List<DataActivityBean> dataActivityBeanList = DataActivityDao.getDataActivity();

        if (dataActivityBeanList.size() < 1) {
            //空的，等待数据添加
            mView.loadNullData();
        }else {
            //数据库有数据
            mView.loadData(dataActivityBeanList);
        }
    }
}
