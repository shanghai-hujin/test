package com.example.hasee.ui.person;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hasee.R;
import com.example.hasee.bean.LoginResponse;
import com.example.hasee.di.component.ApplicationComponent;
import com.example.hasee.di.component.DaggerHttpComponent;
import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.utils.BitmapUtil;
import com.example.hasee.utils.Event;
import com.example.hasee.utils.PasswordHelp;
import com.example.hasee.utils.RxBus;
import com.example.hasee.utils.StatusBarUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

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
    @BindView(R.id.toolbar_login)
    Toolbar mToolbarLogin;
    private Bitmap bitmap;
    private Bitmap bitmap1;
    private Bitmap bitmap2;

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        StatusBarUtil.setTranslucent(LoginActivity.this);
        mToolbarLogin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void initInjector(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(applicationComponent)
                .build()
                .inject(this);
    }



    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @OnClick({R.id.bt_go, R.id.fab, R.id.ib_login_weibo, R.id.ib_login_wx,
            R.id.ib_login_qq, R.id.ib_login_csdn, R.id.ll_login_options, R.id.ll_login_pull})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_go:
                if (TextUtils.isEmpty(etUsername.getText().toString().trim()) || TextUtils.isEmpty(etPassword.getText().toString().trim())) {
                    Toast.makeText(LoginActivity.this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
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

        if(loginResponse == null || loginResponse.getData() == null){
            loginFail("账号密码错误...");
            return;
        }

        LoginResponse.LoginData loginData = loginResponse.getData();
        Toasty.success(this,"登录成功...", Toast.LENGTH_SHORT,true).show();
        PasswordHelp.savePassword(loginData.getUsername(), loginData.getPassword(), true);
        Event.LoginStausEvent event = new Event.LoginStausEvent();
        event.login = true;
        RxBus.INSTANCE.post(event);
        finish();
    }

    @Override
    public void loginFail(String errormsg) {
        Toasty.error(this,"账号密码错误...", Toast.LENGTH_SHORT,true).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewShot(cv);
        viewShot2(llLoginOptions);

    }

    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }

    public static Bitmap getViewBp(View v) {
        if (null == v) {
            return null;
        }
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        if (Build.VERSION.SDK_INT >= 11) {
            v.measure(View.MeasureSpec.makeMeasureSpec(v.getWidth(),
                    View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(
                    v.getHeight(), View.MeasureSpec.EXACTLY));
            v.layout((int) v.getX(), (int) v.getY(),
                    (int) v.getX() + v.getMeasuredWidth(),
                    (int) v.getY() + v.getMeasuredHeight());
        } else {
            v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        }
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache(), 0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());

        v.setDrawingCacheEnabled(false);
        v.destroyDrawingCache();
        return b;
    }

    private Bitmap addBitmap(Bitmap first, Bitmap second) {
        int width = Math.max(first.getWidth(),second.getWidth());
        int height = first.getHeight() + second.getHeight();
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(first, 0, 0, null);
        canvas.drawBitmap(second, 0, first.getHeight(),null);
        return result;
    }

    public  void viewShot(final View v){
        v.post(new Runnable() {
            @Override
            public void run() {
                bitmap1 = Bitmap.createBitmap(v.getWidth() , v.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas(bitmap1);
                v.draw(c);;
            }
        });
    }

    public  void viewShot2(final View v){
        v.post(new Runnable() {
            @Override
            public void run() {
                bitmap2 = Bitmap.createBitmap(v.getWidth() , v.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas(bitmap2);
                v.draw(c);;
                Bitmap addBitmap = addBitmap(bitmap1, bitmap2);
                saveImg(addBitmap);
            }
        });
    }

    public  boolean saveImg(Bitmap bitmap) {
        Random r = new Random();
        String filename = String.valueOf(r.nextInt(10)) + ".png";
        File sd = Environment.getExternalStorageDirectory();
        File dest = new File(sd, filename);

        try {
            FileOutputStream out = new FileOutputStream(dest);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                out.flush();
                out.close();
            }
            out.flush();
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    private  Bitmap mergeBitmap(Bitmap firstBitmap, Bitmap secondBitmap, Bitmap threeBitmap) {
        Bitmap bitmap = Bitmap.createBitmap(firstBitmap.getWidth(), firstBitmap.getHeight() + secondBitmap.getHeight() + threeBitmap.getHeight(), firstBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(firstBitmap, new Matrix(), null);
        canvas.drawBitmap(secondBitmap, 0, firstBitmap.getHeight(), null);
        canvas.drawBitmap(threeBitmap, secondBitmap.getWidth(), firstBitmap.getHeight(), null);
        return bitmap;
    }

    public static Bitmap mergeBitmap(Bitmap firstBitmap, Bitmap secondBitmap) {
        Bitmap bitmap = Bitmap.createBitmap(firstBitmap.getWidth(), firstBitmap.getHeight(),firstBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(firstBitmap, new Matrix(), null);
        canvas.drawBitmap(secondBitmap, 0, 0, null);
        return bitmap;
    }

    @Override
    protected void onDestroy() {
        if(bitmap1 != null && !bitmap1.isRecycled()){
            bitmap1.recycle();
            bitmap1 = null;
        }
        if(bitmap2 != null && !bitmap2.isRecycled()){
            bitmap2.recycle();
            bitmap2 = null;
        }
        super.onDestroy();

    }
}
