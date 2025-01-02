package product;

import category.CategoryEntity;

public class ProductEntity {
	private int id;
	private String name;
	private float price;
	private int productYear;
	private String image;
	private CategoryEntity category;

	public ProductEntity() {
	}

	public ProductEntity(int id, String name, float price, int productYear, String image, CategoryEntity category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.productYear = productYear;
		this.image = image;
		this.category = category;
	}

	public ProductEntity(String name, float price, int productYear, String image, CategoryEntity category) {
		this.name = name;
		this.price = price;
		this.productYear = productYear;
		this.image = image;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
}
