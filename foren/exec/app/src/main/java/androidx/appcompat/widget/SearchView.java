package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.customview.view.AbsSavedState;
import c.a.a.C;
import c.a.f;
import c.a.f.Q;
import c.a.f.S;
import c.a.f.T;
import c.a.f.U;
import c.a.f.V;
import c.a.f.W;
import c.a.f.X;
import c.a.f.Y;
import c.a.f.Z;
import c.a.f.aa;
import c.a.f.ba;
import c.a.f.ca;
import c.a.f.da;
import c.a.f.ka;
import c.a.f.xa;
import c.a.g;
import c.a.h;
import c.a.j;
import c.e.h.n;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.TbsListener;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class SearchView extends LinearLayoutCompat implements c.a.e.b {
    public static final a p = new a();
    public Rect A;
    public Rect B;
    public int[] C;
    public int[] D;
    public final ImageView E;
    public final Drawable F;
    public final int G;
    public final int H;
    public final Intent I;
    public final Intent J;
    public final CharSequence K;
    public View.OnFocusChangeListener L;
    public View.OnClickListener M;
    public boolean N;
    public boolean O;
    public c.f.a.a P;
    public boolean Q;
    public CharSequence R;
    public boolean S;
    public boolean T;
    public int U;
    public boolean V;
    public CharSequence W;
    public CharSequence aa;
    public boolean ba;
    public int ca;
    public SearchableInfo da;
    public Bundle ea;
    public final Runnable fa;
    public Runnable ga;
    public final WeakHashMap<String, Drawable.ConstantState> ha;
    public final View.OnClickListener ia;
    public View.OnKeyListener ja;
    public final TextView.OnEditorActionListener ka;
    public final AdapterView.OnItemClickListener la;
    public final AdapterView.OnItemSelectedListener ma;
    public TextWatcher na;
    public final SearchAutoComplete q;
    public final View r;
    public final View s;
    public final View t;
    public final ImageView u;
    public final ImageView v;
    public final ImageView w;
    public final ImageView x;
    public final View y;
    public e z;

    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new ba();

        /* renamed from: a */
        public boolean f156a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder a2 = e.a.a.a.a.a("SearchView.SavedState{");
            a2.append(Integer.toHexString(System.identityHashCode(this)));
            a2.append(" isIconified=");
            a2.append(this.f156a);
            a2.append("}");
            return a2.toString();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f218b, i);
            parcel.writeValue(Boolean.valueOf(this.f156a));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f156a = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    /* loaded from: classes.dex */
    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {

        /* renamed from: d */
        public int f157d;

        /* renamed from: e */
        public SearchView f158e;
        public boolean f;
        public final Runnable g;

        public SearchAutoComplete(Context context) {
            this(context, null, c.a.a.autoCompleteTextViewStyle);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i >= 960 && i2 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i >= 600) {
                return 192;
            }
            if (i < 640 || i2 < 480) {
                return TbsListener.ErrorCode.STARTDOWNLOAD_1;
            }
            return 192;
        }

        public boolean a() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        public void b() {
            if (this.f) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.f = false;
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.f157d <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f) {
                removeCallbacks(this.g);
                post(this.g);
            }
            return onCreateInputConnection;
        }

        @Override // android.view.View
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.f158e.h();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.f158e.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z) {
            Method method;
            super.onWindowFocusChanged(z);
            if (z && this.f158e.hasFocus() && getVisibility() == 0) {
                this.f = true;
                if (SearchView.a(getContext()) && (method = SearchView.p.f161c) != null) {
                    try {
                        method.invoke(this, true);
                    } catch (Exception unused) {
                    }
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public void replaceText(CharSequence charSequence) {
        }

        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.f = false;
                removeCallbacks(this.g);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this)) {
                this.f = false;
                removeCallbacks(this.g);
                inputMethodManager.showSoftInput(this, 0);
            } else {
                this.f = true;
            }
        }

        public void setSearchView(SearchView searchView) {
            this.f158e = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.f157d = i;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, c.a.a.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.g = new ca(this);
            this.f157d = getThreshold();
        }
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        public Method f159a;

        /* renamed from: b */
        public Method f160b;

        /* renamed from: c */
        public Method f161c;

        public a() {
            try {
                this.f159a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f159a.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                this.f160b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f160b.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                this.f161c = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.f161c.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
    }

    /* loaded from: classes.dex */
    public interface c {
    }

    /* loaded from: classes.dex */
    public interface d {
    }

    /* loaded from: classes.dex */
    private static class e extends TouchDelegate {

        /* renamed from: a */
        public final View f162a;

        /* renamed from: e */
        public final int f166e;
        public boolean f;

        /* renamed from: b */
        public final Rect f163b = new Rect();

        /* renamed from: d */
        public final Rect f165d = new Rect();

        /* renamed from: c */
        public final Rect f164c = new Rect();

        public e(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.f166e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            a(rect, rect2);
            this.f162a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.f163b.set(rect);
            this.f165d.set(rect);
            Rect rect3 = this.f165d;
            int i = this.f166e;
            rect3.inset(-i, -i);
            this.f164c.set(rect2);
        }

        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z;
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z2 = true;
            if (action == 0) {
                if (this.f163b.contains(x, y)) {
                    this.f = true;
                    z = true;
                }
                z = false;
            } else if (action == 1 || action == 2) {
                z = this.f;
                if (z && !this.f165d.contains(x, y)) {
                    z2 = false;
                }
            } else {
                if (action == 3) {
                    z = this.f;
                    this.f = false;
                }
                z = false;
            }
            if (!z) {
                return false;
            }
            if (!z2 || this.f164c.contains(x, y)) {
                Rect rect = this.f164c;
                motionEvent.setLocation(x - rect.left, y - rect.top);
            } else {
                motionEvent.setLocation(this.f162a.getWidth() / 2, this.f162a.getHeight() / 2);
            }
            return this.f162a.dispatchTouchEvent(motionEvent);
        }
    }

    public SearchView(Context context) {
        this(context, null, c.a.a.searchViewStyle);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(c.a.d.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(c.a.d.abc_search_view_preferred_width);
    }

    private void setQuery(CharSequence charSequence) {
        this.q.setText(charSequence);
        this.q.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    public void a(CharSequence charSequence, boolean z) {
        this.q.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.q;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.aa = charSequence;
        }
        if (z && !TextUtils.isEmpty(charSequence)) {
            g();
        }
    }

    public final void b(boolean z) {
        this.O = z;
        int i = 8;
        boolean z2 = false;
        int i2 = z ? 0 : 8;
        boolean z3 = !TextUtils.isEmpty(this.q.getText());
        this.u.setVisibility(i2);
        a(z3);
        this.r.setVisibility(z ? 8 : 0);
        if (this.E.getDrawable() != null && !this.N) {
            i = 0;
        }
        this.E.setVisibility(i);
        j();
        if (!z3) {
            z2 = true;
        }
        c(z2);
        m();
    }

    public boolean c() {
        return this.O;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.T = true;
        super.clearFocus();
        this.q.clearFocus();
        this.q.setImeVisibility(false);
        this.T = false;
    }

    public final boolean d() {
        return (this.Q || this.V) && !c();
    }

    public void e() {
        if (!TextUtils.isEmpty(this.q.getText())) {
            this.q.setText(BuildConfig.FLAVOR);
            this.q.requestFocus();
            this.q.setImeVisibility(true);
        } else if (this.N) {
            clearFocus();
            b(true);
        }
    }

    public void f() {
        b(false);
        this.q.requestFocus();
        this.q.setImeVisibility(true);
        View.OnClickListener onClickListener = this.M;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void g() {
        Editable text = this.q.getText();
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            if (this.da != null) {
                a(0, (String) null, text.toString());
            }
            this.q.setImeVisibility(false);
            this.q.dismissDropDown();
        }
    }

    public int getImeOptions() {
        return this.q.getImeOptions();
    }

    public int getInputType() {
        return this.q.getInputType();
    }

    public int getMaxWidth() {
        return this.U;
    }

    public CharSequence getQuery() {
        return this.q.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.R;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.da;
        if (searchableInfo == null || searchableInfo.getHintId() == 0) {
            return this.K;
        }
        return getContext().getText(this.da.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.H;
    }

    public int getSuggestionRowLayout() {
        return this.G;
    }

    public c.f.a.a getSuggestionsAdapter() {
        return this.P;
    }

    public void h() {
        b(c());
        post(this.fa);
        if (this.q.hasFocus()) {
            b();
        }
    }

    public void i() {
        SearchableInfo searchableInfo = this.da;
        if (searchableInfo != null) {
            try {
                if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                    Intent intent = new Intent(this.I);
                    ComponentName searchActivity = searchableInfo.getSearchActivity();
                    intent.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
                    getContext().startActivity(intent);
                } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                    getContext().startActivity(a(this.J, searchableInfo));
                }
            } catch (ActivityNotFoundException unused) {
            }
        }
    }

    public final void j() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.q.getText());
        int i = 0;
        if (!z2 && (!this.N || this.ba)) {
            z = false;
        }
        ImageView imageView = this.w;
        if (!z) {
            i = 8;
        }
        imageView.setVisibility(i);
        Drawable drawable = this.w.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    public void k() {
        int[] iArr = this.q.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.s.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.t.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public final void l() {
        SpannableStringBuilder queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.q;
        if (queryHint == null) {
            queryHint = BuildConfig.FLAVOR;
        }
        if (this.N && this.F != null) {
            int textSize = (int) (this.q.getTextSize() * 1.25d);
            this.F.setBounds(0, 0, textSize, textSize);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
            spannableStringBuilder.setSpan(new ImageSpan(this.F), 1, 2, 33);
            spannableStringBuilder.append(queryHint);
            queryHint = spannableStringBuilder;
        }
        searchAutoComplete.setHint(queryHint);
    }

    public final void m() {
        this.t.setVisibility((!d() || !(this.v.getVisibility() == 0 || this.x.getVisibility() == 0)) ? 8 : 0);
    }

    @Override // c.a.e.b
    public void onActionViewCollapsed() {
        a(BuildConfig.FLAVOR, false);
        clearFocus();
        b(true);
        this.q.setImeOptions(this.ca);
        this.ba = false;
    }

    @Override // c.a.e.b
    public void onActionViewExpanded() {
        if (!this.ba) {
            this.ba = true;
            this.ca = this.q.getImeOptions();
            this.q.setImeOptions(this.ca | 33554432);
            this.q.setText(BuildConfig.FLAVOR);
            setIconified(false);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.fa);
        post(this.ga);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f142d == 1) {
            b(i, i2, i3, i4);
        } else {
            a(i, i2, i3, i4);
        }
        if (z) {
            SearchAutoComplete searchAutoComplete = this.q;
            Rect rect = this.A;
            searchAutoComplete.getLocationInWindow(this.C);
            getLocationInWindow(this.D);
            int[] iArr = this.C;
            int i5 = iArr[1];
            int[] iArr2 = this.D;
            int i6 = i5 - iArr2[1];
            int i7 = iArr[0] - iArr2[0];
            rect.set(i7, i6, searchAutoComplete.getWidth() + i7, searchAutoComplete.getHeight() + i6);
            Rect rect2 = this.B;
            Rect rect3 = this.A;
            rect2.set(rect3.left, 0, rect3.right, i4 - i2);
            e eVar = this.z;
            if (eVar == null) {
                this.z = new e(this.B, this.A, this.q);
                setTouchDelegate(this.z);
                return;
            }
            eVar.a(this.B, this.A);
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        if (!c()) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            if (mode == Integer.MIN_VALUE) {
                int i4 = this.U;
                size = i4 > 0 ? Math.min(i4, size) : Math.min(getPreferredWidth(), size);
            } else if (mode == 0) {
                size = this.U;
                if (size <= 0) {
                    size = getPreferredWidth();
                }
            } else if (mode == 1073741824 && (i3 = this.U) > 0) {
                size = Math.min(i3, size);
            }
            int mode2 = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i2);
            if (mode2 == Integer.MIN_VALUE) {
                size2 = Math.min(getPreferredHeight(), size2);
            } else if (mode2 == 0) {
                size2 = getPreferredHeight();
            }
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
        } else if (this.f142d == 1) {
            d(i, i2);
        } else {
            c(i, i2);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        b(savedState.f156a);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f156a = c();
        return savedState;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        post(this.fa);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i, Rect rect) {
        if (this.T || !isFocusable()) {
            return false;
        }
        if (c()) {
            return super.requestFocus(i, rect);
        }
        boolean requestFocus = this.q.requestFocus(i, rect);
        if (requestFocus) {
            b(false);
        }
        return requestFocus;
    }

    public void setAppSearchData(Bundle bundle) {
        this.ea = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            e();
        } else {
            f();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.N != z) {
            this.N = z;
            b(z);
            l();
        }
    }

    public void setImeOptions(int i) {
        this.q.setImeOptions(i);
    }

    public void setInputType(int i) {
        this.q.setInputType(i);
    }

    public void setMaxWidth(int i) {
        this.U = i;
        requestLayout();
    }

    public void setOnCloseListener(b bVar) {
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.L = onFocusChangeListener;
    }

    public void setOnQueryTextListener(c cVar) {
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.M = onClickListener;
    }

    public void setOnSuggestionListener(d dVar) {
    }

    public void setQueryHint(CharSequence charSequence) {
        this.R = charSequence;
        l();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.S = z;
        c.f.a.a aVar = this.P;
        if (aVar instanceof da) {
            ((da) aVar).r = z ? 2 : 1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a1, code lost:
        if (getContext().getPackageManager().resolveActivity(r2, 65536) != null) goto L_0x00a5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setSearchableInfo(android.app.SearchableInfo r7) {
        /*
            r6 = this;
            r6.da = r7
            android.app.SearchableInfo r7 = r6.da
            r0 = 1
            r1 = 65536(0x10000, float:9.18355E-41)
            r2 = 0
            if (r7 == 0) goto L_0x0073
            androidx.appcompat.widget.SearchView$SearchAutoComplete r3 = r6.q
            int r7 = r7.getSuggestThreshold()
            r3.setThreshold(r7)
            androidx.appcompat.widget.SearchView$SearchAutoComplete r7 = r6.q
            android.app.SearchableInfo r3 = r6.da
            int r3 = r3.getImeOptions()
            r7.setImeOptions(r3)
            android.app.SearchableInfo r7 = r6.da
            int r7 = r7.getInputType()
            r3 = r7 & 15
            if (r3 != r0) goto L_0x0038
            r3 = -65537(0xfffffffffffeffff, float:NaN)
            r7 = r7 & r3
            android.app.SearchableInfo r3 = r6.da
            java.lang.String r3 = r3.getSuggestAuthority()
            if (r3 == 0) goto L_0x0038
            r7 = r7 | r1
            r3 = 524288(0x80000, float:7.34684E-40)
            r7 = r7 | r3
        L_0x0038:
            androidx.appcompat.widget.SearchView$SearchAutoComplete r3 = r6.q
            r3.setInputType(r7)
            c.f.a.a r7 = r6.P
            if (r7 == 0) goto L_0x0044
            r7.a(r2)
        L_0x0044:
            android.app.SearchableInfo r7 = r6.da
            java.lang.String r7 = r7.getSuggestAuthority()
            if (r7 == 0) goto L_0x0070
            c.a.f.da r7 = new c.a.f.da
            android.content.Context r3 = r6.getContext()
            android.app.SearchableInfo r4 = r6.da
            java.util.WeakHashMap<java.lang.String, android.graphics.drawable.Drawable$ConstantState> r5 = r6.ha
            r7.<init>(r3, r6, r4, r5)
            r6.P = r7
            androidx.appcompat.widget.SearchView$SearchAutoComplete r7 = r6.q
            c.f.a.a r3 = r6.P
            r7.setAdapter(r3)
            c.f.a.a r7 = r6.P
            c.a.f.da r7 = (c.a.f.da) r7
            boolean r3 = r6.S
            if (r3 == 0) goto L_0x006c
            r3 = 2
            goto L_0x006d
        L_0x006c:
            r3 = 1
        L_0x006d:
            r7.a(r3)
        L_0x0070:
            r6.l()
        L_0x0073:
            android.app.SearchableInfo r7 = r6.da
            r3 = 0
            if (r7 == 0) goto L_0x00a4
            boolean r7 = r7.getVoiceSearchEnabled()
            if (r7 == 0) goto L_0x00a4
            android.app.SearchableInfo r7 = r6.da
            boolean r7 = r7.getVoiceSearchLaunchWebSearch()
            if (r7 == 0) goto L_0x0089
            android.content.Intent r2 = r6.I
            goto L_0x0093
        L_0x0089:
            android.app.SearchableInfo r7 = r6.da
            boolean r7 = r7.getVoiceSearchLaunchRecognizer()
            if (r7 == 0) goto L_0x0093
            android.content.Intent r2 = r6.J
        L_0x0093:
            if (r2 == 0) goto L_0x00a4
            android.content.Context r7 = r6.getContext()
            android.content.pm.PackageManager r7 = r7.getPackageManager()
            android.content.pm.ResolveInfo r7 = r7.resolveActivity(r2, r1)
            if (r7 == 0) goto L_0x00a4
            goto L_0x00a5
        L_0x00a4:
            r0 = 0
        L_0x00a5:
            r6.V = r0
            boolean r7 = r6.V
            if (r7 == 0) goto L_0x00b2
            androidx.appcompat.widget.SearchView$SearchAutoComplete r7 = r6.q
            java.lang.String r0 = "nm"
            r7.setPrivateImeOptions(r0)
        L_0x00b2:
            boolean r7 = r6.c()
            r6.b(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.setSearchableInfo(android.app.SearchableInfo):void");
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.Q = z;
        b(c());
    }

    public void setSuggestionsAdapter(c.f.a.a aVar) {
        this.P = aVar;
        this.q.setAdapter(this.P);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.searchViewStyle);
    }

    public final void c(boolean z) {
        int i;
        if (!this.V || c() || !z) {
            i = 8;
        } else {
            i = 0;
            this.v.setVisibility(8);
        }
        this.x.setVisibility(i);
    }

    public boolean d(int i) {
        Editable text = this.q.getText();
        Cursor cursor = this.P.f896c;
        if (cursor == null) {
            return true;
        }
        if (cursor.moveToPosition(i)) {
            CharSequence b2 = this.P.b(cursor);
            if (b2 != null) {
                setQuery(b2);
                return true;
            }
            setQuery(text);
            return true;
        }
        setQuery(text);
        return true;
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.A = new Rect();
        this.B = new Rect();
        this.C = new int[2];
        this.D = new int[2];
        this.fa = new S(this);
        this.ga = new T(this);
        this.ha = new WeakHashMap<>();
        this.ia = new W(this);
        this.ja = new X(this);
        this.ka = new Y(this);
        this.la = new Z(this);
        this.ma = new aa(this);
        this.na = new Q(this);
        ka a2 = ka.a(context, attributeSet, j.SearchView, i, 0);
        LayoutInflater.from(context).inflate(a2.f(j.SearchView_layout, g.abc_search_view), (ViewGroup) this, true);
        this.q = (SearchAutoComplete) findViewById(f.search_src_text);
        this.q.setSearchView(this);
        this.r = findViewById(f.search_edit_frame);
        this.s = findViewById(f.search_plate);
        this.t = findViewById(f.submit_area);
        this.u = (ImageView) findViewById(f.search_button);
        this.v = (ImageView) findViewById(f.search_go_btn);
        this.w = (ImageView) findViewById(f.search_close_btn);
        this.x = (ImageView) findViewById(f.search_voice_btn);
        this.E = (ImageView) findViewById(f.search_mag_icon);
        n.a(this.s, a2.b(j.SearchView_queryBackground));
        n.a(this.t, a2.b(j.SearchView_submitBackground));
        this.u.setImageDrawable(a2.b(j.SearchView_searchIcon));
        this.v.setImageDrawable(a2.b(j.SearchView_goIcon));
        this.w.setImageDrawable(a2.b(j.SearchView_closeIcon));
        this.x.setImageDrawable(a2.b(j.SearchView_voiceIcon));
        this.E.setImageDrawable(a2.b(j.SearchView_searchIcon));
        this.F = a2.b(j.SearchView_searchHintIcon);
        C.a((View) this.u, (CharSequence) getResources().getString(h.abc_searchview_description_search));
        this.G = a2.f(j.SearchView_suggestionRowLayout, g.abc_search_dropdown_item_icons_2line);
        this.H = a2.f(j.SearchView_commitIcon, 0);
        this.u.setOnClickListener(this.ia);
        this.w.setOnClickListener(this.ia);
        this.v.setOnClickListener(this.ia);
        this.x.setOnClickListener(this.ia);
        this.q.setOnClickListener(this.ia);
        this.q.addTextChangedListener(this.na);
        this.q.setOnEditorActionListener(this.ka);
        this.q.setOnItemClickListener(this.la);
        this.q.setOnItemSelectedListener(this.ma);
        this.q.setOnKeyListener(this.ja);
        this.q.setOnFocusChangeListener(new U(this));
        setIconifiedByDefault(a2.a(j.SearchView_iconifiedByDefault, true));
        int c2 = a2.c(j.SearchView_android_maxWidth, -1);
        if (c2 != -1) {
            setMaxWidth(c2);
        }
        this.K = a2.e(j.SearchView_defaultQueryHint);
        this.R = a2.e(j.SearchView_queryHint);
        int d2 = a2.d(j.SearchView_android_imeOptions, -1);
        if (d2 != -1) {
            setImeOptions(d2);
        }
        int d3 = a2.d(j.SearchView_android_inputType, -1);
        if (d3 != -1) {
            setInputType(d3);
        }
        setFocusable(a2.a(j.SearchView_android_focusable, true));
        a2.f605b.recycle();
        this.I = new Intent("android.speech.action.WEB_SEARCH");
        this.I.addFlags(268435456);
        this.I.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.J = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.J.addFlags(268435456);
        this.y = findViewById(this.q.getDropDownAnchor());
        View view = this.y;
        if (view != null) {
            view.addOnLayoutChangeListener(new V(this));
        }
        b(this.N);
        l();
    }

    public final void a(boolean z) {
        this.v.setVisibility((!this.Q || !d() || !hasFocus() || (!z && this.V)) ? 8 : 0);
    }

    public void a(CharSequence charSequence) {
        setQuery(charSequence);
    }

    public boolean a(View view, int i, KeyEvent keyEvent) {
        if (this.da != null && this.P != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i == 66 || i == 84 || i == 61) {
                return a(this.q.getListSelection(), 0, (String) null);
            }
            if (i == 21 || i == 22) {
                this.q.setSelection(i == 21 ? 0 : this.q.length());
                this.q.setListSelection(0);
                this.q.clearListSelection();
                a aVar = p;
                SearchAutoComplete searchAutoComplete = this.q;
                Method method = aVar.f161c;
                if (method != null) {
                    try {
                        method.invoke(searchAutoComplete, true);
                    } catch (Exception unused) {
                    }
                }
                return true;
            } else if (i != 19 || this.q.getListSelection() == 0) {
                return false;
            }
        }
        return false;
    }

    public void b(CharSequence charSequence) {
        Editable text = this.q.getText();
        this.aa = text;
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(text);
        a(z2);
        if (z2) {
            z = false;
        }
        c(z);
        j();
        m();
        this.W = charSequence.toString();
    }

    public void b() {
        a aVar = p;
        SearchAutoComplete searchAutoComplete = this.q;
        Method method = aVar.f159a;
        if (method != null) {
            try {
                method.invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused) {
            }
        }
        a aVar2 = p;
        SearchAutoComplete searchAutoComplete2 = this.q;
        Method method2 = aVar2.f160b;
        if (method2 != null) {
            try {
                method2.invoke(searchAutoComplete2, new Object[0]);
            } catch (Exception unused2) {
            }
        }
    }

    public void a() {
        int i;
        int i2;
        if (this.y.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.s.getPaddingLeft();
            Rect rect = new Rect();
            boolean a2 = xa.a(this);
            if (this.N) {
                i = resources.getDimensionPixelSize(c.a.d.abc_dropdownitem_text_padding_left) + resources.getDimensionPixelSize(c.a.d.abc_dropdownitem_icon_width);
            } else {
                i = 0;
            }
            this.q.getDropDownBackground().getPadding(rect);
            if (a2) {
                i2 = -rect.left;
            } else {
                i2 = paddingLeft - (rect.left + i);
            }
            this.q.setDropDownHorizontalOffset(i2);
            this.q.setDropDownWidth((((this.y.getWidth() + rect.left) + rect.right) + i) - paddingLeft);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(int r8, int r9, java.lang.String r10) {
        /*
            r7 = this;
            c.f.a.a r9 = r7.P
            android.database.Cursor r9 = r9.f896c
            if (r9 == 0) goto L_0x0098
            boolean r8 = r9.moveToPosition(r8)
            if (r8 == 0) goto L_0x0098
            r8 = 0
            java.lang.String r10 = "suggest_intent_action"
            java.lang.String r10 = c.a.f.da.a(r9, r10)     // Catch: RuntimeException -> 0x006d
            if (r10 != 0) goto L_0x001b
            android.app.SearchableInfo r10 = r7.da     // Catch: RuntimeException -> 0x006d
            java.lang.String r10 = r10.getSuggestIntentAction()     // Catch: RuntimeException -> 0x006d
        L_0x001b:
            if (r10 != 0) goto L_0x001f
            java.lang.String r10 = "android.intent.action.SEARCH"
        L_0x001f:
            r1 = r10
            java.lang.String r10 = "suggest_intent_data"
            java.lang.String r10 = c.a.f.da.a(r9, r10)     // Catch: RuntimeException -> 0x006d
            if (r10 != 0) goto L_0x002e
            android.app.SearchableInfo r10 = r7.da     // Catch: RuntimeException -> 0x006d
            java.lang.String r10 = r10.getSuggestIntentData()     // Catch: RuntimeException -> 0x006d
        L_0x002e:
            if (r10 == 0) goto L_0x0050
            java.lang.String r0 = "suggest_intent_data_id"
            java.lang.String r0 = c.a.f.da.a(r9, r0)     // Catch: RuntimeException -> 0x006d
            if (r0 == 0) goto L_0x0050
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: RuntimeException -> 0x006d
            r2.<init>()     // Catch: RuntimeException -> 0x006d
            r2.append(r10)     // Catch: RuntimeException -> 0x006d
            java.lang.String r10 = "/"
            r2.append(r10)     // Catch: RuntimeException -> 0x006d
            java.lang.String r10 = android.net.Uri.encode(r0)     // Catch: RuntimeException -> 0x006d
            r2.append(r10)     // Catch: RuntimeException -> 0x006d
            java.lang.String r10 = r2.toString()     // Catch: RuntimeException -> 0x006d
        L_0x0050:
            if (r10 != 0) goto L_0x0054
            r2 = r8
            goto L_0x0059
        L_0x0054:
            android.net.Uri r10 = android.net.Uri.parse(r10)     // Catch: RuntimeException -> 0x006d
            r2 = r10
        L_0x0059:
            java.lang.String r10 = "suggest_intent_query"
            java.lang.String r4 = c.a.f.da.a(r9, r10)     // Catch: RuntimeException -> 0x006d
            java.lang.String r10 = "suggest_intent_extra_data"
            java.lang.String r3 = c.a.f.da.a(r9, r10)     // Catch: RuntimeException -> 0x006d
            r5 = 0
            r6 = 0
            r0 = r7
            android.content.Intent r8 = r0.a(r1, r2, r3, r4, r5, r6)     // Catch: RuntimeException -> 0x006d
            goto L_0x0088
        L_0x006d:
            int r9 = r9.getPosition()     // Catch: RuntimeException -> 0x0072
            goto L_0x0073
        L_0x0072:
            r9 = -1
        L_0x0073:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "Search suggestions cursor at row "
            r10.append(r0)
            r10.append(r9)
            java.lang.String r9 = " returned exception."
            r10.append(r9)
            r10.toString()
        L_0x0088:
            if (r8 != 0) goto L_0x008b
            goto L_0x0098
        L_0x008b:
            android.content.Context r9 = r7.getContext()     // Catch: RuntimeException -> 0x0093
            r9.startActivity(r8)     // Catch: RuntimeException -> 0x0093
            goto L_0x0098
        L_0x0093:
            java.lang.String r9 = "Failed launch activity: "
            e.a.a.a.a.b(r9, r8)
        L_0x0098:
            androidx.appcompat.widget.SearchView$SearchAutoComplete r8 = r7.q
            r9 = 0
            r8.setImeVisibility(r9)
            androidx.appcompat.widget.SearchView$SearchAutoComplete r8 = r7.q
            r8.dismissDropDown()
            r8 = 1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.a(int, int, java.lang.String):boolean");
    }

    public void a(int i, String str, String str2) {
        getContext().startActivity(a("android.intent.action.SEARCH", (Uri) null, (String) null, str2, i, str));
    }

    public final Intent a(String str, Uri uri, String str2, String str3, int i, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.aa);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.ea;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i != 0) {
            intent.putExtra("action_key", i);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.da.getSearchActivity());
        return intent;
    }

    public final Intent a(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.ea;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        int i = 1;
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String str = null;
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        if (searchableInfo.getVoiceMaxResults() != 0) {
            i = searchableInfo.getVoiceMaxResults();
        }
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", i);
        if (searchActivity != null) {
            str = searchActivity.flattenToShortString();
        }
        intent3.putExtra("calling_package", str);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    public static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
}
