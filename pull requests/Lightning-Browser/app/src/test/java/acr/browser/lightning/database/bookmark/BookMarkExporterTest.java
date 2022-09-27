package acr.browser.lightning.database.bookmark;


import static org.awaitility.Awaitility.await;

import android.Manifest;
import android.app.Activity;
import android.util.Log;


import androidx.test.core.app.ApplicationProvider;
import androidx.test.rule.GrantPermissionRule;


import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


import acr.browser.lightning.R;
import acr.browser.lightning.database.Bookmark;
import acr.browser.lightning.database.HistoryEntry;
import acr.browser.lightning.utils.Utils;

import org.awaitility.Awaitility.*;

@RunWith(RobolectricTestRunner.class)
public class BookMarkExporterTest {


    @Rule
    public GrantPermissionRule mRuntimePermissionRule = GrantPermissionRule.grant(Manifest.permission.READ_EXTERNAL_STORAGE);
    public GrantPermissionRule mRuntimePermissionRule1 = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);



    @Test
    public void importBookmarksFromAssets() {
        List<Bookmark.Entry> items = BookmarkExporter.importBookmarksFromAssets(ApplicationProvider.getApplicationContext());
        assert items.size() == 5;
        for (Bookmark.Entry item : items) {
            switch (item.getTitle()) {
                case "Google":
                    assert item.getUrl().equals("https://www.google.com/");
                    break;
                case "Facebook":
                    assert item.getUrl().equals("https://www.facebook.com/");
                    break;
                case "Twitter":
                    assert item.getUrl().equals("https://twitter.com/");
                    break;
                case "Wikipedia":
                    assert item.getUrl().equals("https://www.wikipedia.org/");
                    break;
                case "Contact Me":
                    assert item.getUrl().equals("https://twitter.com/RestainoAnthony");
                    break;
                default:
                    break;

            }
        }
        return;
    }

    @Test
    public void exportBookmarksToFile() throws Exception {
        createAndCheck();
        createAndCheck();

    }

    public void createAndCheck() throws Exception {
        final boolean[] read = {false, false};
        File file  = BookmarkExporter.createNewExportFile();
        file.createNewFile();
        List<Bookmark.Entry> bookmarks = BookmarkExporter.importBookmarksFromAssets(ApplicationProvider.getApplicationContext());
        BookmarkExporter.exportBookmarksToFile(bookmarks, file);
        bookmarks = BookmarkExporter.importBookmarksFromFileStream(new FileInputStream(file));
        for (Bookmark.Entry item : bookmarks) {
            switch (item.getTitle()) {
                case "Google":
                    assert item.getUrl().equals("https://www.google.com/");
                    break;
                case "Facebook":
                    assert item.getUrl().equals("https://www.facebook.com/");
                    break;
                case "Twitter":
                    assert item.getUrl().equals("https://twitter.com/");
                    break;
                case "Wikipedia":
                    assert item.getUrl().equals("https://www.wikipedia.org/");
                    break;
                case "Contact Me":
                    assert item.getUrl().equals("https://twitter.com/RestainoAnthony");
                    break;
                default:
                    break;

            }
        }

        return;

    }

    @After
    public void tearDown() {
        System.out.println("\nGin Memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
    }

}