package com.valeria.backend.modules.task.model;
import com.valeria.backend.model.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import java.math.BigDecimal;
import jakarta.persistence.*;
@Entity
@Table(name="task")
@SQLDelete(sql="update task set deleted_at = SYSTIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at IS NULL")

public class Task extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	public Task() {}
	public Task(Long id, String name, String description) {
		this.id=id;
		this.name=name;
		this.description=description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
