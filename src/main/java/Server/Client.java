package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    private static Client client;
    private final SocketChannel clientChannel;
    private final ByteBuffer buffer = ByteBuffer.allocate(256);

    private Client() throws IOException {
        clientChannel = SocketChannel.open();
        clientChannel.connect(new InetSocketAddress("localhost", 8080));
        client = this;
        Thread thread = new a();
        thread.start();
    }

    public static Client getClient() throws IOException {
        if (client == null) {
            client = new Client();

        }
        return client;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public SocketChannel getClientChannel() {
        return clientChannel;
    }
    public static boolean isBufferFull(ByteBuffer buffer){
        for (Byte b : buffer.array()){
            if (b != 0) return true;
        }
        return false;
    }
    public static void clearBuffer(ByteBuffer buffer){
        for (int i = 0; i < buffer.array().length; i++) {
            buffer.put(i,(byte) 0);
        }
    }

    public String getServerResponse() throws IOException {
        ByteBuffer q = ByteBuffer.allocate(256);
        clientChannel.read(q);
        q.flip();

        // Convert the response to a String and print it
        String response = new String(q.array(), 0, q.limit());
        return response;
    }
}
