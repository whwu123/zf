<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,icheck,laydate,webuploader"></t:base>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<t:formvalid action="draftr/save">
							<input type="hidden" name="id" id="id" value="${draftEntity.id }">
							<input type="hidden" name="state" id="state" value="${draftEntity.state }">
							<div class="form-group">
                                <label class="col-sm-3 control-label">稿件标题：</label>
                                <div class="col-sm-8">
                                   <input id="title" name="title"  type="text" class="form-control" required="" value="${draftEntity.title }" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">稿件链接：</label>
                                <div class="col-sm-8">
                                   <input id="draftUrl" name="draftUrl"  type="text" class="form-control" required="" value="${draftEntity.draftUrl }" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">稿件类型：</label>
                                <div class="col-sm-8">
                                   <%-- <input id="typeId" name="typeId"  type="text" class="form-control" required="" value="${draftEntity.typeId }" /> --%>
                              
                              		<select name="typeId" class="form-control help-block m-b-none" aria-invalid="false" id="typeId">
	                                   <c:forEach items="${draftTypeEntities }" var="draftType">
	                                    	<option value="${draftType.id }"  <c:if test="${draftType.id==draftEntity.typeId}">selected="selected"</c:if> >${draftType.name }</option>
	                                   </c:forEach>
	                                   
                                    </select>
                              
                                </div>
                            </div>
                           
                            <div class="form-group">
                                <label class="col-sm-3 control-label">任务类型：</label>
                                <div class="col-sm-8">
                                   <%-- <input id="taskType" name="taskType"  type="text" class="form-control" required="" value="${draftEntity.taskType }" /> --%>
                               		<select name="taskType" class="form-control help-block m-b-none" aria-invalid="false" id="taskType">
	                                    <option value="1"  <c:if test="${draftEntity.taskType==1}">selected="selected"</c:if> >自主报题</option>
	                                    <option value="2"  <c:if test="${draftEntity.taskType==2}">selected="selected"</c:if> >指派任务</option>
                                    </select>
                               
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">发布时间：</label>
                                <div class="col-sm-8">
		                            <div class="input-group date">
		                               <!--  <span class="input-group-addon"><i class="fa fa-calendar"></i></span> -->
		                              <%--   <input id="issueDate" name="issueDate"  value="${draftEntity.issueDate }" type="text" class="laydate-icon form-control layer-date"> --%>
		                           <input id="issueDate" name=issueDate type="text" class="laydate-icon form-control layer-date" value='<fmt:formatDate value="${draftEntity.issueDate }" type="date" pattern="yyyy-MM-dd"/>'>
		                            </div>
                                </div>
                            </div>
                            
                            <div class="form-group" >
                                <label class="col-sm-3 control-label">评分：</label>
                                <div class="col-sm-8">
                                   <input id="grade" name="grade"  type="text" class="form-control"  value="${draftEntity.grade }" <c:if test="${gradeFlag }">disabled="disabled"</c:if> />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-8">
                                   <textarea rows="6" id="remark" name="remark"  class="form-control">${draftEntity.remark }</textarea>
                                </div>
                            </div>
						</t:formvalid>
                    </div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function() {
	   
	   laydate({elem:"#issueDate",event:"focus",istime: true, format: 'YYYY-MM-DD'});
	   
})
</script>
</html>

