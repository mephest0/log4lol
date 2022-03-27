package log4lol;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class LolHttpHandler implements HttpHandler {
    static Logger logger = LogManager.getLogger(LolHttpHandler.class);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response;
        String requestMethod = exchange.getRequestMethod();

        logger.error("requestMethod = " + requestMethod);

        if (requestMethod.equals("GET")) {
            response = this.handleGetRequest();
        } else if (requestMethod.equals("POST")) {
            response = this.handlePostRequest(exchange);
        } else {
            response = "Only GET and POST plz";
        }

        String htmlResponse = "<html><head><title>Logging utility</title></head><body>" + response + "</body></html>";

        OutputStream outputStream = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private String handleGetRequest() {
        return """
                <p>Write something in the text box and have it logged by the server</p>

                <form action="/" method="post">
                    <input type="text" name="text">
                    <input type="submit" value="Send">
                </form>
                """;
    }

    private String handlePostRequest(HttpExchange exchange) {
        StringBuilder sb = new StringBuilder();
        sb.append("<a href='/'>back</a>");

        Headers requestHeaders = exchange.getRequestHeaders();
        int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));
        InputStream is = exchange.getRequestBody();

        byte[] data = new byte[contentLength];
        try {
            is.read(data);
        } catch (IOException e) {
            e.printStackTrace();

            sb.append("<p>There was an error getting the request body</p>");
            sb.append("<p>").append(e.getMessage()).append("</p>");
        }

        String requestBody = new String(data, StandardCharsets.UTF_8);
        System.out.println("request body: " + requestBody);

        String cleaned = URLDecoder.decode(requestBody.replace("text=", ""), StandardCharsets.UTF_8);
        System.out.println("cleaned: " + cleaned);

        // Using log level error because it requires no setup
        logger.error(cleaned);

        sb.append("<br />");
        sb.append("<tt>").append(cleaned).append("</tt>");

        return sb.toString();
    }
}
