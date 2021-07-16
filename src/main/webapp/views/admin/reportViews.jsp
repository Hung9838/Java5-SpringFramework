<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="col-md-8">
        <div class="e-panel card mb-3">
          <div class="card-body">
          	<jsp:include page="${categoryReport }"></jsp:include>
          </div>
        </div>
        <div class="e-panel card">
          <div class="card-body">
        	<jsp:include page="${productReport }"></jsp:include>
          </div>        
        </div>
      </div>
      <div class="col-md-4">
        <div class="card">
          <div class="card-body">
            <jsp:include page="${timeReport }"></jsp:include>
          </div>
            
        </div>
      </div>