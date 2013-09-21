package adaptavant.digistore;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Products {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long productId;
	private String brand;
	private String type;
	private String name;
	private Long price;
	private int unitsAvailable;
	private String blobKey;
	
	public String getBlobKey() {
		return blobKey;
	}
	public void setBlobKey(String blobKey) {
		this.blobKey = blobKey;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public int getUnitsAvailable() {
		return unitsAvailable;
	}
	public void setUnitsAvailable(int nua) {
		this.unitsAvailable = nua;
	}
	public void incUnitsAvailable() {
		this.unitsAvailable++;
	}
	public void decUnitsAvailable() {
		this.unitsAvailable--;
	}
}
