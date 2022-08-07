package com.vsf2f.f2f.ui.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;

/* loaded from: classes2.dex */
public class ReadFromFile {
    public static String readFileByBytes(String fileName) {
        File file = new File(fileName);
        try {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                InputStream in = new FileInputStream(file);
                while (true) {
                    try {
                        int tempbyte = in.read();
                        if (tempbyte != -1) {
                            stringBuilder.append(tempbyte);
                            System.out.write(tempbyte);
                        } else {
                            in.close();
                            return stringBuilder.toString();
                        }
                    } catch (IOException e) {
                        e = e;
                        e.printStackTrace();
                        return "";
                    }
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
        }
    }

    public static String readFileByChars(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            Reader reader = new InputStreamReader(new FileInputStream(file));
            while (true) {
                try {
                    int tempchar = reader.read();
                    if (tempchar == -1) {
                        break;
                    } else if (((char) tempchar) != '\r') {
                        stringBuilder.append((char) tempchar);
                        System.out.print((char) tempchar);
                    }
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    return stringBuilder.toString();
                }
            }
            reader.close();
        } catch (Exception e2) {
            e = e2;
        }
        return stringBuilder.toString();
    }

    public static String readFileByLines(String fileName) {
        Throwable th;
        BufferedReader reader;
        BufferedReader reader2;
        try {
            reader = null;
            try {
                reader2 = new BufferedReader(new FileReader(new File(fileName)));
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            StringBuilder sb = new StringBuilder();
            int line = 1;
            while (true) {
                String tempString = reader2.readLine();
                if (tempString == null) {
                    break;
                }
                System.out.println("line " + line + ": " + tempString);
                line++;
                sb.append(tempString);
            }
            reader2.close();
            if (reader2 != null) {
                try {
                    reader2.close();
                } catch (IOException e2) {
                }
            }
            return sb.toString();
        } catch (IOException e3) {
            e = e3;
            reader = reader2;
            e.printStackTrace();
            if (reader == null) {
                return "";
            }
            try {
                reader.close();
                return "";
            } catch (IOException e4) {
                return "";
            }
        } catch (Throwable th3) {
            th = th3;
            reader = reader2;
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e5) {
                }
            }
            throw th;
        }
    }

    public static String readFileByRandomAccess(String fileName) {
        Throwable th;
        try {
            int beginIndex = 0;
            RandomAccessFile randomFile = null;
            StringBuilder stringBuilder = null;
            try {
                RandomAccessFile randomFile2 = new RandomAccessFile(fileName, "r");
                try {
                    if (randomFile2.length() > 4) {
                        beginIndex = 4;
                    }
                    randomFile2.seek(beginIndex);
                    byte[] bytes = new byte[10];
                    StringBuilder stringBuilder2 = new StringBuilder();
                    while (true) {
                        try {
                            int byteread = randomFile2.read(bytes);
                            if (byteread == -1) {
                                break;
                            }
                            System.out.write(bytes, 0, byteread);
                            stringBuilder2.append(bytes);
                        } catch (IOException e) {
                            e = e;
                            stringBuilder = stringBuilder2;
                            randomFile = randomFile2;
                            e.printStackTrace();
                            if (randomFile != null) {
                                try {
                                    randomFile.close();
                                } catch (IOException e2) {
                                }
                            }
                            return stringBuilder.toString();
                        } catch (Throwable th2) {
                            th = th2;
                            randomFile = randomFile2;
                            if (randomFile != null) {
                                try {
                                    randomFile.close();
                                } catch (IOException e3) {
                                }
                            }
                            throw th;
                        }
                    }
                    if (randomFile2 != null) {
                        try {
                            randomFile2.close();
                            stringBuilder = stringBuilder2;
                            randomFile = randomFile2;
                        } catch (IOException e4) {
                            stringBuilder = stringBuilder2;
                            randomFile = randomFile2;
                        }
                    } else {
                        stringBuilder = stringBuilder2;
                        randomFile = randomFile2;
                    }
                } catch (IOException e5) {
                    e = e5;
                    randomFile = randomFile2;
                } catch (Throwable th3) {
                    th = th3;
                    randomFile = randomFile2;
                }
            } catch (IOException e6) {
                e = e6;
            }
            return stringBuilder.toString();
        } catch (Throwable th4) {
            th = th4;
        }
    }

    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
