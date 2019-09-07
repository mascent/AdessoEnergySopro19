package de.sopro.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.sopro.data.Issue;
import de.sopro.data.Role;
import de.sopro.data.User;
import de.sopro.dto.IssueDTO;
import de.sopro.dto.UserDTO;
import de.sopro.repository.IssueRepository;
import de.sopro.repository.PersonRepository;
import de.sopro.repository.UserRepository;

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

	/**
	 * This method allows an user to create a ticket for an issue that occurs.
	 * 
	 * @param name        The name of the user.
	 * @param email       The email address of this user. It is needed because the
	 *                    admin that resolves this ticket will contact the user via
	 *                    email.
	 * @param subject     The subject of the ticket. This is needed because it can
	 *                    be mapped to an admin which knows how to resolve these
	 *                    issues.
	 * @param description A textual description of the problem.
	 * @return The ID of the issue that was created.
	 */
	 // Ich würde hier auch den request mit ausgeben lassen und schauen ob der user seine richtigen Daten angegeben hat bzw ist das überhaupt nötig? wir wissen ja eh wer die Anfrage stellt.
	@PostMapping(path = "/api/issues", params = { "name", "email", "subject", "description" })
	public IssueDTO createIssue(@RequestParam String name, @RequestParam String email, @RequestParam String subject,
			@RequestParam String description) {
		User u1 = userRepository.findByUsername(name);
		User u2 = userRepository.findByEMailAddress(email);
		if (u1.equals(u2)) {
			Issue i;
			try {
				i = issueRepository.save(new Issue(name, email, subject, description, u1.getPersonId()));
			} catch (Exception e) {
				return null;
			}

			return new IssueDTO(i);
		}
		return null;
	}

	/**
	 * This method allows an admin to close an issue after he sent an email to the
	 * email address given in the issue. The further contact will happen via email.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param iid   The ID of the issue that should be closed.
	 * @return A boolean that shows if the closing was successful.
	 */
	@DeleteMapping("/api/issues/{iid}")
	public Boolean closeIssue(@PathVariable Long iid) {
		if (issueRepository.existsById(iid)) {
		    //wir wollen hier nicht tatsächlich löschen, sondern nur deletedAt und By setzen
			issueRepository.deleteById(iid);
			return true;
		}
		return false;
	}

//Get mappings for Put, Post und Delete mappings
	/**
	 * This method allows an admin to get the name, email address, subject and
	 * description belonging to an issue by its ID.
	 * 
	 * @param token The JWT of the admin to authenticate himself. 
	 * @param iid   The ID of the issue.
	 * @return The issue object belonging to the given ID.
	 */
	@GetMapping("/api/issues/{iid}")
	public IssueDTO getIssue(@PathVariable Long iid) {
			Issue i = issueRepository.findById(iid).orElse(null);
			if (i == null) {
				return null;
			}
			return new IssueDTO(i);
	}


// Nach Länge der URL sortieren
	/**
	 * This method allows an admin to get a list of all issues existing in the
	 * system and their status.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @return A list of all issue IDs and their status (closed/open).
	 */
	@GetMapping("/api/issues")
	public Iterable<IssueDTO> getIssues() {
		return StreamSupport.stream(issueRepository.findAll().spliterator(), false).map(u -> new IssueDTO(u))
				.collect(Collectors.toList());
	}
}
