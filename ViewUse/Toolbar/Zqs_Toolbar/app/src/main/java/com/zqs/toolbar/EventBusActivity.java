package com.zqs.toolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * https://www.jianshu.com/p/65cd2d4ad99a
 * */
public class EventBusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_event_bus);
        setTitle("Toolbal使用");
        setBackArrow();
        hideOriginTitle();   //隐藏Toolbar 原有的主标题
        setToolbarMenuOnclick(new MenuItenClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_eventbus, menu);
        return true;
    }

    class MenuItenClickListener implements Toolbar.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_message:
                    Toast.makeText(EventBusActivity.this, "Click action_message", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_message1:
                    Toast.makeText(EventBusActivity.this, "Click action_message1", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    }
}
