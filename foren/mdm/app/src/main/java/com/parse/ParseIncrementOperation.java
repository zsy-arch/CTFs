package com.parse;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class ParseIncrementOperation implements ParseFieldOperation {
    private final Number amount;

    public ParseIncrementOperation(Number amount) {
        this.amount = amount;
    }

    @Override // com.parse.ParseFieldOperation
    public JSONObject encode(ParseEncoder objectEncoder) throws JSONException {
        JSONObject output = new JSONObject();
        output.put("__op", "Increment");
        output.put("amount", this.amount);
        return output;
    }

    @Override // com.parse.ParseFieldOperation
    public ParseFieldOperation mergeWithPrevious(ParseFieldOperation previous) {
        if (previous == null) {
            return this;
        }
        if (previous instanceof ParseDeleteOperation) {
            return new ParseSetOperation(this.amount);
        }
        if (previous instanceof ParseSetOperation) {
            Object oldValue = ((ParseSetOperation) previous).getValue();
            if (oldValue instanceof Number) {
                return new ParseSetOperation(Numbers.add((Number) oldValue, this.amount));
            }
            throw new IllegalArgumentException("You cannot increment a non-number.");
        } else if (previous instanceof ParseIncrementOperation) {
            return new ParseIncrementOperation(Numbers.add(((ParseIncrementOperation) previous).amount, this.amount));
        } else {
            throw new IllegalArgumentException("Operation is invalid after previous operation.");
        }
    }

    @Override // com.parse.ParseFieldOperation
    public Object apply(Object oldValue, String key) {
        if (oldValue == null) {
            return this.amount;
        }
        if (oldValue instanceof Number) {
            return Numbers.add((Number) oldValue, this.amount);
        }
        throw new IllegalArgumentException("You cannot increment a non-number.");
    }
}
