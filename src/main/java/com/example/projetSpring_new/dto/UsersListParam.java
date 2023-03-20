package com.example.projetSpring_new.dto;

import java.io.Serializable;
import java.util.List;

public class UsersListParam implements Serializable {

	private List<UsersData> userDataList;

	public List<UsersData> getUserDataList() {
		return userDataList;
	}

	public void setUserDataList(List<UsersData> userDataList) {
		this.userDataList = userDataList;
	}

}
