package com.yenmin.proton.setting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.yenmin.proton.setting.model.User;
import com.yenmin.proton.setting.pojo.MessageResponse;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	protected RestTemplate restTemplate;
	@Autowired
	ResourceBundleMessageSource messageSource;
	@Value("${currentUser.profile}")
	private String userUrl;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MessageResponse userResponse = restTemplate.getForObject(userUrl,
				MessageResponse.class);
		User user = userResponse.getUser();
		if (user == null) {
			throw new UsernameNotFoundException(messageSource.getMessage("user.notfound", null, LocaleContextHolder.getLocale()) + username);
		}
		return UserDetailsImpl.build(user);
	}

}
