package acr.browser.lightning.database.bookmark;


import static org.awaitility.Awaitility.await;

import android.Manifest;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


import androidx.test.core.app.ApplicationProvider;
import androidx.test.rule.GrantPermissionRule;


import com.anthonycr.bonsai.CompletableOnSubscribe;
import com.anthonycr.bonsai.Schedulers;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleOnSubscribe;
import com.anthonycr.bonsai.Subscription;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


import acr.browser.lightning.R;
import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.utils.Utils;

import org.awaitility.Awaitility.*;

@RunWith(RobolectricTestRunner.class)
public class BookmarkExporterTest {


    @Rule
    public GrantPermissionRule mRuntimePermissionRule = GrantPermissionRule.grant(Manifest.permission.READ_EXTERNAL_STORAGE);
    public GrantPermissionRule mRuntimePermissionRule1 = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);



    @Test
    public void importBookmarksFromAssets() {
        List<HistoryItem> items = BookmarkExporter.importBookmarksFromAssets(ApplicationProvider.getApplicationContext());
        assert items.size() == 5;
        for (HistoryItem item : items) {
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
    public void exportBookmarksToFile() throws IOException {
        createAndCheck();
        createAndCheck();

    }

    public void createAndCheck()  throws IOException  {
        final boolean[] read = {false, false};
        File file  = BookmarkExporter.createNewExportFile();
        file.createNewFile();
        List<HistoryItem> bookmarks = BookmarkExporter.importBookmarksFromAssets(ApplicationProvider.getApplicationContext());
        Subscription mExportSubscription = BookmarkExporter.exportBookmarksToFile(bookmarks, file)
                .subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate())
                .subscribe(new CompletableOnSubscribe() {
                    @Override
                    public void onComplete() {
                        read[0] = true;
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        assert false;
                    }
                });
        await().atMost(20,TimeUnit.SECONDS).until(() -> read[0]);
        Single<List<HistoryItem>> bookmarksRead = BookmarkExporter.importBookmarksFromFile(file);
         bookmarksRead.subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate())
                .subscribe(new SingleOnSubscribe<List<HistoryItem>>() {
                    @Override
                    public void onItem(@Nullable final List<HistoryItem> importList) {
                        assert importList.size() == 5;
                        for (HistoryItem item : importList) {
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

//
                            }
                        }
                        read[1] = true;
                    }

                    @Override
                    public void onError (@NonNull Throwable throwable){
                        assert false;
                    }
                });
        await().atMost(20, TimeUnit.SECONDS).until(() -> read[1]);
        return;

    }

    @After
    public void tearDown() {
        System.out.println("\nGin Memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
    }

}