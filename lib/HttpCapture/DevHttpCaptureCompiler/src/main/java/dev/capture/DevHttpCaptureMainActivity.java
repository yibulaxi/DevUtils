package dev.capture;

import android.os.Bundle;

import androidx.annotation.Nullable;

import dev.DevHttpCapture;
import dev.DevHttpCaptureCompiler;
import dev.capture.compiler.R;
import dev.capture.compiler.databinding.DevHttpCaptureMainActivityBinding;
import dev.utils.DevFinal;
import dev.utils.app.BarUtils;
import dev.utils.app.ResourceUtils;
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
        mBinding.title.vidDhcitBackIgview.setOnClickListener(view -> DevHttpCaptureCompiler.finishAllActivity());
        // 设置标题
        if (StringUtils.isEmpty(mModule)) {
            mBinding.title.vidDhcitTitleTv.setText(DevHttpCapture.TAG);
        } else {
            mBinding.title.vidDhcitTitleTv.setText(mModule);
        }
    }
}