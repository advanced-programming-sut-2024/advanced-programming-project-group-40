package Server;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class a extends Thread {
    @Override
    public void run() {
        try {
            Client client = Client.getClient();
            SocketChannel channel = client.getClientChannel();
            ByteBuffer buffer = client.getBuffer();

            while (true) {
                if (Client.isBufferFull(buffer)) {
                    buffer.clear();
                    while (buffer.hasRemaining()) {
                        channel.write(buffer);
                    }
                    Client.clearBuffer(buffer);
                    buffer.clear();
                }

                Thread.sleep(500);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
    }
}
