<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
<meta charset="utf-8">
<title></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">

<link rel="stylesheet" type="text/css" href="/css/style_pop.css" />

<script type="text/javascript" src="/js/menu.js"></script>

</head>

<body class="sub">
	<div id="container">

		<div class="popForm">

			<!-- 팝업 타이틀 -->
			<div class="popTitArea">
				<h1>거래처 단가 및 재고현황</h1>
			</div>
			<!-- //팝업 타이틀 -->

			<!-- 팝업 콘텐츠 -->
			<div class="popCont">

				<!-- 팝업 서브타이틀 -->
				<div class="pcTit">
					<h2>단가</h2>
					<div class="btn_area">
						<span class="pr10">발주일</span> <span><input type="date"
							id="w120"> - <input type="date" id="w120"></span> <span><a
							href="#" class="btn_search">검색</a></span>
					</div>
				</div>
				<!-- //팝업 서브타이틀 -->

				<!-- 테이블 콘텐츠 -->
				<div class="popList">

					<!-- 게시판 -->
					<div class="popBoard">
						<table>
							<caption></caption>
							<colgroup span="2">
								<col style="width: 17%;" />
								<col style="width:;" />
								<col style="width: 10%;" />
								<col style="width: 10%;" />
								<col style="width: 7%;" />
								<col style="width: 5%;" />
								<col style="width: 5%;" />
								<col style="width: 5%;" />
								<col style="width: 7%;" />
								<col style="width: 7%;" />
							</colgroup>
							<thead>
								<tr>
									<th>발주일</th>
									<th>Description</th>
									<th>Model/Size</th>
									<th>Maker</th>
									<th>단가</th>
								</tr>
							</thead>

							<tbody>
								<tr>
									<td>16-08-20</td>
									<td>Cylinder</td>
									<td>MXQ20-50BS-A93</td>
									<td>SMC</td>
									<td>135,000</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- //게시판 -->

				</div>
				<!-- //테이블 콘텐츠 -->

				<!-- 팝업 서브타이틀 -->
				<div class="pcTit mt20">
					<h2>재고</h2>
				</div>
				<!-- //팝업 서브타이틀 -->


				<!-- 테이블 콘텐츠 -->
				<div class="popList">

					<!-- 게시판 -->
					<div class="popBoard">
						<table>
							<caption></caption>
							<colgroup span="2">
								<col style="width: 20%;" />
								<col style="width:;" />
								<col style="width: 10%;" />
								<col style="width: 10%;" />
								<col style="width: 30%;" />
							</colgroup>
							<tr>
								<th>Description</th>
								<th>Model/Size</th>
								<th>Maker</th>
								<th>재고수량</th>
								<th>재고사유</th>
							</tr>
							<tr>
								<td>Cylinder</td>
								<td>MXQ20-50BS-A93</td>
								<td>SMC</td>
								<td>42</td>
								<td>&nbsp;</td>
							</tr>
						</table>
					</div>
					<!-- //게시판 -->

				</div>
				<!-- //테이블 콘텐츠 -->

			</div>
			<!-- //팝업 콘텐츠 -->



		</div>
	</div>
</body>
</html>
