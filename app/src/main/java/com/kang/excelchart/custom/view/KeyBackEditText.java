package com.kang.excelchart.custom.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * 类描述：
 */
@SuppressLint("AppCompatCustomView")
public class KeyBackEditText  extends EditText {
    public KeyBackEditText(Context context) {
        super(context);
    }
    public KeyBackEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public KeyBackEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // ((Activity)this.getContext()).onKeyDown(KeyEvent.KEYCODE_BACK, event);
            ((Activity)this.getContext()).onBackPressed();
            return true;
        }
        return super.dispatchKeyEventPreIme(event);
    }
}
