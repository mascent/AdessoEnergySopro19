package de.sopro.data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "reading")
public class Reading {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long readingId;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Meter meter;

	@NotNull
	LocalDateTime createdAt;

	LocalDateTime updatedAt;

	LocalDateTime deletedAt;

	public Reading(Meter meter) {
		this.createdAt = LocalDateTime.now();
		this.meter = meter;
	}


	public Reading() {
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public Meter getMeter() {
		return meter;
	}

	public Long getReadingId() {
		return readingId;
	}

	public void delete() {
		if (this.deletedAt == null) {
			this.deletedAt = LocalDateTime.now();
		}
	}

//	public ReadingValue getCurrentReadingValue() {
//		LocalDateTime newestTime = getCreatedAt();
//		ReadingValue newstValue = null;
//		for (ReadingValue rv : readingValues) {
//			if (!rv.getCreatedAt().isBefore(newestTime)) {
//				newestTime = rv.getCreatedAt();
//				newstValue = rv;
//			}
//		}
//
//		return newstValue;
//	}

//	public Long getValue() {
//		return getCurrentReadingValue().getValue();
//	}

//	public LocalDateTime getCreatedAt() {
//		LocalDateTime oldestTime = LocalDateTime.now();
//		for (ReadingValue rv : readingValues) {
//			if (rv.getCreatedAt().isBefore(oldestTime)) {
//				oldestTime = rv.getCreatedAt();
//			}
//		}
//		return oldestTime;
//	}

//	public LocalDateTime getUpdatedAt() {
//		return getCurrentReadingValue().getCreatedAt();
//	}
//
//	public LocalDateTime getDeletedAt() {
//		return deletedAt;
//
//	}

}
