package virtualhorcrux.master;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class AgentLoader {

	private String agentServerUrl;
	private int inputBufferSize = 10 * 1024;

	public byte[] load() throws IOException {
		
		URLConnection connection = new URL(agentServerUrl).openConnection();
		connection.setDoInput(true);
		connection.connect();
		List<byte[]> receivedData = new ArrayList<>();
		int totalReceived = 0;
		try (InputStream input = connection.getInputStream()) {
			int wasRead;
			byte[] buffer;
			do {
				buffer = new byte[inputBufferSize];
				wasRead = input.read(buffer);
				if (wasRead > 0) {
					totalReceived += wasRead;
					receivedData.add(buffer);
				}
				Thread.yield();
			} while(wasRead != -1);
		}
		
		byte[] agentCode = new byte[totalReceived];
		int index = 0;
		return null;
	}
	
	public String getAgentServerUrl() {
		return agentServerUrl;
	}

	public void setAgentServerUrl(String agentServerUrl) {
		this.agentServerUrl = agentServerUrl;
	}
	
}
