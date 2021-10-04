package dev.capture;

import java.util.List;

import dev.capture.compiler.R;
import dev.utils.app.ResourceUtils;
import dev.utils.common.ConvertUtils;
import dev.utils.common.StringUtils;

class Items {

    /**
     * 数据来源类型
     */
    public enum DataType {

        // 全部
        T_ALL("1"),

        // 0-14 分钟
        T_0_14("2"),

        // 15-29 分钟
        T_15_29("3"),

        // 30-44 分钟
        T_30_44("4"),

        // 45-59 分钟
        T_45_59("5"),

        ;

        public String type;

        DataType(String type) {
            this.type = type;
        }

        public String getTitle() {
            int res = R.string.dev_http_capture_data_type_all;
            switch (this) {
                case T_0_14:
                    res = R.string.dev_http_capture_data_type_0_14;
                    break;
                case T_15_29:
                    res = R.string.dev_http_capture_data_type_15_29;
                    break;
                case T_30_44:
                    res = R.string.dev_http_capture_data_type_30_44;
                    break;
                case T_45_59:
                    res = R.string.dev_http_capture_data_type_45_59;
                    break;
            }
            return ResourceUtils.getString(res);
        }
    }

    /**
     * 分组条件类型
     */
    public enum GroupType {

        // 抓包时间
        T_TIME("1"),

        // 请求链接, 以请求接口前缀 ( ? 号前 )
        T_URL("2"),

        ;

        public String type;

        GroupType(String type) {
            this.type = type;
        }

        public String getTitle() {
            int res = R.string.dev_http_capture_group_type_time;
            switch (this) {
                case T_URL:
                    res = R.string.dev_http_capture_group_type_url;
                    break;
            }
            return ResourceUtils.getString(res);
        }
    }

    /**
     * 首页适配器数据包装类
     */
    public static class MainItem {
        public String            moduleName;
        public List<CaptureItem> lists;

        public MainItem(
                final String moduleName,
                final List<CaptureItem> lists
        ) {
            this.moduleName = moduleName;
            this.lists      = lists;
        }
    }

    /**
     * 抓包列表数据包装类
     */
    public static class GroupItem {
        public String            title;
        public List<CaptureFile> lists;
        public String            function;

        public GroupItem(
                final String title,
                final List<CaptureFile> lists
        ) {
            this.title = convertTitleByHHMM(title);
            this.lists = lists;
        }

        public GroupItem setFunction(final String function) {
            this.function = function;
            return this;
        }
    }

    /**
     * 抓包文件数据包装类
     */
    public static class FileItem {
        public String title;
        public String value;

        public FileItem(
                final String title,
                final String value
        ) {
            this.title = title;
            this.value = value;
        }
    }

    // =============
    // = 内部转换方法 =
    // =============

    /**
     * 根据时间转换数据类型
     * @param key HHMM
     * @return 数据类型
     */
    protected static DataType convertDataType(final String key) {
        // 获取分钟
        String minute = StringUtils.substring(key, 2, 4, false);
        int    mm     = ConvertUtils.toInt(minute, -1);
        if (mm == -1) return null;
        // 存储间隔以 15 分钟为单位
        if (mm < 15) { // 00-14
            return DataType.T_0_14;
        } else if (mm < 30) { // 15-29
            return DataType.T_15_29;
        } else if (mm < 45) { // 30-44
            return DataType.T_30_44;
        } else { // 45-59
            return DataType.T_45_59;
        }
    }

    /**
     * 通过时间转换标题
     * <pre>
     *     例
     *     1000 转换为 10 hour 00-14 minute
     *     1015 转换为 10 hour 15-29 minute
     *     1030 转换为 10 hour 30-44 minute
     *     1045 转换为 10 hour 45-59 minute
     * </pre>
     * @param title HHMM
     * @return 转换后的标题
     */
    protected static String convertTitleByHHMM(final String title) {
        if (StringUtils.isLength(title, 4)) {
            StringBuilder builder = new StringBuilder();
            builder.append(title, 0, 2).append(" hour ");
            // 获取分钟
            String minute = StringUtils.substring(title, 2, 4, false);
            int    mm     = ConvertUtils.toInt(minute);
            // 存储间隔以 15 分钟为单位
            if (mm < 15) {
                minute = "00-14"; // 00-14
            } else if (mm < 30) {
                minute = "15-29"; // 15-29
            } else if (mm < 45) {
                minute = "30-44"; // 30-44
            } else {
                minute = "45-59"; // 45-59
            }
            builder.append(minute).append(" minute");
            return builder.toString();
        }
        return title;
    }

    /**
     * 拆分 Url 用于匹配接口所属功能注释
     * @param url 请求接口链接
     * @return 处理后的 Url
     */
    protected static String convertUrlKey(final String url) {
        if (!StringUtils.isSpace(url)) {
            String key = StringUtils.split(url, "\\?", 0);
            return StringUtils.replaceEndsWith(key, "/", "");
        }
        return null;
    }
}