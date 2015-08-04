package box.webSockets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
					
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
					
					for (byte[] archivo : archivos) {
						outputStream.write( archivo );
					}
									
					PropertiesConfiguration config = new PropertiesConfiguration("box/config.properties");					
					
					FileOutputStream fOut = new FileOutputStream(config.getString("PATHAUDIOFILE") + "out.wav");
					fOut.write(outputStream.toByteArray());
					fOut.flush();
					fOut.close();							
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
			File f = new File(config.getString("PATHAUDIOFILE") + "out.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(f));
			clip.start();
			clip.close();
		} catch (Exception exc) {
			exc.printStackTrace();
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
