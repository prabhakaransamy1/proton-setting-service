package com.yenmin.proton.setting.model;

import java.util.List;

public class ProductiveApp {
	
	private List<String> productive;
	private List<String> unproductive;
	private List<String> neutral;
	private List<String> uncategorized;
	public List<String> getProductive() {
		return productive;
	}
	public void setProductive(List<String> productive) {
		this.productive = productive;
	}
	public List<String> getUnproductive() {
		return unproductive;
	}
	public void setUnproductive(List<String> unproductive) {
		this.unproductive = unproductive;
	}
	public List<String> getNeutral() {
		return neutral;
	}
	public void setNeutral(List<String> neutral) {
		this.neutral = neutral;
	}
	public List<String> getUncategorized() {
		return uncategorized;
	}
	public void setUncategorized(List<String> uncategorized) {
		this.uncategorized = uncategorized;
	}
	

}
