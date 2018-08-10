package ca.codeward.escapethehorde.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import ca.codeward.escapethehorde.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
