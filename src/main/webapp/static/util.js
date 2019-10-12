/**
 * 弹出层公共方法 统一样式
 * 1.alert提醒
 * 2.warnning警告
 * 3.info短信息提示
 * 4.load遮罩层
 * 5.doResponse 简单异步请求返回方法 统一restbody简单返回时候使用
 */
var layerx = {
		alert : function(msg) {
			return layer.alert(msg, {icon: 6});
		},
		warnning : function(msg) {
			return layer.alert(msg, {icon: 5});
		},
		info : function(msg) {
			return layer.msg(msg);
		},
		load : function() {
			return layer.load(2, {shade: [0.3]});
		},
		doResponse : function(restbody) {
			if(restbody.code == 1) {
				layerx.alert(restbody.msg);
			}else {
				layerx.warnning(restbody.msg);
			}
		}
	};

/**
 * 表单数据清空
 */
var formx = {
	reset : function(formName) {
		$("#"+formName).find('input[type=text],select,input[type=hidden]').each(function() {
			$(this).val('');
		});
	}	
};
/**
 * 打印公共方法
 * 1.getExcel 入参param为选择器或键值对数组 下载Excel文档
 * 2.getPdf 入参param为选择器或键值对数组 下载Pdf文档
 * 3.printReport 入参param为选择器或键值对数组 以图片形式打印文档
 */
var printer = {
	getExcel : function(reportid,param) {
		var baseurl=localUrl+"/public/report/getExcel";
		baseurl = baseurl + "?reportid=" + reportid;

		var params;
		if(typeof(param)=="string"){
			params=$(param).serializeArray();
		}else{
			params=param;
		}
		$.each(params, function() {
			baseurl = baseurl + "&" + this.name + "=" + encodeURI(this.value);
		});
		window.location.href=baseurl;
	},
	getPdf : function(reportid,param) {
		var baseurl=localUrl+"/public/report/getPdf";
		baseurl = baseurl + "?reportid=" + reportid;
		var params;
		if(typeof(param)=="string"){
			params=$(param).serializeArray();
		}else{
			params=param;
		}
		$.each(params, function() {
			baseurl = baseurl + "&" + this.name + "=" + encodeURI(this.value);
		});
		window.location.href=baseurl;
	},
	printReport : function(reportid,param) {
		var baseurl=localUrl+"/public/report/printReport";
		baseurl = baseurl + "?reportid=" + reportid;

		var params;
		if(typeof(param)=="string"){
			params=$(param).serializeArray();
		}else{
			params=param;
		}
		$.each(params, function() {
			baseurl = baseurl + "&" + this.name + "=" + encodeURI(this.value);
		});
		window.open(baseurl);
	}
}

/**
 * 查询浏览器版本
 */
var browser = {
		ieVersion : function() {
			var userAgent = navigator.userAgent;
			var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1;
			var isEdge = userAgent.indexOf("Edge") > -1 && !isIE;
			var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
			if (isIE) {
				var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
				reIE.test(userAgent);
				var fIEVersion = parseFloat(RegExp["$1"]);
				if (fIEVersion == 7) {
					return 7;
				} else if (fIEVersion == 8) {
					return 8;
				} else if (fIEVersion == 9) {
					return 9;
				} else if (fIEVersion == 10) {
					return 10;
				} else {
					// version <= 6
					return 6;
				}
			} else if (isEdge) {
				return 'edge';
			} else if (isIE11) {
				return 11;
			} else {
				// not ie
				return -1;
			}
		}
}

