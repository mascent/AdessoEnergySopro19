package de.sopro.repository;

import org.springframework.data.repository.CrudRepository;

import de.sopro.data.Address;
import de.sopro.data.Issue;

public interface IssueRepository extends CrudRepository<Issue, Long> {

}
