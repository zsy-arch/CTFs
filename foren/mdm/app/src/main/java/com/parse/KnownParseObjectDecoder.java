package com.parse;

import java.util.Map;

/* loaded from: classes2.dex */
class KnownParseObjectDecoder extends ParseDecoder {
    private Map<String, ParseObject> fetchedObjects;

    public KnownParseObjectDecoder(Map<String, ParseObject> fetchedObjects) {
        this.fetchedObjects = fetchedObjects;
    }

    @Override // com.parse.ParseDecoder
    protected ParseObject decodePointer(String className, String objectId) {
        return (this.fetchedObjects == null || !this.fetchedObjects.containsKey(objectId)) ? super.decodePointer(className, objectId) : this.fetchedObjects.get(objectId);
    }
}
