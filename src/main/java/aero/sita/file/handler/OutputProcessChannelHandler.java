package aero.sita.file.handler;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 * OutputProcessChannelHandler to compute the result of addition of message payload content and provide the new message payload.
 * 
 * @author harsh.shahi
 * 
 */
public class OutputProcessChannelHandler {
	
	private static final Logger LOGGER = Logger.getLogger(OutputProcessChannelHandler.class);

	/**
	 * messageHandler : To provide the new message payload.
	 * 
	 * @param message
	 * @return Object
	 */
	public Object messageHandler(Message<?> message) {
		Message<String> newMsgPayload = null;
		String fileName = message.getHeaders().get("file_name").toString();
		
		long result  = computeResult(message.getPayload().toString(), fileName);
		
		newMsgPayload = MessageBuilder.withPayload(String.valueOf(result)).copyHeaders(message.getHeaders()).build();
		LOGGER.info("New message payload has been generated for file: "+fileName);
		return newMsgPayload;
	}

	/**
	 * computeResult : Compute the result of addition of message payload content.
	 * 
	 * @param payloadData
	 * @param fileName
	 * @return long
	 */
	private long computeResult(String payloadData, String fileName) {
		long result = 0;
		StringTokenizer payloadDataTokens = new StringTokenizer(payloadData, "\n");
		while (payloadDataTokens.hasMoreTokens()){
			result = result + Long.parseLong(payloadDataTokens.nextToken().trim());
		}
		LOGGER.info("Result of addition of message payload content is: " + result + " for file: " +fileName);
		return result;
	}
}
