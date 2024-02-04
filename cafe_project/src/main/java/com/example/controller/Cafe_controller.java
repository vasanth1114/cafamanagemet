package com.example.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dbutil.Dbutil;
import com.example.entity.Cafe;
import com.example.entity.Cartuser;
import com.example.service.Cafe_service;
import com.example.service.Cart_service;
import com.example.service.Order_service;
import com.example.entity.EntityEx;
import com.example.entity.ImageModel;
import com.example.entity.Order_entity;
import com.example.entity.admin;
import com.example.repository.ImageRepository;
import com.example.service.ServiceEx;
import com.example.service.admin_service;
import com.example.service.Cart_service;

@RestController
@Controller
@CrossOrigin("*")
@RequestMapping("/cafehome")
public class Cafe_controller {
	@Autowired
	private Cafe_service service;
	@Autowired
	private ServiceEx see;
	@Autowired
	private Order_service ser;
	@Autowired
	public admin_service adservice;
	@Autowired
	public Cart_service cartservice;
	//---------------------------
	Connection connection;
	public Cafe_controller() throws SQLException {
	connection=Dbutil.getConnection();
	}
	@PostMapping("/addToCart")
	public ResponseEntity<Cartuser> savecart(@RequestBody Cartuser values) {
		return new ResponseEntity<Cartuser>(cartservice.savecart(values), HttpStatus.CREATED);

	}
	@GetMapping("cartbyid/{Userid}")
	public List<Cartuser>  getcartById(@PathVariable("Userid") int Userid) {
		return  cartservice.getCartById(Userid);
	}
	@DeleteMapping("delData/{cart_id}")
	public ResponseEntity<String> delData(@PathVariable("cart_id") int cart_id) {
		cartservice.deletecart(cart_id);
		return new ResponseEntity<String>(" name obj removed from the obj list", HttpStatus.OK);
	}
	@PutMapping("cartupdate/{Userid}")
	public int Orderupdate(@PathVariable("Userid") int Userid) {
		return cartservice.ordercart(Userid);
	}
	@GetMapping("vieworderbycart")
	public List<Cartuser> getAllcart() {
		return cartservice.getAllcart();
	}
//	@PostMapping("productbyids")
//	public List<Cafe>  getdishids(@RequestBody List<Integer> dishids) {
//		//return  service.getdishids(dishids);
//		return new ResponseEntity<Cafe>(service.getdishids(dishids), HttpStatus.CREATED);
//	}
	@Autowired
	ImageRepository imageRepository;
	@PostMapping("upload")
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getBytes()));
		imageRepository.save(img);
		return ResponseEntity.status(HttpStatus.OK);
	}
	@GetMapping(path = { "/get/{imageName}" })
	public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
		final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
		ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
				decompressBytes(retrievedImage.get().getPicByte()));
		return img;
	}
	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}
	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
//-----------------------------------------------------------
	@PostMapping("/saveitems")
	public ResponseEntity<Cafe> saveitems(@RequestParam("dish_id") int dish_id, 
			@RequestParam("dish_name") String dish_name, @RequestParam("price") int price,
			@RequestParam("imageFile") MultipartFile file) throws IOException {
		Cafe img = new Cafe(dish_id, dish_name, price, file.getOriginalFilename(),
				compressBytes(file.getBytes()));
		return new ResponseEntity<Cafe>(service.saveitems(img), HttpStatus.CREATED);
		

	}	
	@GetMapping("viewmenu")
	public  List<Cafe> getAllMenu() {
		List<Cafe> cartList = new ArrayList<>();

	 var menulist=service.getAllmenu();      
	        for(Cafe item : menulist) {
	        	if(item.getPicByte() != null) {
	        	Cafe img = new Cafe(item.getDish_id(),item.getDish_name(), item.getPrice(), 
        				item.getType(),
        				decompressBytes(item.getPicByte()));
        		
            cartList.add(img);
	        }
	        }
		return cartList;
	}
	
	@GetMapping("dishbyid/{dish_id}")
	public ResponseEntity<Cafe> getmenuById(@PathVariable("dish_id") int dish_id) {
		return new ResponseEntity<Cafe>(service.getmenuById(dish_id), HttpStatus.OK);
	}
	//-----------------------------------------------------------------------------------------------
	@PutMapping("dishupdate/{dish_id}")
	public ResponseEntity<Cafe> UpdateEmployee(@PathVariable("dish_id") int dish_id, @RequestBody Cafe item) {
		return new ResponseEntity<Cafe>(service.updatemenu(item, dish_id), HttpStatus.CREATED);
	}
	//-------------------------------------------------------------------------------------------
	@DeleteMapping("dishdelete/{dish_id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("dish_id") int dish_id) {
		service.deletemenu(dish_id);
		return new ResponseEntity<String>(" Menu item removed from the menu list", HttpStatus.OK);
	}
	//------------***************---user details---*******************-----------------------------------------
	
	@PostMapping("/saveusers")
	public ResponseEntity<EntityEx> savename(@RequestBody EntityEx item) {
		return new ResponseEntity<EntityEx>(see.savename(item), HttpStatus.CREATED);
	}
	@GetMapping("viewuser")
	public List<EntityEx> getAllEmployee() {
		return see.UserAllmenu();
	}
	@GetMapping("userbyid/{Userid}")
	public ResponseEntity<EntityEx>  UserId(@PathVariable("Userid") int Userid) {
		return new ResponseEntity<EntityEx>(see. UserId(Userid), HttpStatus.OK);
	}
	@PutMapping("userupdate/{Userid}")
	public ResponseEntity<EntityEx> UpdateUser(@PathVariable("Userid") int User_id, @RequestBody EntityEx item) {
		return new ResponseEntity<EntityEx>(see. Userupdate(item, User_id), HttpStatus.CREATED);
	}
	@DeleteMapping("userdelete/{User_id}")
	public ResponseEntity<String> deleteUser(@PathVariable("User_id") int User_id) {
		see.UserDelete(User_id);
		return new ResponseEntity<String>(" Menu item removed from the menu list", HttpStatus.OK);
	}
	//------------***************---order details---*******************-----------------------------------------
	@PostMapping("/saveorders")
	public ResponseEntity<Order_entity> saveorders(@RequestBody Order_entity order) {
		return new ResponseEntity<Order_entity>(ser.saveorders(order), HttpStatus.CREATED);
	}

	@GetMapping("vieworders")
	public List<Order_entity> getAllorders() {
		return ser.getAllorders();
	}
	@GetMapping("orderbyid/{id}")
	public ResponseEntity<Order_entity>  id(@PathVariable("id") int id, @RequestBody Order_entity order) {
		return new ResponseEntity<Order_entity>(ser. getorderById(id), HttpStatus.OK);
	}
	@PutMapping("orderupdate/{id}")
	public ResponseEntity<Order_entity> updateorder(@PathVariable("id") int id, @RequestBody Order_entity order) {
		return new ResponseEntity<Order_entity>(ser.updateorder(order, id), HttpStatus.CREATED);
	}
	@DeleteMapping("orderdelete/{id}")
	public ResponseEntity<String> deleteorder(@PathVariable("id") int id) {
		ser.deleteorder(id);
		return new ResponseEntity<String>(" Menu item removed from the menu list", HttpStatus.OK);
	}
	//------------***************---Admin details---*******************-----------------------------------------
	@PostMapping("/saveadmin")
	public ResponseEntity<admin> saveadmin(@RequestBody admin obj) {
		return new ResponseEntity<admin>(adservice.saveadmin(obj), HttpStatus.CREATED);

	}
	
	
	@GetMapping("viewadmin")
	public List<admin> getAlladmin() {
		return adservice.getAlladmin();
	}
	
	@GetMapping("adminbyid/{admin_id}")
	public ResponseEntity<admin> getadminById(@PathVariable("admin_id") int admin_id, @RequestBody admin obj) {
		return new ResponseEntity<admin>(adservice.getadminById(admin_id), HttpStatus.OK);
	}
	//-----------------------------------------------------------------------------------------------
	@PutMapping("adminupdate/{admin_id}")
	public ResponseEntity<admin> Updateadmin(@PathVariable("admin_id") int admin_id, @RequestBody admin  obj) {
		return new ResponseEntity<admin>(adservice.updateadmin(obj, admin_id), HttpStatus.CREATED);
	}
	//-------------------------------------------------------------------------------------------
	@DeleteMapping("admindelete/{admin_id}")
	public ResponseEntity<String> deleteadmin(@PathVariable("admin_id") int admin_id) {
		adservice.deleteadmin(admin_id);
		return new ResponseEntity<String>(" name obj removed from the obj list", HttpStatus.OK);
	}
	//----------------------------admin validation------------------------------------
	@GetMapping("adminlogin/{username}/{Password}")
	public int Adminlogin(@PathVariable("username")String username1,@PathVariable("Password")
			String password1) {
		int flag=adservice.loginvalidation(username1, password1);
		if(flag==0) {
			return 0;
		}else {
			return flag;
		}
		
	}
	@GetMapping("userlogin/{username}/{Password}")
	public Object Userlogin(@PathVariable("username")String username1,@PathVariable("Password")
			String password1) {
		int flag= see.Uloginvalidation(username1, password1);
		if(flag==0) {
			return 0;
		}else {
			return flag;
		}
		
	}

}

