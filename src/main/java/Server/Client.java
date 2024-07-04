package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    private final SocketChannel clientChannel ;
    private final ByteBuffer buffer = ByteBuffer.allocate(256);
    public Client() throws IOException {
        clientChannel = SocketChannel.open();
        clientChannel.connect(new InetSocketAddress("localhost", 8080));
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public SocketChannel getClientChannel() {
        return clientChannel;
    }
}
