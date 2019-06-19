package bculkinmodFinal.shufflers;

import bculkinmodFinal.Card;
import java.util.Arrays;
import java.util.List;

/**
*
* @author benadam
*/
class CompositeShuffler implements Shuffler {
	private final List<Shuffler> sfls;
	
	public CompositeShuffler(Shuffler[] shfs) {
		this.sfls = Arrays.asList(shfs);
	}
	
	@Override
	public void shuffle(List<Card> crds) {
		for (Shuffler shuffler : sfls) {
			shuffler.shuffle(crds);
		}
	}
	
	@Override
	public String getShuffleName() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Composite Shuffle using: ");
		
		for (Shuffler shuffler : sfls) {
			sb.append(shuffler.getShuffleName()).append(", ");
		}
		
		sb.deleteCharAt(sb.lastIndexOf(","));
		
		return sb.toString();
	}
	
	@Override
	public void customize() {
		System.out.println("Customizing Composite Shuffler");
		System.out.print("Enter command ( m for help ): ");
		
		String tst = Shufflers.scan.next();
		
		while (true) {
			switch (tst) {
			case "m":
				System.out.println("p - print current shuffler parameters");
				System.out.println("c - customize composited shuffles");
				System.out.println("q - quit customizing shuffle");
				System.out.println("m - show this message.");
				break;
			case "p":
				System.out.println(this.toString());
				break;
			case "c":
				customizeComposites();
				break;
			case "q":
				System.out.println("Done Customizing");
				return;
			default:
				System.out.println("? UC");
			}
			
			tst = Shufflers.scan.next();
			System.out.print("Enter command ( m for help ): ");
		}
	}
	
	private void customizeComposites() {
		System.out.println("Customizing Composite Component Shuffles");
		
		System.out.print("Enter command ( m for help ): ");
		
		String tst = Shufflers.scan.next();
		
		int inp;
		
		while (true) {
			switch (tst) {
			case "m":
				System.out.println("p - print list of component shuffles");
				System.out.println("d - delete a component shuffle");
				System.out.println("c - customize a component shuffle");
				System.out.println("a - add a component shuffle");
				System.out.println("w - wrap a component shuffle");
				System.out.println("q - quit customizing composite component shuffles");
				System.out.println("m - show this help message");
				break;
			case "p":
				System.out.print("Components: ");
		
				for (int i = 0; i < sfls.size(); i++) {
					Shuffler shuffler = sfls.get(i);
				
					if (i != 0) {
						System.out.print(", " + shuffler.getShuffleName());
					} else {
						System.out.print(shuffler.getShuffleName());
					}
				}
				break;
			case "d":
				System.out.print("Enter a index to delete (-1 to list): ");
				
				inp = Shufflers.scan.nextInt();
				
				if (inp == -1) {
					System.out.print("Components: ");
				
					for (int i = 0; i < sfls.size(); i++) {
						Shuffler shuffler = sfls.get(i);
						
						if (i != 0) {
							System.out.print(", " + shuffler.getShuffleName());
						} else {
							System.out.print(shuffler.getShuffleName());
						}
					}
				} else {
					try {
						sfls.remove(inp);
					} catch (IndexOutOfBoundsException ioobx) {
						System.out.println("? II");
					}
				}
				break;
			case "c":
				System.out.print("Enter a index to customize (-1 to list): ");
				
				inp = Shufflers.scan.nextInt();
				
				if (inp == -1) {
					System.out.print("Components: ");
				
					for (int i = 0; i < sfls.size(); i++) {
						Shuffler shuffler = sfls.get(i);
					
						if (i != 0) {
							System.out.print(", " + shuffler.getShuffleName());
						} else {
							System.out.print(shuffler.getShuffleName());
						}
					}
				} else {
					try {
						sfls.get(inp).customize();
					} catch (ArrayIndexOutOfBoundsException aioobx) {
						System.out.println("? II");
					}
				}
				break;
			case "a":
				System.out.print("Enter a index to add at (-1 to list): ");
				
				inp = Shufflers.scan.nextInt();
				
				if (inp == -1) {
					System.out.print("Components: ");
					for (int i = 0; i < sfls.size(); i++) {
						Shuffler shuffler = sfls.get(i);
				
						if (i != 0) {
							System.out.print(", " + shuffler.getShuffleName());
						} else {
							System.out.print(shuffler.getShuffleName());
						}
					}
				} else {
					try {
						sfls.add(inp, Shufflers.newCustom());
					} catch (Exception x) {
						System.out.println("? EX");
					}
				}
				break;
			case "w":
				System.out.print("Enter a index to wrap (-1 to list): ");
				
				inp = Shufflers.scan.nextInt();
				
				if (inp == -1) {
					System.out.print("Components: ");
				
					for (int i = 0; i < sfls.size(); i++) {
						Shuffler shuffler = sfls.get(i);
					
						if (i != 0) {
							System.out.print(", " + shuffler.getShuffleName());
						} else {
							System.out.print(shuffler.getShuffleName());
						}
					}
				} else {
					try {
						sfls.set(inp, Shufflers.newWrap(sfls.get(inp)));
					} catch (Exception x) {
						System.out.println("? EX");
					}
				}
				break;
			case "q":
				System.out.println("Done customizing");
				return;
			default:
				System.out.println("? UC");
			}
		}
	}
}