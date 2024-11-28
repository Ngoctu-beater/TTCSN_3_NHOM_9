package ulti;

public class Population { 
	
	// Một quần thể gồm nhiều cá thể
 	Individual[] individuals;
 	
 	public Population ( int size, int numItems) {
 		
 		// Khởi tạo một quần thể mới với số lượng bằng tham số 'size'= 'số lượng cá thể'
 		individuals = new Individual[size];
 		
 		for ( int i=0; i<size; i++) {
 		// 'số lượng cá thể' gồm các cá thể, mỗi cá thể gồm 1 chuỗi gene: 0 hoặc 1-lấy hay không lấy đồ vật
 			individuals[i] =  new Individual(numItems);
 		}
 		
 	}
 	
}
