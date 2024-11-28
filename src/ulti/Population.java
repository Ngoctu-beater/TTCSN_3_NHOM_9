package ulti;

public class Population { 
	
	// Một quần thể gồm nhiều cá thể
 	Individual[] individuals;
 	
 	public Population ( int size, int numItems) {
 		
 		// Khởi tạo một quần thể mới với số lượng bằng tham số 'size'= 'số lượng cá thể'
 		individuals = new Individual[size];
 		
 		for ( int i=0; i<size; i++) {
 		// Mỗi cá thể được khởi tạo với 1 chuỗi gene
 		// số lượng phần tử của gene = số đồ vật = số lựa chọn có chọn đồ vật vào túi hay không ( 0 hoặc 1 )
 			individuals[i] =  new Individual(numItems);
 		}
 		
 	}
 	
}
