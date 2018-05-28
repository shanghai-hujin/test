package com.example.hasee.widget.time;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.bean.DataActivityBean;
import com.example.hasee.utils.ContextUtils;
import com.example.hasee.utils.Event;
import com.example.hasee.utils.RxBus;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class DataActivityDialog extends DialogFragment implements RadioGroup.OnCheckedChangeListener {

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
    @BindView(R.id.tv_data_start_time)
    TextView mTvDataStartTime;
    @BindView(R.id.tv_data_end_time)
    TextView mTvDataEndTime;

    Unbinder unbinder;
    @BindView(R.id.et_data_name)
    AppCompatEditText mEtDataName;
    @BindView(R.id.iv_add_reduce)
    ImageView mIvAddReduce;
    @BindView(R.id.tl_people_flow)
    TagFlowLayout mTlPeopleFlow;
    @BindView(R.id.et_actvtity_things)
    AppCompatEditText mEtActvtityThings;
    private int mType = 0;
    private String mParam;

    private List<String> peopleLists = new ArrayList<>();
    private Map<String, Boolean> peopleCheckedLists = new HashMap<>();
    private int mLeval;

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
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        mLeval = bundle.getInt("mLeval", 1);
        mParam = bundle.getString("param", "test");

        mRgData.setOnCheckedChangeListener(this);
        initTagFlow();

    }

    private void initTagFlow() {
        if (peopleLists.size() <= 0) {
            mTlPeopleFlow.setVisibility(View.GONE);
        } else {
            mTlPeopleFlow.setVisibility(View.VISIBLE);
        }

        mTlPeopleFlow.setAdapter(new TagAdapter<String>(peopleLists) {
            @Override
            public View getView(FlowLayout parent, int position, String name) {
                assert getActivity() != null;

                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.item_flow_dataname, parent, false);

                assert peopleLists != null;

                tv.setText(name);
                tv.setTextColor(ContextUtils.intrandomColor());

                if (peopleLists.size() > 0) {
                    mTlPeopleFlow.setVisibility(View.VISIBLE);
                } else {
                    mTlPeopleFlow.setVisibility(View.GONE);
                }
                mTlPeopleFlow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {

                        peopleLists.remove(position);
                        mTlPeopleFlow.onChanged();


                        return true;

                    }
                });


                return tv;
            }

            @Override
            public void onSelected(int position, View view) {
                peopleCheckedLists.put(peopleLists.get(position), true);

                super.onSelected(position, view);
            }

            @Override
            public void unSelected(int position, View view) {
                peopleCheckedLists.put(peopleLists.get(position), false);

                super.unSelected(position, view);
            }
        });
    }


    public static DataActivityDialog newInstance(int type, String parnm) {

        Bundle args = new Bundle();
        args.putInt("mLeval", type);
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


    @OnClick({R.id.view, R.id.tv_data_cannle, R.id.tv_data_title, R.id.iv_add_reduce,
            R.id.tv_data_ok, R.id.v_fenge, R.id.et_data_title, R.id.tv_data_end_time,
            R.id.tv_data_start, R.id.iv_data_start, R.id.tv_data_end,
            R.id.iv_data_end, R.id.sv_data, R.id.rb_richeng, R.id.tv_data_start_time,
            R.id.rb_jinain, R.id.rb_shengri, R.id.rb_daoshu, R.id.rg_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view:
                break;
            case R.id.tv_data_cannle:
                dismiss();
                break;
            case R.id.tv_data_title:
                break;
            case R.id.tv_data_ok:
                if(TextUtils.isEmpty(mEtDataTitle.getText().toString().trim())){
                    ContextUtils.getCommonSnackbar(mSvData,"请输入事件标题")
                            .setAction("暂不设置", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mEtDataTitle.setText("待定");
                                }
                            }).show();
                    return;
                }

                if(TextUtils.isEmpty(mEtActvtityThings.getText().toString().trim())){
                    ContextUtils.getCommonSnackbar(mSvData,"请输入事件内容")
                            .setAction("暂不设置", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mEtActvtityThings.setText("待定");
                                }
                            }).show();
                    return;
                }

                StringBuilder stringBuilder = new StringBuilder();
                for (String peopleList : peopleLists) {
                    stringBuilder.append(peopleList);
                    stringBuilder.append(" ");
                }

                if(peopleLists.size() == 0 ){
                    ContextUtils.getCommonSnackbar(mSvData,"请输入事件人数")
                            .setAction("暂不设置", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    peopleLists.add("人数待定");
                                    mTlPeopleFlow.onChanged();
                                }
                            }).show();
                    return;
                }


                DataActivityBean dataActivityBean = new DataActivityBean();
                dataActivityBean.setActivityName(mEtDataTitle.getText().toString().trim());
                dataActivityBean.setActivityDateStart(mTvDataStart.getText().toString().trim() + " " + mTvDataStartTime.getText().toString().trim());
                dataActivityBean.setActivityDateEnd(mTvDataEnd.getText().toString().trim() + " " + mTvDataEndTime.getText().toString().trim());
                dataActivityBean.setActivityThings(mEtActvtityThings.getText().toString().trim());
                dataActivityBean.setActivityPeople(stringBuilder.toString());
                dataActivityBean.setActivityType(mType);
                dataActivityBean.setActivityLevel(mLeval);

                dataActivityBean.save();

                Event.DataActivityChange dataActivityChange = new Event.DataActivityChange();
                dataActivityChange.isAddActivity = true;
                RxBus.INSTANCE.post(dataActivityChange);
                dismiss();
                break;
            case R.id.v_fenge:
                break;
            case R.id.et_data_title:
                break;
            case R.id.tv_data_start:
                DialogFragment datePickerDarkFragment = new DatePickerDarkFragment(new OnDataSetLinstener() {
                    @Override
                    public void onDataSet(int year, int month, int day, String firstWeek) {
                        mTvDataStart.setText(year + "年" + firstWeek);
                    }

                });
                datePickerDarkFragment.show(getChildFragmentManager(), "datePicker");
                break;
            case R.id.tv_data_end:
                DialogFragment datePickerDarkFragmentend = new DatePickerDarkFragment(new OnDataSetLinstener() {
                    @Override
                    public void onDataSet(int year, int month, int day, String firstWeek) {
                        mTvDataEnd.setText(year + "年" + firstWeek);
                    }

                });
                datePickerDarkFragmentend.show(getChildFragmentManager(), "datePickerend");
                break;
            case R.id.tv_data_end_time:
            case R.id.iv_data_end:
                DialogFragment timePickerLightFragmentend = new TimePickerLightFragment(new OnTimeSetLinstener() {
                    @Override
                    public void onTimeSet(int hourOfDay, int minute) {
                        String mSMinute;
                        if (minute < 10) {
                            mSMinute = "0" + minute;
                        } else {
                            mSMinute = "" + minute;
                        }
                        if (hourOfDay > 12) {
                            mTvDataEndTime.setText("下午" + (hourOfDay - 12) + ":" + mSMinute);
                        } else if (hourOfDay == 12) {
                            mTvDataEndTime.setText("中午" + (hourOfDay) + ":" + mSMinute);
                        } else {
                            mTvDataEndTime.setText("上午" + (hourOfDay) + ":" + mSMinute);
                        }
                    }
                });
                timePickerLightFragmentend.show(getChildFragmentManager(), "timePickerend");
                break;
            case R.id.iv_data_start:
            case R.id.tv_data_start_time:
                DialogFragment timePickerLightFragment = new TimePickerLightFragment(new OnTimeSetLinstener() {
                    @Override
                    public void onTimeSet(int hourOfDay, int minute) {
                        String mSMinute;
                        if (minute < 10) {
                            mSMinute = "0" + minute;
                        } else {
                            mSMinute = "" + minute;
                        }
                        if (hourOfDay > 12) {
                            mTvDataStartTime.setText("下午" + (hourOfDay - 12) + ":" + mSMinute);
                        } else if (hourOfDay == 12) {
                            mTvDataStartTime.setText("中午" + (hourOfDay) + ":" + mSMinute);
                        } else {
                            mTvDataStartTime.setText("上午" + (hourOfDay) + ":" + mSMinute);
                        }
                    }
                });
                timePickerLightFragment.show(getChildFragmentManager(), "timePicker");
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
            case R.id.iv_add_reduce:
                //添加参与人数
                if (TextUtils.isEmpty(mEtDataName.getText().toString())) {
                    return;
                }
                peopleLists.add(mEtDataName.getText().toString().trim());
                mTlPeopleFlow.onChanged();
                mEtDataName.setText("");

                break;
            default:
                break;
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int childCount = group.getChildCount();
        RadioButton childAt = null;

        for (int i = 0; i < childCount; i++) {
            childAt = (RadioButton) group.getChildAt(i);
            if(childAt.isChecked()){
                mType = i;
                break;
            }
        }

    }
}
