package com.valeria.backend.modules.userproduct.dto;
import java.util.List;
import java.time.LocalTime;
import java.time.LocalDate;
public class UserProductRequest {
    public Long userId;
    public List<ProductItem> products;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<ProductItem> getProducts() {
		return products;
	}

	public void setProducts(List<ProductItem> products) {
		this.products = products;
	}
	
	
	
	
    public static class ProductItem {
        public Integer howDays;
        public Integer howOften;
        public LocalTime firstTake;
        private LocalDate firstTakeDate;
        public String description;
        public Long productId;
        
        public Integer getHowDays() {
            return howDays;
        }

        public void setHowDays(Integer howDays) {
            this.howDays = howDays;
        }

        public Integer getHowOften() {
            return howOften;
        }

        public void setHowOften(Integer howOften) {
            this.howOften = howOften;
        }

        public LocalTime getFirstTake() {
            return firstTake;
        }

        public void setFirstTake(LocalTime firstTake) {
            this.firstTake = firstTake;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

		public LocalDate getFirstTakeDate() {
			return firstTakeDate;
		}

		public void setFirstTakeDate(LocalDate firstTakeDate) {
			this.firstTakeDate = firstTakeDate;
		}
        
    }

    
    
}
