package afkt_replace.core.lib.debug.provider

import afkt_replace.core.engine.debug.DevDebugEngine
import afkt_replace.core.lib.debug.engine.DevDebugImpl
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class InitProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        DevDebugEngine.setEngine(DevDebugImpl())
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(
        uri: Uri,
        values: ContentValues?
    ): Uri? {
        return null
    }

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return -1
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return -1
    }
}