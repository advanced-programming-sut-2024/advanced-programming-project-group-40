package Server;

import Server.Messages.Client.*;
import Server.Messages.ServerMessages;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    private Socket socket;
    private DataInputStream receiveBuffer;
    private DataOutputStream sendBuffer;
    private String serverIP;
    private int serverPort;

    private Gson gsonAgent;
    private String token;


    public Client(String serverIP, int serverPort) {
        GsonBuilder builder = new GsonBuilder();
        this.gsonAgent = builder.create();
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    private void listener() {
        Thread listener = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        BufferedReader v = new BufferedReader(new InputStreamReader(receiveBuffer));
                        if (v.ready()) {
                            String s = v.readLine();
                            System.out.println(s);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        listener.start();
    }

    private boolean establishConnection() {
        try {
            //if (socket == null)
                socket = new Socket(serverIP, serverPort);
            //if (sendBuffer == null)
                sendBuffer = new DataOutputStream(socket.getOutputStream());
            //if (receiveBuffer == null)
                receiveBuffer = new DataInputStream(socket.getInputStream());
            listener();
            return true;
        } catch (Exception e) {
            System.err.println("Unable to initialize socket!");
            e.printStackTrace();
            return false;
        }
    }

    private boolean endConnection() {
        /*if (socket == null) return true;
        try {
            socket.close();
            receiveBuffer.close();
            sendBuffer.close();
            return true;
        } catch (IOException e) {
            return false;
        }*/

        //commented above code because apparently there is no need to end connection
        // (fuck my life if it actually is important)
        return true;
    }

    private boolean sendMessage(String message) {
        try {
            sendBuffer.writeUTF(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String receiveResponse() {
        try {
            return receiveBuffer.readUTF();
        } catch (IOException e) {
            return null;
        }
    }

    public void test() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Client is running");
        establishConnection();
        String input = scanner.nextLine();
        sendMessage(input);
        endConnection();
    }
    public void addCard(AddRemoveCardMessage addRemoveCardMessage) {
        getServerMessage(addRemoveCardMessage);
    }

    public void removeCard(AddRemoveCardMessage addRemoveCardMessage) {
        getServerMessage(addRemoveCardMessage);
    }

    public ServerMessages login(LoginMessages loginMessages) {
        return getServerMessage(loginMessages);
    }

    public void signUp(SignUpMessages signUpMessages) {
        getServerMessage(signUpMessages);
    }

    public ServerMessages getUser(GetUserMessage getUserMessage) {
        return getServerMessage(getUserMessage);
    }

    public ServerMessages getListOfNames(GetListOfNamesMessage getListOfNamesMessage) {
        return getServerMessage(getListOfNamesMessage);
    }

    public ServerMessages request(RequestMessage requestMessage) {
        return getServerMessage(requestMessage);
    }
    private ServerMessages getServerMessage(ClientMessages clientMessages) {
        establishConnection();
        sendMessage(gsonAgent.toJson(clientMessages));
        String response = receiveResponse();
        ServerMessages serverMessages = gsonAgent.fromJson(response, ServerMessages.class);
        endConnection();
        return serverMessages;
    }

}