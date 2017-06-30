package aero.sita.file.handler;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import aero.sita.file.handler.RouterChannelHandler;
import junit.framework.Assert;

/**
 * Test class for RouterChannelHandler.
 * 
 * @author harsh.shahi
 * 
 */
public class RouterChannelHandlerTest {

	private RouterChannelHandler routerChannelHandler;
	
	/**
     * Resource setup
     */
	@Before
	public void setUp() {
		routerChannelHandler = new RouterChannelHandler();
	}
	
	/**
     * Resource cleanup
     */
    @After
    public void after() {
    	routerChannelHandler = null;
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
		Message<String> message = MessageBuilder.createMessage("34\n4\n43\n54", messageHeaders);
		Assert.assertEquals("Message redirected to wrong channel..", "processingChannel", routerChannelHandler.inputMessageHandler(message));
	}
	
    /**
     * Testing invalid msg processing
     */
	@Test
	public void testInvalidMessage() {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("file_name", "invalid.txt");
		headers.put("correlation_id", "123");
		MessageHeaders messageHeaders = new MessageHeaders(headers);
		Message<String> message = MessageBuilder.createMessage("432\n12gfd\n4\n64", messageHeaders);
		Assert.assertEquals("Message redirected to wrong channel..", "errorChannel", routerChannelHandler.inputMessageHandler(message));
	}
	
	
}
