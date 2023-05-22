package io.github.hidroh.materialistic.data.android;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u000f\u001a\u00020\u000eH\u0007J\b\u0010\u0010\u001a\u00020\u000eH\u0007J\b\u0010\u0011\u001a\u00020\u000eH\u0007J\b\u0010\u0012\u001a\u00020\u000eH\u0007J\b\u0010\u0013\u001a\u00020\u000eH\u0007J\b\u0010\u0014\u001a\u00020\u000eH\u0007J\b\u0010\u0015\u001a\u00020\u000eH\u0007J\b\u0010\u0016\u001a\u00020\u000eH\u0007J\b\u0010\u0017\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00068\u0002@\u0002X\u0083.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0002@\u0002X\u0083.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\n8\u0002@\u0002X\u0083.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\f8\u0002@\u0002X\u0083.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lio/github/hidroh/materialistic/data/android/CacheTest;", "", "()V", "cache", "Lio/github/hidroh/materialistic/data/android/Cache;", "database", "Lio/github/hidroh/materialistic/data/MaterialisticDatabase;", "readStoriesDao", "Lio/github/hidroh/materialistic/data/MaterialisticDatabase$ReadStoriesDao;", "readableDao", "Lio/github/hidroh/materialistic/data/MaterialisticDatabase$ReadableDao;", "savedStoriesDao", "Lio/github/hidroh/materialistic/data/MaterialisticDatabase$SavedStoriesDao;", "setUp", "", "testGetReadabilityNoContent", "testGetReadabilityNoItem", "testGetReadabilityWithContent", "testIsFavoriteNoItem", "testIsFavoriteWithItem", "testIsViewedNoItem", "testIsViewedWithItem", "testPutReadability", "testSetViewed", "app_debug"})
@org.junit.runner.RunWith(value = org.junit.runners.JUnit4.class)
public final class CacheTest {
    @org.mockito.Mock()
    private io.github.hidroh.materialistic.data.MaterialisticDatabase database;
    @org.mockito.Mock()
    private io.github.hidroh.materialistic.data.MaterialisticDatabase.ReadStoriesDao readStoriesDao;
    @org.mockito.Mock()
    private io.github.hidroh.materialistic.data.MaterialisticDatabase.SavedStoriesDao savedStoriesDao;
    @org.mockito.Mock()
    private io.github.hidroh.materialistic.data.MaterialisticDatabase.ReadableDao readableDao;
    private io.github.hidroh.materialistic.data.android.Cache cache;
    
    public CacheTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setUp() {
    }
    
    @org.junit.Test()
    public final void testGetReadabilityNoItem() {
    }
    
    @org.junit.Test()
    public final void testGetReadabilityNoContent() {
    }
    
    @org.junit.Test()
    public final void testGetReadabilityWithContent() {
    }
    
    @org.junit.Test()
    public final void testPutReadability() {
    }
    
    @org.junit.Test()
    public final void testIsViewedNoItem() {
    }
    
    @org.junit.Test()
    public final void testIsViewedWithItem() {
    }
    
    @org.junit.Test()
    public final void testSetViewed() {
    }
    
    @org.junit.Test()
    public final void testIsFavoriteNoItem() {
    }
    
    @org.junit.Test()
    public final void testIsFavoriteWithItem() {
    }
}