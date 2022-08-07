package com.google.zxing.maxicode.decoder;

import android.support.v4.view.InputDeviceCompat;
import com.google.zxing.common.BitMatrix;
import com.hyphenate.EMError;
import com.hyphenate.util.ImageUtils;
import com.parse.ParseException;
import com.tencent.smtt.sdk.TbsListener;
import com.umeng.analytics.a;
import com.vsf2f.f2f.ui.utils.Constant;
import org.apache.http.HttpStatus;

/* loaded from: classes.dex */
final class BitMatrixParser {
    private static final int[][] BITNR;
    private final BitMatrix bitMatrix;

    static {
        int[] iArr = new int[30];
        iArr[0] = 481;
        iArr[1] = 480;
        iArr[2] = 475;
        iArr[3] = 474;
        iArr[4] = 469;
        iArr[5] = 468;
        iArr[6] = 48;
        iArr[7] = -2;
        iArr[8] = 30;
        iArr[9] = -3;
        iArr[10] = -3;
        iArr[11] = -3;
        iArr[12] = -3;
        iArr[13] = -3;
        iArr[14] = -3;
        iArr[15] = -3;
        iArr[16] = -3;
        iArr[17] = -3;
        iArr[18] = -3;
        iArr[20] = 53;
        iArr[21] = 52;
        iArr[22] = 463;
        iArr[23] = 462;
        iArr[24] = 457;
        iArr[25] = 456;
        iArr[26] = 451;
        iArr[27] = 450;
        iArr[28] = 837;
        iArr[29] = -3;
        BITNR = new int[][]{new int[]{121, 120, 127, 126, 133, 132, ParseException.INVALID_ROLE_NAME, 138, 145, 144, 151, 150, 157, 156, 163, 162, 169, 168, 175, 174, 181, 180, 187, 186, 193, 192, 199, 198, -2, -2}, new int[]{123, 122, 129, 128, 135, 134, ParseException.SCRIPT_ERROR, ParseException.EXCEEDED_QUOTA, 147, 146, ParseException.FILE_DELETE_ERROR, 152, 159, 158, 165, 164, Constant.PICTURE_TYPE_171, 170, 177, 176, 183, 182, 189, 188, 195, 194, 201, 200, 816, -3}, new int[]{125, 124, 131, 130, ParseException.DUPLICATE_VALUE, 136, 143, ParseException.VALIDATION_ERROR, 149, 148, ParseException.REQUEST_LIMIT_EXCEEDED, 154, 161, 160, 167, 166, Constant.PICTURE_TYPE_173, Constant.PICTURE_TYPE_172, 179, 178, 185, 184, 191, 190, 197, 196, 203, 202, 818, 817}, new int[]{283, 282, 277, 276, 271, 270, 265, 264, 259, 258, 253, ParseException.UNSUPPORTED_SERVICE, 247, 246, 241, 240, 235, 234, 229, 228, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 222, TbsListener.ErrorCode.INCR_UPDATE_FAIL, TbsListener.ErrorCode.INCR_UPDATE_ERROR, 211, 210, 205, 204, 819, -3}, new int[]{285, 284, 279, 278, 273, 272, 267, 266, 261, 260, 255, 254, 249, 248, 243, 242, 237, 236, 231, 230, TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, TbsListener.ErrorCode.EXCEED_INCR_UPDATE, TbsListener.ErrorCode.RENAME_EXCEPTION, TbsListener.ErrorCode.INCR_UPDATE_EXCEPTION, TbsListener.ErrorCode.COPY_SRCDIR_ERROR, 212, 207, 206, 821, 820}, new int[]{287, 286, 281, 280, 275, 274, 269, 268, 263, 262, InputDeviceCompat.SOURCE_KEYBOARD, 256, ParseException.INVALID_LINKED_SESSION, 250, 245, 244, 239, 238, 233, 232, 227, TbsListener.ErrorCode.DEXOAT_EXCEPTION, TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, TbsListener.ErrorCode.COPY_EXCEPTION, TbsListener.ErrorCode.COPY_TMPDIR_ERROR, 209, 208, 822, -3}, new int[]{289, 288, 295, 294, 301, 300, 307, TbsListener.ErrorCode.THROWABLE_QBSDK_INIT, TbsListener.ErrorCode.ERROR_CANLOADVIDEO_RETURN_FALSE, TbsListener.ErrorCode.ERROR_TBSCORE_SHARE_DIR, TbsListener.ErrorCode.ERROR_QBSDK_INIT_CANLOADX5, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ISSUPPORT, TbsListener.ErrorCode.THROWABLE_INITX5CORE, 324, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_EMPTY_BUNDLE, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_RET_TYPE_NOT_BUNDLE, 337, 336, 343, 342, 349, 348, 355, 354, 361, a.q, 367, 366, 824, 823}, new int[]{291, 290, 297, 296, 303, 302, 309, 308, 315, TbsListener.ErrorCode.ERROR_CANLOADVIDEO_RETURN_NULL, 321, 320, TbsListener.ErrorCode.TEST_THROWABLE_ISNOT_NULL, TbsListener.ErrorCode.TEST_THROWABLE_IS_NULL, 333, 332, 339, 338, 345, 344, 351, 350, 357, 356, 363, 362, 369, 368, 825, -3}, new int[]{293, 292, 299, 298, 305, 304, 311, 310, 317, 316, 323, 322, TbsListener.ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, TbsListener.ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT, 335, 334, 341, 340, 347, 346, 353, 352, 359, 358, 365, 364, 371, 370, 827, 826}, new int[]{409, 408, 403, 402, 397, 396, 391, 390, 79, 78, -2, -2, 13, 12, 37, 36, 2, -1, 44, 43, 109, 108, 385, 384, 379, 378, 373, 372, 828, -3}, new int[]{411, 410, 405, 404, 399, 398, 393, 392, 81, 80, 40, -2, 15, 14, 39, 38, 3, -1, -1, 45, 111, 110, 387, 386, 381, 380, 375, 374, 830, 829}, new int[]{413, 412, 407, 406, 401, 400, 395, 394, 83, 82, 41, -3, -3, -3, -3, -3, 5, 4, 47, 46, 113, 112, 389, 388, 383, 382, 377, 376, 831, -3}, new int[]{415, 414, 421, HttpStatus.SC_METHOD_FAILURE, 427, 426, 103, 102, 55, 54, 16, -3, -3, -3, -3, -3, -3, -3, 20, 19, 85, 84, 433, 432, 439, 438, 445, 444, 833, 832}, new int[]{417, 416, HttpStatus.SC_LOCKED, HttpStatus.SC_UNPROCESSABLE_ENTITY, 429, 428, 105, 104, 57, 56, -3, -3, -3, -3, -3, -3, -3, -3, 22, 21, 87, 86, 435, 434, 441, 440, 447, 446, 834, -3}, new int[]{HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE, TbsListener.ErrorCode.INFO_CORE_EXIST_NOT_LOAD, 425, HttpStatus.SC_FAILED_DEPENDENCY, 431, 430, 107, 106, 59, 58, -3, -3, -3, -3, -3, -3, -3, -3, -3, 23, 89, 88, 437, 436, 443, 442, 449, com.umeng.update.util.a.a, 836, 835}, iArr, new int[]{483, 482, 477, 476, 471, 470, 49, -1, -2, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -2, -1, 465, 464, 459, 458, 453, 452, 839, 838}, new int[]{485, 484, 479, 478, 473, 472, 51, 50, 31, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 1, -2, 42, 467, 466, 461, 460, 455, 454, 840, -3}, new int[]{487, 486, 493, 492, 499, 498, 97, 96, 61, 60, -3, -3, -3, -3, -3, -3, -3, -3, -3, 26, 91, 90, 505, 504, 511, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_MINIQBSUCCESS, 517, 516, 842, 841}, new int[]{489, 488, 495, 494, 501, 500, 99, 98, 63, 62, -3, -3, -3, -3, -3, -3, -3, -3, 28, 27, 93, 92, 507, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_APKFILE, InputDeviceCompat.SOURCE_DPAD, 512, 519, 518, 843, -3}, new int[]{491, 490, 497, 496, 503, 502, 101, 100, 65, 64, 17, -3, -3, -3, -3, -3, -3, -3, 18, 29, 95, 94, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_FILEPATHISNULL, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_OPENINQB, 515, 514, 521, 520, 845, 844}, new int[]{559, 558, 553, 552, 547, 546, 541, 540, 73, 72, 32, -3, -3, -3, -3, -3, -3, 10, 67, 66, 115, 114, 535, 534, 529, 528, 523, 522, 846, -3}, new int[]{561, 560, 555, 554, 549, 548, 543, 542, 75, 74, -2, -1, 7, 6, 35, 34, 11, -2, 69, 68, 117, 116, 537, 536, 531, 530, 525, 524, 848, 847}, new int[]{563, 562, 557, 556, 551, 550, 545, 544, 77, 76, -2, 33, 9, 8, 25, 24, -1, -2, 71, 70, ParseException.OPERATION_FORBIDDEN, 118, 539, 538, 533, 532, 527, 526, 849, -3}, new int[]{565, 564, 571, 570, 577, 576, 583, 582, 589, 588, 595, 594, 601, 600, com.hy.frame.util.Constant.FLAG_RECEIVE_NOTIFY, com.hy.frame.util.Constant.FLAG_RECEIVE_UPDATE_USER, 613, 612, 619, 618, 625, 624, 631, 630, 637, 636, 643, 642, 851, 850}, new int[]{567, 566, 573, 572, 579, 578, 585, 584, 591, 590, 597, 596, 603, 602, com.hy.frame.util.Constant.FLAG_RECEIVE_FORCE_LOGINOUT, com.hy.frame.util.Constant.FLAG_RECEIVE_UPDATE_MSG, 615, 614, 621, 620, 627, 626, 633, 632, 639, 638, 645, 644, 852, -3}, new int[]{569, 568, 575, 574, 581, 580, 587, 586, 593, 592, 599, 598, 605, 604, 611, com.hy.frame.util.Constant.FLAG_RECEIVE_NOTIFY_CLICK, 617, 616, 623, 622, 629, 628, 635, 634, 641, ImageUtils.SCALE_IMAGE_WIDTH, 647, 646, 854, 853}, new int[]{727, 726, 721, 720, 715, 714, 709, 708, 703, 702, 697, 696, 691, 690, 685, 684, 679, 678, 673, 672, 667, 666, 661, 660, 655, 654, 649, 648, 855, -3}, new int[]{729, 728, 723, 722, 717, 716, 711, 710, 705, 704, 699, 698, 693, 692, 687, 686, 681, 680, 675, 674, 669, 668, 663, 662, 657, 656, 651, 650, 857, 856}, new int[]{731, 730, 725, 724, 719, 718, 713, 712, 707, 706, 701, 700, 695, 694, 689, 688, 683, 682, 677, 676, 671, 670, 665, 664, 659, 658, 653, 652, 858, -3}, new int[]{733, 732, 739, 738, 745, 744, 751, 750, 757, 756, 763, 762, 769, 768, 775, 774, 781, 780, 787, 786, 793, 792, 799, 798, 805, EMError.CALL_INVALID_CAMERA_INDEX, 811, 810, 860, 859}, new int[]{735, 734, 741, 740, 747, 746, 753, 752, 759, 758, 765, 764, 771, 770, 777, 776, 783, 782, 789, 788, 795, 794, 801, 800, 807, 806, 813, 812, 861, -3}, new int[]{737, 736, 743, 742, 749, 748, 755, 754, 761, 760, 767, 766, 773, 772, 779, 778, 785, 784, 791, 790, 797, 796, 803, 802, 809, 808, 815, 814, 863, 862}};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BitMatrixParser(BitMatrix bitMatrix) {
        this.bitMatrix = bitMatrix;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] readCodewords() {
        byte[] result = new byte[144];
        int height = this.bitMatrix.getHeight();
        int width = this.bitMatrix.getWidth();
        for (int y = 0; y < height; y++) {
            int[] bitnrRow = BITNR[y];
            for (int x = 0; x < width; x++) {
                int bit = bitnrRow[x];
                if (bit >= 0 && this.bitMatrix.get(x, y)) {
                    int i = bit / 6;
                    result[i] = (byte) (result[i] | ((byte) (1 << (5 - (bit % 6)))));
                }
            }
        }
        return result;
    }
}
