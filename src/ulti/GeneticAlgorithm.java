package ulti;

import java.util.Arrays;
import java.util.Random;
import java.util.Comparator;

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
			for ( int i = 0; i < numItems; i++) {
				// Khởi tạo gene cho từng cá thể, số lượng gene ứng với số lượng 'lựa chọn đồ vật'
				// 'lựa chọn đồ vật' = số đồ vật có thể bỏ vào túi
				individual.genes[i] = random.nextInt(2); // 0 hoặc 1
			}
		}
	}
	
	// Đánh giá độ thích nghi
	public void evaluateFitness (Population population) {
		// Duyệt từng cá thể trong quần thể
		for (Individual individual : population.individuals) {
			// Tổng giá trị từ đồ vật đã chọn
			int totalValue = 0;
			
			// Tổng khối lượng từ đồ vật đã chọn
			int totalWeight = 0;
			
			// Duyệt qua số lượng các đồ vật OR các phần tử trong mảng gene của cá thể
			for (int i = 0; i < numItems; i++) {
				// Nếu phần tử gene = 1 -> tăng giá trị và trọng lượng cái túi 
				if (individual.genes[i] == 1) {
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
	  
	  // Lai ghép 
	  public void crossover ( Population population ) {
		  // Lặp lại quá trình lai ghép 20 lần
		  for ( int i=0; i<20; i++ ) {
			  // Chọn ngẫu nhiên 1 cặp bố mẹ từ quần thể
			  int parent1Index = random.nextInt(populationSize);
			  int parent2Index = random.nextInt(populationSize);
			  
			  Individual parent1 = population.individuals[parent1Index];
			  Individual parent2 = population.individuals[parent2Index];
			  
			  // Thực hiện lai ghép mảng gen của bố mẹ
			  for ( int j=0; j< numItems; j++ ) {
				  if ( random.nextInt(2) == 1 ) { // Tạo xác suất 50% khả năng lai ghép ( Tạo random 1 số hoặc 0 hoặc 1, nếu trả ra 1 == 1 return True => tiến hành lai ghép )
					  // Trao đổi một phần tử gen giữa 2 bố mẹ
					  int temp = parent1.genes[j];
					  parent1.genes[j] = parent2.genes[j];
					  parent2.genes[j] = temp;
				  }
			  }
		  }
	  }
	  
	  // Đột biến
	  public void mutation (Population population) {
		  // Tạo random 1 cá thể đột biến từ quần thể
		  int index = random.nextInt(populationSize);
		  
		  // Tạo random 1 phần tử gene đột biến từ mảng gen
		  int geneIndex = random.nextInt(numItems);
		  
		  // Đảo ngược giá trị của phần từ gen đột biến của cá thể đột biến
		  population.individuals[index].genes[geneIndex]=  1 - population.individuals[index].genes[geneIndex];
	  }
	  
	  // Điều kiện dừng 
	  // Xét các yếu tố: 
	  //	+ số lượng thế hệ: generation-maxGenerations : khi 'số thế hệ' đã đạt hoặc quá giới hạn số lượng thế hệ => return true 
	  //	+ độ thích nghi: bestFitness-threshhold: khi 'giá trị độ thích nghi tốt nhất' đã đạt hoặc vượt ngưỡng => return true
	  //	+ độ cải thiện thích nghi: noImprovement-maxNoImprovement: khi 'số thế hệ không có độ cải thiện' vượt quá ngưỡng => return true
	  public boolean stopCondition ( int generation, int maxGenerations, int bestFitness, int threshold, int noImprovement, int maxNoImprovement) {
		  return generation >= maxGenerations || bestFitness >= threshold || noImprovement >= maxNoImprovement;
	  }
	  
	  // Giải thuật
	  public void run() {
		  // khởi tạo quần thể
		  Population population = new Population( populationSize, numItems);
		  initializePopulation ( population);
		  
		  // Thế hệ đầu 
		  int generation = 0;
		  
		  // Số lượng thế hệ tối đa
		  int maxGenerations = 1000;
		  
		  // Độ thích nghi 
		  int bestFitness = 0;
		  
		  // Ngưỡng thích nghi tốt nhất
		  int threshold = 500;
		  
		  // Số thế hệ không có độ cải thiện 
		  int noImprovement = 0;
		  
		  // Ngưỡng giới hạn số thế hệ không có độ cải thiện
		  int maxNoImprovement = 100; 

		  // Lưu trữ cá thể tốt nhất qua all thế hệ
        	  Individual bestIndividual = null;
		  
		  while ( !stopCondition (generation, maxGenerations, bestFitness, threshold, noImprovement, maxNoImprovement)) {
			  
			  // Đánh giá độ thích nghi của quần thể
			  evaluateFitness (population);
			  
			  // Duyệt qua toàn bộ các cá thể trong quần thể
			  // Tìm giá trị max của độ thích nghi-fitness
			  int currentBest = Arrays.stream(population.individuals)
					  					.max(Comparator.comparingInt(ind -> ind.fitness))
					  					.orElse(null); // TH hiếm: không có cá thể nào, return null
			  System.out.println("New generation "+generation+ " is having their best fitness: " + currentBest);
			  System.out.println("Population has been have the best fitness: "+bestFitness);
			  if ( currentBest != null && currentBest.fitness > bestFitness ) {
				  bestFitness = currentBest.fitness;
                		  bestIndividual = currentBest;
				  noImprovement = 0;
			  } else {
				  noImprovement++;
			  }
			  
			  // Chọn lọc
			  selection (population);
			  
			  // Lai ghép
			  crossover (population);
			  
			  //Đột biến
			  mutation(population);
			  System.out.println(">>>>> Generation: " + generation + " --> Population's best fitness: "+bestFitness +"" );
			  System.out.println();
			  
			  generation++ ;

		  }
		  
		  System.out.println("After "+ generation + " generations, come up with the best fitness for the population: "+ bestFitness);
		  System.out.println("Max value of the bag: "+bestFitness);

		  if (bestIndividual != null) {
            		int totalWeight = 0;
            		int totalValue = 0;

            		System.out.println("\nSelected items in the best solution:");
            		for (int i = 0; i < numItems; i++) {
                		if (bestIndividual.genes[i] == 1) {
                    		System.out.println("Item " + i + " (Value: " + items[i].value + ", Weight: " + items[i].weight + ")");
                    		totalWeight += items[i].weight;
                    		totalValue += items[i].value;
                	}
            	}

            	System.out.println("Total Value: " + totalValue);
            	System.out.println("Total Weight: " + totalWeight);
        	} else {
            		System.out.println("No valid solution found.");
        	}
		  
		  System.out.println("\n *****Algorithm ends*****");
	  }	
}
