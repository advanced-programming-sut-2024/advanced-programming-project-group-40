package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
    public static void main(String[] args) throws IOException {

        // Create a new Selector
        Selector selector = Selector.open();

        // Create a ServerSocketChannel, bind it, and configure it as non-blocking
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", 42069));
        serverChannel.configureBlocking(false);

        // Register the server socket channel with the Selector for accepting connections
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(256);
        System.out.println("Server started and listening on port 5454...");
        while (true) {

            // Select ready channels using the Selector
            selector.select();

            // Get the set of selected keys
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {

                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {

                    // Accept a new client connection
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = server.accept();
                    clientChannel.configureBlocking(false);

                    // Register the client channel with the Selector for reading
                    clientChannel.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()) {

                    // Read data from the client
                    SocketChannel client = (SocketChannel) key.channel();
                    buffer.clear();
                    int bytesRead = client.read(buffer);
                    if (bytesRead == -1) {

                        // Client closed the connection
                        key.cancel();
                        client.close();
                        continue;
                    }

                    buffer.flip();
                    String receivedMessage = new String(buffer.array(), 0, bytesRead);

                    // Process the received message (e.g., echo it back to the client)
                    System.out.println("Received: " + receivedMessage);

                    // Prepare the buffer for writing and echo the received message back to the client
                    buffer.rewind();
                    client.write(buffer);
                }

                // Remove the processed key from the set
                keyIterator.remove();
            }
        }
    }
}
