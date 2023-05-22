package io.github.hidroh.materialistic.data.android;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010\u0012\u001a\u00020\u00112\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016J\u001c\u0010\u0013\u001a\u00020\u00142\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010\u0016\u001a\u00020\u00142\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lio/github/hidroh/materialistic/data/android/Cache;", "Lio/github/hidroh/materialistic/data/LocalCache;", "database", "Lio/github/hidroh/materialistic/data/MaterialisticDatabase;", "savedStoriesDao", "Lio/github/hidroh/materialistic/data/MaterialisticDatabase$SavedStoriesDao;", "readStoriesDao", "Lio/github/hidroh/materialistic/data/MaterialisticDatabase$ReadStoriesDao;", "readableDao", "Lio/github/hidroh/materialistic/data/MaterialisticDatabase$ReadableDao;", "mainScheduler", "Lrx/Scheduler;", "(Lio/github/hidroh/materialistic/data/MaterialisticDatabase;Lio/github/hidroh/materialistic/data/MaterialisticDatabase$SavedStoriesDao;Lio/github/hidroh/materialistic/data/MaterialisticDatabase$ReadStoriesDao;Lio/github/hidroh/materialistic/data/MaterialisticDatabase$ReadableDao;Lrx/Scheduler;)V", "getReadability", "", "itemId", "isFavorite", "", "isViewed", "putReadability", "", "content", "setViewed", "app_debug"})
public final class Cache implements io.github.hidroh.materialistic.data.LocalCache {
    private final io.github.hidroh.materialistic.data.MaterialisticDatabase database = null;
    private final io.github.hidroh.materialistic.data.MaterialisticDatabase.SavedStoriesDao savedStoriesDao = null;
    private final io.github.hidroh.materialistic.data.MaterialisticDatabase.ReadStoriesDao readStoriesDao = null;
    private final io.github.hidroh.materialistic.data.MaterialisticDatabase.ReadableDao readableDao = null;
    private final rx.Scheduler mainScheduler = null;
    
    @javax.inject.Inject()
    public Cache(@org.jetbrains.annotations.NotNull()
    io.github.hidroh.materialistic.data.MaterialisticDatabase database, @org.jetbrains.annotations.NotNull()
    io.github.hidroh.materialistic.data.MaterialisticDatabase.SavedStoriesDao savedStoriesDao, @org.jetbrains.annotations.NotNull()
    io.github.hidroh.materialistic.data.MaterialisticDatabase.ReadStoriesDao readStoriesDao, @org.jetbrains.annotations.NotNull()
    io.github.hidroh.materialistic.data.MaterialisticDatabase.ReadableDao readableDao, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "main")
    rx.Scheduler mainScheduler) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.String getReadability(@org.jetbrains.annotations.Nullable()
    java.lang.String itemId) {
        return null;
    }
    
    @java.lang.Override()
    public void putReadability(@org.jetbrains.annotations.Nullable()
    java.lang.String itemId, @org.jetbrains.annotations.Nullable()
    java.lang.String content) {
    }
    
    @java.lang.Override()
    public boolean isViewed(@org.jetbrains.annotations.Nullable()
    java.lang.String itemId) {
        return false;
    }
    
    @java.lang.Override()
    public void setViewed(@org.jetbrains.annotations.Nullable()
    java.lang.String itemId) {
    }
    
    @java.lang.Override()
    public boolean isFavorite(@org.jetbrains.annotations.Nullable()
    java.lang.String itemId) {
        return false;
    }
}