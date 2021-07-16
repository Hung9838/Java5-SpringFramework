<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Khoai Shop - Kính thời trang</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="../views/user/assets/575475.png" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link rel="stylesheet" href="../views/user/css/styles.css" />
    </head>
    <body id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
            	
                <a class="navbar-brand" href="#page-top"><img src="../views/user/assets/img/khoaiShopLogo.png"/></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ms-1"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        
                        <li class="nav-item"><a class="nav-link" href="#services">Dịch vụ</a></li>
                        <li class="nav-item"><a class="nav-link" href="#sale">Giảm giá</a></li>
                        <!-- <li class="nav-item"><a class="nav-link" href="#selling">Bán chạy</a></li> -->
                        <li class="nav-item"><a class="nav-link" href="#portfolio">Danh mục</a></li>
                        <c:if test="${sessionScope.account == null }">
                        	<li class="nav-item"><a class="nav-link" href="login">Đăng nhập</a></li>
                        </c:if>
                        <c:if test="${sessionScope.account != null }">
                        	<li class="nav-item"><a class="nav-link" href="logout">Đăng xuất</a></li>
                        	<li class="nav-item"><a class="nav-link" data-bs-toggle="modal" onclick="historyUser()" href="#history"><i class="fa fa-history"></i></a></li>
                        	<li class="nav-item"><a class="nav-link" data-bs-toggle="modal" href="#user"><i class="fa fa-user"></i></a></li>
                        	<li class="nav-item"><a class=" nav-link" data-bs-toggle="modal" onclick="ShoppingCart()" href="#cartshop"><i class="fa fa-shopping-cart fa-inverse"></i></a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Masthead-->
        <header class="masthead">
            <div class="container">
                <div class="masthead-subheading">Chào mừng bạn đến với Khoai shop</div>
                <div class="masthead-heading text-uppercase">Không ngừng vươn xa</div>
                <a class="btn btn-primary btn-xl text-uppercase" href="#services">Xem thêm</a>
            </div>
        </header>
        <!-- Services-->
        <section class="page-section" id="services">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">Dịch vụ</h2>
                    <h3 class="section-subheading text-muted">Chúng tôi luôn đặt cao chất lượng dịch vụ</h3>
                </div>
                <div class="row text-center">
                    <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                            <i class="fas fa-circle fa-stack-2x text-primary"></i>
                            <i class="fas fa-shopping-cart fa-stack-1x fa-inverse"></i>
                        </span>
                        <h4 class="my-3">Sản phẩm</h4>
                        <p class="text-muted">Chúng tôi cung cấp các sản phẩm chất lượng cao. Bao gồm: đồng hồ chính hãng, kính thời trang, dây đồng hồ, hộp đồng hồ, bút ký cao cấp</p>
                    </div>
                    <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                            <i class="fas fa-circle fa-stack-2x text-primary"></i>
                            <i class="fas fa-laptop fa-stack-1x fa-inverse"></i>
                        </span>
                        <h4 class="my-3">Hỗ trợ</h4>
                        <p class="text-muted">Quý khách hoàn toàn yên tâm khi mua hàng, cửa hàng sẽ hỗ trợ bạn trong vấn đề bảo hành, đổi hàng, vận chuyển, tư vấn sản phẩm</p>
                    </div>
                    <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                            <i class="fas fa-circle fa-stack-2x text-primary"></i>
                            <i class="fas fa-lock fa-stack-1x fa-inverse"></i>
                        </span>
                        <h4 class="my-3">Bảo mật</h4>
                        <p class="text-muted">Thông tin cá nhân được chúng tôi tuyệt đối bảo mật theo chính sách. Việc sử dụng thông tin chỉ được thực hiện khi có sự đồng ý của khách hàng</p>
                    </div>
                </div>
            </div>
        </section>
        <!-- Sale Grid-->
        <section class="page-section bg-light" id="sale">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">Top sản phẩm giảm giá</h2>
                    <h3 class="section-subheading text-muted">các sản phẩm được sale trong đợt này</h3>
                </div>
                <div class="row">
                     <c:forEach items="${TopSaleProduct }" var="item">
                     	<div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item-->
	                        <div class="sale-item">
	                            <a class="sale-link" data-bs-toggle="modal" href="#portfolioModal">
	                            	<button onclick="loadProductDetail(this)" value="${item.id }">
	                                <div class="sale-hover">
	                                    <div class="sale-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="../views/user/assets/img/portfolio/${item.image }" alt="..." />
	                                </button>
	                            </a>
	                            <div class="sale-caption">
	                                <div class="sale-caption-heading">${item.name }</div>
	                                <c:if test="${item.promotions == null}">
	                                	<div class="sale-caption-subheading text-muted">${item.price }</div>
	                                	<br />
	                                </c:if>
	                                <c:if test="${item.promotions != null}">
		                                <div class="sale-caption-subheading text-muted" style="text-decoration: line-through; ">${item.price }</div>
		                                <div class="sale-caption-subheading text-muted">${item.price / 100 * (100 - item.promotions)}</div>
	                                </c:if>
	                            </div>
	                        </div>
	                    </div>
                     </c:forEach>             
                </div>
            </div>
        </section>
        <!-- Selling Grid-->
        <%-- <section class="page-section bg-light" id="selling">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">Top sản phẩm bán chạy</h2>
                    <h3 class="section-subheading text-muted">mười sản phẩm được sale trong đợt này</h3>
                </div>
                <div class="row">                
                    <c:forEach items="TopSellingProduct" var="item">
                    	<div class="col-lg-4 col-sm-6 mb-4">
	                        <!-- Portfolio item-->
	                        <div class="sale-item">
	                            <a class="sale-link" data-bs-toggle="modal" href="#portfolioModal">
	                                <div class="sale-hover">
	                                    <div class="sale-hover-content"><i class="fas fa-plus fa-3x"></i></div>
	                                </div>
	                                <img class="img-fluid" src="../views/user/assets/img/portfolio/${item.image }" alt="..." />
	                            </a>
	                            <div class="sale-caption">
	                                <div class="sale-caption-heading">${item.name }</div>
	                                <div class="sale-caption-subheading text-muted">${item.price }</div>
	                            </div>
	                        </div>
	                    </div>
                    </c:forEach>                                      
                </div>
            </div>
        </section> --%>
        <!-- Portfolio Grid-->
        <section class="page-section bg-light" id="portfolio">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">Sản phẩm theo danh mục</h2>
                    <br>
   
                    <div class="btn-group mb-4" role="group">
					    <button id="selectCategories" type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
					      Chọn Danh mục
					    </button>
					    <ul class="categoryMenu dropdown-menu" aria-labelledby="selectCategories">
					      <c:forEach items="${ListCategories }" var="item">
					      		<li><input class="categoryItem dropdown-item" type="button" onclick="loadCategories(this)" value="${item.name }"/></li>
					      	<%-- <li><a class="categoryItem dropdown-item" onclick="loadCategories()">${item.name }</a></li> --%>
					      </c:forEach>
					    </ul>
				    </div>
                    <br>
                    <br>
                </div>
                <div class="row" id="categoryDiv">                
                    
                </div>
                
            </div>
            
        </section>

        <!-- Contact-->
        <section class="page-section" id="contact">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">Khoai Shop</h2>
                    <h3 class="section-subheading text-muted">Không ngừng vươn xa</h3>
                </div>
            </div>
            
        </section>
        <!-- Footer-->
        <footer class="footer py-4">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-4 text-lg-start">Liên hệ: 0123456***     Địa chỉ: ********</div>
                    <div class="col-lg-4 my-3 my-lg-0">
                        <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-twitter"></i></a>
                        <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-facebook-f"></i></a>
                        <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    
                </div>
            </div>
        </footer>
        <!-- Portfolio Modals-->
        <!-- Portfolio item modal popup-->
        <div class="portfolio-modal modal fade" id="portfolioModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" id="productDetail">
                    
            </div>
        </div>
        <div class="portfolio-modal modal fade" id="cartshop" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="close-modal" data-bs-dismiss="modal"><img src="../views/user/assets/img/close-icon.svg" alt="Close modal" /></div>
                    <div class="container">
                        <div class="row justify-content-center" id="cardShopping">
								
							<!-- <form>
							  <div class="mb-3">
							    <label for="exampleInputEmail1" class="form-label">Nhập email</label>
							    <input type="email" class="form-control" id="emailInput" aria-describedby="emailHelp">							    
							  </div>
							  <div class="mb-3">
							    <label for="exampleInputPassword1" class="form-label">Nhập địa chỉ</label>
							    <input type="address" class="form-control" id="addressInput">
							  </div>
							  
							  <button type="submit" id="submitInput" class="btn btn-primary">Submit</button>
							</form> -->
								
                             <!-- <div class="col-lg-8">
                                <div class="d-flex justify-content-center row">
                                    <div class="col-md-12">
                                        <div class="p-2">
                                            <h4>Shopping cart</h4>
                                            <a href="" class="d-flex flex-row">Sắp xếp theo giá</a>
                                        </div>
                                        
                                             
                                        <div class="d-flex flex-row justify-content-between align-items-center p-2 bg-white mt-4 mb-5 px-3 rounded">
                                            <div class="mr-1"><img class="rounded" src="../views/user/assets/img/portfolio/2.jpg" alt="..." height="70px" width="80px"/></div>
                                            <div class="d-flex flex-column align-items-center product-details"><span class="font-weight-bold">Basic T-shirt</span>
                                                <div class="size mr-1"><span class="text-grey">Danh mục:</span><span class="font-weight-bold">&nbsp;Danh mục 1</span></div>
                                            </div>                                           			
                                            <div class="d-flex flex-row align-items-center qty"><button class="btn btn-light" value="" onclick="addCart(this)"><i class="fa fa-minus text-danger"></i></button></i>
                                                <h5 class="text-grey mt-1 mr-1 ml-1">| 2 |</h5><button class="btn btn-light" value="" onclick="CartRedution"><i class="fa fa-plus text-success"></i></button>
                                            </div>
                                            <div>
                                                <h5 class="text-grey">$20.00</h5>
                                            </div>
                                            <div class="d-flex align-items-center"><button class="btn btn-light" value="" onclick="CartDelete(this)"><i class="fa fa-trash mb-1 text-danger"></i></button></div>
                                        </div>
                                        
                                                                        
                                    </div>
                                    </div>
                                        <button class="btn btn-primary btn-xl text-uppercase mt-6"  type="button">
                                          
                                            Đặt hàng
                                        </button>
                                    </div> --> 
                                    
                                    
                                    
                                    <!-- data-bs-dismiss="modal" -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>        
        </div>
        <div class="portfolio-modal modal fade" id="user" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="close-modal" data-bs-dismiss="modal"><img src="../views/user/assets/img/close-icon.svg" alt="Close modal" /></div>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-8">
                                <div class="modal-body">
                                    <form>
                                        <div class="mb-3">
                                          <label for="exampleInputEmail1" class="form-label">Email address</label>
                                          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                                          <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                                        </div>
                                        <div class="mb-3">
                                          <label for="exampleInputPassword1" class="form-label">Password</label>
                                          <input type="password" class="form-control" id="exampleInputPassword1">
                                        </div>
                                        <div class="mb-3 form-check">
                                          <input type="checkbox" class="form-check-input" id="exampleCheck1">
                                          <label class="form-check-label" for="exampleCheck1">Check me out</label>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                      </form>
                                    <button class="btn btn-primary btn-xl text-uppercase" data-bs-dismiss="modal" type="button">
                                        <i class="fas fa-times me-1"></i>
                                        Close Project
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>        
        </div>
        <div class="portfolio-modal modal fade" id="history" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="close-modal" data-bs-dismiss="modal"><img src="../views/user/assets/img/close-icon.svg" alt="Close modal" /></div>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-12">
                            	<ul class="nav">
								  <li class="nav-item">
								    <input type="button" class="btn btn-dark m-2" onclick="historyUser()" value="Tất cả"/>
								  </li>
								  <li class="nav-item">
								    <input type="button" class="btn btn-dark m-2" onclick="historyStatus(this)" value="Chờ xác nhận"/>
								  </li>
								  <li class="nav-item">
								    <input type="button" class="btn btn-dark m-2" onclick="historyStatus(this)" value="Chờ lấy hàng"/>
								  </li>
								  <li class="nav-item">
								    <input type="button" class="btn btn-dark m-2" onclick="historyStatus(this)" value="Đang giao"/>
								  </li>
								  <li class="nav-item">
								    <input type="button" class="btn btn-dark m-2" onclick="historyStatus(this)" value="Đã hủy"/>
								  </li>
								</ul>
                                <div class="modal-body" id="historyUser">
                                    <!-- <table class="table table-dark table-hover">
                                        <thead>
                                            <tr>
                                              <th scope="col">ID</th>
                                              <th scope="col">Sản phẩm</th>
                                              <th scope="col">Giá</th>
                                              <th scope="col">Số lượng</th>
                                            </tr>
                                          </thead>
                                          <tbody>
                                            <tr>
                                              <th scope="row">1</th>
                                              <td>Mark</td>
                                              <td>Otto</td>
                                              <td>@mdo</td>
                                            </tr>
                                            
                                          </tbody>
                                    </table> -->
                                </div> 
                                <div class="p-5" id="orderUserDetail">
                                	
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>        
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
			function loadCategories(param){
				var cate = param.value;
				/* alert(cate); */
				$.ajax({
				    url: "/api/loadCategories",
				    type: "get",
				    data: {
					    categorySelect: cate
					},
				    success: function(response) {
					    var row = document.getElementById("categoryDiv");
						row.innerHTML = response;
				    },
				    error: function(xhr) {

				    }
				});
			}
			
			function loadProductDetail(param){
				var idpr = param.value;
				/* alert(idpr); */
				$.ajax({
				    url: "/api/loadDetailProduct",
				    type: "get",
				    data: {
						productId : idpr
					},
				    success: function(response) {
				    	
				    	var row = document.getElementById("productDetail");
						row.innerHTML = response;
				    },
				    error: function(xhr) {

				    }
				});
			}
			
			function addCart(param){
				var spId = param.value;
				/* alert(spId); */
				$.ajax({
				    url: "/api/addCart",
				    type: "get",
				    data: {
						productId : spId
					},
				    success: function(response) {
				    	
				    	alert(response);
				    },
				    error: function(xhr) {

				    }
				});
			}

			function ShoppingCart(){

				$.ajax({
				    url: "/api/ShoppingCart",
				    type: "get",
				    success: function(response) {
				    	var row = document.getElementById("cardShopping");
						row.innerHTML = response;  
				    },
				    error: function(xhr) {

				    }
				});
			} 

			function CartAdd(param){
				var spId = param.value;
				/* alert(spId); */
				$.ajax({
				    url: "/api/CartAdd",
				    type: "get",
				    data: {
						productId : spId
					},
				    success: function(response) {
				    	var row = document.getElementById("cardShopping");
						row.innerHTML = response;
				    },
				    error: function(xhr) {

				    }
				});
			}

			function CartRedution(param){
				var spId = param.value;
				$.ajax({
				    url: "/api/CartRedution",
				    type: "get",
				    data: {
						productId : spId
					},
				    success: function(response) {
				    	
				    	var row = document.getElementById("cardShopping");
						row.innerHTML = response;
				    },
				    error: function(xhr) {

				    }
				});
			}

			function CartDelete(param){
				var spId = param.value;
				$.ajax({
				    url: "/api/CartDelete",
				    type: "get",
				    data: {
						productId : spId
					},
				    success: function(response) {
				    	
				    	var row = document.getElementById("cardShopping");
						row.innerHTML = response;
				    },
				    error: function(xhr) {

				    }
				});
			}

			function orderTap(){

				$.ajax({
				    url: "/api/orderTap",
				    type: "get",
				    success: function(response) {
					    
				    	var row = document.getElementById("cardShopping");
						row.innerHTML = response;  
				    },
				    error: function(xhr) {

				    }
				});
			}

			function submitOrder(){

				$.ajax({
				    url: "/api/submitOrder",
				    type: "get",
				    success: function(response) {
					    alert(response);  
				    },
				    error: function(xhr) {

				    }
				});
			}

			function historyUser(){

				$.ajax({
				    url: "/api/historyUser",
				    type: "get",
				    success: function(response) {
				    	var row = document.getElementById("historyUser");
						row.innerHTML = response;  
				    },
				    error: function(xhr) {

				    }
				});
			}

			function historyStatus(param){
				var status = param.value;
				$.ajax({
				    url: "/api/historyUser/status",
				    type: "get",
				    data: {
						status : status
					},
				    success: function(response) {
				    	var row = document.getElementById("historyUser");
						row.innerHTML = response;  
				    },
				    error: function(xhr) {

				    }
				});
			}

			function historyHuy(param){
				var orderId = param.value;
				$.ajax({
				    url: "/api/historyUser/huy",
				    type: "get",
				    data: {
				    	orderId : orderId
					},
				    success: function(response) {
				    	var row = document.getElementById("historyUser");
						row.innerHTML = response;  
				    },
				    error: function(xhr) {

				    }
				});
			}

			function historyDetail(param){
				var orderId = param.value;
				$.ajax({
				    url: "/api/historyUser/detail",
				    type: "get",
				    data: {
				    	orderId : orderId
					},
				    success: function(response) {
				    	var row = document.getElementById("orderUserDetail");
						row.innerHTML = response; 
				    },
				    error: function(xhr) {

				    }
				});
			}
	/* 		$(document).ready(function () {
				  
	            $("#submitInput").click(function () {
	                var emailInput = $("#emailInput").val();
	                var addressInput = $("#addressInput").val();
	                alert(emailInput);
	                alert(addressInput);
	            });
	        }); */
        </script>
        
        
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        
        <script src="../views/user/js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
    