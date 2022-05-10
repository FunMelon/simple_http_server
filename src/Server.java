import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Server {

    /**
     *  入口函数，启动http服务器
     * @param port 监听的端口号
     * @throws Exception 那些懒得处理的异常
     */
    public void monitor(int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("监听" + port + "号端口");
        while (true) {
            System.out.println("等待新的连接");
            Socket socket = serverSocket.accept();
            // 获取请求报文
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.ISO_8859_1));
            HttpRequest request = decode(bufferedReader);
            if (request == null) {
                System.out.println("无法解析的连接请求");
                continue;
            }
            System.out.println("解码成功");
            HttpResponse response = new HttpResponse();
            response.version = request.version;
            // 查找文件
            response.doGet(request.url);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.write(encode(response));
            out.flush();

            socket.close();
        }
    }

    /**
     * 解析http请求报文的简单版本，只解析命令，url和http版本
     * @param reader 逐行读取
     * @return 返回解析的请求对象，失败就返回空
     */
    private HttpRequest decode(BufferedReader reader) {
        try {
            HttpRequest request = new HttpRequest();
            // 解析起始行
            String firstLine = reader.readLine();
            String[] split = firstLine.split(" ");

            request.method = split[0];
            request.url = new String(split[1].getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            System.out.println(request.url);
            request.version = split[2];
            System.out.println("解析起始行成功");
            // 解析请求首部
            Map<String, String> headers = new HashMap<>();
            while (true) {
                String line = reader.readLine();
                if ("".equals(line.trim())) {
                    break;
                }
                String[] keyValue = line.split(":");
                headers.put(keyValue[0],keyValue[1]);
            }
            System.out.println("解析请求首部成功");
            request.headers = headers;
            // TODO:解析请求主体
            return request;
        } catch (Exception e) {
            System.out.println("解析报文失败");
        }
        return null;
    }

    /**
     * 编码http响应报文
     * @param response 返回数据
     * @return 字节数组
     */
    private byte[] encode(HttpResponse response) {
        StringBuilder msg = new StringBuilder();
        // 响应起始行
        msg.append(response.version).append(" ").append(response.code).append(" ").
                append(response.status).append("\r\n");
        // 响应头
        if (response.body != null && response.body.length() > 0) {
            // msg.append("Content-Length:").append(response.body.length()).append("\r\n");
            msg.append("Content-type:").append(response.MIME).append(";charset=").append(response.encodeType).append("\r\n");
        }
        if (response.headers != null) {
            String headStr = response.headers.entrySet().stream().map(e->e.getKey() + ":" + e.getValue())
                    .collect(Collectors.joining("\r\n"));
            if (!headStr.isEmpty()) {
                msg.append(headStr).append("\r\n");
            }
        }
        msg.append("\r\n");
        // 实体体
        if (response.body != null) {
            msg.append(response.body);
        }
        System.out.println(msg);
        return msg.toString().getBytes(response.encodeType);
    }
}