package de.sopro.repository;

import org.springframework.data.repository.CrudRepository;

import de.sopro.data.Address;

public interface IssueRepository extends CrudRepository<Address,String> {

}
