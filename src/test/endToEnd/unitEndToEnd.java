package endToEnd;

import static org.junit.Assert.*;
import org.junit.Test;

public class unitEndToEnd {

	private final static long[] ids = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,11 };
	private final static long[] timeStamps = { 1232345, 242346, 123523, 1264624, 1643245, 2352346,
			2523417, 313418, 1236349, 1632310, 34126211 };
	
	static RestTestClient methods = new RestTestClient();

	@Test
	public void testEnqueueUser_Expect201Status() {
		try {
			methods.sendPostUser(ids[0], timeStamps[0]);
		} catch (Exception e) {
			e.getMessage();
		}
		
		assertEquals(201, methods.tempResponseCode);
		
		try {
			methods.sendDeleteUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testEnqueueSameUserTwice_Expect400Status() {
		try {
			methods.sendPostUser(ids[0], timeStamps[0]);
			methods.sendPostUser(ids[0], timeStamps[0]);
		} catch (Exception e) {
			e.getMessage();
		}
		
		assertEquals(400, methods.tempResponseCode);
		
		try {
			methods.sendDeleteUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testDequeueUser_Expect200Status() {
		try {
			methods.sendPostUser(ids[0], timeStamps[0]);
			methods.sendPostDequeueUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
		
		assertEquals(200, methods.tempResponseCode);
		
		try {
			methods.sendDeleteUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testDequeueEmptyUser_Expect204Status() {
		try {
			methods.sendPostUser(ids[0], timeStamps[0]);
			methods.sendPostDequeueUser(ids[0]);
			methods.sendPostDequeueUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
		
		assertEquals(204, methods.tempResponseCode);
		
		try {
			methods.sendDeleteUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testListAllUsers_NoExistingUsers_Expect200Status() {
		try {
			methods.sendGetUsers();
		} catch (Exception e) {
			e.getMessage();
		}
		
		assertEquals(200, methods.tempResponseCode);
	}
	
	@Test
	public void testListAllUsers_SingleUserInQueue_Expect200Status() {
		try {
			methods.sendPostUser(ids[0], timeStamps[0]);
			methods.sendGetUsers();
		} catch (Exception e) {
			e.getMessage();
		}
		
		assertEquals(200, methods.tempResponseCode);
		
		try {
			methods.sendDeleteUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testDeleteSpecificUser_Expect204Status() {
		try {
			methods.sendPostUser(ids[0], timeStamps[0]);
			methods.sendDeleteUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
		assertEquals(204, methods.tempResponseCode);
		
	}
	
	@Test
	public void testDeleteNonExistingUser_Expect404Status() {
		try {
			methods.sendDeleteUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
		assertEquals(404, methods.tempResponseCode);
		
	}
	
	@Test
	public void testGetSpecificUser_Expect200Status() {
		try {
			methods.sendGetUsers();
			methods.sendPostUser(ids[0], timeStamps[0]);
			methods.sendPostUser(ids[1], timeStamps[1]);
			methods.sendGetSpecificUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
		
		assertEquals(200, methods.tempResponseCode);
		
		try{
			methods.sendGetSpecificUser(ids[1]);
		} catch (Exception e) {
			e.getMessage();
		}
		
		assertEquals(200, methods.tempResponseCode);
		
		try {
			methods.sendDeleteUser(ids[0]);
			methods.sendDeleteUser(ids[1]);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testGetSpecificUserDoesNotExist_Expect404Status() {
		try {
			methods.sendGetUsers();
			methods.sendPostUser(ids[0], timeStamps[0]);
			methods.sendGetSpecificUser(ids[1]);
		} catch (Exception e) {
			e.getMessage();
		}
		
		assertEquals(404, methods.tempResponseCode);
		
		try {
			methods.sendDeleteUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testGetUserPosition_Expect200Status() {
		try {
			methods.sendPostUser(ids[0], timeStamps[0]);
			methods.sendGetUserPosition(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
		
		assertEquals(200, methods.tempResponseCode);
		
		try {
			methods.sendDeleteUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testNonExistUserPosition_Expect404Status() {
		try {
			methods.sendPostUser(ids[0], timeStamps[0]);
			methods.sendGetUserPosition(ids[1]);
		} catch (Exception e) {
			e.getMessage();
		}
		
		assertEquals(404, methods.tempResponseCode);
		
		try {
			methods.sendDeleteUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testWaitTime_Expect200Status() {
		try {
			methods.sendPostUser(ids[0], timeStamps[0]);
			methods.sendGetWaitTime(timeStamps[0]);
		} catch (Exception e) {
			e.getMessage();
		}
		
		assertEquals(200, methods.tempResponseCode);
		
		try {
			methods.sendDeleteUser(ids[0]);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testWaitTimeNoContent_Expect204Status() {
		try {
			methods.sendGetWaitTime(timeStamps[1]);
		} catch (Exception e) {
			e.getMessage();
		}
		
		assertEquals(204, methods.tempResponseCode);
		
	}
	
}
