<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <div class="card-title">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

              <h6 class="mr-2"><span>Báo cáo: </span><small class="px-1">Doanh thu theo doanh mục</small></h6>
            </div>
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th class="text-nowrap align-middle">Danh mục</th>
                  <th class="text-nowrap align-middle">Số đơn hàng</th>
                  <th class="text-nowrap align-middle">Tổng doanh thu</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${pageCategoryRP.content }" var="item">
                	<tr>
	                  <td class="text-nowrap align-middle">${item.danhMuc.name }</td>
	                  <td class="text-nowrap align-middle">${item.soDonHang }</td>                
	                  <td class="text-nowrap align-middle">${item.doanhThu }</td>
	                </tr> 
                </c:forEach>               
              </tbody>
            </table>
            <div class="d-flex justify-content-center">
              <ul class="pagination mt-3 mb-0">
                <li class="page-item"><a href="/admin/report?pct=0&ppr=${pageProductRP.number }" class="page-link">Min</a></li>
                <li class="page-item"><a href="/admin/report?pct=${pageCategoryRP.number-1 }&ppr=${pageProductRP.number }" class="page-link">‹</a></li>
                <li class="page-item"><a href="/admin/report?pct=${pageCategoryRP.number+1 }&ppr=${pageProductRP.number }" class="page-link">›</a></li>
                <li class="page-item"><a href="/admin/report?pct=${pageCategoryRP.totalPages-1 }&ppr=${pageProductRP.number }" class="page-link">Max</a></li>
              </ul>
            </div>