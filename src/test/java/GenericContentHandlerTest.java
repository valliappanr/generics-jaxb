import static org.junit.Assert.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.generics.impl.GenericContentHandler;
import com.generics.orders.CancelOrder;
import com.generics.orders.ExecutionType;
import com.generics.orders.OrderType;
import com.generics.orders.Orders;

public class GenericContentHandlerTest {

	private static final String PLACE_ORDER = "<Orders>"+
			"<Order><symbol>goog</symbol><size>100</size>" +
			"<executionType>MARKET_ORDER</executionType></Order></Orders>";

	private static final String CANCEL_ORDER = "<CancelOrder>"+
			"<orderNumber>100</orderNumber>" +
			"<reason>too late</reason></CancelOrder>";
	
	private static final Logger log = LoggerFactory.getLogger(GenericContentHandlerTest.class);
	
	
	@Test
	public void testPlaceOrder() {
		GenericContentHandler handler = new GenericContentHandler();
		Orders orderList = handler.unMarshal(PLACE_ORDER, Orders.class);
		log.debug(String.format("orderList: %s", ToStringBuilder.reflectionToString(orderList)));
	}

	@Test
	public void testCancelOrder() {
		GenericContentHandler handler = new GenericContentHandler();
		CancelOrder cancelOrder = handler.unMarshal(CANCEL_ORDER, CancelOrder.class);
		log.debug(String.format("cancelOrder: %s", ToStringBuilder.reflectionToString(cancelOrder)));
	}

	@Test
	public void testGeneratePlaceOrder() {
		GenericContentHandler handler = new GenericContentHandler();
		Orders orderList = new Orders();
		OrderType order = new OrderType();
		order.setSize(20);
		order.setSymbol("MSFT");
		orderList.getOrder().add(order);
		order.setExecutionType(ExecutionType.STOP_LIMIT_ORDER);
		String content = handler.marshal(orderList);
		log.debug(String.format("Order XML Content: %s", content));
	}

	@Test
	public void testGenerateCancelOrder() {
		GenericContentHandler handler = new GenericContentHandler();
		CancelOrder cancelOrder = new CancelOrder();
		cancelOrder.setOrderNumber(01);
		cancelOrder.setReason("too late");
		String content = handler.marshal(cancelOrder);
		log.debug(String.format("CancelOrder XML Content: %s", content));
	}
	
}
