package ulti;

// Class đại diện cho một cá thể
public class Individual {
	
	// Mảng gen ( 0-1 : Chọn hay không chọn đồ vật
	int[] genes;
	
	// Độ thích nghi của cá thể
	int fitness;

	// Constructor của Individual
	public Individual ( int numItems) {
		
		// Mảng genes được khởi tạo với số lượng = numItems - số lượng đồ vật trong bài toán cái túi
		genes = new int [numItems];
		
		// Độ thích nghi khi được khởi tạo = 0
		fitness =0;
	}

	// getter và setter
    	public int[] getGenes() {
        	return genes;
    	}

    	public void setGene(int index, int value) {
        	genes[index] = value;
    	}

    	public int getFitness() {
        	return fitness;
    	}

    	public void setFitness(int fitness) {
        	this.fitness = fitness;
    	}

    	public int size() {
        	return genes.length;
    	}	
}
