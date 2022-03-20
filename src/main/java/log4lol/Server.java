package log4lol;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    static final int PORT = 8000;

    public static void start() throws IOException {
        System.out.println("Starting server");
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);

        server.createContext("/", new LolHttpHandler());
        server.start();

        System.out.println("Server started on port " + PORT);
    }
}
