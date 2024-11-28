package ulti;

// Class đại diện cho một cá thể
public class Individual {
	
	// Mảng gen ( 0-1 : Chọn hay không chọn đồ vật
	int[] genes;
	
	// Độ thích nghi của cá thể
	int fitness;

	// Constructor của Individual
	public Individual ( int numItems) {
		
		// mảng genes được khởi tạo với số lượng = numItems - số lượng đồ vật trong bài toán cái túi
		genes = new int [numItems];
		
		// độ thích nghi khi được khởi tạo = 0
		fitness =0;
	}
	
}
