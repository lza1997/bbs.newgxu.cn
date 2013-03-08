<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  
<!doctype html>
<html>
	<head>
		<jsp:include page="mobile-manifest.jsp" />
		<style type="text/css">
			.digest {
				text-indent: 2em;
			}
		</style>
	</head>
<body>
		<div data-role="page">
			<div data-role="header">
				<a href="#" data-icon="back">返回</a>
				<h1>首页 - 论坛</h1>
				<a href="#" data-icon="refresh">刷新</a>
			</div>
			<div data-role="content" data-theme="b">
					<c:choose>
						<c:when test="${not empty sessionScope._user}">
							<h2>欢迎你，${sessionScope._user.nick}！</h2>
						</c:when>
						<c:otherwise>
							<h2>欢迎你，游客！</h2>
						</c:otherwise>
					</c:choose>
				<hr />
				<button>精彩分享</button>
				<c:forEach items="${index.pubGoodTopics}" var="t">
					<div data-role="collapsible" data-collapsed="true">
						<h2>${t.title}</h2>
						<p><a href="/ng/m/user_info/view?id=${t.topic.user.id}">${t.topic.user.nick}</a></p>
						<p class="digest">${t.content}</p>
					</div>
				</c:forEach>
				<hr />
				<div data-role="collapsible-set">
					<button>分区板块</button>
					<c:forEach items="${index.areas}" var="a">
						<div data-role="collapsible" data-collapsed="true">
							<h2>${a.name}</h2>
							<ul data-role="listview" data-inset="true">
								<c:forEach items="${a.forums}" var="f">
									<li><a href="/ng/m/forum?forumId=${f.id}" data-transition="flip">${f.name}</a></li>
								</c:forEach>
							</ul>
						</div>
					</c:forEach>
				</div>
				<hr />
				<button>分区新帖</button>
				<ul data-role="listview" data-inset="true">
					<c:forEach items="${index.latestTopics}" var="t">
						<li><a href="#">${t.title}</a></li>
					</c:forEach>
				</ul>
				<hr />
				<button>推荐文章</button>
				<div></div>
				<ul data-role="listview" data-inset="true">
					<c:forEach items="${index.pubHotTopics}" var="t">
						<li><a href="#">${t.title}</a></li>			
					</c:forEach>
				</ul>
				<hr />
				<button>校园公告</button>
				<div></div>
				<ul data-role="listview" data-inset="true">
					<c:forEach items="${index.notices}" var="n">
						<li><a href="${n.url}" data-ajax="fasle">${n.title}${sessionScope.user.username}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div></div>
			<div data-role="footer">
				<div data-role="navbar" data-position="fixed">
			        <ul>
				        <li><a href="#home" data-icon="arrow-l" data-transition="fade">上一页</a></li>
				        <li><a href="#sessions" data-icon="arrow-r" data-transition="fade">下一页</a></li>
				        <li><a href="#sessions" data-icon="home" data-transition="fade">首页</a></li>
				        <c:if test="${not empty sessionScope._user}">
				        	<li><a href="/ng/m/topic/create" data-icon="event">发帖</a></li>
				    	</c:if>
				        <c:choose>
				        	<c:when test="${not empty sessionScope._user}">
				        		<li><a href="#sessions" data-icon="event" data-transition="fade">退出</a></li>
				        	</c:when>
				        	<c:otherwise>
				        		<li><a href="/ng/m/login" data-icon="grid" data-transition="fade">登陆</a></li>
				        	</c:otherwise>
				    	</c:choose>
			        </ul>
			    </div>
			</div>
		</div>
	</body>
</html>