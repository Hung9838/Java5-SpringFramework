<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div class="col mb-3">
        <div class="e-panel card mb-3">
          <div class="card-body">
            <form class="row g-3 needs-validation" action="/admin/product/crud/add" method="post" novalidate>
              <div class="col-md-4 mb-2">
                <label for="validationCustom01" class="form-label">Id</label>
                <input type="text" class="form-control" value="${ptForm.id }" disabled="disabled" id="validationCustom01" required>
              </div>
              <div class="col-md-4 mb-2">
                <label for="validationCustom01" class="form-label">Tên</label>
                <input type="text" class="form-control" value="${ptForm.name }" name="name" id="validationCustom01" required>
              </div>
              <div class="col-md-4 mb-2">
                <label for="validationCustom02" class="form-label">Mô tả</label>
                <input type="text" class="form-control" value="${ptForm.describe }" name="describe" id="validationCustom02" required>
              </div>              
              <div class="col-md-4 mb-2">
                <label for="validationCustom02" class="form-label">Giảm giá</label>
                <input type="text" class="form-control" name="promotions" value="${ptForm.promotions }" id="validationCustom02" placeholder="Nhập phần trăm muốn giảm" required>
              </div>
              <div class="col-md-4 mt-1">
                <label for="validationCustom02" class="form-label" >Danh mục</label>
                <select class="form-control" name="categoryName" aria-label="Default select example">
                  <c:forEach items="${listSelectCT }" var="item">
                  	<option ${selected = ptForm.category.name == item.name? "selected" : ""} value="${item.name }">${item.name }</option>
                  </c:forEach>
                </select>
              </div>
              <div class="col-md-4 mt-1">
                <label for="validationCustom02" class="form-label">Giá</label>
                <input type="text"  class="form-control" value="${ptForm.price }" name="price" id="validationCustom02" required>
              </div>
              <input value="${pagePT.number }" name="page" hidden="true"/>
              <input value="${ptForm.id }" name="id" hidden="true"/>
              <div class="col-md-4 mt-1">
              	<label class="form-check-label" for="flexCheckDefault">
                   Hoạt động
                 </label>
                <input class="form-check-input mt-2 ml-4" name="availabe" type="checkbox" ${checked= ptForm.avaliabe == true? "checked" : ""} id="flexCheckDefault">
                 <br />
                 <br />
              </div>
              <div class="col-12 mt-1">
                <button class="btn btn-primary" type="submit">Hoàn thành</button>
                <a class="btn btn-light ml-2" href="/admin/product/page?p=${pagePT.number }">Làm mới</a>
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
                      <th class="text-nowrap align-middle"><a href="/admin/product/crud/sapXepTen?p=${pagePT.number }">Tên</a></th>
                      <th class="text-nowrap align-middle">Ảnh</th>
                      <th class="text-nowrap align-middle">Mô tả</th>
                      <th class="text-nowrap align-middle">Tạo ngày</th>
                      <th class="text-nowrap align-middle">Hoạt động</th>
                      <th class="text-nowrap align-middle"><a href="/admin/product/crud/sapXepGiamGia?p=${pagePT.number }">Giảm giá</a></th>
                      <th class="text-nowrap align-middle">Danh mục</th>
                      <th class="text-nowrap align-middle">Giá</th>
                      <th class="text-nowrap align-middle">Hoạt động</th>
                    </tr>
                  </thead>
                  <tbody>
                                     
                      <c:forEach items="${pagePT.content }" var="item">
                      	<tr>
                      		  <td class="text-nowrap align-middle">${item.id }</td>
	                      	  <td class="text-nowrap align-middle">${item.name }</td>
		                      <td><a class="btn btn-sm btn-outline-secondary badge" type="button">Chi tiết</a></td>
		                      <td class="text-nowrap align-middle">${item.describe }</td>
		                      <td class="text-nowrap align-middle">${item.createDate }</td>
		                      <td class="text-nowrap align-middle">${item.avaliabe }</td>
		                      <td class="text-nowrap align-middle">${item.promotions }%</td>
		                      <td class="text-nowrap align-middle">${item.category.name }</td>
		                      <td class="text-nowrap align-middle">${item.price }</td>
		                      <td class="text-center align-middle">
		                        <div class="btn-group align-top">
		                            <a class="btn btn-sm btn-outline-secondary badge" type="button" href="/admin/product/crud/edit?id=${item.id }&page=${pagePT.number }">Sửa</a>
		                            <a class="btn btn-sm btn-outline-secondary badge" type="button" href="/admin/product/crud/delete?id=${item.id }&page=${pagePT.number }"><i class="fa fa-trash"></i></a>
		                        </div>
	                      	</td>
                      	</tr>
                      </c:forEach>
                    
                    
                  </tbody>
                </table>
              </div>
              <div class="d-flex justify-content-center">
                <ul class="pagination mt-3 mb-0">
                  <li class="page-item"><a href="/admin/product/page?p=0" class="page-link">Min</a></li>
                  <li class="page-item"><a href="/admin/product/page?p=${pagePT.number-1 }" class="page-link">‹</a></li>
                  <li class="page-item"><a href="/admin/product/page?p=${pagePT.number+1 }" class="page-link">›</a></li>
                  <li class="page-item"><a href="/admin/product/page?p=${pagePT.totalPages-1 }" class="page-link">Max</a></li>

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
                <div><input class="form-control w-100" type="text" placeholder="Tên sản phẩm" value=""></div>
              </div>
            </div>
            <hr class="my-3">
            <div class="">
              <label>Trạng thái:</label>
              <div class="px-2">
                <div class="custom-control custom-radio">
                  <input type="radio" class="custom-control-input" name="user-status" id="users-status-disabled">
                  <label class="custom-control-label" for="users-status-disabled">Đang hoạt động</label>
                </div>
              </div>
              <div class="px-2">
                <div class="custom-control custom-radio">
                  <input type="radio" class="custom-control-input" name="user-status" id="users-status-active">
                  <label class="custom-control-label" for="users-status-active">Dừng hoạt động</label>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>