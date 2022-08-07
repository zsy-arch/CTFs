package c.a.f;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import c.a.f;
import c.f.a.c;
import com.tencent.smtt.sdk.BuildConfig;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class da extends c implements View.OnClickListener {
    public final SearchView l;
    public final SearchableInfo m;
    public final Context n;
    public final WeakHashMap<String, Drawable.ConstantState> o;
    public final int p;
    public ColorStateList s;
    public boolean q = false;
    public int r = 1;
    public int t = -1;
    public int u = -1;
    public int v = -1;
    public int w = -1;
    public int x = -1;
    public int y = -1;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a */
        public final TextView f585a;

        /* renamed from: b */
        public final TextView f586b;

        /* renamed from: c */
        public final ImageView f587c;

        /* renamed from: d */
        public final ImageView f588d;

        /* renamed from: e */
        public final ImageView f589e;

        public a(View view) {
            this.f585a = (TextView) view.findViewById(16908308);
            this.f586b = (TextView) view.findViewById(16908309);
            this.f587c = (ImageView) view.findViewById(16908295);
            this.f588d = (ImageView) view.findViewById(16908296);
            this.f589e = (ImageView) view.findViewById(f.edit_query);
        }
    }

    public da(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        SearchManager searchManager = (SearchManager) this.f897d.getSystemService("search");
        this.l = searchView;
        this.m = searchableInfo;
        this.p = searchView.getSuggestionCommitIconResId();
        this.n = context;
        this.o = weakHashMap;
    }

    @Override // c.f.a.c, c.f.a.a
    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View inflate = this.k.inflate(this.i, viewGroup, false);
        inflate.setTag(new a(inflate));
        ((ImageView) inflate.findViewById(f.edit_query)).setImageResource(this.p);
        return inflate;
    }

    @Override // c.f.a.a
    public CharSequence b(Cursor cursor) {
        String a2;
        String a3;
        if (cursor == null) {
            return null;
        }
        String a4 = a(cursor, cursor.getColumnIndex("suggest_intent_query"));
        if (a4 != null) {
            return a4;
        }
        if (this.m.shouldRewriteQueryFromData() && (a3 = a(cursor, cursor.getColumnIndex("suggest_intent_data"))) != null) {
            return a3;
        }
        if (!this.m.shouldRewriteQueryFromText() || (a2 = a(cursor, cursor.getColumnIndex("suggest_text_1"))) == null) {
            return null;
        }
        return a2;
    }

    public final void c(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras == null || extras.getBoolean("in_progress")) {
        }
    }

    @Override // c.f.a.a, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        try {
            if (!this.f894a) {
                return null;
            }
            this.f896c.moveToPosition(i);
            if (view == null) {
                Context context = this.f897d;
                Cursor cursor = this.f896c;
                view = this.k.inflate(this.j, viewGroup, false);
            }
            a(view, this.f897d, this.f896c);
            return view;
        } catch (RuntimeException e2) {
            Context context2 = this.f897d;
            Cursor cursor2 = this.f896c;
            View inflate = this.k.inflate(this.j, viewGroup, false);
            if (inflate != null) {
                ((a) inflate.getTag()).f585a.setText(e2.toString());
            }
            return inflate;
        }
    }

    @Override // c.f.a.a, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (RuntimeException e2) {
            View a2 = a(this.f897d, this.f896c, viewGroup);
            if (a2 != null) {
                ((a) a2.getTag()).f585a.setText(e2.toString());
            }
            return a2;
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        c(this.f896c);
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        c(this.f896c);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.l.a((CharSequence) tag);
        }
    }

    public void a(int i) {
        this.r = i;
    }

    public Cursor a(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? BuildConfig.FLAVOR : charSequence.toString();
        if (this.l.getVisibility() == 0 && this.l.getWindowVisibility() == 0) {
            try {
                Cursor a2 = a(this.m, charSequence2, 50);
                if (a2 != null) {
                    a2.getCount();
                    return a2;
                }
            } catch (RuntimeException unused) {
            }
        }
        return null;
    }

    @Override // c.f.a.a
    public void a(Cursor cursor) {
        if (!this.q) {
            try {
                super.a(cursor);
                if (cursor != null) {
                    this.t = cursor.getColumnIndex("suggest_text_1");
                    this.u = cursor.getColumnIndex("suggest_text_2");
                    this.v = cursor.getColumnIndex("suggest_text_2_url");
                    this.w = cursor.getColumnIndex("suggest_icon_1");
                    this.x = cursor.getColumnIndex("suggest_icon_2");
                    this.y = cursor.getColumnIndex("suggest_flags");
                }
            } catch (Exception unused) {
            }
        } else if (cursor != null) {
            cursor.close();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0147  */
    @Override // c.f.a.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.view.View r19, android.content.Context r20, android.database.Cursor r21) {
        /*
            Method dump skipped, instructions count: 444
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.f.da.a(android.view.View, android.content.Context, android.database.Cursor):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.drawable.Drawable a(java.lang.String r6) {
        /*
            Method dump skipped, instructions count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.f.da.a(java.lang.String):android.graphics.drawable.Drawable");
    }

    public static String a(Cursor cursor, String str) {
        return a(cursor, cursor.getColumnIndex(str));
    }

    public static String a(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception unused) {
            return null;
        }
    }

    public Drawable a(Uri uri) {
        int parseInt;
        String authority = uri.getAuthority();
        if (!TextUtils.isEmpty(authority)) {
            try {
                Resources resourcesForApplication = this.f897d.getPackageManager().getResourcesForApplication(authority);
                List<String> pathSegments = uri.getPathSegments();
                if (pathSegments != null) {
                    int size = pathSegments.size();
                    if (size == 1) {
                        try {
                            parseInt = Integer.parseInt(pathSegments.get(0));
                        } catch (NumberFormatException unused) {
                            throw new FileNotFoundException(e.a.a.a.a.a("Single path segment is not a resource ID: ", uri));
                        }
                    } else if (size == 2) {
                        parseInt = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
                    } else {
                        throw new FileNotFoundException(e.a.a.a.a.a("More than two path segments: ", uri));
                    }
                    if (parseInt != 0) {
                        return resourcesForApplication.getDrawable(parseInt);
                    }
                    throw new FileNotFoundException(e.a.a.a.a.a("No resource found for: ", uri));
                }
                throw new FileNotFoundException(e.a.a.a.a.a("No path: ", uri));
            } catch (PackageManager.NameNotFoundException unused2) {
                throw new FileNotFoundException(e.a.a.a.a.a("No package found for authority: ", uri));
            }
        } else {
            throw new FileNotFoundException(e.a.a.a.a.a("No authority: ", uri));
        }
    }

    public Cursor a(SearchableInfo searchableInfo, String str, int i) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder fragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query(BuildConfig.FLAVOR).fragment(BuildConfig.FLAVOR);
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
        }
        if (i > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i));
        }
        return this.f897d.getContentResolver().query(fragment.build(), null, suggestSelection, strArr, null);
    }
}
