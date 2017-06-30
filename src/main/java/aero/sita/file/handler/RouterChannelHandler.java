package aero.sita.file.handler;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.messaging.Message;

/**
 * RouterChannelHandler to validate the message payload and provide proper channel name for message routing.
 * 
 * @author harsh.shahi
 */
public class RouterChannelHandler {

    private static final Logger LOGGER = Logger.getLogger(RouterChannelHandler.class);

    /**
	 * inputMessageHandler : To process input file.
	 * 
	 * @param payload
	 * @return channelName
	 */
    public String inputMessageHandler(Message<String> message) {
		boolean validData = false;
		validData = validate(message.getPayload());
		if (validData) {
			LOGGER.info("Sending input file: "+ message.getHeaders().get("file_name").toString() + " to processingChannel.");
			return "processingChannel";
		}
		LOGGER.info("Sending input file: "+ message.getHeaders().get("file_name").toString() + " to errorChannel.");
		return "errorChannel";
		}

    /**
	 * validate : To validate the payload.
	 * 
	 * @param payloadData
	 * @return boolean
	 */
    private boolean validate(String payloadData) {
		boolean check = true;
		StringTokenizer payloadDataTokens = new StringTokenizer(payloadData, "\n");
		LOGGER.info("Input file contains : "+ payloadDataTokens.countTokens() +" numbers to add.");
		while (payloadDataTokens.hasMoreTokens())
        {
			try {
				Long.parseLong(payloadDataTokens.nextToken().trim());
			} catch (NumberFormatException e) {
				LOGGER.info("Input file is invalid :: Note: File should contain numbers only: "+e.getMessage());
				check = false;
				break;
			}
        }
		return check;
		}
}
