package ulti;

public class Item {

	int value;     // giá trị đồ vật
	int weight;    // trọng lượng đồ vật

	// hàm tạo không đối
    	public Item() {
    	}
	
	public Item(int value, int weight) {
		this.value = value;
		this.weight = weight;
	}

	// getter và setter
	public int getValue() {
		return value;
	}

	public void setValue() {
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight() {
		this.weight = weight;
	}
}
