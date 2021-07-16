<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col mb-3">
        <div class="e-panel card mb-3">
          <div class="card-body">
            <form class="row g-3 needs-validation" action="/admin/category/crud/add" method="post" novalidate>
              <div class="col-md-7 mb-2">
                <label for="validationCustom01" class="form-label">Tên</label>
                <input type="text" name="name" value="${ctForm.name }" class="form-control" id="validationCustom01" required>
              </div>             
              <div class="col-md-4 mb-2">
                 <input class="form-check-input ml-2 mt-5" name="active" type="checkbox" id="flexCheckDefault" ${checked= ctForm.active == true? "checked" : ""}>
                  <label class="form-check-label " for="flexCheckDefault">
                   Trạng thái
                  </label>
              </div>
              <input value="${page.number }" name="page" hidden="true"/>
              <div class="col-12 mt-3">
                <button class="btn btn-primary" type="submit">Hoàn thành</button>
                <a class="btn btn-light ml-2" href="/admin/category/page?p=${page.number }">Làm mới</a>
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
                      <th class="text-nowrap align-middle"><a href="/admin/category/crud/sapXep?p=${page.number }">Tên</a></th>
                      <th class="text-nowrap align-middle">Trạng thái</th>
                      <th class="text-nowrap align-middle">Hoạt động</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${page.content }" var="item">
                    	<tr>                 
	                      <td class="text-nowrap align-middle">${item.name }</td>
	                      <td class="text-nowrap align-middle">${item.active }</td>
	                      <td class="text-center align-middle">
	                        <div class="btn-group align-top">
	                            <a class="btn btn-sm btn-outline-secondary badge" type="button" href="/admin/category/crud/edit?name=${item.name }&page=${page.number }">Sửa</a>
	                            <a class="btn btn-sm btn-outline-secondary badge" type="button" href="/admin/category/crud/delete?name=${item.name }&page=${page.number }"><i class="fa fa-trash"></i></a>
	                        </div>
	                      </td>
	                    </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
              <div class="d-flex justify-content-center">
                <ul class="pagination mt-3 mb-0">
                  <li class="page-item"><a href="/admin/category/page?p=0" class="page-link">Min</a></li>
                  <li class="page-item"><a href="/admin/category/page?p=${page.number-1 }" class="page-link">‹</a></li>
                  <li class="page-item"><a href="/admin/category/page?p=${page.number+1 }" class="page-link">›</a></li>
                  <li class="page-item"><a href="/admin/category/page?p=${page.totalPages-1 }" class="page-link">Max</a></li>

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
                <div><input class="form-control w-100" type="text" placeholder="Tên danh mục" value=""></div>
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