package com.ass.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ass.dao.CategoryDao;
import com.ass.dao.OrderDao;
import com.ass.dao.OrderDetailDao;
import com.ass.dao.ProductDao;
import com.ass.model.Account;
import com.ass.model.Order;
import com.ass.model.OrderDetail;
import com.ass.model.Product;
import com.ass.service.CartItemModel;
import com.ass.service.SessionService;
import com.ass.service.ShopingCartService;

@Controller
@RequestMapping("/api")
public class ApiController {
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderDetailDao oderDetailDao;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ShopingCartService shopingCart;
		
	@GetMapping("/loadCategories")
	@ResponseBody
	public String loadCategories(String categorySelect) {
		
		List<Product> list = productDao.finProductCT(categoryDao.findById(categorySelect));
		
		String chuoi = "";
		
		for (Product p : list) {
			String GiamGia = "";
			if (p.getPromotions() == null) {
				GiamGia = "<div class=\"portfolio-caption-subheading text-muted\">"+p.getPrice()+"</div>\r\n"
						+ "	                                	<br />";
			}else {
				GiamGia = "<div class=\"portfolio-caption-subheading text-muted\" style=\"text-decoration: line-through; \">"+p.getPrice()+"</div>\r\n"
						+ "		                                <div class=\"sale-caption-subheading text-muted\">"+p.getPrice()/100*(p.getPrice()-p.getPromotions())+"</div>";
			}
			chuoi += "<div class=\"col-lg-4 col-sm-6 mb-4\">\r\n"
					+ "                        <!-- Portfolio item-->\r\n"
					+ "                        <div class=\"portfolio-item\">\r\n"
					+ "                            <a class=\"portfolio-link\" data-bs-toggle=\"modal\" href=\"#portfolioModal\">\r\n"
					+ "								<button onclick=\"loadProductDetail(this)\" value=\""+p.getId()+"\">"
					+ "                                <div class=\"portfolio-hover\">\r\n"
					+ "                                    <div class=\"portfolio-hover-content\"><i class=\"fas fa-plus fa-3x\"></i></div>\r\n"
					+ "                                </div>\r\n"
					+ "                                <img class=\"img-fluid\" src=\"../views/user/assets/img/portfolio/2.jpg\" alt=\"...\" />\r\n"
					+ "								</button>"
					+ "                            </a>\r\n"
					+ "                            <div class=\"portfolio-caption\">\r\n"
					+ "                                <div class=\"portfolio-caption-heading\">"+p.getName()+"</div>\r\n"
					+ GiamGia
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>";
		}
		return chuoi;
	}
	
	@GetMapping("/loadDetailProduct")
	@ResponseBody
	public String loadDetailProduct(int productId) {
		Optional<Product> getProduct = productDao.findById(productId);
		Product p = getProduct.get();
		
		String check = "";
		if (sessionService.getAttribute("account") == null) {
			check = "<p>B???n ph???i ????ng nh???p m???i c?? th??? mua h??ng</p>";
		}else {
			check = "                                    <button class=\"btn btn-primary btn-xl text-uppercase\" data-bs-dismiss=\"modal\" type=\"button\" onclick=\"addCart(this)\" value=\""+productId+"\">\r\n"								
					+ "                                        Th??m v??o gi??? h??ng\r\n"
					+ "                                    </button>\r\n";
		}
		
		String chuoi = "<div class=\"modal-content\">\r\n"
				+ "                    <div class=\"close-modal\" data-bs-dismiss=\"modal\"><img src=\"../views/user/assets/img/close-icon.svg\" alt=\"Close modal\" /></div>\r\n"
				+ "                    <div class=\"container\">\r\n"
				+ "                        <div class=\"row justify-content-center\">\r\n"
				+ "                            <div class=\"col-lg-8\">\r\n"
				+ "                                <div class=\"modal-body\">\r\n"
				+ "                                    <!-- Project details-->\r\n"
				+ "                                    <h2 class=\"text-uppercase mb-5\">"+p.getName()+"</h2>\r\n"				                                    
				+ "                                    <img class=\"img-fluid\" src=\"../views/user/assets/img/portfolio/"+p.getImage()+"\" alt=\"...\" />\r\n"
				+ "                                    <p>"+p.getDescribe()+"</p>\r\n"
				+ "                                    <ul class=\"list-inline\">\r\n"
				+ "                                        <li>\r\n"
				+ "                                            <strong>T??n:</strong>\r\n"
				+ "                                            "+p.getName()+"\r\n"
				+ "                                        </li>\r\n"
				+ "                                        <li>\r\n"
				+ "                                            <strong>Danh m???c:</strong>\r\n"
				+ "                                            "+p.getCategory().getName()+"\r\n"
				+ "                                        </li>\r\n"
				+ "                                    </ul>\r\n"
				+ check
				+ "                                </div>\r\n"
				+ "                            </div>\r\n"
				+ "                        </div>\r\n"
				+ "                    </div>\r\n"
				+ "                </div>";
		return chuoi;
	}
	
	
	@RequestMapping("/addCart")
	@ResponseBody
	public String addCart(int productId) {
		Product pt = productDao.findById(productId).get();
		shopingCart.add(pt);
		
		return "Th??m th??nh c??ng: "+productId;
	}
		
	@RequestMapping("/ShoppingCart")
	@ResponseBody
	public String ShoppingCart() {
		List<CartItemModel> list = shopingCart.getList();

		double tongGia = 0;
		String chuoi = "<div class=\"col-lg-8\">\r\n"
				+ "                                <div class=\"d-flex justify-content-center row\">\r\n"
				+ "                                    <div class=\"col-md-12\">\r\n"
				+ "                                        <div class=\"p-2\">\r\n"
				+ "                                            <h4>Shopping cart</h4>\r\n"
				+ "                                            <a href=\"\" class=\"d-flex flex-row\">S???p x???p theo gi??</a>\r\n"
				+ "                                        </div>";
				
		for (CartItemModel item : list) {
			Product pd = productDao.findById(item.getId()).get();																	
			tongGia += item.getPrice();
			chuoi += "<div class=\"d-flex flex-row justify-content-between align-items-center p-2 bg-white mt-4 mb-5 px-3 rounded\">\r\n"
					+ "                                            <div class=\"mr-1\"><img class=\"rounded\" src=\"../views/user/assets/img/portfolio/"+item.getImg()+"\" alt=\"...\" height=\"70px\" width=\"80px\"/></div>\r\n"
					+ "                                            <div class=\"d-flex flex-column align-items-center product-details\"><span class=\"font-weight-bold\">"+item.getName()+"</span>\r\n"
					+ "                                                <div class=\"size mr-1\"><span class=\"text-grey\">Danh m???c:</span><span class=\"font-weight-bold\">&nbsp;"+pd.getCategory().getName()+"</span></div>\r\n"
					+ "                                            </div>                                           			\r\n"
					+ "                                            <div class=\"d-flex flex-row align-items-center qty\"><button class=\"btn btn-light\" value=\""+pd.getId()+"\" onclick=\"CartRedution(this)\"><i class=\"fa fa-minus text-danger\"></i></button></i>\r\n"
					+ "                                                <h5 class=\"text-grey mt-1 mr-1 ml-1\">"+item.getQty()+"</h5><button class=\"btn btn-light\" value=\""+pd.getId()+"\" onclick=\"CartAdd(this)\"><i class=\"fa fa-plus text-success\"></i></button>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div>\r\n"
					+ "                                                <h5 class=\"text-grey\">"+item.getPrice()+"</h5>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"d-flex align-items-center\"><button class=\"btn btn-light\" value=\""+pd.getId()+"\" onclick=\"CartDelete(this)\"><i class=\"fa fa-trash mb-1 text-danger\"></i></button></div>\r\n"
					+ "                                        </div>";
			
		}
		
		chuoi += "</div>  T???ng gi??: $"+tongGia+" \r\n"
				+ "                                        <button class=\"btn btn-primary btn-xl text-uppercase mt-6\" onclick=\"orderTap()\"  type=\"button\">\r\n"
				+ "                                          \r\n"
				+ "                                            ?????t h??ng\r\n"
				+ "                                        </button>\r\n"
				+ "                                    </div>";
		
		return chuoi;
	}
	
	@RequestMapping("/CartRedution")
	@ResponseBody
	public String cartReduciton(int productId) {
		Product pt = productDao.findById(productId).get();
		shopingCart.redution(pt);
		
		List<CartItemModel> list = shopingCart.getList();

		double tongGia = 0;
		String chuoi = "<div class=\"col-lg-8\">\r\n"
				+ "                                <div class=\"d-flex justify-content-center row\">\r\n"
				+ "                                    <div class=\"col-md-12\">\r\n"
				+ "                                        <div class=\"p-2\">\r\n"
				+ "                                            <h4>Shopping cart</h4>\r\n"
				+ "                                            <a href=\"\" class=\"d-flex flex-row\">S???p x???p theo gi??</a>\r\n"
				+ "                                        </div>";
				
		for (CartItemModel item : list) {
			Product pd = productDao.findById(item.getId()).get();																	
			tongGia += item.getPrice();
			chuoi += "<div class=\"d-flex flex-row justify-content-between align-items-center p-2 bg-white mt-4 mb-5 px-3 rounded\">\r\n"
					+ "                                            <div class=\"mr-1\"><img class=\"rounded\" src=\"../views/user/assets/img/portfolio/"+item.getImg()+"\" alt=\"...\" height=\"70px\" width=\"80px\"/></div>\r\n"
					+ "                                            <div class=\"d-flex flex-column align-items-center product-details\"><span class=\"font-weight-bold\">"+item.getName()+"</span>\r\n"
					+ "                                                <div class=\"size mr-1\"><span class=\"text-grey\">Danh m???c:</span><span class=\"font-weight-bold\">&nbsp;"+pd.getCategory().getName()+"</span></div>\r\n"
					+ "                                            </div>                                           			\r\n"
					+ "                                            <div class=\"d-flex flex-row align-items-center qty\"><button class=\"btn btn-light\" value=\""+pd.getId()+"\" onclick=\"CartRedution(this)\"><i class=\"fa fa-minus text-danger\"></i></button></i>\r\n"
					+ "                                                <h5 class=\"text-grey mt-1 mr-1 ml-1\">"+item.getQty()+"</h5><button class=\"btn btn-light\" value=\""+pd.getId()+"\" onclick=\"CartAdd(this)\"><i class=\"fa fa-plus text-success\"></i></button>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div>\r\n"
					+ "                                                <h5 class=\"text-grey\">"+item.getPrice()+"</h5>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"d-flex align-items-center\"><button class=\"btn btn-light\" value=\""+pd.getId()+"\" onclick=\"CartDelete(this)\"><i class=\"fa fa-trash mb-1 text-danger\"></i></button></div>\r\n"
					+ "                                        </div>";
			
		}
		
		chuoi += "</div>  T???ng gi??: $"+tongGia+" \r\n"
				+ "                                        <button class=\"btn btn-primary btn-xl text-uppercase mt-6\" onclick=\"orderTap()\"  type=\"button\">\r\n"
				+ "                                          \r\n"
				+ "                                            ?????t h??ng\r\n"
				+ "                                        </button>\r\n"
				+ "                                    </div>";
		
		return chuoi;
	}
	
	@RequestMapping("/CartAdd")
	@ResponseBody
	public String CartAdd(int productId) {
		Product pt = productDao.findById(productId).get();
		shopingCart.add(pt);
		
		List<CartItemModel> list = shopingCart.getList();

		double tongGia = 0;
		String chuoi = "<div class=\"col-lg-8\">\r\n"
				+ "                                <div class=\"d-flex justify-content-center row\">\r\n"
				+ "                                    <div class=\"col-md-12\">\r\n"
				+ "                                        <div class=\"p-2\">\r\n"
				+ "                                            <h4>Shopping cart</h4>\r\n"
				+ "                                            <a href=\"\" class=\"d-flex flex-row\">S???p x???p theo gi??</a>\r\n"
				+ "                                        </div>";
				
		for (CartItemModel item : list) {
			Product pd = productDao.findById(item.getId()).get();																	
			tongGia += item.getPrice();
			chuoi += "<div class=\"d-flex flex-row justify-content-between align-items-center p-2 bg-white mt-4 mb-5 px-3 rounded\">\r\n"
					+ "                                            <div class=\"mr-1\"><img class=\"rounded\" src=\"../views/user/assets/img/portfolio/"+item.getImg()+"\" alt=\"...\" height=\"70px\" width=\"80px\"/></div>\r\n"
					+ "                                            <div class=\"d-flex flex-column align-items-center product-details\"><span class=\"font-weight-bold\">"+item.getName()+"</span>\r\n"
					+ "                                                <div class=\"size mr-1\"><span class=\"text-grey\">Danh m???c:</span><span class=\"font-weight-bold\">&nbsp;"+pd.getCategory().getName()+"</span></div>\r\n"
					+ "                                            </div>                                           			\r\n"
					+ "                                            <div class=\"d-flex flex-row align-items-center qty\"><button class=\"btn btn-light\" value=\""+pd.getId()+"\" onclick=\"CartRedution(this)\"><i class=\"fa fa-minus text-danger\"></i></button></i>\r\n"
					+ "                                                <h5 class=\"text-grey mt-1 mr-1 ml-1\">"+item.getQty()+"</h5><button class=\"btn btn-light\" value=\""+pd.getId()+"\" onclick=\"CartAdd(this)\"><i class=\"fa fa-plus text-success\"></i></button>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div>\r\n"
					+ "                                                <h5 class=\"text-grey\">"+item.getPrice()+"</h5>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"d-flex align-items-center\"><button class=\"btn btn-light\" value=\""+pd.getId()+"\" onclick=\"CartDelete(this)\"><i class=\"fa fa-trash mb-1 text-danger\"></i></button></div>\r\n"
					+ "                                        </div>";
			
		}
		
		chuoi += "</div>  T???ng gi??: $"+tongGia+" \r\n"
				+ "                                        <button class=\"btn btn-primary btn-xl text-uppercase mt-6\" onclick=\"orderTap()\"  type=\"button\">\r\n"
				+ "                                          \r\n"
				+ "                                            ?????t h??ng\r\n"
				+ "                                        </button>\r\n"
				+ "                                    </div>";
		
		return chuoi;
	}
	
	@RequestMapping("/CartDelete")
	@ResponseBody
	public String CartDelete(int productId) {
		Product pt = productDao.findById(productId).get();
		shopingCart.remove(pt);
		
		List<CartItemModel> list = shopingCart.getList();

		double tongGia = 0;
		String chuoi = "<div class=\"col-lg-8\">\r\n"
				+ "                                <div class=\"d-flex justify-content-center row\">\r\n"
				+ "                                    <div class=\"col-md-12\">\r\n"
				+ "                                        <div class=\"p-2\">\r\n"
				+ "                                            <h4>Shopping cart</h4>\r\n"
				+ "                                            <a href=\"\" class=\"d-flex flex-row\">S???p x???p theo gi??</a>\r\n"
				+ "                                        </div>";
				
		for (CartItemModel item : list) {
			Product pd = productDao.findById(item.getId()).get();																	
			tongGia += item.getPrice();
			chuoi += "<div class=\"d-flex flex-row justify-content-between align-items-center p-2 bg-white mt-4 mb-5 px-3 rounded\">\r\n"
					+ "                                            <div class=\"mr-1\"><img class=\"rounded\" src=\"../views/user/assets/img/portfolio/"+item.getImg()+"\" alt=\"...\" height=\"70px\" width=\"80px\"/></div>\r\n"
					+ "                                            <div class=\"d-flex flex-column align-items-center product-details\"><span class=\"font-weight-bold\">"+item.getName()+"</span>\r\n"
					+ "                                                <div class=\"size mr-1\"><span class=\"text-grey\">Danh m???c:</span><span class=\"font-weight-bold\">&nbsp;"+pd.getCategory().getName()+"</span></div>\r\n"
					+ "                                            </div>                                           			\r\n"
					+ "                                            <div class=\"d-flex flex-row align-items-center qty\"><button class=\"btn btn-light\" value=\""+pd.getId()+"\" onclick=\"CartRedution(this)\"><i class=\"fa fa-minus text-danger\"></i></button></i>\r\n"
					+ "                                                <h5 class=\"text-grey mt-1 mr-1 ml-1\">"+item.getQty()+"</h5><button class=\"btn btn-light\" value=\""+pd.getId()+"\" onclick=\"CartAdd(this)\"><i class=\"fa fa-plus text-success\"></i></button>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div>\r\n"
					+ "                                                <h5 class=\"text-grey\">"+item.getPrice()+"</h5>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"d-flex align-items-center\"><button class=\"btn btn-light\" value=\""+pd.getId()+"\" onclick=\"CartDelete(this)\"><i class=\"fa fa-trash mb-1 text-danger\"></i></button></div>\r\n"
					+ "                                        </div>";
			
		}
		
		chuoi += "</div>  T???ng gi??: $"+tongGia+" \r\n"
				+ "                                        <button class=\"btn btn-primary btn-xl text-uppercase mt-6\" onclick=\"orderTap()\"  type=\"button\">\r\n"
				+ "                                          \r\n"
				+ "                                            ?????t h??ng\r\n"
				+ "                                        </button>\r\n"
				+ "                                    </div>";
		
		return chuoi;
	}
	
	@RequestMapping("/orderTap")
	@ResponseBody
	public String orderTap() {

		if (shopingCart.getList().isEmpty()) {
			return "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
					+ "  B???n ph???i ch???n s???n ph???m tr?????c khi ?????t h??ng\r\n"
					+ "</div>";
		}
		return "<form action=\"user/order\" method=\"post\">\r\n"
				+ "							  <div class=\"mb-3\">\r\n"
				+ "							    <label for=\"exampleInputEmail1\" class=\"form-label\">Nh???p email</label>\r\n"
				+ "							    <input type=\"email\" class=\"form-control\" name=\"emailInput\" id=\"emailInput\" aria-describedby=\"emailHelp\">							    \r\n"
				+ "							  </div>\r\n"
				+ "							  <div class=\"mb-3\">\r\n"
				+ "							    <label for=\"exampleInputPassword1\" class=\"form-label\">Nh???p ?????a ch???</label>\r\n"
				+ "							    <input type=\"address\" class=\"form-control\" name=\"addressInput\" id=\"addressInput\">\r\n"
				+ "							  </div>\r\n"
				+ "							  \r\n"
				+ "							  <button type=\"submit\" id=\"submitInput\" onclick=\"submitOrder()\" class=\"btn btn-primary\">Submit</button>\r\n"
				+ "							</form>";
	}
	
	@RequestMapping("/submitOrder")
	@ResponseBody
	public String submitOrder() {
		
		return "B???n ?????t h??ng th??nh c??ng, ch??ng t??i s??? g???i th??ng tin ????n h??ng ?????n mail c???a b???n";
	}
	
	// History
	
	@RequestMapping("/historyUser")
	@ResponseBody
	public String historyUser() {
		Account ac = sessionService.getAttribute("account");
		List<Order> listOrderUser = orderDao.findOrderUser(ac.getUsername());
		String chuoi = "<div class=\"modal-body\" id=\"historyUser\">\r\n"
				+ "                                    <table class=\"table table-dark table-hover\">\r\n"
				+ "                                        <thead>\r\n"
				+ "                                            <tr>\r\n"
				+ "                                              <th scope=\"col\">Id</a></th>\r\n"
				+ "                                              <th scope=\"col\">Ng?????i d??ng</th>\r\n"
				+ "                                              <th scope=\"col\">Ng??y t???o</th>\r\n"
				+ "                                              <th scope=\"col\">?????a ch???</th>\r\n"
				+ "                                              <th scope=\"col\">Tr???ng th??i</th>\r\n"
				+ "                                            </tr>\r\n"
				+ "                                          </thead>\r\n"
				+ "                                          <tbody>";
		for (Order item : listOrderUser) {
			chuoi += "<tr>\r\n"
					+ "                                              <td><input type=\"button\" class=\"btn btn-dark\" onclick=\"historyDetail(this)\" value=\""+item.getId()+"\">Chi ti???t</input></td>\r\n"
					+ "                                              <td>"+item.getAccount().getUsername()+"</td>\r\n"
					+ "                                              <td>"+item.getCreateDate()+"</td>\r\n"
					+ "                                              <td>"+item.getAddress()+"</td>\r\n"
					+ "                                              <td>"+item.getStatus()+"</td>\r\n"
					+ "                                            </tr>";
		}
		chuoi += "</tbody>\r\n"
				+ "</table";
		return chuoi;
	}
	
	@RequestMapping("/historyUser/status")
	@ResponseBody
	public String historyStatus(String status) {
		Account ac = sessionService.getAttribute("account");
		List<Order> listOrderUser = orderDao.findOrderUserStatus(ac.getUsername(), status);
		String chuoi = "<div class=\"modal-body\" id=\"historyUser\">\r\n"
				+ "                                    <table class=\"table table-dark table-hover\">\r\n"
				+ "                                        <thead>\r\n"
				+ "                                            <tr>\r\n"
				+ "                                              <th scope=\"col\">ID</a></th>\r\n"
				+ "                                              <th scope=\"col\">Ng?????i d??ng</th>\r\n"
				+ "                                              <th scope=\"col\">Ng??y t???o</th>\r\n"
				+ "                                              <th scope=\"col\">?????a ch???</th>\r\n";
				if (status.equals("Ch??? x??c nh???n")) {
					chuoi += "<th scope=\"col\">H??nh ?????ng</th>\r\n";
				}
				chuoi += "                                   </tr>\r\n"
				+ "                                          </thead>\r\n"
				+ "                                          <tbody>";
		for (Order item : listOrderUser) {
			chuoi += "<tr>\r\n"
					+ "                                              <td><input type=\"button\" class=\"btn btn-dark\" onclick=\"historyDetail(this)\" value=\""+item.getId()+"\">Chi ti???t</input></td>\r\n"
					+ "                                              <td>"+item.getAccount().getUsername()+"</td>\r\n"
					+ "                                              <td>"+item.getCreateDate()+"</td>\r\n"
					+ "                                              <td>"+item.getAddress()+"</td>\r\n";
					if (status.equals("Ch??? x??c nh???n")) {
						chuoi += "<td><input type=\"button\" class=\"btn btn-dark\" onclick=\"historyHuy(this)\" value=\""+item.getId()+"\">H???y</input></td>\r\n";
					}
					chuoi+= "                                            </tr>";
		}
		chuoi += "</tbody>\r\n"
				+ "</table";
		return chuoi;
	}
	
	@RequestMapping("/historyUser/huy")
	@ResponseBody
	public String historyHuy(int orderId) {
		Order or = orderDao.getById(orderId);
		or.setStatus("???? h???y");
		orderDao.save(or);
		Account ac = sessionService.getAttribute("account");
		String status = "Ch??? x??c nh???n";
		List<Order> listOrderUser = orderDao.findOrderUserStatus(ac.getUsername(), status);
		String chuoi = "<div class=\"modal-body\" id=\"historyUser\">\r\n"
				+ "                                    <table class=\"table table-dark table-hover\">\r\n"
				+ "                                        <thead>\r\n"
				+ "                                            <tr>\r\n"
				+ "                                              <th scope=\"col\">ID</a></th>\r\n"
				+ "                                              <th scope=\"col\">Ng?????i d??ng</th>\r\n"
				+ "                                              <th scope=\"col\">Ng??y t???o</th>\r\n"
				+ "                                              <th scope=\"col\">?????a ch???</th>\r\n";
				if (status.equals("Ch??? x??c nh???n")) {
					chuoi += "<th scope=\"col\">H??nh ?????ng</th>\r\n";
				}
				chuoi += "                                   </tr>\r\n"
				+ "                                          </thead>\r\n"
				+ "                                          <tbody>";
		for (Order item : listOrderUser) {
			chuoi += "<tr>\r\n"
					+ "                                              <td><input type=\"button\" class=\"btn btn-dark\" onclick=\"historyDetail(this)\" value=\""+item.getId()+"\">Chi ti???t</input></td>\r\n"
					+ "                                              <td>"+item.getAccount().getUsername()+"</td>\r\n"
					+ "                                              <td>"+item.getCreateDate()+"</td>\r\n"
					+ "                                              <td>"+item.getAddress()+"</td>\r\n";
					if (status.equals("Ch??? x??c nh???n")) {
						chuoi += "<td><input type=\"button\" class=\"btn btn-dark\" onclick=\"historyHuy(this)\" value=\""+item.getId()+"\">H???y</input></td>\r\n";
					}
					chuoi+= "                                            </tr>";
		}
		chuoi += "</tbody>\r\n"
				+ "</table";
		return chuoi;
	}
	
	@RequestMapping("/historyUser/detail")
	@ResponseBody
	public String historyDetail(int orderId) {
		List<OrderDetail> listOrderUserDetail = oderDetailDao.findOrderORID(orderId);	
		String chuoi = "<table class=\"table table-dark table-hover\">\r\n"
				+ "                                        <thead>\r\n"
				+ "                                            <tr>\r\n"
				+ "                                              <th scope=\"col\">ID</th>\r\n"
				+ "                                              <th scope=\"col\">S???n ph???m</th>\r\n"
				+ "                                              <th scope=\"col\">Gi??</th>\r\n"
				+ "                                              <th scope=\"col\">S??? l?????ng</th>\r\n"
				+ "                                            </tr>\r\n"
				+ "                                          </thead>\r\n"
				+ "                                          <tbody>\r\n";
				for (OrderDetail item : listOrderUserDetail) {
							chuoi+="                                       <tr>\r\n"
							+ "                                              <th scope=\"row\">"+item.getId()+"</th>\r\n"
							+ "                                              <td>"+item.getProduct().getName()+"</td>\r\n"
							+ "                                              <td>"+item.getPrice()+"</td>\r\n"
							+ "                                              <td>"+item.getQuantity()+"</td>\r\n"
							+ "                                            </tr>\r\n";
				}
				chuoi += "                                           \r\n"
				+ "                                          </tbody>\r\n"
				+ "                                    </table>";
		return chuoi;
	}
}
