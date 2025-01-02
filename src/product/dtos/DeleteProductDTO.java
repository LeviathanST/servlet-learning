package product.dtos;

public class DeleteProductDTO{
	private Integer id;

	public DeleteProductDTO(Integer id)  {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
