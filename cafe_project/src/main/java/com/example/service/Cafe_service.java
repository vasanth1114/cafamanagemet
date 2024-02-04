package com.example.service;

import java.util.List;

import com.example.entity.Cafe;
import com.example.entity.Cartuser;

public interface Cafe_service {
	public Cafe saveitems(Cafe item);
	public List<Cafe> getAllmenu();// view
	public Cafe getmenuById(int dish_id);//particular view
	public Cafe updatemenu(Cafe item, int dish_id);
	public void deletemenu(int dish_id);
	public List<Cafe> getdishids(List<Integer> dishids);
}
