package com.yenmin.proton.setting.service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yenmin.proton.setting.model.Team;
import com.yenmin.proton.setting.model.User;
import com.yenmin.proton.setting.pojo.MessageResponse;
import com.yenmin.proton.setting.pojo.TeamRequest;
import com.yenmin.proton.setting.pojo.UserMemberResponse;
import com.yenmin.proton.setting.repository.TeamRepository;

@Service

/**
 * To perform team id(RequestBody teamId, members)
 **/
public class TeamService {
	@Autowired
	ResourceBundleMessageSource messageSource;
	@Autowired
	TeamRepository trepo;
	@Autowired
	private RestTemplate restTemplate;
	@Value("${user.find}")
	private String userUrl;

	/**
	 * To save a team from the database differentiated by title(RequestBody title)
	 **/
	public ResponseEntity<Object> addTeam(TeamRequest teamRequest) {

		Team team = new Team();

		team.setTitle(teamRequest.getTitle());
		team.setoid("5f1d6fb22200a208863a3787");
		team.setCreatedby("5ee99f888eafbd4ecf275761");
		trepo.save(team);
		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("team.title", null, LocaleContextHolder.getLocale())));

	}

	/**
	 * To update a team from the database by title(RequestBody teamId, title)
	 **/
	public ResponseEntity<Object> updateTeam(@Valid TeamRequest teamRequest) {
		Team team = trepo.findById(teamRequest.getTeamId()).orElse(null);
		if (team == null) {
			throw new NullPointerException(
					messageSource.getMessage("team.noentry", null, LocaleContextHolder.getLocale()));
		}
		team.setTitle(teamRequest.getTitle());
		team.setLastModifiedBy("5ee99f888eafbd4ecf275761");
		trepo.save(team);
		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("team.update", null, LocaleContextHolder.getLocale())));

	}

	/**
	 * To get a team from the database by teamId(RequestParam teamId)
	 **/
	public ResponseEntity<Object> getTeam(String teamId) {
		String oid = teamId == null ? "5f1d6fb22200a208863a3787" : "";
		List<Team> teams = trepo.findByIdOrOid(teamId, oid);
		if (teams.isEmpty()) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse(
							messageSource.getMessage("code.error", null, LocaleContextHolder.getLocale()),
							messageSource.getMessage("team.noentry", null, LocaleContextHolder.getLocale())));

		}

		teams.forEach(team -> {
			Set<Object> members = new LinkedHashSet<>();

			Iterator<Object> itr = team.getMembers().iterator();
			while (itr.hasNext()) {
				String userId = (String) itr.next();
				MessageResponse response = restTemplate.getForObject(userUrl + userId, MessageResponse.class);
				User user = response.getUser();
				UserMemberResponse userMember = new UserMemberResponse(user.getId(), user.getUsername(),
						user.getImage());
				members.add(userMember);
				team.setMembers(members);

			}

		});

		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("team.found", null, LocaleContextHolder.getLocale()), teams));
	}

	/**
	 * To delete a team from the database by id(RequestBody teamId)
	 **/
	public ResponseEntity<Object> deleteTeam(@Valid TeamRequest teamRequest) {
		Team team = trepo.findById(teamRequest.getTeamId()).orElse(null);
		if (team == null) {
			throw new NullPointerException(
					messageSource.getMessage("team.noentry", null, LocaleContextHolder.getLocale()));
		}
		trepo.delete(team);

		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("team.delete", null, LocaleContextHolder.getLocale())));

	}

	/**
	 * To add members in a team from the database by id(RequestBody teamId, members)
	 **/
	public ResponseEntity<Object> addMember(@Valid TeamRequest teamRequest) {
		Team team = trepo.findById(teamRequest.getTeamId()).orElse(null);
		if (team != null) {
			Iterator<String> itr = teamRequest.getMembers().iterator();
			while (itr.hasNext()) {
				team.getMembers().add(itr.next());
			}
			trepo.save(team);
			return ResponseEntity
					.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
							messageSource.getMessage("member.add", null, LocaleContextHolder.getLocale())));

		}

		return ResponseEntity.badRequest()
				.body(new MessageResponse(messageSource.getMessage("code.error", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("team.noentry", null, LocaleContextHolder.getLocale())));

	}

	/**
	 * To delete the added team members in a team from the database by
	 * id(RequestBody teamId, members)
	 **/
	public ResponseEntity<Object> deleteMember(@Valid TeamRequest teamRequest) {
		Team team = trepo.findById(teamRequest.getTeamId()).orElse(null);
		if (team != null) {
			Iterator<String> itr = teamRequest.getMembers().iterator();
			while (itr.hasNext()) {
				team.getMembers().remove(itr.next());
			}
			team.setLastModifiedBy("5ee99f888eafbd4ecf275761");
			trepo.save(team);
			return ResponseEntity
					.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
							messageSource.getMessage("member.remove", null, LocaleContextHolder.getLocale())));

		}

		return ResponseEntity.badRequest()
				.body(new MessageResponse(messageSource.getMessage("code.error", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("team.noentry", null, LocaleContextHolder.getLocale())));
	}

}
