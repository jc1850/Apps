package io.github.hidroh.materialistic.data;

import java.lang.System;

@androidx.annotation.WorkerThread()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\'J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&J\u001c\u0010\b\u001a\u00020\t2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0003H&J\u0012\u0010\u000b\u001a\u00020\t2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&\u00a8\u0006\f"}, d2 = {"Lio/github/hidroh/materialistic/data/LocalCache;", "", "getReadability", "", "itemId", "isFavorite", "", "isViewed", "putReadability", "", "content", "setViewed", "app_debug"})
public abstract interface LocalCache {
    
    @org.jetbrains.annotations.Nullable()
    @androidx.annotation.Nullable()
    public abstract java.lang.String getReadability(@org.jetbrains.annotations.Nullable()
    java.lang.String itemId);
    
    public abstract void putReadability(@org.jetbrains.annotations.Nullable()
    java.lang.String itemId, @org.jetbrains.annotations.Nullable()
    java.lang.String content);
    
    public abstract boolean isViewed(@org.jetbrains.annotations.Nullable()
    java.lang.String itemId);
    
    public abstract void setViewed(@org.jetbrains.annotations.Nullable()
    java.lang.String itemId);
    
    public abstract boolean isFavorite(@org.jetbrains.annotations.Nullable()
    java.lang.String itemId);
}