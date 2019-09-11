package de.sopro.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.sopro.TestConfig;
import de.sopro.data.Issue;
import de.sopro.data.Meter;
import de.sopro.data.MeterType;
import de.sopro.dto.IssueDTO;
import de.sopro.dto.MeterDTO;
import de.sopro.dto.ReadingDTO;
import de.sopro.repository.MeterRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfig.class)
public class MeterControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	MeterRepository meterRepository;
	
	Meter m;
	Long meterId;

	@BeforeEach
	public void createEntrys() {
		m = new Meter("98765432", (long) 1111111, MeterType.Electricity);
		meterRepository.save(m);
		meterId = m.getMeterId();
	}

	@AfterEach
	public void removeEntrys() {
		if (m != null) {
			meterRepository.delete(m);
		}
	}
	
	@Test
	@WithAnonymousUser
	public void testAddReadingIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to add a reading to a meter

		mvc.perform(post("/api/meters/" + meterId + "/readings")).andExpect(status().is4xxClientError());
	} 

	@Test 
	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testAddReadingAsUser() throws Exception {

	// Check if users without administration rights can only add readings to their
	// own meter

		MvcResult result = mvc.perform(post("/api/meters/" + meterId + "/readings").contentType("applications/json").param("mid", meterId.toString()).param("value", "7318312")).andExpect(status().isOk()).andReturn();
		Boolean succes = new Gson().fromJson(result.getResponse().getContentAsString(), Boolean.class);
		assertEquals(succes, true);
		
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testAddReadingAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to add a new reading

		MvcResult result = mvc.perform(post("/api/meters/" + meterId + "/readings").contentType("applications/json").param("mid", meterId.toString()).param("value", "7318312")).andExpect(status().isOk()).andReturn();
		Boolean succes = new Gson().fromJson(result.getResponse().getContentAsString(), Boolean.class);
		assertEquals(succes, true);
	}

//	@Test
	@WithAnonymousUser
	public void testGetReadingsIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to get all readings belonging to a meter

		mvc.perform(get("/api/meters/" + meterId + "/readings")).andExpect(status().is4xxClientError());
	}

	@Test 
	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testGetReadingsAsUser() throws Exception {

	// Check if users without administration rights can only get readings of their
	// own meters

		MvcResult result =  mvc.perform(get("/api/meters/" + meterId + "/readings").contentType("applications/json")).andExpect(status().isOk()).andReturn();
		String s = result.getResponse().getContentAsString(); //wird hier auch empty sein
		ReadingDTO reading = new Gson().fromJson(s, ReadingDTO.class);
		//TODO reading überprüfen, damit erstmal eins adden 
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testGetReadingsAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to get a reading

		MvcResult result =  mvc.perform(get("/api/meters/" + meterId + "/readings").contentType("applications/json")).andExpect(status().isOk()).andReturn();
		String s = result.getResponse().getContentAsString(); //wird hier auch empty sein
		ReadingDTO reading = new Gson().fromJson(s, ReadingDTO.class);
		//TODO reading überprüfen, damit erstmal eins adden 
	}

	@Test
	@WithAnonymousUser
	public void testCreateMeterIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to create a meter

		mvc.perform(post("api/meters")).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testCreateMeterAsUser() throws Exception {

		// Check if users without administration rights can't create a new meter

		mvc.perform(post("api/meters")).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testCreateMeterAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to create a new meter

		MvcResult result = mvc.perform(post("/api/meters").contentType("applications/json").param("meternumber", "12345678").param("initialReading", "1").param("meterType", MeterType.Electricity.toString())).andExpect(status().isOk()).andReturn();
		MeterDTO meter = new Gson().fromJson(result.getResponse().getContentAsString(), MeterDTO.class);
		assertEquals(meter.getNumber(), "12345678"); //TODO getNumber(): Name mit MeterDTO abgleichen
	}

	@Test
	@WithAnonymousUser
	public void testUpdateMeterIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to update a meter

		mvc.perform(put("/api/meters/" + meterId)).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testUpdateMeterAsUser() throws Exception {

		// Check if users without administration rights can't update a meter

		mvc.perform(put("/api/meters/" + meterId)).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testUpdateMeterAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to update a meter

		mvc.perform(put("/api/meters/" + meterId).contentType("applications/json")).andExpect(status().isOk());
		//TODO Methode noch leer, implementen sobald Methode implementiert
	}

	@Test
	@WithAnonymousUser
	public void testDeleteMeterIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to delete a meter

		mvc.perform(delete("/api/meters/" + meterId)).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testDeleteMeterAsUser() throws Exception {

		// Check if users without administration rights can't delete a meter

		mvc.perform(delete("/api/meters/" + meterId)).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testDeleteMeterAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to delete a meter

		mvc.perform(delete("/api/meters/" + meterId).contentType("applications/json")).andExpect(status().isOk());
		//TODO Methode verstehen und dann gucken auf was noch testen
	}

	@Test
	@WithAnonymousUser
	public void testGetAllMetersIfNotLoggedIn() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to get a list of all meters existing in the system

		mvc.perform(get("/api/meters")).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser(username = "user", roles = { "USER" })
	public void testGetAllMetersAsUser() throws Exception {

		// Check if users without administration rights can't get a list of all meters
		// existing

		mvc.perform(get("/api/meters")).andExpect(status().is4xxClientError());
	}

//	@Test
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void testGetAllMetersAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to get all meters.

		MvcResult result = mvc.perform(get("/api/issues").contentType("applications/json")).andExpect(status().isOk())
				.andReturn();
		Type listType = new TypeToken<List<IssueDTO>>() {}.getType();
		String s = result.getResponse().getContentAsString(); //wird hier auch empty sein
		List<MeterDTO> meters = new Gson().fromJson(s, listType);
		assertTrue(meters.contains(new MeterDTO(m))); // TODO fix assertion, issues wird leer sein, liegt an Auslesen
														// mit Gson (wie oben)
	}

//	@Test
	@WithAnonymousUser

	// Check if anonymous users are redirected to the login page when
	// trying to get a meter by its ID

	public void testGetMeterByIDIfNotLoggedIn() throws Exception {
		mvc.perform(get("/api/meters/" + meterId)).andExpect(status().is4xxClientError());
	}

//	@Test 
	@WithMockUser(username = "user", roles = { "User", "Shared" })
	public void testGetMeterByIDAsUser() throws Exception {

	// Check if users without administration rights can only get their own meters
	// by their ID

		mvc.perform(get("/api/meters/" + meterId).contentType("applications/json")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", roles = { "Admin", "Shared" })
	public void testGetMeterByIDAsAdmin() throws Exception {

		// Check if users with administration rights are allowed to get a meter by its
		// id

		MvcResult result = mvc.perform(get("/api/issues/" + meterId.toString()).param("mid", meterId.toString())
				.contentType("applications/json")).andExpect(status().isOk()).andReturn();
		MeterDTO issue = new Gson().fromJson(result.getResponse().getContentAsString(), MeterDTO.class); //sollte hier auch gehen aber Frage ist warum
		assertEquals(issue.getMeterNumber(), "12345678"); //TODO gegenchecken mit Methoden aus MeterDTO
		assertEquals(issue.getReading(), (long) 1111111);
		assertEquals(issue.getType(), MeterType.Electricity);
	}

}
