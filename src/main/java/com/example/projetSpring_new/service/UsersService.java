package com.example.projetSpring_new.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.projetSpring_new.dto.UsersData;
import com.example.projetSpring_new.dto.UsersListParam;
import com.example.projetSpring_new.model.Users;
import com.example.projetSpring_new.repository.UsersRepository;

/**
 * ユーザー情報 Service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UsersService {
  /**
   * ユーザー情報 Repository
   */
  @Autowired
  private UsersRepository userRepository;
  /**
   * ユーザー情報 全検索
   * @return 検索結果
   */
  public UsersListParam searchAll() {
   
    List<Users> userList = userRepository.findAll();
    UsersListParam userListParam = new UsersListParam();
    List<UsersData> list = new ArrayList<UsersData>();
  
    for(Users user : userList) {
      UsersData data = new UsersData();
      data.setId(user.getId());
      data.setName(user.getName());
      data.setAddress(user.getAddress());
      data.setPhone(user.getPhone());
      data.setUpdateDate(user.getUpdateDate());
      list.add(data);
    }
    userListParam.setUserDataList(list);
    return userListParam;
  }
  /**
   * ユーザー情報更新
   * @param param 画面パラメータ
   */
  public void updateAll(UsersListParam param) {
    List<Users> userList = new ArrayList<Users>();
    // 画面パラメータをエンティティに詰め替える
    for (UsersData data : param.getUserDataList()) {
      Users user = userRepository.findById(data.getId()).get();
      user.setAddress(data.getAddress());
      user.setName(data.getName());
      user.setPhone(data.getPhone());
      user.setUpdateDate(new Date());
      userList.add(user);
    }
    userRepository.saveAll(userList);
  }
}
