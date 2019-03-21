package com.doubleysoft.kun.mvc.server.protocal;

import com.doubleysoft.kun.mvc.server.socket.SocketWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author cupofish@gmail.com
 * 3/21/19 21:32
 */
@Slf4j
public class HttpProcessor implements ReqProcessor {
    @Override
    public void process(SocketWrapper socketWrapper) {
        log.info("receive socket connection, begin process request");
        Socket socket = socketWrapper.getSocket();
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String content = null;
            try (Scanner scanner = new Scanner(socket.getInputStream())) {
                content = scanner.useDelimiter("\\A").next();
            }
            System.out.println(content);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("hello world");
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
