<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	 <div class="col mb-3">
        <div class="e-panel card mb-3">
          <div class="card-body">
            <form class="row g-3 needs-validation" action="/admin/order/crud/add" method="post" novalidate>
              <div class="col-md-4 mb-2">
                <label for="validationCustom01" class="form-label">Id</label>
                <input type="text" class="form-control" value="${orForm.id }" id="validationCustom01" disabled required>
              </div>
              <div class="col-md-4 mb-2">
                <label for="validationCustom01" class="form-label">Người dùng</label>
                <input type="text" class="form-control" value="${orForm.account.username }" name="account" id="validationCustom01" required>
              </div>
              <div class="col-md-4 mt-1">
                <label for="validationCustom02" class="form-label" >Trạng thái</label>
                <select class="form-control" name="status" aria-label="Default select example">
                  <c:forEach items="${orListStatus }" var="item">
                  	<option ${selected = orForm.status == item? "selected" : ""} value="${item }">${item }</option>
                  </c:forEach>
                </select>
              </div>             
              <div class="col-md-12 mb-2">
                <label for="validationCustom01" class="form-label">Địa chỉ</label>
                <input type="text" class="form-control" value="${orForm.address }" name="address" id="validationCustom01" required>
              </div>
              
              <input value="${orForm.id }" name="id" hidden="true"/>
              <input value="${pageOR.number }" name="page" hidden="true"/>
              <div class="col-12 mt-3">
                <button class="btn btn-primary" type="submit">Hoàn thành</button>
                <a class="btn btn-light ml-2" type="submit" href="/admin/order/page?p=${pageOR.number }">Làm mới</a>
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
                      <th class="text-nowrap align-middle"><a href="/admin/order/crud/sapXepTheoTaiKhoan?p=${pageOR.number }">Người dùng</a></th>
                      <th class="text-nowrap align-middle"><a href="/admin/order/crud/sapXepTheoNgay?p=${pageOR.number }">Ngày tạo</a></th>
                      <th class="text-nowrap align-middle">Địa chỉ</th>
                      <th class="text-nowrap align-middle">Trạng thái</th>
                      <th class="text-nowrap align-middle">Hoạt động</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${pageOR.content }" var="item">
                    	<tr>
	                      <td>${item.id }<a class="btn btn-sm btn-outline-secondary badge ml-4" type="button" href="/admin/orderDetail?orderId=${item.id }">Chi tiết</a></td>
	                      <td class="text-nowrap align-middle">${item.account.username }</td>                
	                      <td class="text-nowrap align-middle">${item.createDate }</td>
	                      <td class="text-nowrap align-middle">${item.address }</td>
	                      <td class="text-nowrap align-middle">${item.status }</td>
	                      <td class="text-center align-middle">
	                        <div class="btn-group align-top">
	                            <a class="btn btn-sm btn-outline-secondary badge" type="button" href="/admin/order/crud/edit?id=${item.id }&page=${pageOR.number }">Sửa</a>
	                            <a class="btn btn-sm btn-outline-secondary badge" type="button" href="/admin/order/crud/delete?id=${item.id }&page=${pageOR.number }"><i class="fa fa-trash"></i></a>
	                        </div>
	                      </td>
	                    </tr>
                    </c:forEach>
                    
                  </tbody>
                </table>
              </div>
              <div class="d-flex justify-content-center">
                <ul class="pagination mt-3 mb-0">
                  <li class="page-item"><a href="/admin/order/page?p=0" class="page-link">Min</a></li>
                  <li class="page-item"><a href="/admin/order/page?p=${pageOR.number-1 }" class="page-link">‹</a></li>
                  <li class="page-item"><a href="/admin/order/page?p=${pageOR.number+1 }" class="page-link">›</a></li>
                  <li class="page-item"><a href="/admin/order/page?p=${pageOR.totalPages-1 }" class="page-link">Max</a></li>

                </ul>
              </div>
            </div>
          </div>        
        </div>
      </div>
      <div class="col-12 col-lg-3 mb-3">
        <div class="card">
          <div class="card-body">
            <!-- <div class="text-center px-xl-3">
              <button class="btn btn-success btn-block" type="button" data-toggle="modal" data-target="#user-form-modal">New User</button>
            </div> -->
            <!-- <hr class="my-3"> -->
            <div>
              <div class="form-group">
                <label>Tìm kiếm</label>
                <div><input class="form-control w-100" type="text" placeholder="Người dùng" value=""></div>
              </div>
            </div>
            
          </div>
        </div>
      </div>