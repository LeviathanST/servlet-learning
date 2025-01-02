package product.dtos;

public class UpdateProductDTO {
	private Integer id;
	private String name;
	private Float price;
	private Integer productYear;
	private String image;
	private Integer categoryId;

	public UpdateProductDTO() {
	}

	public UpdateProductDTO(int id, String name, float price, int productYear, String image, int categoryId) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.productYear = productYear;
		this.image = image;
		this.categoryId = categoryId;
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

	public Float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getProductYear() {
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

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
