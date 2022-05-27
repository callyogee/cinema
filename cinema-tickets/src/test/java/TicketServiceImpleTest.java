import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;

public class TicketServiceImpleTest {
	
	TicketTypeRequest req1 ;
	TicketTypeRequest req2 ;
	TicketTypeRequest req3 ;

	@Before
	public void setUp() throws Exception {
		
		 req1 = new TicketTypeRequest(Type.ADULT,1);
		 req2 = new TicketTypeRequest(Type.INFANT,1);
		 req3 = new TicketTypeRequest(Type.CHILD,1);
		
		
	}
	

	@Test
	public void testPurchaseTickets() {
		TicketTypeRequest[] inputReq = {req1,req2,req3};
		TicketServiceImpl obj = new TicketServiceImpl();
		
		obj.purchaseTickets((long) 10, inputReq);
	
	}

	
	@Test
	public void testInvalidPurchaseTickets() {
		TicketTypeRequest[] inputReq = {req1,req2,req3};
		TicketServiceImpl obj = new TicketServiceImpl();
		
		obj.purchaseTickets((long) 0, inputReq);
	
	}

	
}
