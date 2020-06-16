package com.zzq.base.fragment;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.zzq.base.R;
import com.zzq.base.base.BaseDialogFragment;
import com.zzq.base.interfaces.ClickProxy;
import com.zzq.base.interfaces.OnSelectCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputMessageDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    private OnSelectCallback mOnSelectCallback;
    private EditText mEtInputMessage;
    public static final String TITLE = "title";
    public static final String TEXT = "text";
    public static final String HAS_TITLE = "has_title";
    public static final String HINT = "hintText";
    private InputFilter[] mInputFilters = new InputFilter[0];
    private String alarmText;
    private int inputLength;

    /**
     * 禁止输入换行符 废弃！
     * 原因：在华为，包括荣耀等部分机型在Android 10系统出现了输入"mi"字符后，出现的字符错乱，并且难以删除已经显示到
     * EditText上的内容，后面直接采用setOnEditorActionListener方式来处理该DialogFragment禁止换行问题
     *
     * @since 20200428
     * <p>
     * 如上来自MSensor项目
     */
//    private InputFilter noHasNewLineFilter = (source, start, end, dest, dstart, dend) -> source.toString().replace("\n", "");
    public static InputMessageDialogFragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(HAS_TITLE, false);
        bundle.putString(TEXT, "");
        bundle.putString(HINT, "");
        InputMessageDialogFragment dialog = new InputMessageDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    public static InputMessageDialogFragment newInstance(String title, @Nullable CharSequence text, String hintText) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(TEXT, text == null ? "" : text.toString());
        bundle.putString(HINT, hintText);
        InputMessageDialogFragment dialog = new InputMessageDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    public static InputMessageDialogFragment newInstance(@Nullable CharSequence text, String hintText) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(HAS_TITLE, false);
        bundle.putString(TEXT, text == null ? "" : text.toString());
        bundle.putString(HINT, hintText);
        InputMessageDialogFragment dialog = new InputMessageDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_input_message, container, false);

        Bundle bundle = getArguments();
        if (bundle == null) {
            throw new IllegalArgumentException("未使用newInstance方法创建实例！");
        }
        TextView tvTitle = view.findViewById(R.id.tv_dialog_input_psw_title);
        TextView tvCancel = view.findViewById(R.id.tv_dialog_input_psw_cancel);
        TextView tvOk = view.findViewById(R.id.tv_dialog_input_psw_ok);
        TextView tvAlarmText = view.findViewById(R.id.tv_alrm_text);
        mEtInputMessage = view.findViewById(R.id.et_dialog_input_psw);
        mEtInputMessage.setText("");
        //20200428 禁止输入换行符 更换，原来是通过InputFilter
        mEtInputMessage.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    return true;
                }
                return false;
            }
        });

        if (mInputFilters.length != 0) {
            mEtInputMessage.setFilters(mInputFilters);
        }

        String title = bundle.getString(TITLE);
        String text = bundle.getString(TEXT);
        String hintText = bundle.getString(HINT);

        if (TextUtils.isEmpty(alarmText)) {
            tvAlarmText.setVisibility(View.GONE);
        } else {
            tvAlarmText.setVisibility(View.VISIBLE);
            tvAlarmText.setText(alarmText);
        }

        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(hintText)) {
            mEtInputMessage.setHint(hintText);
        }

        if (!TextUtils.isEmpty(text)) {
            mEtInputMessage.setText(text);
            if (inputLength <= text.length()) {
                mEtInputMessage.setSelection(inputLength);
            } else {
                mEtInputMessage.setSelection(text.length());
            }
        }
        if (!bundle.getBoolean(HAS_TITLE, true)) {
            tvTitle.setVisibility(View.GONE);
        }

        tvCancel.setOnClickListener(new ClickProxy(this));
        tvOk.setOnClickListener(new ClickProxy(this));

        getDialog().setCanceledOnTouchOutside(false);
        return view;
    }

    public void setOnSelectCallback(OnSelectCallback onSelectCallback) {
        mOnSelectCallback = onSelectCallback;
    }

    public void setInputFilter(@NonNull final String specialChat) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                String speChat="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
//                String speChat="[:/?*？：]";
                Pattern pattern = Pattern.compile(specialChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) {
                    return "";
                } else {
                    return null;
                }
//                return source.toString().replace("\n", "");
            }
        };

        addInputFilter(filter);
    }

    /**
     * 设置输入文字长度，但每个Dialog仅能调用一次，或者以设置的最短长度为基准(特指每个DialogFragment实例）
     *
     * @param length 要设置的文字长度
     */
    public void setInputLength(int length) {
        //用InputFilter来限制输入长度
        inputLength = length;
        addInputFilter(new InputFilter.LengthFilter(length));
    }

    private void addInputFilter(InputFilter inputFilter) {
        InputFilter[] newInputFilters = new InputFilter[mInputFilters.length + 1];
        System.arraycopy(mInputFilters, 0, newInputFilters, 0, mInputFilters.length);
        newInputFilters[newInputFilters.length - 1] = inputFilter;
        mInputFilters = newInputFilters;
    }

    public void setAlarmText(String text) {
        alarmText = text;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_dialog_input_psw_cancel) {
            if (mOnSelectCallback != null) {
                mOnSelectCallback.onNegative();
            }
        } else if (id == R.id.tv_dialog_input_psw_ok) {
            String input = mEtInputMessage.getText().toString();
            if (mOnSelectCallback != null) {
                mOnSelectCallback.onPositive(input);
            }
        }
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        return super.show(transaction, tag);
    }

    @Override
    public void dismiss() {
        if (getDialog() != null && getDialog().isShowing()) {
            super.dismiss();
        }
    }
}
