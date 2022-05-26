import java.io.*;
import java.net.*;

public class EmoThread extends Thread {
	
	public Socket soc;
	private int id;
	
	public EmoThread(Socket soc, int id) {
		this.soc = soc;
		this.id = id;
	}

	public void run() {
		try {
			OutputStream os = soc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);

			dos.writeUTF("Emo Set " + id);
			System.out.println(Server.getLog("emo is sent (" + id + ")"));
			
			//dos.close();
			//this.soc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendToClient(String s) {
		try {
			OutputStream os = soc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);

			dos.writeUTF(s);
			System.out.println(Server.getLog("emo is sent (" + id + ")"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public static void send(Socket socket, ChessBoard cb) throws IOException {

        byte[] data = toByteArray(cb);
        OutputStream os = socket.getOutputStream();
        os.write(data);
        os.flush();
    }

    public static byte[] toByteArray(Object obj)
    {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
            bos.close();
            bytes = bos.toByteArray();
        }
        catch (IOException ex) {
            //TODO: Handle the exception
        }
        return bytes;
    }
	
    public void receive(Socket socket, DataOutputStream dos) throws IOException {
    	
        int maxBufferSize = 1024;
        byte[] recvBuffer = new byte[maxBufferSize];
        InputStream is = socket.getInputStream();
        int nReadSize = is.read(recvBuffer);

        if (nReadSize > 0) {
            String i = toObject(recvBuffer, String.class);
			dos.writeUTF("Server has received from " + this.id + ":	" + i);
        }
    }
    
    public static <T> T toObject (byte[] bytes, Class<T> type)
    {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = ois.readObject();
        }
        catch (IOException ex) {
            //TODO: Handle the exception
        }
        catch (ClassNotFoundException ex) {
            //TODO: Handle the exception
        }
        return type.cast(obj);
    }
}