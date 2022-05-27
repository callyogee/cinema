package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImpl implements TicketService {

	/**
	 * Should only have private methods other than the one below.
	 */
	@Override
	public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests)
			throws InvalidPurchaseException {
	
		int countAdult = 0;
		int countChild = 0;
		int countInfant = 0 ;
	
		if (!validateInputParameter(accountId)) {
			throw new InvalidPurchaseException();
		}

		for (int i = 0; i < ticketTypeRequests.length; i++) {
			
			if(ticketTypeRequests[i].getTicketType().name().equalsIgnoreCase(TicketTypeRequest.Type.ADULT.name())) {
				countAdult ++;
			}else if(ticketTypeRequests[i].getTicketType().name().equalsIgnoreCase(TicketTypeRequest.Type.CHILD.name())) {
				countChild ++;
			} else {
				countInfant ++;
			}
				

		}

		// Either ticket requested more then 20 or one Adult tkt not present

		if (!(validateMaxTicket(countAdult +countChild ) || countAdult>0)) {
			throw new InvalidPurchaseException();
		}
		
		int totalAmountToPay = countAdult * 20 + countChild * 10;
		int totalSeatsToAllocate = countAdult +countChild ;
		
		// All the Criterion validted now book the ticket
		
		TicketPaymentService payment = new TicketPaymentServiceImpl();
		payment.makePayment(accountId, totalAmountToPay);
		
	
		SeatReservationService obj = new SeatReservationService() {
			
			@Override
			public void reserveSeat(long accountId, int totalSeatsToAllocate) {
				// TODO Auto-generated method stub
				
			}
		};
		
		obj.reserveSeat(accountId, totalSeatsToAllocate);
		
		}
		

	private boolean validateMaxTicket(int numberofTicket) {
		return numberofTicket <= 20 ? true : false;

	}

	private boolean validateInputParameter(Long accountId) {
		// assume Account is valid and have suffecient balance
		if (accountId.intValue() < 0)
			return false;

		return true;

	}

}
