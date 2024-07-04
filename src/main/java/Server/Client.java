package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
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
}
