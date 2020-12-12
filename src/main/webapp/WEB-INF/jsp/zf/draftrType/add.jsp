<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,icheck"></t:base>
	
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<t:formvalid action="draftrType/save">
							<input type="hidden" name="id" id="id" value="${draftTypeEntity.id }">
							<input type="hidden" name="state" id="state" value="${draftTypeEntity.state }">
							<div class="form-group">
                                <label class="col-sm-3 control-label">类型名称：</label>
                                <div class="col-sm-8">
                                   <input id="name" name="name"  type="text" class="form-control" required="" value="${draftTypeEntity.name }" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">排序：</label>
                                <div class="col-sm-8">
                                   <input id="sort" name="sort"  type="number" class="form-control" required="" value="${draftTypeEntity.sort }" />
                                </div>
                            </div>
						</t:formvalid>
                    </div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>

