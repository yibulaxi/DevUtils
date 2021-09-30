package dev.capture;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import dev.adapter.DevDataAdapterExt;
import dev.capture.compiler.databinding.DevHttpCaptureMainModuleListAdapterBinding;
import dev.utils.DevFinal;
import dev.utils.app.helper.quick.QuickHelper;

/**
 * detail: DevHttpCapture 模块列表适配器
 * @author Ttt
 */
public class AdapterMainModuleList
        extends DevDataAdapterExt<CaptureItem, BaseDevHttpViewHolder<DevHttpCaptureMainModuleListAdapterBinding>> {

    private final Items.MainItem mMainItem;

    public AdapterMainModuleList(Items.MainItem mainItem) {
        this.mMainItem = mainItem;
        setDataList(mainItem.lists, false);
    }

    @NonNull
    @Override
    public BaseDevHttpViewHolder<DevHttpCaptureMainModuleListAdapterBinding> onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        parentContext(parent);
        return new BaseDevHttpViewHolder<>(
                DevHttpCaptureMainModuleListAdapterBinding.inflate(
                        LayoutInflater.from(mContext), parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(
            @NonNull BaseDevHttpViewHolder<DevHttpCaptureMainModuleListAdapterBinding> holder,
            int position
    ) {
        CaptureItem item = getDataItem(position);
        QuickHelper.get(holder.binding.vidTitleTv)
                .setText(item.getYyyyMMdd())
                .setOnClick(view -> {
                    // 跳转 抓包数据列表 Activity
                    start(mContext, mMainItem.moduleName, item.getYyyyMMdd());
                });
    }

    // ==========
    // = 其他方法 =
    // ==========

    /**
     * 跳转 抓包数据列表 Activity
     * @param context    {@link Context}
     * @param moduleName 模块名 ( 要求唯一性 )
     * @param date       yyyyMMdd
     */
    private void start(
            final Context context,
            final String moduleName,
            final String date
    ) {
        try {
            Intent intent = new Intent(context, DevHttpCaptureListActivity.class);
            intent.putExtra(DevFinal.MODULE, moduleName);
            intent.putExtra(DevFinal.DATE, date);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception ignored) {
        }
    }
}