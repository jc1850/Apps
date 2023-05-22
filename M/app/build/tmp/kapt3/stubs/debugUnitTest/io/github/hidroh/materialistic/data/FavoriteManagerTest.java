package io.github.hidroh.materialistic.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0007J\b\u0010\u0015\u001a\u00020\u0014H\u0007J\b\u0010\u0016\u001a\u00020\u0014H\u0007J\b\u0010\u0017\u001a\u00020\u0014H\u0007J\b\u0010\u0018\u001a\u00020\u0014H\u0007J\b\u0010\u0019\u001a\u00020\u0014H\u0007J\b\u0010\u001a\u001a\u00020\u0014H\u0007J\b\u0010\u001b\u001a\u00020\u0014H\u0007J\b\u0010\u001c\u001a\u00020\u0014H\u0007J\b\u0010\u001d\u001a\u00020\u0014H\u0007J\b\u0010\u001e\u001a\u00020\u0014H\u0007J\b\u0010\u001f\u001a\u00020\u0014H\u0007J\b\u0010 \u001a\u00020\u0014H\u0007J\b\u0010!\u001a\u00020\u0014H\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0002@\u0002X\u0083.\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u0005*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0005*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n \u0005*\u0004\u0018\u00010\u00120\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lio/github/hidroh/materialistic/data/FavoriteManagerTest;", "", "()V", "context", "Landroid/app/Application;", "kotlin.jvm.PlatformType", "database", "Lio/github/hidroh/materialistic/data/MaterialisticDatabase;", "manager", "Lio/github/hidroh/materialistic/data/FavoriteManager;", "observer", "Landroidx/lifecycle/Observer;", "Landroid/net/Uri;", "readStoriesDao", "Lio/github/hidroh/materialistic/data/MaterialisticDatabase$ReadStoriesDao;", "readableDao", "Lio/github/hidroh/materialistic/data/MaterialisticDatabase$ReadableDao;", "savedStoriesDao", "Lio/github/hidroh/materialistic/data/MaterialisticDatabase$SavedStoriesDao;", "setUp", "", "tearDown", "testCheckFalse", "testCheckNoId", "testCheckTrue", "testClearAll", "testClearQuery", "testExportEmpty", "testExportNoQuery", "testLocalItemManager", "testReAdd", "testRemoveMultipleNoId", "testRemoveMultipleWithId", "testRemoveWithId", "app_debug"})
@org.junit.runner.RunWith(value = io.github.hidroh.materialistic.test.TestRunner.class)
@org.robolectric.annotation.LooperMode(value = org.robolectric.annotation.LooperMode.Mode.LEGACY)
public final class FavoriteManagerTest {
    private final android.app.Application context = null;
    private final io.github.hidroh.materialistic.data.MaterialisticDatabase database = null;
    private final io.github.hidroh.materialistic.data.MaterialisticDatabase.SavedStoriesDao savedStoriesDao = null;
    private final io.github.hidroh.materialistic.data.MaterialisticDatabase.ReadStoriesDao readStoriesDao = null;
    private final io.github.hidroh.materialistic.data.MaterialisticDatabase.ReadableDao readableDao = null;
    private io.github.hidroh.materialistic.data.FavoriteManager manager;
    @org.mockito.Mock()
    private androidx.lifecycle.Observer<android.net.Uri> observer;
    
    public FavoriteManagerTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setUp() {
    }
    
    @org.junit.Test()
    public final void testLocalItemManager() {
    }
    
    @org.junit.Test()
    public final void testExportNoQuery() {
    }
    
    @org.junit.Test()
    public final void testExportEmpty() {
    }
    
    @org.junit.Test()
    public final void testCheckNoId() {
    }
    
    @org.junit.Test()
    public final void testCheckTrue() {
    }
    
    @org.junit.Test()
    public final void testCheckFalse() {
    }
    
    @org.junit.Test()
    public final void testReAdd() {
    }
    
    @org.junit.Test()
    public final void testClearAll() {
    }
    
    @org.junit.Test()
    public final void testClearQuery() {
    }
    
    @org.junit.Test()
    public final void testRemoveWithId() {
    }
    
    @org.junit.Test()
    public final void testRemoveMultipleNoId() {
    }
    
    @org.junit.Test()
    public final void testRemoveMultipleWithId() {
    }
    
    @org.junit.After()
    public final void tearDown() {
    }
}