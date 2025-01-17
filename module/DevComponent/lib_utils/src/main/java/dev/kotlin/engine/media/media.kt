package dev.kotlin.engine.media

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import dev.engine.DevEngine
import dev.engine.media.IMediaEngine

// ==========================================
// = IMediaEngine<EngineConfig, EngineData> =
// ==========================================

/**
 * 通过 Key 获取 Media Engine
 * @param engine String?
 * @return IMediaEngine<EngineConfig, EngineData>
 * 内部做了处理如果匹配不到则返回默认 Media Engine
 */
internal fun getEngine(engine: String?): IMediaEngine<in IMediaEngine.EngineConfig, in IMediaEngine.EngineData>? {
    DevEngine.getMedia(engine)?.let { value ->
        return value
    }
    return DevEngine.getMedia()
}

// ====================
// = Key Media Engine =
// ====================

// =============
// = 对外公开方法 =
// =============

fun Activity.media_openCamera(
    engine: String? = null
): Boolean {
    return getEngine(engine)?.openCamera(this) ?: false
}

fun <Config : IMediaEngine.EngineConfig> Activity.media_openCamera(
    engine: String? = null,
    config: Config?
): Boolean {
    return getEngine(engine)?.openCamera(this, config) ?: false
}

fun Fragment.media_openCamera(
    engine: String? = null
): Boolean {
    return getEngine(engine)?.openCamera(this) ?: false
}

fun <Config : IMediaEngine.EngineConfig> Fragment.media_openCamera(
    engine: String? = null,
    config: Config?
): Boolean {
    return getEngine(engine)?.openCamera(this, config) ?: false
}

// =

fun Activity.media_openGallery(
    engine: String? = null
): Boolean {
    return getEngine(engine)?.openGallery(this) ?: false
}

fun <Config : IMediaEngine.EngineConfig> Activity.media_openGallery(
    engine: String? = null,
    config: Config?
): Boolean {
    return getEngine(engine)?.openGallery(this, config) ?: false
}

fun Fragment.media_openGallery(
    engine: String? = null
): Boolean {
    return getEngine(engine)?.openGallery(this) ?: false
}

fun <Config : IMediaEngine.EngineConfig> Fragment.media_openGallery(
    engine: String? = null,
    config: Config?
): Boolean {
    return getEngine(engine)?.openGallery(this, config) ?: false
}

// ==========
// = 配置方法 =
// ==========

fun <Config : IMediaEngine.EngineConfig> media_getConfig(
    engine: String? = null
): Config? {
    return getEngine(engine)?.config as? Config
}

fun <Config : IMediaEngine.EngineConfig> media_setConfig(
    engine: String? = null,
    config: Config?
) {
    getEngine(engine)?.config = config
}

fun media_getCameraSavePath(
    engine: String? = null
): String? {
    return getEngine(engine)?.cameraSavePath
}

fun media_getCompressSavePath(
    engine: String? = null
): String? {
    return getEngine(engine)?.compressSavePath
}

fun media_setSavePath(
    engine: String? = null,
    cameraSavePath: String?,
    compressSavePath: String?
) {
    getEngine(engine)?.setSavePath(
        cameraSavePath, compressSavePath
    )
}

fun media_getMinimumCompressSize(
    engine: String? = null
): Int {
    return getEngine(engine)?.minimumCompressSize ?: 0
}

fun media_setMinimumCompressSize(
    engine: String? = null,
    minimumCompressSize: Int
) {
    getEngine(engine)?.minimumCompressSize = minimumCompressSize
}

// ==========
// = 其他方法 =
// ==========

fun Context.media_deleteCacheDirFile(
    engine: String? = null,
    type: Int
) {
    getEngine(engine)?.deleteCacheDirFile(this, type)
}

fun Context.media_deleteAllCacheDirFile(
    engine: String? = null
) {
    getEngine(engine)?.deleteAllCacheDirFile(this)
}

fun media_isMediaSelectorResult(
    engine: String? = null,
    requestCode: Int,
    resultCode: Int
): Boolean {
    return getEngine(engine)?.isMediaSelectorResult(requestCode, resultCode) ?: false
}

// =

fun <Data : IMediaEngine.EngineData> Intent.media_getSelectors(
    engine: String? = null
): List<Data?>? {
    return getEngine(engine)?.getSelectors(this) as? List<Data?>
}

fun Intent.media_getSelectorPaths(
    engine: String? = null,
    original: Boolean
): List<String?>? {
    return getEngine(engine)?.getSelectorPaths(this, original)
}

fun <Data : IMediaEngine.EngineData> Intent.media_getSingleSelector(
    engine: String? = null
): Data? {
    return getEngine(engine)?.getSingleSelector(this) as? Data
}

fun Intent.media_getSingleSelectorPath(
    engine: String? = null,
    original: Boolean
): String? {
    return getEngine(engine)?.getSingleSelectorPath(this, original)
}