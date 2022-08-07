package d.a.a.c.a;

import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import e.b.a.d;
import e.b.a.e;
import java.util.List;

/* loaded from: classes.dex */
public class c extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    public List<f> f1605a;

    /* renamed from: b  reason: collision with root package name */
    public int f1606b;

    /* renamed from: c  reason: collision with root package name */
    public int f1607c;

    public c(List<f> list) {
        this.f1605a = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f1605a.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.f1605a.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        f fVar = this.f1605a.get(i);
        View inflate = View.inflate(viewGroup.getContext(), e.permission_info_item, null);
        int blue = Color.blue(this.f1607c);
        int green = Color.green(this.f1607c);
        int red = Color.red(this.f1607c);
        ImageView imageView = (ImageView) inflate.findViewById(d.icon);
        imageView.setColorFilter(new ColorMatrixColorFilter(new float[]{1.0f, 0.0f, 0.0f, 0.0f, red, 0.0f, 1.0f, 0.0f, 0.0f, green, 0.0f, 0.0f, 1.0f, 0.0f, blue, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f}));
        TextView textView = (TextView) inflate.findViewById(d.name);
        int i2 = this.f1606b;
        if (i2 != 0) {
            textView.setTextColor(i2);
        }
        imageView.setImageResource(fVar.f1611c);
        textView.setText(fVar.f1609a);
        return inflate;
    }
}
