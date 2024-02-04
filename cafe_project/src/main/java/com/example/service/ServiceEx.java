package com.example.service;

import java.util.List;
import com.example.entity.EntityEx;

public interface ServiceEx {
	
	public EntityEx savename(EntityEx item);
	public List<EntityEx>UserAllmenu();
	public EntityEx UserId(int Userid);
	public EntityEx Userupdate(EntityEx item,int Userid);
	public void UserDelete(int Userid);
	//-------------validation------------
	public int Uloginvalidation(String username, String Password);
}
	


