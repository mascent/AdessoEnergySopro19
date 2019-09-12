package de.sopro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import de.sopro.data.Meter;
import de.sopro.data.User;
import de.sopro.data.UserMeterAssociation;

public interface UserMeterAssociationRepository extends CrudRepository<UserMeterAssociation, Long> {

	Iterable<UserMeterAssociation> findAllByMeter(Meter meter);

	Iterable<UserMeterAssociation> findAllByUserAndMeter(User user, Meter meter);

	Iterable<UserMeterAssociation> findAllByUser(User user);

	@Query(value = "Select uma FROM userMeterAssociation WHERE (uma.meterID = :meterID) AND (endOfAssociation is null)", nativeQuery = true)
	List<UserMeterAssociation> findCurrentUserOfMeter(@Param("meterID") Long meterID);

	// TODO create soft delte for associations
	// void deleteByMeter(Meter meter);

}
