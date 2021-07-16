<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			<form class="row g-3 needs-validation" method="post" action="/admin/TimeReport" novalidate>
              
              <div class="col-md-6 mb-3">
                <label for="validationCustom01" class="form-label">Ngày bắt đầu</label>
                <input type="date" class="form-control" name="dateFrom" id="validationCustom01"  required>
              </div>             
              <div class="col-md-6 mb-3 ">
                <label for="validationCustom01" class="form-label">Ngày kết thúc</label>
                <input type="date" class="form-control" name="dateEnd" id="validationCustom01" required>
              </div>
              <div class="col-md-12">
                <button class="btn btn-dark col-md-12" type="submit">Tra doanh thu</button>
              </div>
              <input value="${pageCategoryRP.number}" name="pct" hidden="true"/>
              <input value="${pageProductRP.number }" name="ppr" hidden="true"/>
                    
            </form>
            <div class="col-md-12 mt-4"> 
              <c:if test="${soDonHang != null }">
              	<table class="table table-bordered">
	                <thead>
	                  <tr>
	                    <th class="text-nowrap align-middle">Số đơn hàng</th>
	                    <th class="text-nowrap align-middle">Tổng doanh thu</th>
	                  </tr>
	                </thead>
	                <tbody>
	                  	  <tr>
		                    <td class="text-nowrap align-middle">${soDonHang }</td>
		                    <td class="text-nowrap align-middle">${doanhThu }</td>                
		                  </tr>                  
	                </tbody>
	              </table>
              </c:if>
            </div>