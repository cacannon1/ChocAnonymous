package main;

/**
 * Verifies that the member or provider is in the database.
 * @author Tabitha Williams
 */
public class Verify {
	
	/**
	 * Verifies that the given member ID is in the database.
	 * @param Id The member's ID.
	 * @return 0=valid, 1=suspended, 2=member not found.
	 */
	public static int verifyMember (int Id) {
		for (int i=0; i < Database.members.size(); i++) {
			if (Database.members.get(i).getId() == Id) {
				if (Database.members.get(i).getStatus() == true ) { //valid
					return 0; 
				}
				else { 
					return 1; 										//suspended
				}
			}
		}
		return 2;													//member does not exist
	}
	
	/**
	 * Verifies that the given provider ID is in the database.
	 * @param Id The provider's ID.
	 * @return 0=valid, 2=provider not found.
	 */
	public static int verifyProvider (int Id) {
		for (int i=0; i < Database.providers.size(); i++) {
			if (Database.providers.get(i).getId() == Id) {
				return 0;
			}
		}
		return 2;
	}	
}
