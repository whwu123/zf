<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,jqgrid,datetimePicker,laydate,select2"></t:base>
</head>
<body class="gray-bg">
	<!-- 页面部分 -->
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="row">
					<div class="col-sm-12" id="searchGroupId">
					</div>
				</div>
				<div class="ibox">
					<div class="ibox-content">
						<div id="jqGrid_wrapper" class="jqGrid_wrapper"></div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 脚本部分 -->
	<t:datagrid actionUrl="draftr/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="统计管理" name="table_list_2" pageSize="20" sortName="createDate" sortOrder="desc">
		<t:dgCol name="id" label="序号" hidden="true" key="true" width="20"></t:dgCol>
		<t:dgCol name="title" label="标题"  query="true"></t:dgCol>
		<t:dgCol name="typeId" label="类型" replace="${draftTypeReplace }" width="50" query="true"></t:dgCol>
		<t:dgCol name="draftUrl" width="40" label="链接"  ></t:dgCol>
		<t:dgCol name="taskType" label="任务类型" replace="指派任务_2,自主报题_1" width="50" query="true"></t:dgCol>
		<t:dgCol name="createNickname" label="创建者"  width="60" query="true"></t:dgCol>
		<t:dgCol name="grade" label="评分"  width="30"></t:dgCol>
		<t:dgCol name="issueDate" label="发布时间"   width="60" datefmt="yyyy-MM-dd" query="true" queryModel="group" datePlugin="laydate"></t:dgCol>
		<t:dgCol name="opt" label="操作" width="50"></t:dgCol>
		<t:dgDelOpt label="删除" url="draftr/del?id={id}" operationCode="	sys:draft:del"/>
		<t:dgFunOpt label="查看链接" funName="showUrl(draftUrl)" icon="glyphicon glyphicon-saved"></t:dgFunOpt>
		<t:dgToolBar url="draftr/addorupdate" type="add" width="50%" height="70%" operationCode="sys:draft:add"></t:dgToolBar>
		<t:dgToolBar url="draftr/addorupdate" type="edit" width="50%" height="70%" operationCode="sys:draft:edit"></t:dgToolBar>
		<t:dgToolBar url="draftr/addorupdate" type="view" width="50%" height="70%" operationCode="sys:draft:view"></t:dgToolBar>
		<t:dgToolBar label="导出" icon="glyphicon glyphicon-resize-full" type="define"  funName="excel"></t:dgToolBar>
	</t:datagrid>
<script type="text/javascript">
		$(function(){
			laydate({elem:"#issueDate_begin",event:"focus",istime: true, format: 'YYYY-MM-DD'});
			laydate({elem:"#issueDate_end",event:"focus",istime: true, format: 'YYYY-MM-DD'});
		});
		function showUrl(url){
			window.open(url); 
		}
		function excel(){
			var createNickname = $("#createNickname").val();
			location.href = "excel/export2003?createNickname="+createNickname;
		}
		
</script>
</body>
</html>