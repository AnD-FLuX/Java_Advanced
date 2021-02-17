package ua.lviv.lgs.domain;

public class Bucket {
	private Integer id;
	private Integer userId;
	private Integer productId;
	private String nowDate;

	public Bucket(Integer id, Integer userId, Integer productId, String nowDate) {
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.nowDate = nowDate;
	}

	public Bucket(Integer userId, Integer productId) {
		this.userId = userId;
		this.productId = productId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String date) {
		this.nowDate = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nowDate == null) ? 0 : nowDate.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bucket other = (Bucket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nowDate == null) {
			if (other.nowDate != null)
				return false;
		} else if (!nowDate.equals(other.nowDate))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nBucket [id=" + id + ", userId=" + userId + ", productId=" + productId + ", purchace_date = " + nowDate
				+ "]";
	}

}
