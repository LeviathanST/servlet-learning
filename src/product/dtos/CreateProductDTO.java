package product.dtos;

public class CreateProductDTO {
	private String name;
	private float price;
	private int productYear;
	private String image;
	private int categoryId;

	public CreateProductDTO(String name, float price, int productYear, String image, int categoryId) {
		this.name = name;
		this.price = price;
		this.productYear = productYear;
		this.image = image;
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getProductYear() {
		return productYear;
	}

	public void setProductYear(int productYear) {
		this.productYear = productYear;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
