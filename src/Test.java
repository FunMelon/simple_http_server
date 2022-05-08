import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws IOException {

//        FileInputStream fis = new FileInputStream("./line.jpg");
//        BufferedInputStream bis = new BufferedInputStream(fis);
//        FileOutputStream fos = new FileOutputStream("./newLine.jpg");
//        BufferedOutputStream bos = new BufferedOutputStream(fos);
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        byte[] buffer = new byte[1024];
//        int len = bis.read(buffer);
//        while (len != -1) {
//            stringBuilder.append(new String(buffer, 0 ,len, StandardCharsets.ISO_8859_1));
//            len = bis.read(buffer);
//        }
//        bos.write(stringBuilder.toString().getBytes(StandardCharsets.ISO_8859_1));
// #TODO
//        FileInputStream in = new FileInputStream("./line.jpg");
//        InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
//        BufferedReader reader = new BufferedReader(inputStreamReader);
//
//        FileOutputStream out = new FileOutputStream("./newLine.jpg");
//        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out, StandardCharsets.UTF_8);
//        BufferedWriter writer = new BufferedWriter(outputStreamWriter);
//
////        char[] buffer = new char[20];
////        int len = inputStreamReader.read(buffer);
////        while (len != -1) {
////            outputStreamWriter.write(buffer, 0, len);
////            len = inputStreamReader.read(buffer);
////        }
////        inputStreamReader.close();
////        outputStreamWriter.close();
//
//        String line = reader.readLine();
//        while (line != null) {
//            writer.write(line);
//            writer.newLine();
//            line = reader.readLine();
//        }
//        System.out.println("复制完成");
    }
}