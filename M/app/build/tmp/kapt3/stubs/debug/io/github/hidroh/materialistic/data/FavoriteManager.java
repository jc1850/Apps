package io.github.hidroh.materialistic.data;

import java.lang.System;

/**
 * Data repository for {@link Favorite}
 */
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0005\b\u0007\u0018\u0000 72\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003789B!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J \u0010\u001d\u001a\u0010\u0012\f\u0012\n  *\u0004\u0018\u00010\u001f0\u001f0\u001e2\b\u0010!\u001a\u0004\u0018\u00010\u001cH\u0007J\u0018\u0010\"\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010#\u001a\u0004\u0018\u00010\u001cJ\u0010\u0010$\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010&\u001a\u00020\u00132\b\u0010!\u001a\u0004\u0018\u00010\u001cH\u0003J\u0012\u0010\'\u001a\u00020\u000f2\b\u0010#\u001a\u0004\u0018\u00010\u001cH\u0003J\b\u0010(\u001a\u00020\u0013H\u0016J\u0018\u0010)\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010#\u001a\u0004\u0018\u00010\u001cJ\u0012\u0010*\u001a\u0004\u0018\u00010\u00022\u0006\u0010+\u001a\u00020\u000fH\u0016J\b\u0010,\u001a\u00020\u000fH\u0016J\u0010\u0010-\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0003J\u001a\u0010.\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010/\u001a\u0004\u0018\u000100H\u0002J\u0010\u00101\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010#\u001a\u0002022\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0003J\u0018\u00103\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\u001cJ\u001e\u00103\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u000e\u00104\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u000105J\u001a\u00106\u001a\u0004\u0018\u0001002\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000bH\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0018\u00010\rR\u00020\u0000X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006:"}, d2 = {"Lio/github/hidroh/materialistic/data/FavoriteManager;", "Lio/github/hidroh/materialistic/data/LocalItemManager;", "Lio/github/hidroh/materialistic/data/Favorite;", "cache", "Lio/github/hidroh/materialistic/data/LocalCache;", "ioScheduler", "Lrx/Scheduler;", "dao", "Lio/github/hidroh/materialistic/data/MaterialisticDatabase$SavedStoriesDao;", "(Lio/github/hidroh/materialistic/data/LocalCache;Lrx/Scheduler;Lio/github/hidroh/materialistic/data/MaterialisticDatabase$SavedStoriesDao;)V", "cursor", "Lio/github/hidroh/materialistic/data/FavoriteManager$Cursor;", "loader", "Lio/github/hidroh/materialistic/data/FavoriteManager$FavoriteRoomLoader;", "notificationId", "", "syncScheduler", "Lio/github/hidroh/materialistic/data/SyncScheduler;", "add", "", "context", "Landroid/content/Context;", "story", "Lio/github/hidroh/materialistic/data/WebItem;", "attach", "observer", "Lio/github/hidroh/materialistic/data/LocalItemManager$Observer;", "filter", "", "check", "Lrx/Observable;", "", "kotlin.jvm.PlatformType", "itemId", "clear", "query", "createNotificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "delete", "deleteMultiple", "detach", "export", "getItem", "position", "getSize", "insert", "notifyExportDone", "uri", "Landroid/net/Uri;", "notifyExportStart", "Landroid/database/Cursor;", "remove", "itemIds", "", "toFile", "Companion", "Cursor", "FavoriteRoomLoader", "app_debug"})
@javax.inject.Singleton()
public final class FavoriteManager implements io.github.hidroh.materialistic.data.LocalItemManager<io.github.hidroh.materialistic.data.Favorite> {
    private final io.github.hidroh.materialistic.data.LocalCache cache = null;
    private final rx.Scheduler ioScheduler = null;
    private final io.github.hidroh.materialistic.data.MaterialisticDatabase.SavedStoriesDao dao = null;
    @org.jetbrains.annotations.NotNull()
    public static final io.github.hidroh.materialistic.data.FavoriteManager.Companion Companion = null;
    private static final java.lang.String CHANNEL_EXPORT = "export";
    private static final java.lang.String URI_PATH_ADD = "add";
    private static final java.lang.String URI_PATH_REMOVE = "remove";
    private static final java.lang.String URI_PATH_CLEAR = "clear";
    private static final java.lang.String PATH_SAVED = "saved";
    private static final java.lang.String FILENAME_EXPORT = "materialistic-export.txt";
    private static final java.lang.String FILE_AUTHORITY = "io.github.hidroh.materialistic.fileprovider";
    private final int notificationId = 0;
    private final io.github.hidroh.materialistic.data.SyncScheduler syncScheduler = null;
    private io.github.hidroh.materialistic.data.FavoriteManager.Cursor cursor;
    private io.github.hidroh.materialistic.data.FavoriteManager.FavoriteRoomLoader loader;
    
    @javax.inject.Inject()
    public FavoriteManager(@org.jetbrains.annotations.NotNull()
    io.github.hidroh.materialistic.data.LocalCache cache, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "io")
    rx.Scheduler ioScheduler, @org.jetbrains.annotations.NotNull()
    io.github.hidroh.materialistic.data.MaterialisticDatabase.SavedStoriesDao dao) {
        super();
    }
    
    @java.lang.Override()
    public int getSize() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public io.github.hidroh.materialistic.data.Favorite getItem(int position) {
        return null;
    }
    
    @java.lang.Override()
    public void attach(@org.jetbrains.annotations.NotNull()
    io.github.hidroh.materialistic.data.LocalItemManager.Observer observer, @org.jetbrains.annotations.Nullable()
    java.lang.String filter) {
    }
    
    @java.lang.Override()
    public void detach() {
    }
    
    /**
     * Exports all favorites matched given query to file
     * @param context   an instance of {@link android.content.Context}
     * @param query     query to filter stories to be retrieved
     */
    public final void export(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String query) {
    }
    
    /**
     * Adds given story as favorite
     * @param context   an instance of {@link android.content.Context}
     * @param story     story to be added as favorite
     */
    public final void add(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    io.github.hidroh.materialistic.data.WebItem story) {
    }
    
    /**
     * Clears all stories matched given query from favorites
     * will be sent upon completion
     * @param context   an instance of {@link android.content.Context}
     * @param query     query to filter stories to be cleared
     */
    public final void clear(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String query) {
    }
    
    /**
     * Removes story with given ID from favorites
     * upon completion
     * @param context   an instance of {@link android.content.Context}
     * @param itemId    story ID to be removed from favorites
     */
    public final void remove(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String itemId) {
    }
    
    /**
     * Removes multiple stories with given IDs from favorites
     * be sent upon completion
     * @param context   an instance of {@link android.content.Context}
     * @param itemIds   array of story IDs to be removed from favorites
     */
    public final void remove(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.util.Collection<java.lang.String> itemIds) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.annotation.WorkerThread()
    public final rx.Observable<java.lang.Boolean> check(@org.jetbrains.annotations.Nullable()
    java.lang.String itemId) {
        return null;
    }
    
    @androidx.annotation.WorkerThread()
    private final android.net.Uri toFile(android.content.Context context, io.github.hidroh.materialistic.data.FavoriteManager.Cursor cursor) {
        return null;
    }
    
    private final void notifyExportStart(android.content.Context context) {
    }
    
    private final void notifyExportDone(android.content.Context context, android.net.Uri uri) {
    }
    
    private final androidx.core.app.NotificationCompat.Builder createNotificationBuilder(android.content.Context context) {
        return null;
    }
    
    @androidx.annotation.WorkerThread()
    private final android.database.Cursor query(java.lang.String filter) {
        return null;
    }
    
    @androidx.annotation.WorkerThread()
    private final void insert(io.github.hidroh.materialistic.data.WebItem story) {
    }
    
    @androidx.annotation.WorkerThread()
    private final void delete(java.lang.String itemId) {
    }
    
    @androidx.annotation.WorkerThread()
    private final int deleteMultiple(java.lang.String query) {
        return 0;
    }
    
    /**
     * A cursor wrapper to retrieve associated {@link Favorite}
     */
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lio/github/hidroh/materialistic/data/FavoriteManager$Cursor;", "Landroid/database/CursorWrapper;", "cursor", "Landroid/database/Cursor;", "(Landroid/database/Cursor;)V", "favorite", "Lio/github/hidroh/materialistic/data/Favorite;", "getFavorite", "()Lio/github/hidroh/materialistic/data/Favorite;", "app_debug"})
    static final class Cursor extends android.database.CursorWrapper {
        
        public Cursor(@org.jetbrains.annotations.NotNull()
        android.database.Cursor cursor) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final io.github.hidroh.materialistic.data.Favorite getFavorite() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0007R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lio/github/hidroh/materialistic/data/FavoriteManager$FavoriteRoomLoader;", "", "filter", "", "observer", "Lio/github/hidroh/materialistic/data/LocalItemManager$Observer;", "(Lio/github/hidroh/materialistic/data/FavoriteManager;Ljava/lang/String;Lio/github/hidroh/materialistic/data/LocalItemManager$Observer;)V", "load", "", "app_debug"})
    public final class FavoriteRoomLoader {
        private final java.lang.String filter = null;
        private final io.github.hidroh.materialistic.data.LocalItemManager.Observer observer = null;
        
        public FavoriteRoomLoader(@org.jetbrains.annotations.Nullable()
        java.lang.String filter, @org.jetbrains.annotations.NotNull()
        io.github.hidroh.materialistic.data.LocalItemManager.Observer observer) {
            super();
        }
        
        @androidx.annotation.AnyThread()
        public final void load() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u000e\u001a\u00020\fH\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lio/github/hidroh/materialistic/data/FavoriteManager$Companion;", "", "()V", "CHANNEL_EXPORT", "", "FILENAME_EXPORT", "FILE_AUTHORITY", "PATH_SAVED", "URI_PATH_ADD", "URI_PATH_CLEAR", "URI_PATH_REMOVE", "buildAdded", "Landroid/net/Uri$Builder;", "buildCleared", "buildRemoved", "isAdded", "", "uri", "Landroid/net/Uri;", "isCleared", "isRemoved", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean isAdded(@org.jetbrains.annotations.NotNull()
        android.net.Uri uri) {
            return false;
        }
        
        public final boolean isRemoved(@org.jetbrains.annotations.NotNull()
        android.net.Uri uri) {
            return false;
        }
        
        public final boolean isCleared(@org.jetbrains.annotations.NotNull()
        android.net.Uri uri) {
            return false;
        }
        
        private final android.net.Uri.Builder buildAdded() {
            return null;
        }
        
        private final android.net.Uri.Builder buildCleared() {
            return null;
        }
        
        private final android.net.Uri.Builder buildRemoved() {
            return null;
        }
    }
}