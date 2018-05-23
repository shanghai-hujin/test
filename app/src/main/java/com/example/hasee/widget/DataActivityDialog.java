package com.example.hasee.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.hasee.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/22 16:18
 */

public class DataActivityDialog extends DialogFragment {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.tv_data_cannle)
    TextView mTvDataCannle;
    @BindView(R.id.tv_data_title)
    TextView mTvDataTitle;
    @BindView(R.id.tv_data_ok)
    TextView mTvDataOk;
    @BindView(R.id.v_fenge)
    View mVFenge;
    @BindView(R.id.et_data_title)
    AppCompatEditText mEtDataTitle;
    @BindView(R.id.tv_data_start)
    TextView mTvDataStart;
    @BindView(R.id.iv_data_start)
    ImageView mIvDataStart;
    @BindView(R.id.tv_data_end)
    TextView mTvDataEnd;
    @BindView(R.id.iv_data_end)
    ImageView mIvDataEnd;
    @BindView(R.id.sv_data)
    ScrollView mSvData;
    Unbinder unbinder;
    @BindView(R.id.rb_richeng)
    RadioButton mRbRicheng;
    @BindView(R.id.rb_jinain)
    RadioButton mRbJinain;
    @BindView(R.id.rb_shengri)
    RadioButton mRbShengri;
    @BindView(R.id.rb_daoshu)
    RadioButton mRbDaoshu;
    @BindView(R.id.rg_data)
    RadioGroup mRgData;
    Unbinder unbinder1;
    private int mType;
    private String mParam;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            //添加动画
            dialog.getWindow().setWindowAnimations(R.style.dialogSlideAnim);
        }

        View view = inflater.inflate(R.layout.dialog_data, null);
        unbinder1 = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        mType = bundle.getInt("type", 1);
        mParam = bundle.getString("param", "test");


        initType();

    }

    private void initType() {
        switch (mType) {
            case 1:
                mRbRicheng.setChecked(true);
                mRbDaoshu.setChecked(false);
                mRbJinain.setChecked(false);
                mRbShengri.setChecked(false);
                break;
            case 2:
                mRbDaoshu.setChecked(false);
                mRbJinain.setChecked(true);
                mRbShengri.setChecked(false);
                mRbRicheng.setChecked(false);
                break;
            case 3:
                mRbDaoshu.setChecked(false);
                mRbJinain.setChecked(false);
                mRbShengri.setChecked(true);
                mRbRicheng.setChecked(false);
                break;
            case 4:
                mRbJinain.setChecked(false);
                mRbShengri.setChecked(false);
                mRbRicheng.setChecked(false);
                mRbDaoshu.setChecked(true);
                break;
            default:
                break;
        }
    }


    public static DataActivityDialog newInstance(int type, String parnm) {

        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putString("param", parnm);
        DataActivityDialog fragment = new DataActivityDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.view, R.id.tv_data_cannle, R.id.tv_data_title,
            R.id.tv_data_ok, R.id.v_fenge, R.id.et_data_title,
            R.id.tv_data_start, R.id.iv_data_start, R.id.tv_data_end,
            R.id.iv_data_end, R.id.sv_data,R.id.rb_richeng,
            R.id.rb_jinain, R.id.rb_shengri, R.id.rb_daoshu, R.id.rg_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view:
                break;
            case R.id.tv_data_cannle:
                break;
            case R.id.tv_data_title:
                break;
            case R.id.tv_data_ok:
                break;
            case R.id.v_fenge:
                break;
            case R.id.et_data_title:
                break;
            case R.id.tv_data_start:
                break;
            case R.id.iv_data_start:
                break;
            case R.id.tv_data_end:
                break;
            case R.id.iv_data_end:
                break;
            case R.id.sv_data:
                break;
            case R.id.rb_richeng:
                break;
            case R.id.rb_jinain:
                break;
            case R.id.rb_shengri:
                break;
            case R.id.rb_daoshu:
                break;
            case R.id.rg_data:
                break;
            default:
                break;
        }
    }

}
