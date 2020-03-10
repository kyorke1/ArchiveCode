public class DisplayTest002 {
	
	public static void main(String[] args) {
		
		System.out.println("\n");
		System.out.println("      ====Displaying only those with wage over 50000====");
		System.out.println("\n");
		treeModel model = new treeModel();
		model.showOver50000(null, "      ");
		System.out.println("\n");
		System.out.println("      ====Displaying the number of subordinates====");
		System.out.println("\n");
		model.showSubordinates(null, "      ");
	}
}

