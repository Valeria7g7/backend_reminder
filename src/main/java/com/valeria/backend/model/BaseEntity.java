package com.valeria.backend.model;
import java.time.LocalDateTime;
import lombok.Data;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
//import lombok.Getter;
//import lombok.Setter;
//@Getter
//@Setter
@Data
@MappedSuperclass
public abstract class BaseEntity {
	@CreationTimestamp
	//este campo NO puede modificarse después del INSERT.
	@Column(name = "created_at", updatable = false)
	private LocalDateTime created_at;
	
	@UpdateTimestamp
	@Column(name = "updated_at", updatable = true)
	private LocalDateTime updated_at;
	@Column(name = "deleted_at", updatable = true)
	private LocalDateTime deleted_at;
	
//	public LocalDateTime getCreated_at() {
//		return created_at;
//	}
//	public void setCreated_at(LocalDateTime created_at) {
//		this.created_at = created_at;
//	}
//	public LocalDateTime getUpdated_at() {
//		return updated_at;
//	}
//	public void setUpdated_at(LocalDateTime updated_at) {
//		this.updated_at = updated_at;
//	}
//	public LocalDateTime getDeleted_at() {
//		return deleted_at;
//	}
//	public void setDeleted_at(LocalDateTime deleted_at) {
//		this.deleted_at = deleted_at;
//	}


}
