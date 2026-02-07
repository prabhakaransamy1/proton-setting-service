package com.yenmin.proton.setting.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yenmin.proton.setting.Exception.ForbiddenException;
import com.yenmin.proton.setting.model.Organization;
import com.yenmin.proton.setting.model.Role;
import com.yenmin.proton.setting.pojo.MessageResponse;
import com.yenmin.proton.setting.pojo.RoleRequest;
import com.yenmin.proton.setting.repository.OrganizationRepository;
import com.yenmin.proton.setting.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository repo;

	@Autowired
	OrganizationRepository orgrepo;

	@Autowired
	ResourceBundleMessageSource messageSource;

	/**
	 * To create roles for the user with attributes and store in the
	 * database(RequestBody contains fields like title,description,list of
	 * permissions)
	 **/
	public ResponseEntity<Object> createRole(RoleRequest roleRequest) {
		Role role = new Role(roleRequest.getRoleId(), roleRequest.getOrganizationid(), roleRequest.getTitle(),
				roleRequest.getPermission(), roleRequest.getDescription());
		// UserDetailsImpl userDetails = (UserDetailsImpl)
		// SecurityContextHolder.getContext().getAuthentication()
		// .getPrincipal();
		role.setCreatedBy("5ee99f888eafbd4ecf275761");
		Organization organization = orgrepo.findByOid("5ee99f888eafbd4ecf275761");
		if (organization == null) {
			throw new ForbiddenException(messageSource.getMessage("role.auth", null, LocaleContextHolder.getLocale()));
		}
		role.setOrganizationId(organization.getId());
		repo.save(role);
		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("role.create", null, LocaleContextHolder.getLocale())));

	}

	/**
	 * To update particular fields of role entry in the database based on the
	 * changes received from the user (RequestBody contains fields of respective
	 * changes)
	 **/
	public ResponseEntity<Object> updateRole(RoleRequest roleRequest) {
		Role role = repo.findById(roleRequest.getRoleId()).orElse(null);
		if (role == null) {
			throw new NoSuchElementException(
					messageSource.getMessage("role.null", null, LocaleContextHolder.getLocale()));
		}
		// UserDetailsImpl userDetails = (UserDetailsImpl)
		// SecurityContextHolder.getContext().getAuthentication()
		// .getPrincipal();
		role.setPermission(roleRequest.getPermission() != null ? roleRequest.getPermission() : role.getPermission());
		role.setTitle(roleRequest.getTitle() != null ? roleRequest.getTitle() : role.getTitle());
		role.setDescription(
				roleRequest.getDescription() != null ? roleRequest.getDescription() : role.getDescription());
		role.setLastModifiedBy("5ee99f888eafbd4ecf275761");
		repo.save(role);
		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("role.update", null, LocaleContextHolder.getLocale()), role));

	}

	/** To delete a particular role entry from the database based on the "id" **/
	public ResponseEntity<Object> deleteRole(String id) {
		Role roledetails = repo.findById(id).orElse(null);

		if (roledetails == null) {
			throw new NoSuchElementException(
					messageSource.getMessage("role.null", null, LocaleContextHolder.getLocale()));
		}
		repo.deleteById(id);
		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("role.delete", null, LocaleContextHolder.getLocale())));

	}

	/** To get a role from the database using "id" **/
	public ResponseEntity<Object> getRoleDetails(String id) {

		Role roledetails = repo.findById(id).orElse(null);

		if (roledetails == null) {
			throw new NoSuchElementException(
					messageSource.getMessage("role.null", null, LocaleContextHolder.getLocale()));
		}
		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("role.details", null, LocaleContextHolder.getLocale()), roledetails));

	}

	/** To delete all entries from the database **/
	public ResponseEntity<Object> deleteAll() {
		repo.deleteAll();
		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("role.delete", null, LocaleContextHolder.getLocale())));

	}

}
