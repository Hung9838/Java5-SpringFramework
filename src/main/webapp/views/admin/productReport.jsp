<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="card-title">
              <h6 class="mr-2"><span>Báo cáo: </span><small class="px-1">doanh thu theo sản phẩm</small></h6>
            </div>
              <div class="e-table">
                <div class="table-responsive table-lg mt-3">
                  <table class="table table-bordered">
                    <thead>
                      <tr>
                        <th class="text-nowrap align-middle">Sản phẩm</th>
                        <th class="text-nowrap align-middle">Số đơn hàng</th>
                        <th class="text-nowrap align-middle">Tổng doanh thu</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${pageProductRP.content }" var="item">
	                	<tr>
		                  <td class="text-nowrap align-middle">${item.sanPham.name }</td>
		                  <td class="text-nowrap align-middle">${item.soDonHang }</td>                
		                  <td class="text-nowrap align-middle">${item.doanhThu }</td>
		                </tr> 
	                </c:forEach>               
                    </tbody>
                  </table>
                </div>
                <div class="d-flex justify-content-center">
                  <ul class="pagination mt-3 mb-0">
                    <li class="page-item"><a href="/admin/report?ppr=0&pct=${pageCategoryRP.number}" class="page-link">Min</a></li>
	                <li class="page-item"><a href="/admin/report?ppr=${pageProductRP.number-1 }&pct=${pageCategoryRP.number}" class="page-link">‹</a></li>
	                <li class="page-item"><a href="/admin/report?ppr=${pageProductRP.number+1 }&pct=${pageCategoryRP.number}" class="page-link">›</a></li>
	                <li class="page-item"><a href="/admin/report?ppr=${pageProductRP.totalPages-1 }&pct=${pageCategoryRP.number}" class="page-link">Max</a></li>
                  </ul>
                </div>
              </div>