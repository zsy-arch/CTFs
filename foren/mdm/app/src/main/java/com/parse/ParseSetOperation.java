package com.parse;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseSetOperation implements ParseFieldOperation {
    private final Object value;

    public ParseSetOperation(Object newValue) {
        this.value = newValue;
    }

    public Object getValue() {
        return this.value;
    }

    @Override // com.parse.ParseFieldOperation
    public Object encode(ParseEncoder objectEncoder) {
        return objectEncoder.encode(this.value);
    }

    @Override // com.parse.ParseFieldOperation
    public ParseFieldOperation mergeWithPrevious(ParseFieldOperation previous) {
        return this;
    }

    @Override // com.parse.ParseFieldOperation
    public Object apply(Object oldValue, String key) {
        return this.value;
    }
}
