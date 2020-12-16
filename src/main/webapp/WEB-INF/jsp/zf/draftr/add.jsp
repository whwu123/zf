<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,treeview,laydate,icheck,prettyfile,webuploader"></t:base>
<style type="text/css">
.file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}

.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}
</style>
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
							<input type="hidden" name="filePath" id="filePath"   value="${draftEntity.filePath }" />
							<div class="form-group">
                                <label class="col-sm-3 control-label">稿件标题*：</label>
                                <div class="col-sm-8">
                                   <input id="title" name="title"  type="text" class="form-control" required="" value="${draftEntity.title }" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">稿件链接：</label>
                                <div class="col-sm-8">
                                   <input id="draftUrl" name="draftUrl"  type="text" class="form-control" value="${draftEntity.draftUrl }" />
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
                            
                            <div class="form-group" <c:if test="${gradeFlag }">style="display: none"</c:if> >
                                <label class="col-sm-3 control-label">评分：</label>
                                <div class="col-sm-8">
                                 
                                	<select name="grade" class="form-control help-block m-b-none" aria-invalid="false" id="grade" >
                                		<option value=""  > 请选择 </option>
	                                    <option value="A"  <c:if test="${draftEntity.grade=='A'}">selected="selected"</c:if> >A</option>
	                                    <option value="B+"  <c:if test="${draftEntity.grade=='B+'}">selected="selected"</c:if> >B+</option>
	                                    <option value="B"  <c:if test="${draftEntity.grade=='B'}">selected="selected"</c:if> >B</option>
	                                    <option value="C+"  <c:if test="${draftEntity.grade=='C+'}">selected="selected"</c:if> >C+</option>
	                                    <option value="C"  <c:if test="${draftEntity.grade=='C'}">selected="selected"</c:if> >C</option>
	                                    <option value="D"  <c:if test="${draftEntity.grade=='D'}">selected="selected"</c:if> >D</option>
	                                    <option value="E"  <c:if test="${draftEntity.grade=='E'}">selected="selected"</c:if> >E</option>
	                                    <option value="E+"  <c:if test="${draftEntity.grade=='E+'}">selected="selected"</c:if> >E+</option>
                                    </select>
                                
                                
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-8">
                                   <textarea rows="6" id="remark" name="remark"  class="form-control">${draftEntity.remark }</textarea>
                                </div>
                            </div>
                             <div class="form-group" id="filePickerdiv">
                                <label class="col-sm-3 control-label m-b">附件:</label>
								<div class="col-sm-2" >
									<div id="filePicker">上传附件</div>
									
								</div>
								<div class="col-sm-4">
									<div id="fileList" class="uploader-list" style="margin-top: 10px;"></div>
								</div>
                            </div>
                            
                             <div class="form-group" id="filedowDiv" >
                                <label class="col-sm-3 control-label m-b">附件:</label>
								<div class="col-sm-8" >
									 <a href="javascript:void(0)" class="file" style="margin-top: 10px" onclick="popup()">下载附件 </a>
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
function popup(){
	var draftrId = $("#id").val();
	if(draftrId!= null && draftrId!=""){
		window.location.href= "fileDownloadController/download?draftrId="+draftrId;
	}
}
$(function() {
	
	//初始化Web Uploader
	var uploader = WebUploader.create({

		// 选完文件后，是否自动上传。
		auto : true,

		// swf文件路径
		swf : 'static/webuploader/Uploader.swf',

		// 文件接收服务端。
		server : 'fileUploaderZfController/fileupload',

		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : {
			id : '#filePicker',
		},

	});


	// 文件上传过程中创建进度条实时显示。
	uploader.on('uploadProgress', function(file, percentage) {
	});

	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on('uploadSuccess', function(file, data) {
		var filePath = data.filePath; 
		$("#fileList").html(file.name);
		$("#filePath").val(filePath);
		alert($("#filePath").val());
	});

	// 文件上传失败，显示上传出错。
	uploader.on('uploadError', function(file) {

	});

	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on('uploadComplete', function(file) {
		qhTipSuccess('上传完成....');
	});
	
	
	laydate({elem:"#issueDate",event:"focus",istime: true, format: 'YYYY-MM-DD'});
	
	var filePathValue = $("#filePath").val();
	if(filePathValue!=null && filePathValue!="" ){
		$("#filedowDiv").show();
		$("#filePickerdiv").hide();
	}else{
		$("#filedowDiv").hide();
		$("#filePickerdiv").show();
	}
})


</script>
</html>

