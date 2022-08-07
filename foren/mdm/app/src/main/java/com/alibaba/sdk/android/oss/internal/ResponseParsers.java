package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.AppendObjectResult;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.CopyObjectResult;
import com.alibaba.sdk.android.oss.model.CreateBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.GetBucketACLResult;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.HeadObjectResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.OSSObjectSummary;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PartSummary;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import com.em.RedPacketConstant;
import com.hy.http.HttpEntity;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import okhttp3.Response;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: classes.dex */
public final class ResponseParsers {
    public static final DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();

    /* loaded from: classes.dex */
    public static final class PutObjectResponseParser extends AbstractResponseParser<PutObjectResult> {
        public PutObjectResult parseData(Response response, PutObjectResult result) throws IOException {
            result.setETag(ResponseParsers.trimQuotes(response.header("ETag")));
            if (response.body().contentLength() > 0) {
                result.setServerCallbackReturnBody(response.body().string());
            }
            return result;
        }
    }

    /* loaded from: classes.dex */
    public static final class AppendObjectResponseParser extends AbstractResponseParser<AppendObjectResult> {
        public AppendObjectResult parseData(Response response, AppendObjectResult result) throws IOException {
            String nextPosition = response.header(OSSHeaders.OSS_NEXT_APPEND_POSITION);
            if (nextPosition != null) {
                result.setNextPosition(Long.valueOf(nextPosition));
            }
            result.setObjectCRC64(response.header(OSSHeaders.OSS_HASH_CRC64_ECMA));
            return result;
        }
    }

    /* loaded from: classes.dex */
    public static final class HeadObjectResponseParser extends AbstractResponseParser<HeadObjectResult> {
        public HeadObjectResult parseData(Response response, HeadObjectResult result) throws IOException {
            result.setMetadata(ResponseParsers.parseObjectMetadata(result.getResponseHeader()));
            return result;
        }
    }

    /* loaded from: classes.dex */
    public static final class GetObjectResponseParser extends AbstractResponseParser<GetObjectResult> {
        public GetObjectResult parseData(Response response, GetObjectResult result) throws IOException {
            result.setMetadata(ResponseParsers.parseObjectMetadata(result.getResponseHeader()));
            result.setContentLength(response.body().contentLength());
            result.setObjectContent(response.body().byteStream());
            return result;
        }

        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public boolean needCloseResponse() {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static final class CopyObjectResponseParser extends AbstractResponseParser<CopyObjectResult> {
        public CopyObjectResult parseData(Response response, CopyObjectResult result) throws Exception {
            return ResponseParsers.parseCopyObjectResponseXML(response.body().byteStream(), result);
        }
    }

    /* loaded from: classes.dex */
    public static final class CreateBucketResponseParser extends AbstractResponseParser<CreateBucketResult> {
        public CreateBucketResult parseData(Response response, CreateBucketResult result) throws IOException {
            if (result.getResponseHeader().containsKey("Location")) {
                result.bucketLocation = result.getResponseHeader().get("Location");
            }
            return result;
        }
    }

    /* loaded from: classes.dex */
    public static final class DeleteBucketResponseParser extends AbstractResponseParser<DeleteBucketResult> {
        public DeleteBucketResult parseData(Response response, DeleteBucketResult result) throws IOException {
            return result;
        }
    }

    /* loaded from: classes.dex */
    public static final class GetBucketACLResponseParser extends AbstractResponseParser<GetBucketACLResult> {
        public GetBucketACLResult parseData(Response response, GetBucketACLResult result) throws Exception {
            return ResponseParsers.parseGetBucketACLResponse(response.body().byteStream(), result);
        }
    }

    /* loaded from: classes.dex */
    public static final class DeleteObjectResponseParser extends AbstractResponseParser<DeleteObjectResult> {
        public DeleteObjectResult parseData(Response response, DeleteObjectResult result) throws IOException {
            return result;
        }
    }

    /* loaded from: classes.dex */
    public static final class ListObjectsResponseParser extends AbstractResponseParser<ListObjectsResult> {
        public ListObjectsResult parseData(Response response, ListObjectsResult result) throws Exception {
            return ResponseParsers.parseObjectListResponse(response.body().byteStream(), result);
        }
    }

    /* loaded from: classes.dex */
    public static final class InitMultipartResponseParser extends AbstractResponseParser<InitiateMultipartUploadResult> {
        public InitiateMultipartUploadResult parseData(Response response, InitiateMultipartUploadResult result) throws Exception {
            return ResponseParsers.parseInitMultipartResponseXML(response.body().byteStream(), result);
        }
    }

    /* loaded from: classes.dex */
    public static final class UploadPartResponseParser extends AbstractResponseParser<UploadPartResult> {
        public UploadPartResult parseData(Response response, UploadPartResult result) throws IOException {
            result.setETag(ResponseParsers.trimQuotes(response.header("ETag")));
            return result;
        }
    }

    /* loaded from: classes.dex */
    public static final class AbortMultipartUploadResponseParser extends AbstractResponseParser<AbortMultipartUploadResult> {
        public AbortMultipartUploadResult parseData(Response response, AbortMultipartUploadResult result) throws IOException {
            return result;
        }
    }

    /* loaded from: classes.dex */
    public static final class CompleteMultipartUploadResponseParser extends AbstractResponseParser<CompleteMultipartUploadResult> {
        public CompleteMultipartUploadResult parseData(Response response, CompleteMultipartUploadResult result) throws Exception {
            if (response.header("Content-Type").equals(HttpEntity.APPLICATION_XML)) {
                return ResponseParsers.parseCompleteMultipartUploadResponseXML(response.body().byteStream(), result);
            }
            if (response.body() == null) {
                return result;
            }
            result.setServerCallbackReturnBody(response.body().string());
            return result;
        }
    }

    /* loaded from: classes.dex */
    public static final class ListPartsResponseParser extends AbstractResponseParser<ListPartsResult> {
        public ListPartsResult parseData(Response response, ListPartsResult result) throws Exception {
            return ResponseParsers.parseListPartsResponseXML(response.body().byteStream(), result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CopyObjectResult parseCopyObjectResponseXML(InputStream in, CopyObjectResult result) throws ParseException, ParserConfigurationException, IOException, SAXException {
        Element element = domFactory.newDocumentBuilder().parse(in).getDocumentElement();
        OSSLog.logDebug("[item] - " + element.getNodeName());
        NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            String name = item.getNodeName();
            if (name != null) {
                if (name.equals("LastModified")) {
                    result.setLastModified(DateUtil.parseIso8601Date(checkChildNotNullAndGetValue(item)));
                } else if (name.equals("ETag")) {
                    result.setEtag(checkChildNotNullAndGetValue(item));
                }
            }
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ListPartsResult parseListPartsResponseXML(InputStream in, ListPartsResult result) throws ParserConfigurationException, IOException, SAXException, ParseException {
        String size;
        Element element = domFactory.newDocumentBuilder().parse(in).getDocumentElement();
        OSSLog.logDebug("[parseObjectListResponse] - " + element.getNodeName());
        List<PartSummary> partEtagList = new ArrayList<>();
        NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            String name = item.getNodeName();
            if (name != null) {
                if (name.equals("Bucket")) {
                    result.setBucketName(checkChildNotNullAndGetValue(item));
                } else if (name.equals("Key")) {
                    result.setKey(checkChildNotNullAndGetValue(item));
                } else if (name.equals("UploadId")) {
                    result.setUploadId(checkChildNotNullAndGetValue(item));
                } else if (name.equals("PartNumberMarker")) {
                    String partNumberMarker = checkChildNotNullAndGetValue(item);
                    if (partNumberMarker != null) {
                        result.setPartNumberMarker(Integer.valueOf(partNumberMarker).intValue());
                    }
                } else if (name.equals("NextPartNumberMarker")) {
                    String nextPartNumberMarker = checkChildNotNullAndGetValue(item);
                    if (nextPartNumberMarker != null) {
                        result.setNextPartNumberMarker(Integer.valueOf(nextPartNumberMarker).intValue());
                    }
                } else if (name.equals("MaxParts")) {
                    String maxParts = checkChildNotNullAndGetValue(item);
                    if (maxParts != null) {
                        result.setMaxParts(Integer.valueOf(maxParts).intValue());
                    }
                } else if (name.equals("IsTruncated")) {
                    String isTruncated = checkChildNotNullAndGetValue(item);
                    if (isTruncated != null) {
                        result.setTruncated(Boolean.valueOf(isTruncated).booleanValue());
                    }
                } else if (name.equals("StorageClass")) {
                    String storageClass = checkChildNotNullAndGetValue(item);
                    if (storageClass != null) {
                        result.setStorageClass(storageClass);
                    }
                } else if (name.equals("Part")) {
                    NodeList partNodeList = item.getChildNodes();
                    PartSummary partSummary = new PartSummary();
                    for (int k = 0; k < partNodeList.getLength(); k++) {
                        Node partItem = partNodeList.item(k);
                        String partItemName = partItem.getNodeName();
                        if (partItemName != null) {
                            if (partItemName.equals("PartNumber")) {
                                String partNumber = checkChildNotNullAndGetValue(partItem);
                                if (partNumber != null) {
                                    partSummary.setPartNumber(Integer.valueOf(partNumber).intValue());
                                }
                            } else if (partItemName.equals("LastModified")) {
                                partSummary.setLastModified(DateUtil.parseIso8601Date(checkChildNotNullAndGetValue(partItem)));
                            } else if (partItemName.equals("ETag")) {
                                partSummary.setETag(checkChildNotNullAndGetValue(partItem));
                            } else if (partItemName.equals("Size") && (size = checkChildNotNullAndGetValue(partItem)) != null) {
                                partSummary.setSize(Integer.valueOf(size).intValue());
                            }
                        }
                    }
                    partEtagList.add(partSummary);
                }
            }
        }
        result.setParts(partEtagList);
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CompleteMultipartUploadResult parseCompleteMultipartUploadResponseXML(InputStream in, CompleteMultipartUploadResult result) throws ParserConfigurationException, IOException, SAXException {
        Element element = domFactory.newDocumentBuilder().parse(in).getDocumentElement();
        OSSLog.logDebug("[item] - " + element.getNodeName());
        NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            String name = item.getNodeName();
            if (name != null) {
                if (name.equalsIgnoreCase("Location")) {
                    result.setLocation(checkChildNotNullAndGetValue(item));
                } else if (name.equalsIgnoreCase("Bucket")) {
                    result.setBucketName(checkChildNotNullAndGetValue(item));
                } else if (name.equalsIgnoreCase("Key")) {
                    result.setObjectKey(checkChildNotNullAndGetValue(item));
                } else if (name.equalsIgnoreCase("ETag")) {
                    result.setETag(checkChildNotNullAndGetValue(item));
                }
            }
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static InitiateMultipartUploadResult parseInitMultipartResponseXML(InputStream in, InitiateMultipartUploadResult result) throws IOException, SAXException, ParserConfigurationException {
        Element element = domFactory.newDocumentBuilder().parse(in).getDocumentElement();
        OSSLog.logDebug("[item] - " + element.getNodeName());
        NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            String name = item.getNodeName();
            if (name != null) {
                if (name.equalsIgnoreCase("UploadId")) {
                    result.setUploadId(checkChildNotNullAndGetValue(item));
                } else if (name.equalsIgnoreCase("Bucket")) {
                    result.setBucketName(checkChildNotNullAndGetValue(item));
                } else if (name.equalsIgnoreCase("Key")) {
                    result.setObjectKey(checkChildNotNullAndGetValue(item));
                }
            }
        }
        return result;
    }

    private static OSSObjectSummary parseObjectSummaryXML(NodeList list) throws ParseException {
        OSSObjectSummary object = new OSSObjectSummary();
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            String name = item.getNodeName();
            if (name != null) {
                if (name.equals("Key")) {
                    object.setKey(checkChildNotNullAndGetValue(item));
                } else if (name.equals("LastModified")) {
                    object.setLastModified(DateUtil.parseIso8601Date(checkChildNotNullAndGetValue(item)));
                } else if (name.equals("Size")) {
                    String size = checkChildNotNullAndGetValue(item);
                    if (size != null) {
                        object.setSize(Integer.valueOf(size).intValue());
                    }
                } else if (name.equals("ETag")) {
                    object.setETag(checkChildNotNullAndGetValue(item));
                } else if (name.equals("Type")) {
                    object.setType(checkChildNotNullAndGetValue(item));
                } else if (name.equals("StorageClass")) {
                    object.setStorageClass(checkChildNotNullAndGetValue(item));
                }
            }
        }
        return object;
    }

    private static String parseCommonPrefixXML(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            String name = item.getNodeName();
            if (name != null && name.equals("Prefix")) {
                return checkChildNotNullAndGetValue(item);
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GetBucketACLResult parseGetBucketACLResponse(InputStream in, GetBucketACLResult result) throws ParserConfigurationException, IOException, SAXException, ParseException {
        Element element = domFactory.newDocumentBuilder().parse(in).getDocumentElement();
        OSSLog.logDebug("[parseGetBucketACLResponse - " + element.getNodeName());
        NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            String name = item.getNodeName();
            if (name != null) {
                if (name.equals("Owner")) {
                    NodeList ownerList = item.getChildNodes();
                    for (int j = 0; j < ownerList.getLength(); j++) {
                        Node ownerItem = ownerList.item(j);
                        String ownerName = ownerItem.getNodeName();
                        if (ownerName != null) {
                            if (ownerName.equals(RedPacketConstant.EXTRA_RED_PACKET_ID)) {
                                result.setBucketOwnerID(checkChildNotNullAndGetValue(ownerItem));
                            } else if (ownerName.equals("DisplayName")) {
                                result.setBucketOwner(checkChildNotNullAndGetValue(ownerItem));
                            }
                        }
                    }
                } else if (name.equals("AccessControlList")) {
                    NodeList aclList = item.getChildNodes();
                    for (int k = 0; k < aclList.getLength(); k++) {
                        Node aclItem = aclList.item(k);
                        String aclName = aclItem.getNodeName();
                        if (aclName != null && aclName.equals("Grant")) {
                            result.setBucketACL(checkChildNotNullAndGetValue(aclItem));
                        }
                    }
                }
            }
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ListObjectsResult parseObjectListResponse(InputStream in, ListObjectsResult result) throws ParserConfigurationException, IOException, SAXException, ParseException {
        String prefix;
        Element element = domFactory.newDocumentBuilder().parse(in).getDocumentElement();
        OSSLog.logDebug("[parseObjectListResponse] - " + element.getNodeName());
        result.clearCommonPrefixes();
        result.clearObjectSummaries();
        NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            String name = item.getNodeName();
            if (name != null) {
                if (name.equals("Name")) {
                    result.setBucketName(checkChildNotNullAndGetValue(item));
                } else if (name.equals("Prefix")) {
                    result.setPrefix(checkChildNotNullAndGetValue(item));
                } else if (name.equals("Marker")) {
                    result.setMarker(checkChildNotNullAndGetValue(item));
                } else if (name.equals("Delimiter")) {
                    result.setDelimiter(checkChildNotNullAndGetValue(item));
                } else if (name.equals("EncodingType")) {
                    result.setEncodingType(checkChildNotNullAndGetValue(item));
                } else if (name.equals("MaxKeys")) {
                    String maxKeys = checkChildNotNullAndGetValue(item);
                    if (maxKeys != null) {
                        result.setMaxKeys(Integer.valueOf(maxKeys).intValue());
                    }
                } else if (name.equals("NextMarker")) {
                    result.setNextMarker(checkChildNotNullAndGetValue(item));
                } else if (name.equals("IsTruncated")) {
                    String isTruncated = checkChildNotNullAndGetValue(item);
                    if (isTruncated != null) {
                        result.setTruncated(Boolean.valueOf(isTruncated).booleanValue());
                    }
                } else if (name.equals("Contents")) {
                    if (item.getChildNodes() != null) {
                        result.addObjectSummary(parseObjectSummaryXML(item.getChildNodes()));
                    }
                } else if (!(!name.equals("CommonPrefixes") || item.getChildNodes() == null || (prefix = parseCommonPrefixXML(item.getChildNodes())) == null)) {
                    result.addCommonPrefix(prefix);
                }
            }
        }
        return result;
    }

    public static String trimQuotes(String s) {
        if (s == null) {
            return null;
        }
        String s2 = s.trim();
        if (s2.startsWith("\"")) {
            s2 = s2.substring(1);
        }
        return s2.endsWith("\"") ? s2.substring(0, s2.length() - 1) : s2;
    }

    public static ObjectMetadata parseObjectMetadata(Map<String, String> headers) throws IOException {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            for (String key : headers.keySet()) {
                if (key.indexOf(OSSHeaders.OSS_USER_METADATA_PREFIX) >= 0) {
                    objectMetadata.addUserMetadata(key, headers.get(key));
                } else if (key.equals("Last-Modified") || key.equals("Date")) {
                    try {
                        objectMetadata.setHeader(key, DateUtil.parseRfc822Date(headers.get(key)));
                    } catch (ParseException pe) {
                        throw new IOException(pe.getMessage(), pe);
                    }
                } else if (key.equals("Content-Length")) {
                    objectMetadata.setHeader(key, Long.valueOf(headers.get(key)));
                } else if (key.equals("ETag")) {
                    objectMetadata.setHeader(key, trimQuotes(headers.get(key)));
                } else {
                    objectMetadata.setHeader(key, headers.get(key));
                }
            }
            return objectMetadata;
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public static ServiceException parseResponseErrorXML(Response response, boolean isHeadRequest) throws IOException {
        int statusCode = response.code();
        String requestId = response.header(OSSHeaders.OSS_HEADER_REQUEST_ID);
        String code = null;
        String message = null;
        String hostId = null;
        String errorMessage = null;
        if (!isHeadRequest) {
            try {
                errorMessage = response.body().string();
                NodeList list = domFactory.newDocumentBuilder().parse(new InputSource(new StringReader(errorMessage))).getDocumentElement().getChildNodes();
                for (int i = 0; i < list.getLength(); i++) {
                    Node item = list.item(i);
                    String name = item.getNodeName();
                    if (name != null) {
                        if (name.equals("Code")) {
                            code = checkChildNotNullAndGetValue(item);
                        }
                        if (name.equals("Message")) {
                            message = checkChildNotNullAndGetValue(item);
                        }
                        if (name.equals("RequestId")) {
                            requestId = checkChildNotNullAndGetValue(item);
                        }
                        if (name.equals("HostId")) {
                            hostId = checkChildNotNullAndGetValue(item);
                        }
                    }
                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e2) {
                e2.printStackTrace();
            }
        }
        return new ServiceException(statusCode, message, code, requestId, hostId, errorMessage);
    }

    public static String checkChildNotNullAndGetValue(Node item) {
        if (item.getFirstChild() != null) {
            return item.getFirstChild().getNodeValue();
        }
        return null;
    }
}
