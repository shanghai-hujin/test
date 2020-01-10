package com.example.hasee.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.hasee.common.base.mvp.XDaggerActivity;
import com.example.hasee.common.db.DBManager;
import com.example.hasee.common.net.bean.response.LoginResponce;
import com.example.hasee.login.di.LoginDiHelper;

import es.dmoral.toasty.Toasty;

@Route(path = "/login/LoginActivity")
public class MainLoginActivity extends XDaggerActivity<LoginPresenter> implements ILoginContract.IView {

    private Button btnLogin;
    private EditText userName;
    private EditText password;
    private View llLoginLayer;
    private LinearLayout llLoginPull;
    private LinearLayout llLoginOptions;
    private Toolbar toolbar;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main_login;
    }


    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        btnLogin = (Button) find(R.id.bt_go);
        userName = (EditText) find(R.id.et_username);
        password = (EditText) find(R.id.et_password);
        llLoginLayer = find(R.id.ll_login_layer);
        llLoginPull = (LinearLayout) find(R.id.ll_login_pull);
        llLoginOptions = (LinearLayout) find(R.id.ll_login_options);
        toolbar = (Toolbar) find(R.id.toolbar_login);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        userName.setText("hujinjava");
        password.setText("2525980111");

        llLoginPull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llLoginPull.animate().cancel();
                llLoginLayer.animate().cancel();

                int height = llLoginOptions.getHeight();
                float progress = (llLoginLayer.getTag() != null && llLoginLayer.getTag() instanceof Float)
                        ? (float) llLoginLayer.getTag() : 1;
                int time = (int) (360*progress);

                if(llLoginPull.getTag() != null){
                    llLoginPull.setTag(null);
                    glide(height, progress, time);
                }else {
                    llLoginPull.setTag(true);
                    upGlide(height, progress, time);
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.goToLogin(userName.getText().toString().trim(), password.getText().toString().trim());
            }
        });

     //   MMKV.initialize(this);

    }

    @Override
    public void initInject(Bundle savedInstanceState) {
        LoginDiHelper.getActivityComponent(getActivityModule()).inject(this);
    }

    @Override
    public void showLoginSucess(LoginResponce response) {
        //登陆成功
//        MMKV.initialize(this);
//        MMKV kv = MMKV.defaultMMKV();
//        kv.encode("username", response.getUsername());


        DBManager.getInstance(this).updateLoginResponce(response);
        Toasty.success(this,"登陆成功").show();
        finish();

    }

    @Override
    public void showLoginFaile(String message) {
        Toasty.success(this,"登陆失败").show();
    }

    /**
     * 上升偏移
     *
     * @param height
     * @param progress
     * @param time
     */
    private void upGlide(int height, float progress, int time) {
        llLoginPull.animate()
                .translationYBy(height * progress)
                .translationY(0)
                .setDuration(time)
                .start();
        llLoginLayer.animate()
                .alphaBy(1 - progress)
                .alpha(1)
                .setDuration(time)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        llLoginLayer.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        if (animation instanceof ValueAnimator) {
                            llLoginLayer.setTag(((ValueAnimator) animation).getAnimatedValue());
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (animation instanceof ValueAnimator) {
                            llLoginLayer.setTag(((ValueAnimator) animation).getAnimatedValue());
                        }
                    }
                })
                .start();

    }

    /**
     *
     * @param height  下沉高度
     * @param progress 进度
     * @param time  动画时间
     */
    private void glide(int height, float progress, int time) {
        //Y方向上的偏移动画
        llLoginPull.animate()
                .translationYBy(height - height * progress)
                .translationY(height)
                .setDuration(time)
                .start();

        llLoginLayer.animate()
                .alphaBy(1*progress)
                .setDuration(time)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        if(animation instanceof ValueAnimator){
                            llLoginLayer.setTag(((ValueAnimator)animation).getAnimatedValue());
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(animation instanceof  ValueAnimator){
                            llLoginLayer.setTag(((ValueAnimator)animation).getAnimatedValue());
                        }
                        llLoginLayer.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }
                }).start();

    }
}
