package de.storchp.fdroidbuildstatus;

import static de.storchp.fdroidbuildstatus.utils.DrawableUtils.setMenuIconTint;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import de.storchp.fdroidbuildstatus.databinding.ActivityShowErrorBinding;

public class ShowErrorActivity extends AppCompatActivity {

    public static final String EXTRA_ERROR_TEXT = "error";

    private ActivityShowErrorBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShowErrorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textViewError.setText(getIntent().getStringExtra(EXTRA_ERROR_TEXT));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(createErrorTitle());
        }
    }

    private String createErrorTitle() {
        return String.format(getString(R.string.error_crash_title), getString(R.string.app_name));
    }

    private void reportBug() {
        Uri uriUrl;
        try {
            uriUrl = Uri.parse(
                    String.format(
                            getString(R.string.report_issue_link),
                            URLEncoder.encode(binding.textViewError.getText().toString(), StandardCharsets.UTF_8.toString())
                    )
            );
        } catch (UnsupportedEncodingException ignored) {
            // can't happen as UTF-8 is always available
            return;
        }
        var intent = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_error, menu);
        setMenuIconTint(this, menu, R.id.error_share, R.color.design_default_color_on_primary);
        setMenuIconTint(this, menu, R.id.error_report, R.color.design_default_color_on_primary);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.error_share) {
            onClickedShare();
            return true;
        } else if (item.getItemId() == R.id.error_report) {
            reportBug();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onClickedShare() {
        var intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, createErrorTitle());
        intent.putExtra(Intent.EXTRA_TEXT, binding.textViewError.getText());
        intent.setType("text/plain");
        startActivity(intent);
    }

}
