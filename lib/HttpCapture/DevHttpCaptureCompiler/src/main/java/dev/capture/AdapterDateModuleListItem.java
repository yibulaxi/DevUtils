package dev.capture;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import dev.adapter.DevDataAdapterExt;
import dev.capture.compiler.databinding.DevHttpCaptureDateModuleListItemAdapterBinding;
import dev.utils.app.helper.view.ViewHelper;
import dev.utils.common.DateUtils;

/**
 * detail: DevHttpCapture 模块列表适配器
 * @author Ttt
 */
public class AdapterDateModuleListItem
        extends DevDataAdapterExt<CaptureFile, BaseDevHttpViewHolder<DevHttpCaptureDateModuleListItemAdapterBinding>> {

    public AdapterDateModuleListItem(Items.GroupItem groupItem) {
        setDataList(groupItem.lists, false);
    }

    @NonNull
    @Override
    public BaseDevHttpViewHolder<DevHttpCaptureDateModuleListItemAdapterBinding> onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        parentContext(parent);
        return new BaseDevHttpViewHolder<>(
                DevHttpCaptureDateModuleListItemAdapterBinding.inflate(
                        LayoutInflater.from(mContext), parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(
            @NonNull BaseDevHttpViewHolder<DevHttpCaptureDateModuleListItemAdapterBinding> holder,
            int position
    ) {
        CaptureFile item = getDataItem(position);
        ViewHelper.get()
                .setVisibilitys(isFirstPosition(position), holder.binding.vidLineView)
                .setText(item.getMethod(), holder.binding.vidMethodTv)
                .setText(item.getUrl(), holder.binding.vidUrlTv)
                .setText(DateUtils.formatTime(item.getTime()), holder.binding.vidTimeTv)
                .setOnClick(view -> {
                    // 跳转 抓包数据列表 Activity
//                    start(mContext, mMainItem.moduleName, item.getYyyyMMdd());
                });
    }
}