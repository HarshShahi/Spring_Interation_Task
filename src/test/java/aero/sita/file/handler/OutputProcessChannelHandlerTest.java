package aero.sita.file.handler;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import aero.sita.file.handler.OutputProcessChannelHandler;
import junit.framework.Assert;

/**
 * Test class for OutputProcessChannelHandler.
 * 
 * @author harsh.shahi
 * 
 */
public class OutputProcessChannelHandlerTest {

	private OutputProcessChannelHandler outputProcessChannelHandler;
	
	/**
     * Resource setup
     */
	@Before
	public void setUp() {
		outputProcessChannelHandler = new OutputProcessChannelHandler();
	}
	
	/**
     * Resource cleanup
     */
    @After
    public void after() {
    	outputProcessChannelHandler = null;
    }
	
    /**
     * Testing valid msg processing
     */
	@Test
	public void testValidMessage() {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("file_name", "valid.txt");
		headers.put("correlation_id", "123");
		MessageHeaders messageHeaders = new MessageHeaders(headers);
		Message<String> message = MessageBuilder.createMessage("32\n4\n43\n54", messageHeaders);
		Message<String> outputMsg = (Message<String>) outputProcessChannelHandler.messageHandler(message);
		Assert.assertEquals("Sum is incorrect..", "133", outputMsg.getPayload().toString());
		Assert.assertEquals("file_name name has been modified..", "valid.txt", outputMsg.getHeaders().get("file_name"));
	}
}
