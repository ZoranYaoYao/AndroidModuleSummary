package com.zoran.dialogfragmenttest.test2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zoran.dialogfragmenttest.MyCancelFragment;
import com.zoran.dialogfragmenttest.MyFragment;
import com.zoran.dialogfragmenttest.R;

/**
 * Created by zqs on 2018/10/18.
 */
public class FourActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    MyFragment myFragment = new MyFragment();
    MyCancelFragment cancelFragment = new MyCancelFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        if (savedInstanceState != null)
//            savedInstanceState.putParcelable("android:support:fragments", null);
        super.onCreate(savedInstanceState);
        Log.e("zqs1", "FourActivity onCreate");
        setContentView(R.layout.activity_four);
        fragmentManager = getSupportFragmentManager();
        Button btn = findViewById(R.id.btn_replace);
        Integer i = new Integer(1111);
//        fragmentManager.beginTransaction().add(R.id.fl_content, myFragment, "my_fragment").commit();
//        showFg = myFragment;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /** 替换操作*/
//                replace();

                /** 添加操作*/
//                                add();

                /** dialog 被杀死之后,是不会有修复效果的*/
//                AlertDialog.Builder builder = new AlertDialog.Builder(FourActivity.this).setTitle("你好");
//                builder.create().show();

                /** 交替使用*/
//                add_Show_Hide();


            }
        });



    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("zqs1", "FourActivity onRestoreInstanceState");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("zqs1", "FourActivity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("zqs1", "FourActivity onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("zqs1", "FourActivity onSaveInstanceState");
    }

    /**
     * replace 同一个fragment不会出错
     * repalce() : 会走fragment onAttach() -> onRecume() , 并且把上一个显示的fragment走onPause() -> onDestory()
     *
     * 2. .addToBackStack(null)
     *  添加到回退栈, 类似于命令模式, 有回退的功能, 意思就是能返回到上一个Fragment的UI
     *  .addToBackStack(null) : 走回退到上一个Fragment的生命周期 onCreateView() -> onResume()
     */
    private void replace() {
        fragmentManager.beginTransaction().replace(R.id.fl_content, cancelFragment).commit();  //Core. repalce 会把加载到容器中的fragment进行给销毁, 会走ondestroy流程

//        fragmentManager.beginTransaction().replace(R.id.fl_content, cancelFragment).addToBackStack(null).commit();
    }

    /**
     * add
     * 1. 添加一个已添加过的fragment:
     * Fragment already added: MyFragment{f337001 #0 id=0x7f07003c}
     * 解决方案:
     * https://www.2cto.com/kf/201711/697716.html
     * <p>
     * add() : 会走fragment 周期的 onAttach() -> onResume()
     */
    private void add() {
        try {
            fragmentManager.beginTransaction().add(R.id.fl_content, myFragment).commit();
        } catch (IllegalStateException e) {
            Log.e("zqs", e.getMessage());
        }
    }

    /**
     * add() hide() show()交互使用
     * add() : 会走fragment 周期的 onAttach() -> onResume()
     * hide() :  当fragment显示时, 进行隐藏操作, 不会走fragment的任何生命周期, 只是将view视图进行了隐藏
     * show() :  当fragment隐藏时, 进行显示操作, 不会走fragment的任何生命周期, 只是将view视图进行了显示
     */
    Fragment showFg;

    private void showFragment(Fragment f, String tag) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Log.e("zqs", "f = " + f + ", isAdded()" + f.isAdded());
        if (!f.isAdded() && fragmentManager.findFragmentByTag(tag) == null) { //Core. f.isAdded() 会出现错误状态
            if (showFg != null)
                ft.hide(showFg).add(R.id.fl_content, f, tag);
            else
                ft.add(R.id.fl_content, f, tag);
        } else { //已经加载进容器里了
            if (showFg != null)
                ft.hide(showFg).show(f);
            else
                ft.show(f);
        }

        showFg = f;
        if (!isFinishing()) {
            ft.commit();
            fragmentManager.executePendingTransactions();
        }
    }

    private void add_Show_Hide() {
        Fragment curFragment = null;
        String tag = null;
        if (showFg == null || showFg == myFragment) {
            curFragment = cancelFragment;
            tag = "cancel_fragment";
        } else {
            curFragment = myFragment;
            tag = "my_fragment";
        }
        showFragment(curFragment, tag);
    }
}
