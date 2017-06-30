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
		Message<String> message = MessageBuilder.withPayload("34\n4\n43\n54")
				                      			.setHeader("file_name", "valid.txt")
				                      			.setHeader("correlation_id", "123")
				                      			.build();
		Assert.assertEquals("Message redirected to wrong channel..", "processingChannel", routerChannelHandler.inputMessageHandler(message));
	}
	
    /**
     * Testing invalid msg processing
     */
	@Test
	public void testInvalidMessage() {
		Message<String> message = MessageBuilder.withPayload("432\n12gfd\n4\n64")
      											.setHeader("file_name", "valid.txt")
      											.setHeader("correlation_id", "123").build();
		Assert.assertEquals("Message redirected to wrong channel..", "errorChannel", routerChannelHandler.inputMessageHandler(message));
	}
	
	
}
