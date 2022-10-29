package com.example.nursinghomeapplication;

import java.io.*;

public class iotest {
    public static void main(String[] args) {
        File InFile = new File("D:\\io\\pv\\part-r-00000");
        try {
            InputStream inputStream = new FileInputStream(InFile);
            //逐行读取文件
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            System.out.println(stringBuilder);
            inputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void division(File targetFile, long cutSize) {
        if (targetFile == null) return;
        //计算总分割的次数
        int num = targetFile.length() % cutSize == 0 ?
                (int) (targetFile.length() / cutSize) : (int) (targetFile.length() / cutSize + 1);

        try {
            //构造输入流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(targetFile));
            //输出流
            BufferedOutputStream bufferedOutputStream = null;
            int len = -1;//每次实际读取长度
            byte[] bytes = null;//每次要读取的字节数
            int count = 0;//每次文件要读的次数
            //循环次数为生成文件的个数
            for (int i = 0; i < num; i++) {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("D:\\1xue\\text\\my\\" + (i + 1) + "_temp_" + targetFile.getName()));

                if (cutSize <= 1024) {
                    bytes = new byte[(int) cutSize];
                    count = 1;
                } else {
                    bytes = new byte[1024];
                    count = (int) cutSize / 1024;
                }
                while (count > 0 && (len = bufferedInputStream.read()) != -1) {
                    bufferedOutputStream.write(bytes, 0, len);
                    bufferedOutputStream.flush();
                    count--;
                }
                if (cutSize % 1024 != 0) {
                    bytes = new byte[(int) cutSize % 1024];
                    len = bufferedInputStream.read();
                    bufferedOutputStream.write(bytes, 0, len);
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                }
            }
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}