package box.webSockets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.configuration.PropertiesConfiguration;

@ServerEndpoint(value = "/websocket")
public class WebSocketBox {

	private static String currentCommand = null;
	private static List<byte[]> archivos = new ArrayList<>();

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		if (message.startsWith("start")) {
			cleanup();
			currentCommand = "start";
		} else if (message.startsWith("stop")) {
			play();
			cleanup();
			currentCommand = "stop";
		}
	}

	@OnMessage
	public void processUpload(byte[] data, boolean last, Session session) {
		if (currentCommand.equals("start")) {
			
			if(last){
				try {
					archivos.add(data);
					System.out.println(1);
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
					System.out.println(2);
					for (byte[] archivo : archivos) {
						outputStream.write( archivo );
					}
					System.out.println(3);
					PropertiesConfiguration config = new PropertiesConfiguration("box/config.properties");					
					System.out.println(4);
					File fDir = new File(config.getString("PATHAUDIOFILE"));
					System.out.println(4.1);
					if(!fDir.exists()){
						System.out.println(4.2);
						fDir.mkdir();
					}
					System.out.println(4.3);
					
					FileOutputStream fOut = new FileOutputStream(config.getString("PATHAUDIOFILE") + "out.wav");
					System.out.println(5);
					fOut.write(outputStream.toByteArray());
					System.out.println(6);
					fOut.flush();
					System.out.println(7);
					fOut.close();		
					System.out.println(8);
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
			else{		
				archivos.add(data);
			}

		}
	}

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Client connected: " + session.getId() + " has opened a connection");
		try {
			session.getBasicRemote().sendText("Connection Established");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("Connection closed Session " + session.getId() + " has ended");
	}

	private static void play() {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration("box/config.properties");
//			String audioFilePath = config.getString("PATHAUDIOFILE") + "out.wav";
//			AudioPlayer player = new AudioPlayer();
//	        player.play(audioFilePath);
			
			//TODO:
			Process p = Runtime.getRuntime().exec("sudo aplay " + config.getString("PATHAUDIOFILE") + "out.wav");
			p.waitFor();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void cleanup() {
		currentCommand = null;
		archivos.clear();
		deleteSoundFile();
	}
	
	private static void deleteSoundFile() {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration("box/config.properties");
			File f = new File(config.getString("PATHAUDIOFILE") + "out.wav");
			if(f.exists()){
				f.delete();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}		
}
