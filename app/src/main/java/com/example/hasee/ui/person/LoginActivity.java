package com.example.hasee.ui.person;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hasee.R;
import com.example.hasee.bean.LoginResponse;
import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * @author HASEE
 * @date 2018/4/30
 */

public class LoginActivity extends BaseActivity<LoginPresenter>
        implements LoginContract.LoginView {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_go)
    Button btGo;
    @BindView(R.id.cv)
    CardView cv;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.ll_login_layer)
    View llLoginLayer;
    @BindView(R.id.ib_login_weibo)
    ImageView ibLoginWeibo;
    @BindView(R.id.ib_login_wx)
    ImageView ibLoginWx;
    @BindView(R.id.ib_login_qq)
    ImageView ibLoginQq;
    @BindView(R.id.ib_login_csdn)
    ImageView ibLoginCsdn;
    @BindView(R.id.ll_login_options)
    LinearLayout llLoginOptions;
    @BindView(R.id.ll_login_pull)
    LinearLayout llLoginPull;

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        StatusBarUtil.setTranslucent(LoginActivity.this);
        if (savedInstanceState == null) {

        }
    }

    @Override
    public void initData() {

    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }



    @OnClick({R.id.bt_go, R.id.fab, R.id.ib_login_weibo, R.id.ib_login_wx,
            R.id.ib_login_qq, R.id.ib_login_csdn, R.id.ll_login_options, R.id.ll_login_pull})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_go:
                basePresenter.Login(etUsername.getText().toString().trim(),etPassword.getText().toString().trim());
                break;
            case R.id.fab:
                break;
            case R.id.ib_login_weibo:
                break;
            case R.id.ib_login_wx:
                break;
            case R.id.ib_login_qq:
                break;
            case R.id.ib_login_csdn:
                break;
            case R.id.ll_login_options:
                break;
            case R.id.ll_login_pull:
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

                break;
            default:
                break;
        }
    }

    /**
     * 上升偏移
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

    /**
     *
     * To display an error Toast:

     Toasty.error(yourContext, "This is an error toast.", Toast.LENGTH_SHORT, true).show();
     To display a success Toast:

     Toasty.success(yourContext, "Success!", Toast.LENGTH_SHORT, true).show();
     To display an info Toast:

     Toasty.info(yourContext, "Here is some info for you.", Toast.LENGTH_SHORT, true).show();
     To display a warning Toast:

     Toasty.warning(yourContext, "Beware of the dog.", Toast.LENGTH_SHORT, true).show();
     To display the usual Toast:

     Toasty.normal(yourContext, "Normal toast w/o icon").show();
     To display the usual Toast with icon:

     Toasty.normal(yourContext, "Normal toast w/ icon", yourIconDrawable).show();
     You can also create your custom Toasts with the custom() method:

     Toasty.custom(yourContext, "I'm a custom Toast", yourIconDrawable, tintColor, duration, withIcon,
     shouldTint).show();
     */


    @Override
    /**
     * P层 返回的数据
     */
    public void loadData(LoginResponse loginResponse) {
        Toasty.success(this,"登录成功...", Toast.LENGTH_SHORT,true).show();
    }

    @Override
    public void loginFail(String errormsg) {
        Toasty.error(this,"账号密码错误...", Toast.LENGTH_SHORT,true).show();
    }
}
