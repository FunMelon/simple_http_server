import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpResponse {
    String version; // http版本
    int code;       // 响应码
    String status;  // 状态信息
    Map<String, String> headers; // 响应头
    String body;    // 响应数据
    String MIME;   // 响应文件格式信息
    Charset encodeType = StandardCharsets.UTF_8;

    public String getStatus() {
        return switch (code) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            default -> null;
        };
    }

    // 根据url来读取报文
    public void doGet(String url) {
        try(
                FileInputStream in = new FileInputStream("./resources/" + url)
        ) {
            StringBuilder stringBuilder = new StringBuilder();
            byte[] buffer = new byte[1024];
            int len = in.read(buffer);
            while (len != -1) {
                stringBuilder.append(new String(buffer, 0 ,len, StandardCharsets.ISO_8859_1));
                len =in.read(buffer);
            }
            this.encodeType = StandardCharsets.ISO_8859_1;
            this.body = stringBuilder.toString();
            this.MIME = url.substring(url.indexOf('.') + 1) + "/html";
            this.code = 200;
        } catch (IOException e) {
            this.code = 404;
        } finally {
            this.status = getStatus();
        }
    }
}