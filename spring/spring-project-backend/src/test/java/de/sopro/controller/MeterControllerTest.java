package de.sopro.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.google.gson.Gson;

import de.sopro.data.Meter;
import de.sopro.data.MeterType;
import de.sopro.data.Person;
import de.sopro.data.Reading;
import de.sopro.data.ReadingValue;
import de.sopro.data.Role;
import de.sopro.data.User;
import de.sopro.data.UserMeterAssociation;
import de.sopro.dto.ReadingDTO;
import de.sopro.repository.MeterRepository;
import de.sopro.repository.PersonRepository;
import de.sopro.repository.ReadingRepository;
import de.sopro.repository.ReadingValueRepository;
import de.sopro.repository.UserMeterAssociationRepository;
import de.sopro.repository.UserRepository;
import okhttp3.Credentials;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MeterControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	MeterRepository meterRepository;

	@Autowired
	ReadingRepository readingRepository;

	@Autowired
	UserMeterAssociationRepository umaRepository;

	@Autowired
	ReadingValueRepository readingValueRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	Meter m;
	Long meterId;
	User user;
	Person admin;
	String userCredentials;
	String adminCredentials;
	UserMeterAssociation uma;

	@BeforeEach
	public void createEntrys() {
		// assertEquals(1, 2);
		m = new Meter("98765432", MeterType.Electricity);
		assertNotEquals(null, m);
		meterRepository.save(m);

		Reading nr = readingRepository.save(new Reading(m));
		readingValueRepository.save(new ReadingValue(nr, (long) 1111111, (long) 1, "lol"));

		meterId = m.getMeterId();
		String userPassword = "password";
		String adminPassword = "password";
		if (userRepository.findByUsername("fwm").orElse(null) != null) {
			userRepository.deleteById(userRepository.findByUsername("fwm").orElse(null).getPersonId());
		}

		if (personRepository.findByUsername("fler").orElse(null) != null) {
			personRepository.deleteById(personRepository.findByUsername("fler").orElse(null).getPersonId());
		}

		user = new User("frank", "white", "frank@white.mercedes", "fwm", passwordEncoder.encode(userPassword),
				Role.User);

		userRepository.save(user);

		admin = personRepository.save(new Person("fler", passwordEncoder.encode(adminPassword), Role.Admin));

		userCredentials = Credentials.basic("fwm", userPassword);
		adminCredentials = Credentials.basic("fler", adminPassword);
		uma = umaRepository.save(new UserMeterAssociation(user, m));
	}

	@AfterEach
	public void removeEntrys() {
		if (m != null) {
			meterRepository.deleteById(m.getMeterId());
		}
		if (user != null) {
			userRepository.deleteById(user.getPersonId());
		}
		if (admin != null) {
			personRepository.deleteById(admin.getPersonId());
		}
//		if (uma != null) {
//			umaRepository.delete(uma);
//		}
	}

	@Test
	public void dummy() {

	}

	@Test
	@WithAnonymousUser
	public void testAddReadingIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to add a reading to a meter

		mvc.perform(post("/api/meters/" + meterId.toString())).andExpect(status().is4xxClientError());
	}

	@Test
	public void testGetMeterByIDAsUser() throws Exception {

		// Check if users without administration rights can only get their own meters
		// by their ID

		mvc.perform(get("/api/meters/" + meterId).header(HttpHeaders.AUTHORIZATION, userCredentials)
				.contentType("applications/json")).andExpect(status().isOk());
	}

	@Test
	public void testAddReadingAsUser() throws Exception {

		// Check if users without administration rights can only add readings to their
		// own meter

		MvcResult result = mvc
				.perform(post("/api/meters/" + meterId.toString() + "/readings")
						.header(HttpHeaders.AUTHORIZATION, userCredentials).contentType("applications/json")
						.param("mid", meterId.toString()).param("value", "7318312"))
				.andExpect(status().isOk()).andReturn();
		Boolean succes = new Gson().fromJson(result.getResponse().getContentAsString(), Boolean.class);
		assertEquals(true, succes);

	}

	@Test
	public void testAddReadingAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to add a new reading

		MvcResult result = mvc
				.perform(post("/api/meters/" + meterId + "/readings")
						.header(HttpHeaders.AUTHORIZATION, adminCredentials).contentType("applications/json")
						.param("mid", meterId.toString()).param("value", "7318312"))
				.andExpect(status().isOk()).andReturn();
		Boolean succes = new Gson().fromJson(result.getResponse().getContentAsString(), Boolean.class);
		assertEquals(true, succes);
	}

	@Test
	@WithAnonymousUser
	public void testGetReadingsIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to get all readings belonging to a meter

		mvc.perform(get("/api/meters/" + meterId.toString() + "/readings")).andExpect(status().is4xxClientError());
	}

	@Test
	public void testGetReadingsAsUser() throws Exception {

		// Check if users without administration rights can only get readings of their
		// own meters

		MvcResult result = mvc
				.perform(get("/api/meters/" + meterId.toString() + "/readings")
						.header(HttpHeaders.AUTHORIZATION, userCredentials).contentType("applications/json"))
				.andExpect(status().isOk()).andReturn();
		String s = result.getResponse().getContentAsString(); // wird hier auch empty sein
		List<ReadingDTO> reading = new Gson().fromJson(s, List.class);
		// TODO reading überprüfen, damit erstmal eins adden
	}

	@Test
	public void testGetReadingsAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to get a reading

		MvcResult result = mvc
				.perform(get("/api/meters/" + meterId.toString() + "/readings")
						.header(HttpHeaders.AUTHORIZATION, adminCredentials).contentType("applications/json"))
				.andExpect(status().isOk()).andReturn();
		String s = result.getResponse().getContentAsString(); // wird hier auch empty sein
		List<ReadingDTO> reading = new Gson().fromJson(s, List.class);
		// TODO reading überprüfen, damit erstmal eins adden
	}

	@Test
	@WithAnonymousUser
	public void testCreateMeterIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to create a meter

		mvc.perform(post("api/meters")).andExpect(status().is4xxClientError());
	}

//	@Test
//	@WithMockUser(username = "user", roles = { "User", "Shared" })
//	public void testCreateMeterAsUser() throws Exception {
//
//		// Check if users without administration rights can't create a new meter
//
//		mvc.perform(post("api/meters")).andExpect(status().is4xxClientError());
//	}
//
//	@Test
//	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
//	public void testCreateMeterAsAdmin() throws Exception {
//
//		// Check if users with administration rights are allowed to create a new meter
//
//		MvcResult result = mvc
//				.perform(post("/api/meters").contentType("applications/json").param("meternumber", "12345678")
//						.param("initialReading", "1").param("meterType", MeterType.Electricity.toString()))
//				.andExpect(status().isOk()).andReturn();
//		MeterDTO meter = new Gson().fromJson(result.getResponse().getContentAsString(), MeterDTO.class);
//		assertEquals(meter.getMeterNumber(), "12345678"); // TODO getNumber(): Name mit MeterDTO abgleichen
//	}
//
//	@Test
//	@WithAnonymousUser
//	public void testUpdateMeterIfNotLoggedIn() throws Exception {
//
//		// Check if anonymous users are redirected to the login page when
//		// trying to update a meter
//
//		mvc.perform(put("/api/meters/" + meterId)).andExpect(status().is4xxClientError());
//	}
//
//	@Test
//	@WithMockUser(username = "user", roles = { "User", "Shared" })
//	public void testUpdateMeterAsUser() throws Exception {
//
//		// Check if users without administration rights can't update a meter
//
//		mvc.perform(put("/api/meters/" + meterId)).andExpect(status().is4xxClientError());
//	}
//
//	@Test
//	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
//	public void testUpdateMeterAsAdmin() throws Exception {
//
//		// Check if users with administration rights are allowed to update a meter
//
//		mvc.perform(put("/api/meters/" + meterId).contentType("applications/json")).andExpect(status().isOk());
//		// TODO Methode noch leer, implementen sobald Methode implementiert
//	}
//
//	@Test
//	@WithAnonymousUser
//	public void testDeleteMeterIfNotLoggedIn() throws Exception {
//
//		// Check if anonymous users are redirected to the login page when
//		// trying to delete a meter
//
//		mvc.perform(delete("/api/meters/" + meterId)).andExpect(status().is4xxClientError());
//	}
//
//	@Test
//	@WithMockUser(username = "user", roles = { "User", "Shared" })
//	public void testDeleteMeterAsUser() throws Exception {
//
//		// Check if users without administration rights can't delete a meter
//
//		mvc.perform(delete("/api/meters/" + meterId)).andExpect(status().is4xxClientError());
//	}
//
//	@Test
//	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
//	public void testDeleteMeterAsAdmin() throws Exception {
//
//		// Check if users with administration rights are allowed to delete a meter
//
//		mvc.perform(delete("/api/meters/" + meterId).contentType("applications/json")).andExpect(status().isOk());
//		// TODO Methode verstehen und dann gucken auf was noch testen
//	}
//
//	@Test
//	@WithAnonymousUser
//	public void testGetAllMetersIfNotLoggedIn() throws Exception {
//
//		// Check if anonymous users are redirected to the login page when
//		// trying to get a list of all meters existing in the system
//
//		mvc.perform(get("/api/meters")).andExpect(status().is4xxClientError());
//	}
//
//	@Test
//	@WithMockUser(username = "user", roles = { "USER" })
//	public void testGetAllMetersAsUser() throws Exception {
//
//		// Check if users without administration rights can't get a list of all meters
//		// existing
//
//		mvc.perform(get("/api/meters")).andExpect(status().is4xxClientError());
//	}
//
////	@Test
//	@WithMockUser(username = "admin", roles = { "ADMIN" })
//	public void testGetAllMetersAsAdmin() throws Exception {
//
//		// Check if users with administration rights are allowed to get all meters.
//
//		MvcResult result = mvc.perform(get("/api/issues").contentType("applications/json")).andExpect(status().isOk())
//				.andReturn();
//		Type listType = new TypeToken<List<IssueDTO>>() {
//		}.getType();
//		String s = result.getResponse().getContentAsString(); // wird hier auch empty sein
//		List<MeterDTO> meters = new Gson().fromJson(s, listType);
//		assertTrue(meters.contains(new MeterDTO(m))); // TODO fix assertion, issues wird leer sein, liegt an Auslesen
//														// mit Gson (wie oben)
//	}
//
////	@Test
//	@WithAnonymousUser
//
//	// Check if anonymous users are redirected to the login page when
//	// trying to get a meter by its ID
//
//	public void testGetMeterByIDIfNotLoggedIn() throws Exception {
//		mvc.perform(get("/api/meters/" + meterId)).andExpect(status().is4xxClientError());
//	}
//

//	@Test
//	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
//	public void testGetMeterByIDAsAdmin() throws Exception {
//
//		// Check if users with administration rights are allowed to get a meter by its
//		// id
//
//		MvcResult result = mvc.perform(get("/api/issues/" + meterId.toString()).param("mid", meterId.toString())
//				.contentType("applications/json")).andExpect(status().isOk()).andReturn();
//		MeterDTO issue = new Gson().fromJson(result.getResponse().getContentAsString(), MeterDTO.class); // sollte hier
//																											// auch
//																											// gehen
//																											// aber
//																											// Frage ist
//																											// warum
//		assertEquals(issue.getMeterNumber(), "12345678"); // TODO gegenchecken mit Methoden aus MeterDTO
//		assertEquals(issue.getLastReading().getValue(), new Long(1111111));
//		assertEquals(issue.getType(), MeterType.Electricity);
//	}

}
