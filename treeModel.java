import javax.swing.tree.DefaultMutableTreeNode;
public class treeModel {
	
	DefaultMutableTreeNode e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14;
	
	public treeModel(){
		initialiseNode();
		buildTree();
	}
	
	public void initialiseNode(){
		e1 = new DefaultMutableTreeNode (new Employee ("Mohammed", "Ali", "Senior Partner", 120000));
		e2 = new DefaultMutableTreeNode (new Employee ("Sara", "Johnson", "Managing Partner", 89000));
		e3 = new DefaultMutableTreeNode (new Employee ("Sandra", "Dee", "Partner", 78500));
		e4 = new DefaultMutableTreeNode (new Employee ("Fred", "Dibner", "Finance Manager", 67900));
		e5 = new DefaultMutableTreeNode (new Employee ("Cleo", "Patrar", "Junior Partner", 45000));
		e6 = new DefaultMutableTreeNode (new Employee ("Irfan", "Patel", "Junior Partner", 45000));
		e7 = new DefaultMutableTreeNode (new Employee ("George", "Bush", "Office Manager", 37000));
		e8 = new DefaultMutableTreeNode (new Employee ("Harry", "Potter", "Solicitor", 52500));
		e9 = new DefaultMutableTreeNode (new Employee ("Ronald", "Reagan", "Senior Clerk", 22000));
		e10 = new DefaultMutableTreeNode (new Employee ("Simon", "Templar", "Finance Officer", 18000));
		e11 = new DefaultMutableTreeNode (new Employee ("Jacob", "Heart", "Clerk", 16000));
		e12 = new DefaultMutableTreeNode (new Employee ("Barry", "Dwyer", "Clerk", 16000));
		e13 = new DefaultMutableTreeNode (new Employee ("Mary", "Fritz", "Clerk", 16000));
		e14 = new DefaultMutableTreeNode (new Employee ("Gordon", "Brown", "Finance Clerk", 16500 ));
		
	}
	
	public void buildTree(){
		e1.add(e2);
		e1.add(e3);
		e1.add(e4);
		e2.add(e5);
		e2.add(e6);
		e2.add(e7);
		e3.add(e8);
		e3.add(e9);
		e4.add(e10);
		e7.add(e11);
		e7.add(e12);
		e9.add(e13);
		e10.add(e14);
		
	}
	
	public void showAllChildren(DefaultMutableTreeNode node, String tab){
		if(node == null)
		{node = e1;}
		System.out.println(tab + node);	// calls toString to print node	
		for(int i = 0 ; i < node.getChildCount(); i++)
			showAllChildren((DefaultMutableTreeNode)node.getChildAt(i), tab + "      ");
			
	}
	public void showOver50000(DefaultMutableTreeNode node, String tab){
		
		if(node == null)
		{node = e1;}
		if (((Employee) node.getUserObject()).getSalary()>49000){
		System.out.println(tab + node);	// calls toString to print node	
		}
		for(int i = 0 ; i < node.getChildCount(); i++){
			showOver50000((DefaultMutableTreeNode)node.getChildAt(i), tab + "      ");
			}
		}
		
	public void showSubordinates(DefaultMutableTreeNode node, String tab){
		if(node == null)
		{node = e1;}
		if (!node.isLeaf()){
			System.out.println(tab + node + ", Number of subordinates: " + node.getChildCount());	// calls toString to print node	
			}
		for(int i = 0 ; i < node.getChildCount(); i++){
			showSubordinates((DefaultMutableTreeNode)node.getChildAt(i), tab + "      ");
			}
	}
}