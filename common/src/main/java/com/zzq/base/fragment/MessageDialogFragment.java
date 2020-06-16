package com.zzq.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zzq.base.R;
import com.zzq.base.base.BaseDialogFragment;
import com.zzq.base.interfaces.ClickProxy;
import com.zzq.base.interfaces.DialogCallback;

public class MessageDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    private DialogCallback mDialogCallback;
    private static MessageDialogFragment instance;
    private static boolean isStrict = false;
    private TextView mTvDialogMessage;

    public static MessageDialogFragment newInstance(String message) {
        isStrict = true;
        instance = new MessageDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        instance.setArguments(bundle);
        return instance;
    }

    public MessageDialogFragment() {
        if (!isStrict) {
            throw new IllegalArgumentException("要使用newInstance创建MessageDialogFragment！");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_message, container, false);

        mTvDialogMessage = view.findViewById(R.id.tv_dialog_message);
        TextView tvDialogOk = view.findViewById(R.id.tv_dialog_btn_ok);
        TextView tvDialogCancel = view.findViewById(R.id.tv_dialog_btn_cancel);

        tvDialogOk.setOnClickListener(new ClickProxy(this));
        tvDialogCancel.setOnClickListener(new ClickProxy(this));

        Bundle bundle = getArguments();
        if (bundle != null && bundle.getString("message") != null) {
            mTvDialogMessage.setText(bundle.getString("message"));
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setMessage(@NonNull String message) {
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        setArguments(bundle);
    }

    public void setDialogCallback(DialogCallback dialogCallback) {
        mDialogCallback = dialogCallback;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_dialog_btn_ok) {
            if (mDialogCallback != null) {
                mDialogCallback.confirm();
            }
            dismiss();
        } else if (id == R.id.tv_dialog_btn_cancel) {
            if (mDialogCallback != null) {
                mDialogCallback.cancel();
            }
            dismiss();
        }
    }
}
