package com.yenmin.proton.setting.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.yenmin.proton.setting.model.IntegrationModel;
import com.yenmin.proton.setting.model.User;
import com.yenmin.proton.setting.pojo.MessageResponse;
import com.yenmin.proton.setting.repository.IntegrationRepository;

@Service
public class IntegrationService {

	@Autowired
	IntegrationRepository integrationRepo;
	@Autowired
	protected RestTemplate restTemplate;

	@Value("${currentUser.profile}")
	private String userUrl;

	/** It is used to save user credentials to integrate other tools **/
	public IntegrationModel createintegration(@RequestBody IntegrationModel model) {
		//MessageResponse userDetails = restTemplate.getForObject(userUrl, MessageResponse.class);
		//User user = userDetails.getUser();
		String timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Calendar.getInstance().getTime());
		model.setApiDetailsModel(model.getApiDetailsModel());;
		model.setCreatedDate(timestamp);
		model.setCreatedby("5f1e5756bed1487c09879240");
		return integrationRepo.save(model);
	}

	/** To used to change credentials **/

	public IntegrationModel updateintegration(@RequestBody IntegrationModel model) {
		//MessageResponse userDetails = restTemplate.getForObject(userUrl, MessageResponse.class);
		//User user = userDetails.getUser();
		String lastModifiedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		IntegrationModel modelDetails = integrationRepo.findById(model.getId()).orElseThrow();
		model.setCreatedby(modelDetails.getCreatedby());
		model.setCreatedDate(modelDetails.getCreatedDate());
		model.setLastModifiedDate(lastModifiedDate);
		model.setLastModifiedBy("5f1e5756bed1487c09879240");
		return integrationRepo.save(model);
	}

	/** To get single entry using "id" **/
	public IntegrationModel getIntegrationdetails(IntegrationModel model) {
		IntegrationModel integrationDetails = integrationRepo.findById(model.getId()).orElseThrow();
		return integrationDetails;
	}

	/** To get list of saved integration details using title,user id **/
	public List<IntegrationModel> getUserIntegrationdetails(String title) {
		MessageResponse user = restTemplate.getForObject(userUrl, MessageResponse.class);
		User userdetails = user.getUser();
		List<IntegrationModel> integrationDetails = integrationRepo.findByTitleAndCreatedby(title, userdetails.getId());
		return integrationDetails;
	}

	/** To get list of saved integration details using user id **/
	public List<IntegrationModel> getUserIntegration() {
		MessageResponse user = restTemplate.getForObject(userUrl, MessageResponse.class);
		User userdetails = user.getUser();
		List<IntegrationModel> integrationDetails = integrationRepo.findByCreatedby(userdetails.getId());
		return integrationDetails;
	}

	/** To delete saved integration detail using user id **/

	public void deleteIntegration(IntegrationModel model) {
		integrationRepo.deleteById(model.getId());

	}

	/** To get all the saved entry from database **/

	public List<IntegrationModel> getAllIntegration() {
		List<IntegrationModel> list = integrationRepo.findAll();
		return list;
	}

	/** To delete all the saved entry from database **/

	public void deleteAll() {
		integrationRepo.deleteAll();
	}

}
