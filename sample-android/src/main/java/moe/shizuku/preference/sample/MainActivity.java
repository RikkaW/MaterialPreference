package moe.shizuku.preference.sample;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import java.util.Locale;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            SettingsFragment fragment = new SettingsFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                    fragment).commit();
        }

        if (getActionBar() != null) {
            getActionBar().setSubtitle("android version (without appcompat)");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_night_mode:
                /*AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES ?
                        AppCompatDelegate.MODE_NIGHT_NO : AppCompatDelegate.MODE_NIGHT_YES);*/

                recreate();

                return true;
            case R.id.rtl:
                Resources resources = getBaseContext().getResources();

                Locale locale = resources.getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL
                        ? Locale.ENGLISH : new Locale("ar");
                Locale.setDefault(locale);

                resources.getConfiguration().setLocale(locale);
                resources.updateConfiguration(resources.getConfiguration(), resources.getDisplayMetrics());

                startActivity(new Intent(this, this.getClass()));
                finish();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
