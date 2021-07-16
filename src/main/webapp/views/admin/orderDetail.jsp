<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div class="col mb-3">
        <div class="e-panel card mb-3">
          <div class="card-body">
            <form class="row g-3 needs-validation" action="/admin/orderDetail/crud/add" method="post" novalidate>
              
              <div class="col-md-4 mb-2">
                <label for="validationCustom01" class="form-label">Mã sản phẩm</label>
                <input type="text" class="form-control" value="${ORDTForm.product.id }" name="product" id="validationCustom01"  required>
              </div>             
              <div class="col-md-4 mb-2">
                <label for="validationCustom01" class="form-label">Số lượng</label>
                <input type="text" class="form-control" value="${ORDTForm.quantity }" name="quantity" id="validationCustom01" required>
              </div>
              <div class="col-md-4 mb-2">
                <label for="validationCustom01" class="form-label">Giá</label>
                <input type="text" class="form-control" value="${ORDTForm.price }" name="price" id="validationCustom01"  required>
              </div>
              <div class="col-md-4 mb-2">
                <label for="validationCustom01" class="form-label">Id</label>
                <input type="text" class="form-control" value="${ORDTForm.id }"  id="validationCustom01" disabled required>
              </div>
              <div class="col-md-4 mb-2">
                <label for="validationCustom01" class="form-label">Mã đơn hàng</label>
                <input type="text" class="form-control" value="${OrderID }" id="validationCustom01" disabled required>
              </div>
              <input value="${ORDTForm.id }" name="id" hidden="true"/>
              <input value="${OrderID }" name="orderID" hidden="true"/>
              <div class="col-12 mt-3">
                <button class="btn btn-primary" type="submit">Hoàn thành</button>
                <a class="btn btn-light ml-2" type="submit" href="/admin/orderDetail?orderId=${OrderID }">Làm mới</a>
              </div>
              
            </form>
          </div>
        </div>
        <div class="e-panel card">
          <div class="card-body">
            <div class="card-title">
              <h6 class="mr-2"><span>Quản lý</span><small class="px-1">Sản phẩm</small></h6>
            </div>
            <div class="e-table">
              <div class="table-responsive table-lg mt-3">
                <table class="table table-bordered">
                  <thead>
                    <tr>
                      <th class="text-nowrap align-middle">Id</th>
                      <th class="text-nowrap align-middle">Mã đơn hàng</th>
                      <th class="text-nowrap align-middle">Mã sản phẩm</th>
                      <th class="text-nowrap align-middle">Tên sản phẩm</th>
                      <th class="text-nowrap align-middle">Số lượng</th>
                      <th class="text-nowrap align-middle">Giá</th>
                      <th class="text-nowrap align-middle">Hành động</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${ListORDT }" var="item">
                    	<tr>
	                      <td class="text-nowrap align-middle">${item.id }</td>
	                      <td class="text-nowrap align-middle">${item.order.id }</td>                
	                      <td >${item.product.id }<a class="btn btn-sm btn-outline-secondary badge ml-4" href="/admin/orderDetail/crud/loadProductDetail?id=${item.id }&idPR=${item.product.id }" type="button">Chi tiết</a></td>
	                      <td class="text-nowrap align-middle">${item.product.name }</td>
	                      <td class="text-nowrap align-middle">${item.quantity }</td>
	                      <td class="text-nowrap align-middle">${item.price }</td>
	                      <td class="text-center align-middle">
	                        <div class="btn-group align-top">
	                            <a class="btn btn-sm btn-outline-secondary badge" type="button" href="/admin/orderDetail/crud/edit?id=${item.id }">Sửa</a>
	                            <a class="btn btn-sm btn-outline-secondary badge" type="button" href="/admin/orderDetail/crud/delete?id=${item.id }"><i class="fa fa-trash"></i></a>
	                        </div>
	                      </td>
	                    </tr>
                    </c:forEach>
                    
                  </tbody>
                </table>
              </div>
              
            </div>
          </div>        
        </div>
      </div>
      <div class="col-12 col-lg-3 mb-3">
        <div class="card">
          <div class="card-body">
            <img class="rounded" src="../views/user/assets/img/portfolio/${infoPD.image }" alt="..." height="70px" width="80px"/>
            <hr>  
            <p>Id :${infoPD.id }</p>
            <p>Tên sản phẩm :${infoPD.name }</p> 
            <p>Mô tả :${infoPD.describe }</p>
            <p>Ngày tạo :${infoPD.createDate }</p>
            <p>Hoạt động :${infoPD.avaliabe }</p>
            <p>Khuyến mãi giảm :${infoPD.promotions }</p>
            <p>Danh mục :${infoPD.category.name }</p>
            <p>Giá :${infoPD.price }</p>                  
          </div>
        </div>
      </div>