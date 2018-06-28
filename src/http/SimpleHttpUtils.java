package http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ���׵� HTTP ���󹤾�, һ����̬�����������, ֧�� 301, 302 ���ض���, ֧���Զ�ʶ�� charset, ֧��ͬ������ Cookie ���Զ������뷢��  <br/><br/>
 *
 * Demo:
 *
 * <pre>{@code
 *     // (��ѡ) ����Ĭ�ϵ�User-Agent�Ƿ�Ϊ�ƶ������ģʽ, Ĭ��Ϊfalse(PC�����ģʽ)
 *     SimpleHttpUtils.setMobileBrowserModel(true);
 *     // (��ѡ) ����Ĭ�ϵ�����ͷ, ÿ������ʱ������ ��� �� ���� ԭ�е�Ĭ������ͷ
 *     SimpleHttpUtils.setDefaultRequestHeader("header-key", "header-value");
 *     // (��ѡ) ���� ���� �� ��ȡ �ĳ�ʱʱ��, ���ӳ�ʱʱ��Ĭ��Ϊ15000����, ��ȡ��ʱʱ��Ϊ0(������鳬ʱ)
 *     SimpleHttpUtils.setTimeOut(15000, 0);
 *
 *     // GET ����, ������Ӧ�ı�
 *     String html = SimpleHttpUtils.get("http://blog.csdn.net/");
 *
 *     // GET ����, �����ļ�, �����ļ�·��
 *     SimpleHttpUtils.get("http://blog.csdn.net/", new File("csdn.txt"));
 *
 *     // POST ����, ������Ӧ�ı�
 *     String respText = SimpleHttpUtils.post("http://url", "body-data".getBytes());
 *
 *     // POST ����, �ϴ��ļ�, ������Ӧ�ı�
 *     SimpleHttpUtils.post("http://url", new File("aa.jpg"));
 *
 *     // ������������ get(...) �� post(...) ����������(��������ʱ�����������ͷ), �������ʵ��
 * }</pre>
 *
 * @author xietansheng
 */
public class SimpleHttpUtils {

    /** �ı�����ʱ�����Ƶ������Ӧ����, 5MB */
    private static final int TEXT_REQUEST_MAX_LENGTH = 5 * 1024 * 1024;

    /** Ĭ�ϵ�����ͷ */
    private static final Map<String, String> DEFAULT_REQUEST_HEADERS = new HashMap<String, String>();

    /** ����Ĭ������ͷ�Ķ�д�� */
    private static final ReadWriteLock RW_LOCK = new ReentrantReadWriteLock();

    /** User-Agent PC: Windows10 IE 11 */
    private static final String USER_AGENT_FOR_PC = "Mozilla 0.0 Mozilla/5.0 (Windows NT 10.0; Trident/7.0; rv:11.0) like Gecko";

    /** User-Agent Mobile: Android 7.0 Chrome ����� */
    private static final String USER_AGENT_FOR_MOBILE = "Chrome Mozilla/5.0 (Linux; Android 7.0; Nexus 6 Build/NBD92D) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.132 Mobile Safari/537.36";

    /** ���ӳ�ʱʱ��, ��λ: ms, 0 ��ʾ�����ʱ(������鳬ʱ), Ĭ�� 15s ��ʱ */
    private static int CONNECT_TIME_OUT = 15 * 1000;

    /** ��ȡ��ʱʱ��, ��λ: ms, 0 ��ʾ�����ʱ(������鳬ʱ), Ĭ��Ϊ 0 */
    private static int READ_TIME_OUT = 0;

    static {
        // ����һ��Ĭ�ϵ� Cookie ������, ʹ�� HttpURLConnection ����ʱ,
        // �����ڴ����Զ�������Ӧ�� Cookie, ��������һ������ʱ�Զ�������Ӧ�� Cookie
        CookieHandler.setDefault(new CookieManager());

        // Ĭ��ʹ��PC�����ģʽ
        setMobileBrowserModel(false);
    }

    /**
     * ����Ĭ�ϵ�User-Agent�Ƿ�Ϊ�ƶ������ģʽ, Ĭ��ΪPC�����ģʽ,  <br/>
     *
     * Ҳ����ͨ�� {@link #setDefaultRequestHeader(String, String)} �Զ������� User-Agent
     *
     * @param isMobileBrowser true: �ֻ������; false: PC�����
     */
    public static void setMobileBrowserModel(boolean isMobileBrowser) {
        setDefaultRequestHeader("User-Agent", isMobileBrowser ? USER_AGENT_FOR_MOBILE : USER_AGENT_FOR_PC);
    }

    /**
     * ���ó�ʱʱ��, ��λ: ms, 0 ��ʾ�����ʱ(������鳬ʱ)
     *
     * @param connectTimeOut ���ӳ�ʱʱ��, Ĭ��Ϊ 15s
     * @param readTimeOut ��ȡ��ʱʱ��, Ĭ��Ϊ 0
     */
    public static void setTimeOut(int connectTimeOut, int readTimeOut) {
        if (connectTimeOut < 0 || readTimeOut < 0) {
            throw new IllegalArgumentException("timeout can not be negative");
        }
        RW_LOCK.writeLock().lock();
        try {
            CONNECT_TIME_OUT = connectTimeOut;
            READ_TIME_OUT = readTimeOut;
        } finally {
            RW_LOCK.writeLock().unlock();
        }
    }

    /**
     * ����Ĭ�ϵ�����ͷ, ÿ������ʱ������ ��� �� ���� ԭ�е�Ĭ������ͷ
     */
    public static void setDefaultRequestHeader(String key, String value) {
        RW_LOCK.writeLock().lock();
        try {
            DEFAULT_REQUEST_HEADERS.put(key, value);
        } finally {
            RW_LOCK.writeLock().unlock();
        }
    }

    /**
     * �Ƴ�Ĭ�ϵ�����ͷ
     */
    public static void removeDefaultRequestHeader(String key) {
        RW_LOCK.writeLock().lock();
        try {
            DEFAULT_REQUEST_HEADERS.remove(key);
        } finally {
            RW_LOCK.writeLock().unlock();
        }
    }

    public static String get(String url) throws Exception  {
        return get(url, null, null);
    }

    public static String get(String url, Map<String, String> headers) throws Exception  {
        return get(url, headers, null);
    }

    public static String get(String url, File saveToFile) throws Exception  {
        return get(url, null, saveToFile);
    }

    public static String get(String url, Map<String, String> headers, File saveToFile) throws Exception  {
        return sendRequest(url, "GET", headers, null, saveToFile);
    }

    public static String post(String url, byte[] body) throws Exception {
        return post(url, null, body);
    }

    public static String post(String url, Map<String, String> headers, byte[] body) throws Exception {
        InputStream in = null;
        if (body != null && body.length > 0) {
            in = new ByteArrayInputStream(body);
        }
        return post(url, headers, in);
    }

    public static String post(String url, File bodyFile) throws Exception {
        return post(url, null, bodyFile);
    }

    public static String post(String url, Map<String, String> headers, File bodyFile) throws Exception {
        InputStream in = null;
        if (bodyFile != null && bodyFile.exists() && bodyFile.isFile() && bodyFile.length() > 0) {
            in = new FileInputStream(bodyFile);
        }
        return post(url, headers, in);
    }

    public static String post(String url, InputStream bodyStream) throws Exception {
        return post(url, null, bodyStream);
    }

    public static String post(String url, Map<String, String> headers, InputStream bodyStream) throws Exception {
        return sendRequest(url, "POST", headers, bodyStream, null);
    }

    /**
     * ִ��һ��ͨ�õ� http/https ����, ֧�� 301, 302 ���ض���, ֧���Զ�ʶ�� charset, ֧��ͬ������ Cookie ���Զ������뷢��
     *
     * @param url
     *          ���������, ֻ֧�� http �� https ����
     *
     * @param method
     *          (��ѡ) ���󷽷�, ����Ϊ null
     *
     * @param headers
     *          (��ѡ) ����ͷ (������Ĭ������), ����Ϊ null
     *
     * @param bodyStream
     *          (��ѡ) ��������, �����Զ��ر�, ����Ϊ null
     *
     * @param saveToFile
     *          (��ѡ) ��Ӧ���浽���ļ�, ����Ϊ null
     *
     * @return
     *          �����Ӧ���ݱ��浽�ļ�, �򷵻��ļ�·��, ���򷵻���Ӧ���ݵ��ı� (�Զ����� charset ���н���)
     *
     * @throws Exception
     *          http ��Ӧ code �� 200, ���������쳣���׳��쳣
     */
    public static String sendRequest(String url, String method, Map<String, String> headers, InputStream bodyStream, File saveToFile) throws Exception {
        assertUrlValid(url);

        HttpURLConnection conn = null;

        try {
            // ������
            URL urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();

            // ���ø���Ĭ������
            setDefaultProperties(conn);

            // �������󷽷�
            if (method != null && method.length() > 0) {
                conn.setRequestMethod(method);
            }

            // �������ͷ
            if (headers != null && headers.size() > 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            // ������������
            if (bodyStream != null) {
                conn.setDoOutput(true);
                copyStreamAndClose(bodyStream, conn.getOutputStream());
            }

            // ��ȡ��Ӧcode
            int code = conn.getResponseCode();

            // �����ض���
            if (code == HttpURLConnection.HTTP_MOVED_PERM || code == HttpURLConnection.HTTP_MOVED_TEMP) {
                String location = conn.getHeaderField("Location");
                if (location != null) {
                    closeStream(bodyStream);
                    // �ض���Ϊ GET ����
                    return sendRequest(location, "GET", headers, null, saveToFile);
                }
            }

            // ��ȡ��Ӧ���ݳ���
            long contentLength = conn.getContentLengthLong();
            // ��ȡ��������
            String contentType = conn.getContentType();

            // ��ȡ��Ӧ����������
            InputStream in = conn.getInputStream();

            // û����Ӧ�ɹ�, ���׳��쳣
            if (code != HttpURLConnection.HTTP_OK) {
                throw new IOException("Http Error: " + code + "; Desc: " + handleResponseBodyToString(in, contentType));
            }

            // ����ļ�������Ϊnull, �����Ӧ���ݱ��浽�ļ�
            if (saveToFile != null) {
                handleResponseBodyToFile(in, saveToFile);
                return saveToFile.getPath();
            }

            // �����Ҫ����Ӧ���ݽ���Ϊ�ı�, ��������󳤶�
            if (contentLength > TEXT_REQUEST_MAX_LENGTH) {
                throw new IOException("Response content length too large: " + contentLength);
            }
            return handleResponseBodyToString(in, contentType);

        } finally {
            closeConnection(conn);
        }
    }

    private static void assertUrlValid(String url) throws IllegalAccessException {
        boolean isValid = false;
        if (url != null) {
            url = url.toLowerCase();
            if (url.startsWith("http://") || url.startsWith("https://")) {
                isValid = true;
            }
        }
        if (!isValid) {
            throw new IllegalAccessException("Only support http or https url: " + url);
        }
    }

    private static void setDefaultProperties(HttpURLConnection conn) {
        RW_LOCK.readLock().lock();
        try {
            // �������ӳ�ʱʱ��
            conn.setConnectTimeout(CONNECT_TIME_OUT);

            // ���ö�ȡ��ʱʱ��
            conn.setReadTimeout(READ_TIME_OUT);

            // ���Ĭ�ϵ�����ͷ
            if (DEFAULT_REQUEST_HEADERS.size() > 0) {
                for (Map.Entry<String, String> entry : DEFAULT_REQUEST_HEADERS.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
        } finally {
            RW_LOCK.readLock().unlock();
        }
    }

    private static void handleResponseBodyToFile(InputStream in, File saveToFile) throws Exception  {
        OutputStream out = null;
        try {
            out = new FileOutputStream(saveToFile);
            copyStreamAndClose(in, out);
        } finally {
            closeStream(out);
        }
    }

    private static String handleResponseBodyToString(InputStream in, String contentType) throws Exception {
        ByteArrayOutputStream bytesOut = null;

        try {
            bytesOut = new ByteArrayOutputStream();

            // ��ȡ��Ӧ����
            copyStreamAndClose(in, bytesOut);

            // ��Ӧ���ݵ��ֽ�����
            byte[] contentBytes = bytesOut.toByteArray();

            // �����ı����ݱ����ʽ
            String charset = parseCharset(contentType);
            if (charset == null) {
                charset = parseCharsetFromHtml(contentBytes);
                if (charset == null) {
                    charset = "utf-8";
                }
            }

            // ������Ӧ����
            String content = null;
            try {
                content = new String(contentBytes, charset);
            } catch (UnsupportedEncodingException e) {
                content = new String(contentBytes);
            }

            return content;

        } finally {
            closeStream(bytesOut);
        }
    }

    private static void copyStreamAndClose(InputStream in, OutputStream out) {
        try {
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(in);
            closeStream(out);
        }
    }

    private static String parseCharsetFromHtml(byte[] htmlBytes) {
        if (htmlBytes == null || htmlBytes.length == 0) {
            return null;
        }
        String html = null;
        try {
            // ��ʹ�õ��ֽڱ���� ISO-8859-1 ȥ���Խ���
            html = new String(htmlBytes, "ISO-8859-1");
            return parseCharsetFromHtml(html);
        } catch (UnsupportedEncodingException e) {
            html = new String(htmlBytes);
        }
        return parseCharsetFromHtml(html);
    }

    private static String parseCharsetFromHtml(String html) {
        if (html == null || html.length() == 0) {
            return null;
        }
        html = html.toLowerCase();
        Pattern p = Pattern.compile("<meta [^>]+>");
        Matcher m = p.matcher(html);
        String meta = null;
        String charset = null;
        while (m.find()) {
            meta = m.group();
            charset = parseCharset(meta);
            if (charset != null) {
                break;
            }
        }
        return charset;
    }

    private static String parseCharset(String content) {
        // text/html; charset=iso-8859-1
        // <meta charset="utf-8">
        // <meta charset='utf-8'>
        // <meta http-equiv="Content-Type" content="text/html; charset=gbk" />
        // <meta http-equiv="Content-Type" content='text/html; charset=gbk' />
        // <meta http-equiv=content-type content=text/html;charset=utf-8>
        if (content == null) {
            return null;
        }
        content = content.trim().toLowerCase();
        Pattern p = Pattern.compile("(?<=((charset=)|(charset=')|(charset=\")))[^'\"/> ]+(?=($|'|\"|/|>| ))");
        Matcher m = p.matcher(content);
        String charset = null;
        while (m.find()) {
            charset = m.group();
            if (charset != null) {
                break;
            }
        }
        return charset;
    }

    private static void closeConnection(HttpURLConnection conn) {
        if (conn != null) {
            try {
                conn.disconnect();
            } catch (Exception e) {
                // nothing
            }
        }
    }

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                // nothing
            }
        }
    }

}