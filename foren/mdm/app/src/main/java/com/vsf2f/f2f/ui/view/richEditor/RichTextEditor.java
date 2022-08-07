package com.vsf2f.f2f.ui.view.richEditor;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.area.ScreenUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class RichTextEditor extends InterceptLinearLayout {
    private static final int EDIT_PADDING = 10;
    private final int DEFAULT_IMAGE_HEIGHT;
    private final int DEFAULT_MARGING;
    private LinearLayout allLayout;
    private View.OnClickListener btnListener;
    private Context context;
    private int disappearingImageIndex;
    private int editNormalPadding;
    private View.OnFocusChangeListener focusListener;
    private LayoutInflater inflater;
    private View.OnKeyListener keyListener;
    private EditText lastFocusEdit;
    private LayoutClickListener mLayoutClickListener;
    private LayoutTransition mTransitioner;
    private int viewTagIndex;

    /* loaded from: classes2.dex */
    public interface LayoutClickListener {
        void layoutClick();
    }

    public void setLayoutClickListener(LayoutClickListener mLayoutClickListener) {
        this.mLayoutClickListener = mLayoutClickListener;
    }

    public RichTextEditor(Context context) {
        this(context, null);
    }

    public RichTextEditor(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RichTextEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.viewTagIndex = 1;
        this.editNormalPadding = 0;
        this.disappearingImageIndex = 0;
        this.DEFAULT_MARGING = dip2px(15.0f);
        this.DEFAULT_IMAGE_HEIGHT = dip2px(170.0f);
        this.context = context;
        init();
    }

    @Override // com.vsf2f.f2f.ui.view.richEditor.InterceptLinearLayout
    public void setIntercept(boolean b) {
        super.setIntercept(b);
    }

    private void init() {
        this.inflater = LayoutInflater.from(this.context);
        this.allLayout = this;
        this.allLayout.setOrientation(1);
        this.allLayout.setBackgroundColor(-1);
        setupLayoutTransitions();
        this.keyListener = new View.OnKeyListener() { // from class: com.vsf2f.f2f.ui.view.richEditor.RichTextEditor.1
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() != 0 || event.getKeyCode() != 67) {
                    return false;
                }
                RichTextEditor.this.onBackspacePress((EditText) v);
                return false;
            }
        };
        this.btnListener = new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.view.richEditor.RichTextEditor.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                RichTextEditor.this.onImageCloseClick((RelativeLayout) v.getParent());
            }
        };
        this.focusListener = new View.OnFocusChangeListener() { // from class: com.vsf2f.f2f.ui.view.richEditor.RichTextEditor.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    RichTextEditor.this.lastFocusEdit = (EditText) v;
                }
            }
        };
        LinearLayout.LayoutParams firstEditParam = new LinearLayout.LayoutParams(-1, -1);
        this.editNormalPadding = dip2px(10.0f);
        EditText firstEdit = createEditText();
        this.allLayout.addView(firstEdit, firstEditParam);
        this.lastFocusEdit = firstEdit;
    }

    @Override // com.vsf2f.f2f.ui.view.richEditor.InterceptLinearLayout, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 1:
                if (this.mLayoutClickListener != null) {
                    this.mLayoutClickListener.layoutClick();
                    break;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBackspacePress(EditText editTxt) {
        if (editTxt.getSelectionStart() == 0) {
            View preView = this.allLayout.getChildAt(this.allLayout.indexOfChild(editTxt) - 1);
            if (preView == null) {
                return;
            }
            if (preView instanceof RelativeLayout) {
                onImageCloseClick(preView);
            } else if (preView instanceof EditText) {
                String str1 = editTxt.getText().toString();
                EditText preEdit = (EditText) preView;
                String str2 = preEdit.getText().toString();
                this.allLayout.setLayoutTransition(null);
                this.allLayout.removeView(editTxt);
                this.allLayout.setLayoutTransition(this.mTransitioner);
                preEdit.setText(str2 + str1);
                preEdit.requestFocus();
                preEdit.setSelection(str2.length(), str2.length());
                this.lastFocusEdit = preEdit;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onImageCloseClick(View view) {
        if (!this.mTransitioner.isRunning()) {
            this.disappearingImageIndex = this.allLayout.indexOfChild(view);
            this.allLayout.removeViewAt(this.disappearingImageIndex);
            this.allLayout.removeView(view);
        }
    }

    private EditText createEditText() {
        EditText editText = (EditText) this.inflater.inflate(R.layout.richtextedit_textview, (ViewGroup) null);
        editText.setOnKeyListener(this.keyListener);
        int i = this.viewTagIndex;
        this.viewTagIndex = i + 1;
        editText.setTag(Integer.valueOf(i));
        editText.setOnFocusChangeListener(this.focusListener);
        return editText;
    }

    private EditText createEditText(String hint, int paddingTop) {
        EditText editText = createEditText();
        editText.setPadding(this.editNormalPadding, paddingTop, this.editNormalPadding, this.editNormalPadding);
        editText.setHint(hint);
        return editText;
    }

    private RelativeLayout createImageLayout() {
        RelativeLayout layout = (RelativeLayout) this.inflater.inflate(R.layout.richtextedit_imageview, (ViewGroup) null);
        int i = this.viewTagIndex;
        this.viewTagIndex = i + 1;
        layout.setTag(Integer.valueOf(i));
        View closeView = layout.findViewById(R.id.image_close);
        closeView.setTag(layout.getTag());
        closeView.setOnClickListener(this.btnListener);
        return layout;
    }

    public void insertImage(String imagePath) {
        insertImage(getScaledBitmap(imagePath, ScreenUtils.widthPixels(this.context)), imagePath);
    }

    public void insertImage(List<String> pathList) {
        for (int i = 0; i < pathList.size(); i++) {
            String imagePath = pathList.get(i);
            insertImage(getScaledBitmap(imagePath, ScreenUtils.widthPixels(this.context)), imagePath);
        }
    }

    public void insertText(String text) {
        View itemView = this.allLayout.getChildAt(this.allLayout.getChildCount() - 1);
        if (itemView instanceof EditText) {
            ((EditText) itemView).setText(text);
        }
    }

    private void insertImage(Bitmap bitmap, String imagePath) {
        String lastEditStr = this.lastFocusEdit.getText().toString();
        String editStr1 = lastEditStr.substring(0, this.lastFocusEdit.getSelectionStart()).trim();
        int lastEditIndex = this.allLayout.indexOfChild(this.lastFocusEdit);
        this.lastFocusEdit.setText(lastEditStr);
        addEditTextAtIndex(lastEditIndex + 1, "");
        addImageViewAtIndex(lastEditIndex + 1, bitmap, imagePath);
        this.lastFocusEdit.requestFocus();
        this.lastFocusEdit.setSelection(editStr1.length(), editStr1.length());
        hideKeyBoard();
    }

    public void hideKeyBoard() {
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.lastFocusEdit.getWindowToken(), 0);
    }

    private void addEditTextAtIndex(int index, String editStr) {
        EditText editText2 = createEditText();
        editText2.setText(editStr);
        this.lastFocusEdit = editText2;
        this.allLayout.setLayoutTransition(null);
        this.allLayout.addView(editText2, index);
        this.allLayout.setLayoutTransition(this.mTransitioner);
    }

    private void addImageViewAtIndex(int index, Bitmap bmp, String imagePath) {
        RelativeLayout imageLayout = createImageLayout();
        DataImageView imageView = (DataImageView) imageLayout.findViewById(R.id.edit_imageView);
        imageView.setImageBitmap(bmp);
        imageView.setBitmap(bmp);
        imageView.setAbsolutePath(imagePath);
        this.allLayout.addView(imageLayout, index);
    }

    public void insertImageByURL(String url) {
        if (url != null) {
            RelativeLayout imageLayout = createImageLayout();
            DataImageView imageView = (DataImageView) imageLayout.findViewById(R.id.edit_imageView);
            imageView.setImageResource(R.mipmap.ico_edit);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            this.allLayout.addView(imageLayout);
            addEditTextAtIndex(-1, "");
            Glide.with(getContext()).load(Integer.valueOf((int) R.drawable.icon_addpic_focused)).override(getWidth() - (this.DEFAULT_MARGING * 2), this.DEFAULT_IMAGE_HEIGHT).error((int) R.drawable.img_empty).centerCrop().crossFade().into(imageView);
        }
    }

    private Bitmap getScaledBitmap(String filePath, int width) {
        int sampleSize = 1;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        if (options.outWidth > width) {
            sampleSize = (options.outWidth / width) + 1;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeFile(filePath, options);
    }

    private void setupLayoutTransitions() {
        this.mTransitioner = new LayoutTransition();
        this.allLayout.setLayoutTransition(this.mTransitioner);
        this.mTransitioner.addTransitionListener(new LayoutTransition.TransitionListener() { // from class: com.vsf2f.f2f.ui.view.richEditor.RichTextEditor.4
            @Override // android.animation.LayoutTransition.TransitionListener
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
            }

            @Override // android.animation.LayoutTransition.TransitionListener
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                if (transition.isRunning() || transitionType == 1) {
                }
            }
        });
        this.mTransitioner.setDuration(300L);
    }

    private void mergeEditText() {
        String mergeText;
        View preView = this.allLayout.getChildAt(this.disappearingImageIndex - 1);
        View nextView = this.allLayout.getChildAt(this.disappearingImageIndex);
        if (preView != null && (preView instanceof EditText) && nextView != null && (nextView instanceof EditText)) {
            EditText preEdit = (EditText) preView;
            EditText nextEdit = (EditText) nextView;
            String str1 = preEdit.getText().toString();
            String str2 = nextEdit.getText().toString();
            if (str2.length() > 0) {
                mergeText = str1 + "\n" + str2;
            } else {
                mergeText = str1;
            }
            this.allLayout.setLayoutTransition(null);
            this.allLayout.removeView(nextEdit);
            preEdit.setText(mergeText);
            preEdit.requestFocus();
            preEdit.setSelection(str1.length(), str1.length());
            this.allLayout.setLayoutTransition(this.mTransitioner);
        }
    }

    public int dip2px(float dipValue) {
        return (int) ((dipValue * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public List<EditData> buildEditData() {
        List<EditData> dataList = new ArrayList<>();
        int num = this.allLayout.getChildCount();
        for (int index = 0; index < num; index++) {
            View itemView = this.allLayout.getChildAt(index);
            EditData itemData = new EditData();
            if (itemView instanceof EditText) {
                String content = ((EditText) itemView).getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    itemData.inputStr = content;
                    dataList.add(itemData);
                }
            } else if (itemView instanceof RelativeLayout) {
                itemData.imagePath = ((DataImageView) itemView.findViewById(R.id.edit_imageView)).getAbsolutePath();
                dataList.add(itemData);
            }
        }
        return dataList;
    }

    public HashMap<String, Object> getRichEditData() {
        HashMap<String, Object> data = new HashMap<>();
        StringBuilder editTextSB = new StringBuilder();
        List<String> imgUrls = new ArrayList<>();
        int num = this.allLayout.getChildCount();
        for (int index = 0; index < num; index++) {
            View itemView = this.allLayout.getChildAt(index);
            if (itemView instanceof EditText) {
                editTextSB.append(((EditText) itemView).getText().toString());
            } else if (itemView instanceof RelativeLayout) {
                imgUrls.add(((DataImageView) itemView.findViewById(R.id.edit_imageView)).getAbsolutePath());
                editTextSB.append((char) 26);
            }
        }
        data.put("text", editTextSB);
        data.put("imgUrls", imgUrls);
        return data;
    }

    /* loaded from: classes2.dex */
    public class EditData {
        Bitmap bitmap;
        String imagePath;
        String inputStr;

        public EditData() {
        }

        public String getInputStr() {
            return this.inputStr;
        }

        public void setInputStr(String inputStr) {
            this.inputStr = inputStr;
        }

        public String getImagePath() {
            return this.imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
    }
}
