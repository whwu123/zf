<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,jqgrid"></t:base>
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
	<t:datagrid actionUrl="draftrType/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="类型管理" name="table_list_2" pageSize="20" sortName="sort" sortOrder="asc">
		<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
		<t:dgCol name="name" label="标题"  ></t:dgCol>
		<t:dgCol name="sort" label="排序"  ></t:dgCol>
		<t:dgCol name="state" label="状态" replace="正常_1,禁用_0" width="60"></t:dgCol>
		<t:dgCol name="opt" label="操作" width="100"></t:dgCol>
		<t:dgDelOpt label="删除" url="draftrType/del?id={id}" operationCode="sys:drafttype:del"/>
		<t:dgToolBar url="draftrType/addorupdate" type="add" width="40%" height="30%" operationCode="sys:drafttype:add"></t:dgToolBar>
		<t:dgToolBar url="draftrType/addorupdate" type="edit" width="40%" height="30%" operationCode="sys:drafttype:edit"></t:dgToolBar>
	</t:datagrid>
<script type="text/javascript">
		
</script>
</body>
</html>