
$(function(){
	$(".layui-table-box tr[data-index]").on("click", function(e){
		var trIndex = $(this).data("index");
		$(".layui-table-box tr.table-row-click").removeClass("table-row-click");
		$(".layui-table-box tr[data-index='"+trIndex+"']").addClass("table-row-click");
	});
	$(".layui-table-box tr[data-index]").hover(
		function(e){
			var trIndex = $(this).data("index");
			$(".layui-table-box tr[data-index='"+trIndex+"']").addClass("table-row-hover");
		},
		function(e){
			var trIndex = $(this).data("index");
			$(".layui-table-box tr.table-row-hover").removeClass("table-row-hover");
		}
	);
});
//多选框关闭
$(document).on('click',function(e){
	var e = e || window.event; //浏览器兼容性
	var elem = e.target || e.srcElement;
	while (elem) {
		if (elem.className && elem.className=='pick_box'  ) {
			return;
		}
		elem = elem.parentNode;
	}
	$(".check_box").hide();
});
//折叠
var Accordion = function(el, multiple) {
  EventDelegates.apply(this);
  var _this = this;
  this.el = el || {};
  this._events = {};
  this.multiple = multiple || false;

  var links = this.el.find('.link');
  links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
  var itemFiles = this.el.find('.submenu .item-file');
  itemFiles.on('click', function () {
    _this.emit('itemClick', this);
  });
}

Accordion.prototype.dropdown = function(e) {
  var $el = e.data.el;
    $this = $(this),
    $parent = $this.closest('ul') || $el,
    $next = $this.next();
  $next.slideToggle();
  $this.parent().toggleClass('open');

  if (!e.data.multiple) {
    $parent.find('.submenu').not($next).slideUp().parent().removeClass('open');
  };
}
//首页
$(function() {
	var MENUTREEDATA = [
		{
			label: '教务运行',
			type: 'folder',
			icon: 'iconfont iconjiaowuyunhang',
			level: 1,
			children: [
				{
					label: '开课安排管理',
					type: 'folder',
					children: [
						{label: '开课时间控制', type: 'file'},
						{label: '理论开课生成', type: 'file'},
						{label: '教学安排管理', type: 'file'},
						{label: '教学安排查询', type: 'file'},
					]
				},
				{
					label: '分级教学管理',
					type: 'folder',
					children: [
						{label: '分级教学管理-1', type: 'file'}
					]
				},
				{
					label: '体育开课管理',
					type: 'folder',
					children: [
						{label: '体育开课管理-1', type: 'file'}
					]
				}
			]
		},
		{
			label: '考务管理',
			type: 'folder',
			level: 1,
			icon: 'iconfont iconkaowuguanli',
			children: []
		}
	];

	function renderTree(data) {
		var $ul = $('<ul class="submenu"></ul>');
		Array.isArray(data) && data.forEach(function(vo) {
			var $li = $('<li></li>');
			if(vo.type === 'folder') {
				var iconClz = vo.icon || '';
				$div = $('<div class="link"><i class="'+iconClz+'"></i><span>'+vo.label+'</span></div>');
				$li.append($div);
				!(vo.level && vo.level === 1) && $div.append($('<i class="iconfont iconsanjiao-down"></i>'));
			}else {
				$li.addClass('item-file');
				$li.append($('<a><b class="dot"></b>'+ vo.label +'</a>'))
			}
			if(vo.children && vo.children.length) {
				$li.append(renderTree(vo.children));
			}
			$ul.append($li);
		});
		return $ul;
	}

	// console.log(renderTree(MENUTREEDATA)[0]);

	var accordion = new Accordion($('#accordion'), false);
	
	accordion.on('itemClick', function(obj) {
		this.el.find('a.active').removeClass('active');
		var $li = $(obj);
		var $aDom = $li.find('a');
		$aDom.addClass('active');
		$aDom.parents('.submenu.fold-submenu').removeAttr('style');
	});

	$('.testClose').click(function() {
	  $('.edu-dialog-model').hide();
	});
	
	let scroll = 0
	$("#scrollLeft").click(function(e){
		scroll -= 150;
		$('.breadcrumbs').scrollLeft(scroll);
	});
	$("#scrollRight").click(function(){
		scroll = $('.breadcrumbs').scrollLeft() + 150;
		$('.breadcrumbs').scrollLeft(scroll);
	});
	$('.breadcrumbs ul li').click(function(){
		$('.breadcrumbs').scrollLeft($(this).offset().left - $(this).width()*3);
	});
	$("#fileInput").change(function(e){
		$(this).parents().find('.file-name').text(e.currentTarget.files[0].name);
	})
	$('select').change(function(){
		$(this).css('color','#272727');
		$(this).css('font-weight','400');
	});
});
// 侧边栏
$(function(){
    $('.lsm-mini-btn i').on('click',function(){
		if ($('.lsm-mini-btn input[type="checkbox"]').prop("checked")) {
			$('.edu-sideMenu').css('overflow-x','hidden')
			$('.edu-sideMenu').css('overflow-y','auto')
			$('.accordion>li>.link>i').stop().animate({fontSize : 20,left:16},50);
			$('.lsm-expand-btn').stop().animate({left:60},50);
			$('.edu-aside').stop().animate({width : 60},200);
			$('.edu-logo>div').stop().animate({backgroundPositionX : 0,height:25},50);
		}else{
			$('.edu-sideMenu').css('overflow','auto')
			$('.accordion>li>.link>i').stop().animate({fontSize : 14,left:32},50);
			$('.lsm-expand-btn').stop().animate({left:208},50);
			$('.edu-aside').stop().animate({width: 208},200);
			$('.edu-logo>div').stop().animate({height:35},50);

		}
	});
	$('.lsm-mini-btn input[type="checkbox"]').bind('input propertychange', function() {
		$('.accordion>li>ul.submenu').hide()
		$('.accordion>li>ul.submenu').toggleClass('fold-submenu',200)
		$('ul.accordion>li').each(function(e){
			if($(this).hasClass('open')){
				$(this).children('.submenu.fold-submenu').css('top',$(this).offset().top + 'px')
			}
		})
	});
	$('ul.accordion>li>.link').bind('click',function() {
		if (!$('.lsm-mini-btn input[type="checkbox"]').prop("checked")){
			$('ul.accordion>li').each(function(e){
				if($(this).hasClass('open')){
					$(this).children('.submenu.fold-submenu').css('top',$(this).offset().top + 'px')
				}
			})
		}else {

		}
	})
});
