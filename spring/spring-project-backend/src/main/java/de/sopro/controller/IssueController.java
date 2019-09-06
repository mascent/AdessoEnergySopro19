package de.sopro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The issue controller contains operations to manage all requests belonging to
 * issue, which can be created by users to get in contact with an admin.
 * 
 * @author Mattis
 *
 */
@RestController
public class IssueController {

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
	@PostMapping("/api/issues")
	public String createIssue(@RequestParam String name, @RequestParam String email, @RequestParam String subject,
			@RequestParam String description) {
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
	public String closeIssue(@PathVariable Long iid) {
		return null;
	}

	/**
	 * This method allows an admin to get the name, email address, subject and
	 * description belonging to an issue by its ID.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @param iid   The ID of the issue.
	 * @return The issue object belonging to the given ID.
	 */
	@GetMapping("/api/issues/{iid}")
	public String getIssue(@PathVariable Long iid) {
		return null;
	}

	/**
	 * This method allows an admin to get a list of all issues existing in the
	 * system and their status.
	 * 
	 * @param token The JWT of the admin to authenticate himself.
	 * @return A list of all issue IDs and their status (closed/open).
	 */
	@GetMapping("/api/issues")
	public String getIssues() {
		return null;
	}
}
