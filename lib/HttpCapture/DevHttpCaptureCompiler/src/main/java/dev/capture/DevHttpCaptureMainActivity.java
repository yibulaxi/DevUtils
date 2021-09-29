package dev.capture;

import android.os.Bundle;

import androidx.annotation.Nullable;

import dev.DevHttpCapture;
import dev.DevHttpCaptureCompiler;
import dev.callback.DevCallback;
import dev.capture.compiler.R;
import dev.capture.compiler.databinding.DevHttpCaptureMainActivityBinding;
import dev.utils.DevFinal;
import dev.utils.app.BarUtils;
import dev.utils.app.ResourceUtils;
import dev.utils.app.toast.ToastTintUtils;
import dev.utils.common.StringUtils;

/**
 * detail: DevHttpCapture 入口
 * @author Ttt
 */
public class DevHttpCaptureMainActivity
        extends BaseDevHttpActivity {

    private DevHttpCaptureMainActivityBinding mBinding;
    // 当前选中的 Module
    private String                            mModule;
    // 首页适配器
    private AdapterMainModule                 mAdapter  = new AdapterMainModule();
    // 查询回调
    private DevCallback<Boolean>              mCallback = new DevCallback<Boolean>() {
        @Override
        public void callback(Boolean isQuerying) {
            if (!isFinishing()) {
                if (isQuerying) {
                    ToastTintUtils.normal(
                            ResourceUtils.getString(R.string.dev_http_capture_querying)
                    );
                    return;
                }
                // 设置数据源
                mAdapter.setDataList(UtilsCompiler.getInstance().getMainData(mModule));
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DevHttpCaptureMainActivityBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        // 设置状态栏颜色
        BarUtils.setStatusBarColor(this, ResourceUtils.getColor(R.color.dev_http_capture_title_bg));
        // 获取模块名
        mModule = getIntent().getStringExtra(DevFinal.MODULE);

        // 设置点击事件
        mBinding.title.vidBackIgview.setOnClickListener(view -> DevHttpCaptureCompiler.finishAllActivity());
        // 设置标题
        if (StringUtils.isEmpty(mModule)) {
            mBinding.title.vidTitleTv.setText(DevHttpCapture.TAG);
        } else {
            mBinding.title.vidTitleTv.setText(mModule);
        }
        // 初始化适配器
        mBinding.vidRecycler.setAdapter(mAdapter);

        // ==========
        // = 数据获取 =
        // ==========

        UtilsCompiler.getInstance().queryData(
                mCallback, true
        );
    }
}