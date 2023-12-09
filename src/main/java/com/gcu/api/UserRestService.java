package com.gcu.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.business.SecurityBusinessService;
import com.gcu.model.PostModel;
import com.gcu.model.UserList;
import com.gcu.model.UserModel;

/**
 * This class models all API responses for user models
 */
@RestController
@RequestMapping("/api/user-service")
public class UserRestService {
	@Autowired
	SecurityBusinessService securityBusinessService;

	/**
	 * Endpoint returns all user models as JSON data
	 * @return
	 */
	@GetMapping(path = "/getjson", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<UserModel> getUsersAsJson() {
		return securityBusinessService.getAllUsers();
	}

	/**
	 * Endpoint returns all user models as XML data
	 * @return
	 */
	@GetMapping(path = "/getxml", produces = { MediaType.APPLICATION_XML_VALUE })
	public UserList getUsersAsXML() {
		UserList list = new UserList();
		list.setUsers(securityBusinessService.getAllUsers());
		return list;
	}

	/**
	 * Endpoint returns json data for a specific user based on user ID
	 * @param userId
	 * @return
	 */
	@GetMapping(path = "/getjson/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserModel> getUserAsJSONById(@PathVariable Long userId) {


		if (securityBusinessService.getUser(userId) == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		Optional<UserModel> user = Optional.of(securityBusinessService.getUser(userId));

		return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Endpoint returns XML data for specific user based on user ID
	 */
	@GetMapping(path = "/getxml/{userId}", produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserModel> getUserAsXMLById(@PathVariable Long userId) {

		if (securityBusinessService.getUser(userId) == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		Optional<UserModel> user = Optional.of(securityBusinessService.getUser(userId));

		return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
