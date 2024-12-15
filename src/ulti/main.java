package ulti;

public class main {
	public static void main(String[] args) {
		// Khởi tạo quần thể
		Item[] items = { 
			    new Item(10, 5), new Item(5, 3), new Item(15, 8), new Item(7, 4), new Item(6, 2),
			    // new Item(18, 6), new Item(3, 1), new Item(8, 5), new Item(20, 7), new Item(2, 2),
			    // new Item(12, 7), new Item(9, 4), new Item(11, 3), new Item(13, 6), new Item(14, 8),
			    // new Item(16, 10), new Item(17, 9), new Item(19, 11), new Item(21, 12), new Item(22, 15)
			};
		
		GeneticAlgorithm rocket = new GeneticAlgorithm (100, items.length, 50, items);
		rocket.run();
	}
}
