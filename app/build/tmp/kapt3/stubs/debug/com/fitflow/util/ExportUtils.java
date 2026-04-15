package com.fitflow.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u001c\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ \u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u00a8\u0006\u0010"}, d2 = {"Lcom/fitflow/util/ExportUtils;", "", "()V", "exportToCsv", "", "context", "Landroid/content/Context;", "history", "", "Lcom/fitflow/data/HistoryEntry;", "exportToHtml", "shareFile", "file", "Ljava/io/File;", "mimeType", "", "app_debug"})
public final class ExportUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.fitflow.util.ExportUtils INSTANCE = null;
    
    private ExportUtils() {
        super();
    }
    
    public final void exportToCsv(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitflow.data.HistoryEntry> history) {
    }
    
    public final void exportToHtml(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitflow.data.HistoryEntry> history) {
    }
    
    private final void shareFile(android.content.Context context, java.io.File file, java.lang.String mimeType) {
    }
}