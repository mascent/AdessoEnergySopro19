package de.sopro.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.sopro.data.Issue;
import de.sopro.data.Person;
import de.sopro.data.User;
import de.sopro.dto.IssueDTO;
import de.sopro.repository.IssueRepository;
import de.sopro.repository.PersonRepository;
import de.sopro.repository.UserRepository;
import de.sopro.util.exception.ResourceNotFoundException;

/**
 * The issue controller contains operations to manage all requests belonging to
 * issue, which can be created by users to get in contact with an admin.
 * 
 * @author Mattis
 *
 */
@RestController
public class IssueController {

	@Autowired
	IssueRepository issueRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PersonRepository personRepository;
	

	/**
	 * This method allows an user to create a ticket for an issue that occurs.
	 * 
	 * @param request     The HTTPServletRequest that shows the identity of the
	 *                    user.
	 * @param name        The name of the user.
	 * @param email       The email address of this user. It is needed because the
	 *                    admin that resolves this ticket will contact the user via
	 *                    email.
	 * @param subject     The subject of the ticket. This is needed because it can
	 *                    be mapped to an admin which knows how to resolve these
	 *                    issues.
	 * @param description A textual description of the problem.
	 * @return The created issue packed as an IssueDTO.
	 * @throws ResourceNotFoundException 
	 */
	@PostMapping(path = "/api/issues", params = { "name", "email", "subject", "description" })
	@CrossOrigin
	public IssueDTO createIssue(HttpServletRequest request, @RequestParam String name,
			@RequestParam String email, @RequestParam String subject, @RequestParam String description) throws ResourceNotFoundException {
		String username = request.getUserPrincipal().getName();
		User u = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException());
		Issue i;
		try {
			i = issueRepository.save(new Issue(name, email, subject, description, u.getPersonId()));
		} catch (Exception e) {
			return null;
		}

		return new IssueDTO(i);
	}

	/**
	 * This method allows an admin to get a list of all issues existing in the
	 * system and their status.
	 * 
	 * @return An Iterable of the IssueDTOs of all existing issues.
	 */
	@GetMapping("/api/issues")
	@CrossOrigin
	public Iterable<IssueDTO> getIssues() {
		return StreamSupport.stream(issueRepository.findAll().spliterator(), false).map(i -> new IssueDTO(i))
				.collect(Collectors.toList());
	}
	
	/**
	 * This method allows an admin to get the name, email address, subject and
	 * description belonging to an issue by its ID.
	 * 
	 * @param iid The ID of the issue.
	 * @return The issue belonging to the given ID packed as an IssueDTO.
	 */
	@GetMapping(path = "/api/issues/{iid}", params = { "iid" })
	@CrossOrigin
	public IssueDTO getIssue(@PathVariable Long iid) {
		Issue i = issueRepository.findById(iid).orElse(null);
		if (i == null) {
			return null;
		}
		return new IssueDTO(i);
	}

	/**
	 * This method allows an admin to close an issue after he sent an email to the
	 * email address given in the issue. The further contact will happen via email.
	 * 
	 * @param request The HTTPServletRequest that shows the identity of the admin.
	 * @param iid     The ID of the issue that should be closed.
	 * @return A boolean that shows if the closing-operation was successful.
	 */
	@DeleteMapping(path = "/api/issues/{iid}", params = { "iid" })
	@CrossOrigin

	public Boolean closeIssue(HttpServletRequest request, @PathVariable Long iid) {
		Issue i = issueRepository.findById(iid).orElse(null);
		if (i == null) {
			return false;
		}
		String username = request.getUserPrincipal().getName();
		Person p = personRepository.findByUsername(username).orElse(null);
		if (p != null) {
			i.setCloserId(p.getPersonId());
			issueRepository.save(i);
			return true;
		}
		return false;
	}
}
