package io.github.hidroh.materialistic.data;

import java.lang.System;

/**
 * Data repository for session state
 */
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\u0010\u0010\f\u001a\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lio/github/hidroh/materialistic/data/SessionManager;", "", "ioScheduler", "Lrx/Scheduler;", "cache", "Lio/github/hidroh/materialistic/data/LocalCache;", "(Lrx/Scheduler;Lio/github/hidroh/materialistic/data/LocalCache;)V", "isViewed", "Lrx/Observable;", "", "itemId", "", "view", "", "app_debug"})
@javax.inject.Singleton()
public final class SessionManager {
    private final rx.Scheduler ioScheduler = null;
    private final io.github.hidroh.materialistic.data.LocalCache cache = null;
    
    @javax.inject.Inject()
    public SessionManager(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "io")
    rx.Scheduler ioScheduler, @org.jetbrains.annotations.NotNull()
    io.github.hidroh.materialistic.data.LocalCache cache) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.annotation.WorkerThread()
    public final rx.Observable<java.lang.Boolean> isViewed(@org.jetbrains.annotations.Nullable()
    java.lang.String itemId) {
        return null;
    }
    
    /**
     * Marks an item as already being viewed
     * @param itemId    item ID that has been viewed
     */
    public final void view(@org.jetbrains.annotations.Nullable()
    java.lang.String itemId) {
    }
}