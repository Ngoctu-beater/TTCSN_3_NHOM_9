package ulti;

import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm {
	// Kích thước quần thể
	private int populationSize;
	
	// Số lượng phần tử 
	private int numItems;
	
	// Khối lượng tối đa (của cái túi)
	private int maxWeight;
	
	// Mảng các đồ vật
	private Item[] items;
	
	// Phép toán random trong giải thuật di truyền
	private Random random;
	
	public GeneticAlgorithm(int populationSize, int numItems, int maxWeight, Item[] items) {
		this.populationSize = populationSize;
        this.numItems = numItems;
        this.maxWeight = maxWeight;
        this.items = items;
        this.random = new Random();
	}
	
	// Khởi tạo quần thể 
	public void initializePopulation ( Population population) {
		// Duyệt từng cá thể trong quần thể
		for (Individual individual : population.individuals) {
			for ( int i=0; i < numItems; i++) {
				// Khởi tạo gene cho từng cá thể, số lượng gene ứng với số lượng 'lựa chọn đồ vật'
				// 'lựa chọn đồ vật' = số đồ vật có thể bỏ vào túi
				individual.genes[i] = random.nextInt(2); // 0 hoặc 1
			}
		}
	}
	
	// Đánh giá độ thích nghi
	public void evaluateFitness ( Population population ) {
		// Duyệt từng cá thể trong quần thể
		for (Individual individual : population.individuals) {
			// Tổng giá trị từ đồ vật đã chọn
			int totalValue =0;
			
			// Tổng khối lượng từ đồ vật đã chọn
			int totalWeight =0;
			
			// Duyệt qua số lượng các đồ vật OR các phần tử trong mảng gene của cá thể
			for ( int i=0; i < numItems; i++ ) {
				
				if ( individual.genes[i] ==1 ) {
					totalValue += items[i].value;
					totalWeight += items[i].weight;
				}
			}
			
			// Đánh giá mức độ thích nghi 
			if ( totalWeight > maxWeight) {
				// Nếu 'tổng khối lượng' mà mã gene của cá thể đem lại > 'khối lượng tối đa' của cái túi
				// thì sẽ áp dụng "hình phạt"- giảm độ thích nghi của cá thể (bằng cách trừ đi một số có giá trị lớn)
				individual.fitness = totalValue - 1000000;
			} else {
				// Ngược lại, gán độ thích nghi của cá thể  = giá trị mà mã gene đem lại
				individual.fitness = totalValue;
			}
		}
	}
	
	// Chọn lọc
	  public void selection(Population population) {
		  //
	        int[] fitnessArray = Arrays.stream(population.individuals) // Chuyển đổi mảng individuals của quần thể population thành 1 chuỗi S
	                                   .mapToInt(ind -> ind.fitness) // lấy giá trị fitness trong mỗi individuals và chuyển thành kiểu int => mỗi stream fitness (int)
	                                   .toArray(); // Chuyển stream fitness (int) bên trên thành 1 mảng
	        Arrays.sort(fitnessArray); // săp xếp mảng int fitness theo thứ tự tăng dần
	        
	        // Từ số lượng của quần thể, lấy ra 20% vượt trội trong bầy đàn
	        int superiors = populationSize * 20 / 100;
	        
	        // Ngưỡng để phân loại quần thể, lấy mốc từ độ thích nghi của 20% cá thể vượt trội
	        int threshold = fitnessArray[superiors]; 

	        for (int i = 0; i < populationSize; i++) {
	        	
	        	// Lọc những cá thể có 'độ thích nghi' fitness nhỏ hơn 'ngưỡng phân loại ' threhold
	            if (population.individuals[i].fitness < threshold) {
	            	// Cá thể có độ thích nghi kém sẽ bị thay thế bởi 1 cá thể ngẫu nhiên trong quần thể
	                population.individuals[i] = population.individuals[random.nextInt(populationSize)];
	            }
	        }
	    }

	
	
}
