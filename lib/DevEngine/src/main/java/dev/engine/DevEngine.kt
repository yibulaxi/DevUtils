package dev.engine

/**
 * detail: DevEngine
 * @author Ttt
 * <p></p>
 * GitHub
 * @see https://github.com/afkT/DevUtils
 * DevApp Api
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevApp/README.md
 * DevAssist Api
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevAssist/README.md
 * DevBase README
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevBase/README.md
 * DevBaseMVVM README
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevBaseMVVM/README.md
 * DevEngine README
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevEngine/README.md
 * DevHttpCapture Api
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevHttpCapture/README.md
 * DevJava Api
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevJava/README.md
 * DevWidget Api
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevWidget/README_API.md
 * DevEnvironment Api
 * @see https://github.com/afkT/DevUtils/blob/master/lib/Environment
 */
object DevEngine {

    // ============
    // = 工具类版本 =
    // ============

    /**
     * 获取 DevEngine 版本号
     * @return DevEngine versionCode
     */
    fun getDevEngineVersionCode(): Int {
        return BuildConfig.DevEngine_VersionCode
    }

    /**
     * 获取 DevEngine 版本
     * @return DevEngine versionName
     */
    fun getDevEngineVersion(): String {
        return BuildConfig.DevEngine_Version
    }

    /**
     * 获取 DevApp 版本号
     * @return DevApp versionCode
     */
    fun getDevAppVersionCode(): Int {
        return BuildConfig.DevApp_VersionCode
    }

    /**
     * 获取 DevApp 版本
     * @return DevApp versionName
     */
    fun getDevAppVersion(): String {
        return BuildConfig.DevApp_Version
    }

    // ============
    // = 初始化方法 =
    // ============

    fun initialize() {

    }
}