package com.revature.hms.comparator;

import java.util.Comparator;

import com.revature.hms.model.BookingHistory;

public class IdComparator implements Comparator<BookingHistory> {

	@Override
	public int compare(BookingHistory o1, BookingHistory o2) {
		if(o1.getBookingId()>o2.getBookingId()) {
			return 1;
		}
		else {
			return -1;
		}
		
	}

}
