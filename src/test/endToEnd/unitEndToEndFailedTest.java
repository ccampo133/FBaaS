package endToEnd;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class unitEndToEndFailedTest {
	
	private final static long[] ids = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,11 };
	private final static long[] timeStamps = { 1232345, 242346, 123523, 1264624, 1643245, 2352346,
			2523417, 313418, 1236349, 1632310, 34126211 };
	
	static RestTestClient methods = new RestTestClient();

	@Test
	public void testListAllUsers_MultipleUsersInQueue_Expect200Status() {
		try {
			methods.sendPostUser(ids[0], timeStamps[0]);
			methods.sendPostUser(ids[1], timeStamps[1]);
			methods.sendGetUsers();
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
}
